package com.youtube.dao;

import javax.naming.*;
import javax.sql.*;

import java.sql.Connection;

public class Oracle308tube {
	private static DataSource Oracle308tube = null;
	private static Context context = null;
	
	public static DataSource Oracle308tubeConn() throws Exception{
		
		if (Oracle308tube != null){
			return Oracle308tube ;
		}
		
		try {
			if (context == null)
			{
				context = new InitialContext();
			}
			
			Oracle308tube = (DataSource) context.lookup("mysql56");
			
		}
		catch (Exception e){
			e.printStackTrace();
			
		}
		
		return Oracle308tube;
	}
	
	protected static Connection oraclePcPartsConnection(){
		Connection conn = null;
		try{
			conn = Oracle308tubeConn().getConnection();
			return conn;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}	

}
