package com.resources;
import java.sql.*; 

public class connect {
	public Connection connectMethod() {
		
		Connection con = null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver"); 
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hcdb", "root", "root");
			//For testing
			System.out.print("Successfully connected");
			
		}catch(Exception e){
			System.out.println(e);
		}
		return con;
	}

}
