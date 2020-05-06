package com.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



import com.resources.connect;

public class hospital {
	
		connect DbObj = new connect();
		
		//Read Hospitals Details
		public String readHospitals() {  
			String output = "";  


			try {  
				Connection con = DbObj.connectMethod();
				if (con == null)  {   
					return "Error while connecting to the database for reading.";  
				} 

			// Prepare the html table to be displayed   
				output = "<table border=\"1\"><tr>"
						+ "<th>Hospital_Name</th>"
						+ "<th>Hospital_Address</th>"
						+ "<th>Hospital_City</th>"
						+ "<th>Hospital_Phone</th>"
						+ "<th>Hospital_Email</th>"
						+ "<th>Hospital_Description</th>"
						+ "<th>Open_Hours</th>"
						+ "<th>UPDATE</th>"
						+ "<th>REMOVE</th>"	
						+ "</tr>";
			  String query = "select * from hospitals";   
			  Statement stmt = con.createStatement();   
			  ResultSet rs = stmt.executeQuery(query); 

			  // iterate through the rows in the result set   
			  while (rs.next())   {    
				  String Hospital_ID = Integer.toString(rs.getInt("Hospital_ID"));    
				  String Hospital_Name = rs.getString("Hospital_Name");    
				  String Hospital_Address = rs.getString("Hospital_Address");    
				  String Hospital_City = rs.getString("Hospital_City");    
				  String Hospital_Phone = rs.getString("Hospital_Phone"); 
				  String Hospital_Email = rs.getString("Hospital_Email");
				  String Hospital_Description = rs.getString("Hospital_Description");
				  String Open_Hours = Integer.toString(rs.getInt("Open_Hours"));  

			   // Add into the html table    
				
				  
			  output += "<tr><td><input id='hidHospitalIDUpdate' name='hidHospitalIDUpdate' type='hidden' value='" + Hospital_ID + "'></td>";
			  output += "<tr><td>" + Hospital_Name + "</td>";       
			  output += "<td>" + Hospital_Address + "</td>";
			  output += "<td>" + Hospital_City + "</td>";    
			  output += "<td>" + Hospital_Phone + "</td>"; 
			  output += "<td>" + Hospital_Email + "</td>";    
			  output += "<td>" + Hospital_Description + "</td>";
			  output += "<td>" + Open_Hours + "</td>"; 

//			   // buttons   
			  output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-warning btnUpdate\"></td>"
						+ "<td><form method=\"post\" action=\"hospital.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"hidHospitalIDDelete\" type=\"hidden\" value=\"" + Hospital_ID + "\">"
						+ "</form></td></tr>";

     		/*  output += "<td><input name=\"btnUpdate\" "     + " "
			  		+ "type=\"button\" value=\"Update\"></td>"     + ""
			  				+ "<td><form method=\"post\" action=\"hospital.jsp\">"     + ""
			  						+ "<input name=\"btnRemove\" "     + " "
			  								+ "type=\"submit\" value=\"Remove\">"     + ""
			  										+ "<input name=\"Hospital_ID\" type=\"hidden\" "     + " "
			  												+ "value=\"" + 
			  										Hospital_ID + "\">" + "</form></td></tr>";   */
			  } 

			  con.close(); 

			  // Complete the html table   
			  output += "</table>"; 
			}
			catch (Exception e) {  
				output = "Error while reading the Hospital data.";  
				System.err.println(e.getMessage()); 
			}

			return output;
		}	
		public String insertHospital(String hos_id,String name, String address, String city, String phone, String email, String desc, String hours) {
			String output = "";

			try {
				Connection con = DbObj.connectMethod();

				if (con == null) {
					return "Error while connecting to the database";
				}

				// create a prepared statement   
				String query = " insert into hospitals ('Hospital_ID',`Hospital_Name`,`Hospital_Address`,`Hospital_City`,`Hospital_Phone`,`Hospital_Email`,`Hospital_Description`,`Open_Hours`)"+" values (?, ?, ?, ?, ?, ?, ?, ?)";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values 
				preparedStmt.setInt(1, 0);   
				preparedStmt.setString(2, name);   
				preparedStmt.setString(3, address);    
				preparedStmt.setString(4, city);
				preparedStmt.setString(5, phone);
				preparedStmt.setString(6, email);
				preparedStmt.setString(7, desc);
				preparedStmt.setInt(8, Integer.parseInt(hours));  

				//execute the statement   
				preparedStmt.execute();   
				con.close(); 

				output = "Inserted successfully";
			}
			catch (Exception e) {   
				output = "Error while inserting the Hospitals.";   
				System.err.println(e.getMessage());  
			} 

			 return output; 
		}
		
		public String updateHospitals(String hos_id, String name, String address, String city, String phone, String email, String desc, String hours)  {   
			String output = ""; 
		 
		  try   {   
			  Connection con = DbObj.connectMethod();
		 
			  if (con == null)    {
				  return "Error while connecting to the database for updating."; 
			  } 
		 
		   // create a prepared statement    
		   String query = "UPDATE hospitals SET Hospital_Name=?,Hospital_Address=?,Hospital_City=?,Hospital_Phone=?,Hospital_Email=?,Hospital_Description=?,Open_Hours=?      "
		   		+ "			WHERE Hospital_ID=?"; 
		 
		   PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		   // binding values    
		   preparedStmt.setInt(1, 0); 
		   preparedStmt.setString(1, name);    
		   preparedStmt.setString(2, address);    
		   preparedStmt.setString(3, city);
		   preparedStmt.setString(4, phone);
		   preparedStmt.setString(5, email);
		   preparedStmt.setString(6, desc);
		   preparedStmt.setInt(7, Integer.parseInt(hours));
		   preparedStmt.setInt(8,Integer.parseInt(hos_id));
		 
		   // execute the statement    
		   preparedStmt.execute();    
		   con.close(); 
		 
		   output = "Updated successfully";   
		   }   catch (Exception e)   {    
			   output = "Error while updating the Hospitals.";    
			   System.err.println(e.getMessage());   
		   } 
		 
		  return output;  
		  }
		
		public String deleteHospitals(String hos_id) {  
			String output = ""; 
		 
		 try  {   
			 Connection con = DbObj.connectMethod();
		 
		  if (con == null)   {    
			  return "Error while connecting to the database for deleting.";   
		  } 
		 
		  // create a prepared statement   
		  String query = "delete from hospitals where Hospital_ID=?"; 
		 
		  PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		  
		  preparedStmt.setInt(1, Integer.parseInt(hos_id));       
		     
		  preparedStmt.execute();   
		  con.close(); 
		 
		  output = "Deleted successfully";  
		  }  catch (Exception e)  {   
			  output = "Sorry , Error while deleting the hospital.";   
			  System.err.println(e.getMessage());  
			  
		 } 
		 
		 return output; 
		 }
	}


