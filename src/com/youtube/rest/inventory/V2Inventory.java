package com.youtube.rest.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.QueryParam;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.dao.Oracle308tube;
import com.youtube.dao.Schema308Tube;
import com.youtube.util.ToJSON;

@Path("/v2/inventory")
public class V2Inventory {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrandParts(
			@QueryParam("brand") String brand)
			throws Exception {
		
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try{
			if (brand == null) {
				return Response.status(400).entity("Error. Pplease specify brand for this search").build();
			}
			
			Schema308Tube dao = new Schema308Tube();
			
			json = dao.queryReturnBrandParts(brand);
			returnString = json.toString();			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		return Response.ok(returnString).build();
	}


/*		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response returnErrorOnBrand()throws Exception {
			
			return Response.status(500).entity("Server was not able to process your request").build();
		}*/
	
	@Path("/{brand}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrand(
			@PathParam("brand") String brand)
			throws Exception {
		
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try{
			
			Schema308Tube dao = new Schema308Tube();
			
			json = dao.queryReturnBrandParts(brand);
			returnString = json.toString();			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		return Response.ok(returnString).build();
	}

	/*First path parameter defines the scope, the second one refines it producing a smaller list.*/
	@Path("/{brand}/{itemNumber}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnSpecificBrandItem(
			@PathParam("brand") String brand,
			@PathParam("itemNumber") int itemNumber)
			throws Exception {
		
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try{
			
			Schema308Tube dao = new Schema308Tube();
			
			json = dao.queryReturnBrandItemNumber(brand, itemNumber);
			returnString = json.toString();			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		return Response.ok(returnString).build();
	}

}
