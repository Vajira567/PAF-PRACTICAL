package com.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.java.admin;

@Path("/admin")
public class adminService {
	
    admin adminObj = new admin();
	
	@GET  
	@Path("/")  
	@Produces(MediaType.TEXT_HTML)  
	public String readHospitals()  {   
		return adminObj.readAdmin();
	}

	@POST 
	@Path("/admin") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertAdmin(@FormParam("Admin_ID") String Admin_ID,
			                @FormParam("Admin_Name") String Admin_Name,
							@FormParam("Admin_Gender") String Admin_Gender,    
							@FormParam("Admin_Address") String Admin_Address,       
							@FormParam("Admin_Password") String Admin_Password,
							@FormParam("Admin_Phone") String Admin_Phone,       
							@FormParam("Admin_Email") String Admin_Email )
							
	                        
	{  
		String output = adminObj.insertAdmin(Admin_ID,Admin_Name, Admin_Gender, Admin_Address, Admin_Password, Admin_Phone,Admin_Email);  
		return output; 
	}
	
	

	@PUT 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateAdmins(String AdminData) { 
		//Convert the input string to a JSON object  
		JsonObject adminObject = new JsonParser().parse(AdminData).getAsJsonObject(); 
		 
		 //Read the values from the JSON object  
		String Admin_ID = adminObject.get("(Admin_ID").getAsString();
		String Admin_Name = adminObject.get("Admin_Name").getAsString();  
		String Admin_Gender = adminObject.get("Admin_Gender").getAsString();  
		String Admin_Address = adminObject.get("Admin_Address").getAsString();  
		String Admin_Password = adminObject.get("Admin_Password").getAsString();  
		String Admin_Phone = adminObject.get("Admin_Phone").getAsString();
		String Admin_Email = adminObject.get("Admin_Email").getAsString();  
		
		 
		String output = adminObj.updateAdmins(Admin_ID, Admin_Name, Admin_Gender, Admin_Address, Admin_Password, Admin_Phone, Admin_Email); 
		 
		return output; 
	}

	@DELETE 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteAdmin(String AdminData) {  
		//Convert the input string to an XML document  
		Document admin = Jsoup.parse(AdminData, "", Parser.xmlParser());     
		
		//Read the value from the element <AdminID>  
		 String Admin_ID = admin.select("Admin_ID").text(); 
		 
		 String output = adminObj.deleteAdmin(Admin_ID);
		 
		 return output; 
		 }
}
