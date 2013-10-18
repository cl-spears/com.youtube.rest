package com.youtube.util;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import java.sql.ResultSet;

import org.owasp.esapi.ESAPI;

/**
 * This utility will convert a database data into JSON format.
 * 
 * @author 308tube
 */
public class ToJSON {
	
	/**
	 * This will convert database records into a JSON Array.
	 * Pass in a ResultSet from a database connection and it
	 * will loop through and return a JSON array.
	 * 
	 * @param rs - database ResultSet
	 * @return - JSON array
	 * @throws Exception
	 * 
	 */
	public JSONArray toJSONArray(ResultSet rs) throws Exception {
		
		JSONArray json = new JSONArray(); // JSON array to be returned
		String temp = null;
		
		try{
			//need the column names, to save the meta-data e.g. column name
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			
			//determine number of columns
			int numColumns = rsmd.getColumnCount();
			/*Debug*/ System.out.println("ToJson: numColumns: " + numColumns);
			
			//loop through the rows of the  ResultSet
			while(rs.next()) {
				

				//convert each row in result set to JSON object.
				JSONObject obj = new JSONObject();

				// loop through columns of result set
				for(int i = 1; i <= numColumns; i++){
				
					String columnName = rsmd.getColumnName(i);
					
					if(rsmd.getColumnType(i) == java.sql.Types.ARRAY){
						obj.put(columnName, rs.getArray(columnName));
						/*Debug*/ System.out.println("ToJson: ARRAY");												
					}
					else if(rsmd.getColumnType(i) == java.sql.Types.BIGINT){
						obj.put(columnName, rs.getInt(columnName));
						/*Debug*/ System.out.println("ToJson: BIGINT");
					}
					else if(rsmd.getColumnType(i) == java.sql.Types.BIT){
						obj.put(columnName, rs.getBigDecimal(columnName));
						/*Debug*/ System.out.println("ToJson: BIT");
					}
					else if(rsmd.getColumnType(i) == java.sql.Types.BOOLEAN){
						obj.put(columnName, rs.getBoolean(columnName));
						/*Debug*/ System.out.println("ToJson: BOOLEAN");
					}
					else if(rsmd.getColumnType(i) == java.sql.Types.BLOB){
						obj.put(columnName, rs.getBlob(columnName));
						/*Debug*/ System.out.println("ToJson: BLOB");
					}
					else if(rsmd.getColumnType(i) == java.sql.Types.CHAR){
						obj.put(columnName, rs.getString(columnName));
						/*Debug*/ System.out.println("ToJson: CHAR");
					}
					else if(rsmd.getColumnType(i) == java.sql.Types.DATE){
						obj.put(columnName, rs.getDate(columnName));
						/*Debug*/ System.out.println("ToJson: DATE");
					}
					else if(rsmd.getColumnType(i) == java.sql.Types.DECIMAL){
						obj.put(columnName, rs.getBigDecimal(columnName));
						/*Debug*/ System.out.println("ToJson: DECIMAL");
					}
					else if(rsmd.getColumnType(i) == java.sql.Types.DOUBLE){
						obj.put(columnName, rs.getDouble(columnName));
						/*Debug*/ System.out.println("ToJson: DOUBLE");
					}
					else if(rsmd.getColumnType(i) == java.sql.Types.FLOAT){
						obj.put(columnName, rs.getFloat(columnName));
						/*Debug*/ System.out.println("ToJson: FLOAT");
					}
					else if(rsmd.getColumnType(i) == java.sql.Types.INTEGER){
						obj.put(columnName, rs.getInt(columnName));
						/*Debug*/ System.out.println("ToJson: INTEGER ," + columnName);
					}
					else if(rsmd.getColumnType(i) == java.sql.Types.NUMERIC){
						obj.put(columnName, rs.getBigDecimal(columnName));
						/*Debug*/ System.out.println("ToJson: NUMERIC");
					}
					else if(rsmd.getColumnType(i) == java.sql.Types.NVARCHAR){
						obj.put(columnName, rs.getString(columnName));
						/*Debug*/ System.out.println("ToJson: NVARCHAR");
					}
					else if(rsmd.getColumnType(i) == java.sql.Types.SMALLINT){
						obj.put(columnName, rs.getInt(columnName));
						/*Debug*/ System.out.println("ToJson: SMALLINT");
					}
					else if(rsmd.getColumnType(i) == java.sql.Types.TIMESTAMP){
						obj.put(columnName, rs.getTimestamp(columnName));
						/*Debug*/ System.out.println("ToJson: TIMESTAMP");
					}
					else if(rsmd.getColumnType(i) == java.sql.Types.TINYINT){
						obj.put(columnName, rs.getInt(columnName));
						/*Debug*/ System.out.println("ToJson: TINYINT");
					}
					else if(rsmd.getColumnType(i) == java.sql.Types.VARCHAR){
						temp = rs.getString(columnName);
						temp = ESAPI.encoder().canonicalize(temp);
						temp = ESAPI.encoder().encodeForHTML(temp);
						obj.put(columnName,  temp);
						/*obj.put(columnName, rs.getString(columnName));*/
						/*Debug*/ System.out.println("ToJson: VARCHAR ," + columnName);
					}
					else {
						obj.put(columnName, rs.getObject(columnName));
						/*Debug*/ System.out.println("ToJson: Object " + columnName + " , TYPE index: " + rsmd.getColumnType(i));
					}
				}	//end for
				
				json.put(obj);
			} // end while
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		finally {
			
		}
		return json;
	}

}
