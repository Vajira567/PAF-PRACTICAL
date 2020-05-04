package com.service;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

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

import com.resources.connect;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.java.*;

@Path("/doctors")
public class doctorService {
	doctor doctorObj = new doctor();
	
	@GET  
	@Path("/")  
	@Produces(MediaType.TEXT_HTML)  
	public String readHospitals()  {   
		return doctorObj.readDoctors();
	}

	@POST 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertDoctors(
			                @FormParam("DoctorName") String DoctorName,
							@FormParam("NIC") String NIC,    
							@FormParam("DepartmentName") String DepartmentName,       
							@FormParam("Address") String Address,
							@FormParam("MobileNo") String MobileNo,       
							@FormParam("Email") String Email,
							@FormParam("Specialization") String Specialization,
							@FormParam("HospitalName") String HospitalName)
	                        
	{  
		String output = doctorObj.insertDoctors( DoctorName, NIC, DepartmentName, Address, MobileNo, Email, Specialization, HospitalName);  
		return output; 
	}
	
	

	@PUT 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateDoctors(String DoctorsData) { 
		//Convert the input string to a JSON object  
		JsonObject doctorObject = new JsonParser().parse(DoctorsData).getAsJsonObject(); 
		 
		 //Read the values from the JSON object  
		String DoctorID = doctorObject.get("DoctorID").getAsString();  
		String DoctorName = doctorObject.get("DoctorName").getAsString();  
		String NIC = doctorObject.get("NIC").getAsString();  
		String DepartmentName = doctorObject.get("DepartmentName").getAsString();  
		String Address = doctorObject.get("Address").getAsString();
		String MobileNo = doctorObject.get("MobileNo").getAsString();  
		String Email = doctorObject.get("Email").getAsString();  
		String Specialization = doctorObject.get("Specialization").getAsString(); 
		String HospitalName = doctorObject.get("HospitalName").getAsString(); 
		 
		String output = doctorObj.updateDoctors(DoctorID,DoctorName, NIC, DepartmentName, Address, MobileNo, Email, Specialization, HospitalName); 
		 
		return output; 
	}

	@DELETE 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteDoctors(String DoctorsData) {  
		//Convert the input string to an XML document  
		Document doc = Jsoup.parse(DoctorsData, "", Parser.xmlParser());     
		
		//Read the value from the element <DoctorID>  
		 String   DoctorID = doc.select("DoctorID").text(); 
		 
		 String output = doctorObj.deleteDoctors(DoctorID); 
		 
		 return output; 
		 }

}
