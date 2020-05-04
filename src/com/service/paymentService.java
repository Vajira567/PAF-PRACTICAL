package com.service;

import java.awt.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.java.*;
import com.google.gson.*;

@Path("/payment")
public class paymentService {

	payment paymentObj = new payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readHospitals() {
		return paymentObj.readPayment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("p_id")  int paymentID,@FormParam("p_type") String paymentType,@FormParam("p_amount") Double paymentAmount,@FormParam("appointment_id") int appointmentID ){
		
		String output = paymentObj.insertPayment(paymentID, paymentType, paymentAmount, appointmentID);
				
				return output;
		
	}
	

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String PaymentData) {
		// Convert the input string to a JSON object
		JsonObject paymentObject = new JsonParser().parse(PaymentData).getAsJsonObject();

		// Read the values from the JSON object
		String paymentID = paymentObject.get("(paymentID").getAsString();
		String paymentType = paymentObject.get("paymentType").getAsString();
		String paymentAmount = paymentObject.get("paymentAmount").getAsString();
		String appointmentID = paymentObject.get("appointmentID").getAsString();

		String output = paymentObj.updatePayment(paymentID, paymentType, paymentAmount, appointmentID);

		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String PaymentData) {
		// Convert the input string to an XML document
		Document payment = Jsoup.parse(PaymentData, "", Parser.xmlParser());

		// Read the value from the element <PaymentID>
		String paymentID = payment.select("paymentID").text();

		String output = paymentObj.deletePayment(paymentID);

		return output;
	}
}
