package com.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.resources.connect;

import com.resources.*;

public class admin {
	
	connect DbObj = new connect();
	Connection con = DbObj.connectMethod();
	
	
   

	public String insertAdmin(String id,String name, String gender, String address, String password,String phone, String email) {
		String output = "";

		try {
			Connection con = DbObj.connectMethod();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement   
			String query = " insert into admin (`Admin_ID`,`Admin_Name`,`Admin_Gender`,`Admin_Address`,`Admin_Password`,`Admin_Phone`,`Admin_Email`)"+" values (?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values 
			preparedStmt.setInt(1, 0);   
			preparedStmt.setString(2, name);   
			preparedStmt.setString(3, gender);    
			preparedStmt.setString(4, address);
			preparedStmt.setString(5,  password);
			preparedStmt.setInt(6, Integer.parseInt(phone));
			preparedStmt.setString(7, email);
			
			

			//execute the statement   
			preparedStmt.execute();   
			con.close(); 

			output = "Inserted successfully";
		}
		catch (Exception e) {   
			output = "Error while inserting the Admin Details.";   
			System.err.println(e.getMessage());  
		} 

		 return output; 
	}
	
	public String readAdmin() {  
		String output = "";  


		try {  
			Connection con = DbObj.connectMethod();
			if (con == null)  {   
				return "Error while connecting to the database for reading.";  
			} 

		// Prepare the html table to be displayed   
			output = "<table border=\"1\"><tr>"
					+ "<th>Admin ID</th>" 
					+ "<th>Admin Name</th>" 
					+ "<th>Admin Gender</th>"
					+ "<th>Admin Address</th>"
					+ "<th>Admin Password</th>"
					+ "<th>Admin Phone</th>"
					+ "<th>Admin Email</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th>"
					+ "</tr>";

		  String query = "select * from admin";   
		  Statement stmt = con.createStatement();   
		  ResultSet rs = stmt.executeQuery(query); 

		  // iterate through the rows in the result set   
		  while (rs.next())   {    
			  String Admin_ID = Integer.toString(rs.getInt("Admin_ID"));    
			  String Admin_Name = rs.getString("Admin_Name");    
			  String Admin_Gender= rs.getString("Admin_Gender"); 
			  String Admin_Address= rs.getString("Admin_Address");
			  String Admin_Password = rs.getString("Admin_Password");    
			  String Admin_Phone = Integer.toString(rs.getInt("Admin_Phone"));
			  String Admin_Email = rs.getString("Admin_Email");
			 

		   // Add into the html table  
			
		  output += "<tr><td><input id='hidAdminIDUpdate' name='hidAdminIDUpdate' type='hidden' value='" + Admin_ID + "'></td>";  
          output += "<tr><td>" + Admin_ID + "</td>";    
		  output += "<td>" + Admin_Name+ "</td>";
		  output += "<td>" + Admin_Gender + "</td>";    
		  output += "<td>" + Admin_Address + "</td>"; 
		  output += "<td>" + Admin_Password + "</td>";    
		  output += "<td>" + Admin_Phone + "</td>";
		  output += "<td>" +Admin_Email + "</td>"; 
		  

//		   // buttons    

		  output += "<td><input name=\"btnUpdate\" "     + " "
			  		+ "type=\"button\" value=\"Update\" class='btnUpdate btn btn-secondary'></td>"     + ""
			  				+ "<td><form method=\"post\" action=\"admin.jsp\">"     + ""
			  						+ "<input name=\"btnRemove\" "     + " "
			  								+ "type=\"submit\" value=\"Remove\" class='btnRemove btn btn-danger'>"     + ""
			  										+ "<input name=\"hidAdminIDDelete\" type=\"hidden\" "     + " "
			  												+ "value=\"" + 
			  												Admin_ID + "\">" + "</form></td></tr>";   
		  } 
		  con.close(); 

		  // Complete the html table   
		  output += "</table>"; 
		}
		catch (Exception e) {  
			output = "Error while reading the Admin data.";  
			System.err.println(e.getMessage()); 
		}

		return output;
	}	
					

	
	public String updateAdmins(String admin_id, String name, String gender, String address, String password,String phone, String email)  {   
		String output = ""; 
	 
	  try   {   
		  Connection con = DbObj.connectMethod();
	 
		  if (con == null)    {
			  return "Error while connecting to the database for updating."; 
		  } 
	 
	   // create a prepared statement    
	   String query = "UPDATE admin SET Admin_Name=?,Admin_Gender=?,Admin_Address=?,Admin_Password=?,Admin_Phone=?,Admin_Email=?    "
	   		+ "			WHERE Admin_ID=?"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
	    preparedStmt.setInt(1, 0);   
	    preparedStmt.setString(2, name);   
		preparedStmt.setString(3, gender);    
		preparedStmt.setString(4, address);
		preparedStmt.setString(5,  password);
		preparedStmt.setInt(6, Integer.parseInt(phone));
		preparedStmt.setString(7, email);

	 
	   // execute the statement    
	   preparedStmt.execute();    
	   con.close(); 
	 
	   output = "Updated successfully";   
	   }   catch (Exception e)   {    
		   output = "Error while updating the admin details.";    
		   System.err.println(e.getMessage());   
	   } 
	 
	  return output;  
	  }
	
	
	public String deleteAdmin(String Admin_ID) {  
		String output = ""; 
	 
	 try  {   
		 Connection con = DbObj.connectMethod();
	 
	  if (con == null)   {    
		  return "Error while connecting to the database for deleting.";   
	  } 
	 
	  // create a prepared statement   
	  String query = "delete from admin where Admin_ID=?"; 
	 
	  PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 // 
	  preparedStmt.setInt(1, Integer.parseInt(Admin_ID));       
	     
	  preparedStmt.execute();   
	  con.close(); 
	 
	  output = "Deleted successfully";  
	  }  catch (Exception e)  {   
		  output = "Sorry , Error while deleting the admin details.";   
		  System.err.println(e.getMessage());  
		  
	 } 
	 
	 return output; 
	 }
}
