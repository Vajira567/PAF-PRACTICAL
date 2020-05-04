package com.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.hospital;
import com.service.hospitalService;
import java.util.Scanner;

/**
 * Servlet implementation class hospitalAPI
 */
@WebServlet("/hospitalAPI")
public class hospitalAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	hospital hospitalObj = new hospital();
       
   
    
    public hospitalAPI() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = hospitalObj.insertHospital(
				request.getParameter("Hospital_Name"),
				request.getParameter("Hospital_Address"), 
				request.getParameter("Hospital_City"),
				request.getParameter("Hospital_Phone"),
				request.getParameter("Hospital_Email"), 
				request.getParameter("Hospital_Description"),
				request.getParameter("Open_Hours")
				
				);
				
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	private static Map getParasMap(HttpServletRequest request) {
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			Scanner scanner = new Scanner(request.getInputStream(),"UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			
			String[] params = queryString.split("&");
			for(String param : params) {
				String[] p = param.split("=");
				map.put(p[0],p[1]);
			}
		}catch(Exception e) {
			
		}
		return map;
	}
	

	protected void doPut(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		Map paras = getParasMap(request);
		String output = hospitalObj.updateHospitals(paras.get("hidHospitalIDSave").toString(),
				paras.get("Hospital_Name").toString(),
				paras.get("Hospital_Address").toString(),
				paras.get("Hospital_City").toString(),
				paras.get("Hospital_Phone").toString(),
				paras.get("Hospital_Email").toString(),
				paras.get("Hospital_Description").toString(),
				paras.get("Open_Hours").toString()
				);
		response.getWriter().write(output);
	}
	
	
	
	
	
	protected void doDelete(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		Map paras = getParasMap(request);
		String output = hospitalObj.deleteHospitals(paras.get("hidHospitalIDelete").toString());
		
		response.getWriter().write(output);
	}

}
