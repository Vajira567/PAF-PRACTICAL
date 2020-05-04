package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.java.doctor;


/**
 * Servlet implementation class DoctorAPI
 */
@WebServlet("/DoctorAPI")
public class DoctorAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	doctor obj = new doctor();
    
    public DoctorAPI() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String output = obj.insertDoctors(
					request.getParameter("DoctorName"),
					request.getParameter("NIC"),
					request.getParameter("DepartmentName"),
					request.getParameter("Address"),
					request.getParameter("MobileNo"),
					request.getParameter("Email"),
					request.getParameter("Specialization"),
					request.getParameter("HospitalName"));
			
			response.getWriter().write(output);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	protected void doPut(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		Map paras = getParasMap(request);
		
		try {
			String output = obj.updateDoctors(
					paras.get("hidDoctor_IDSave").toString(),
					paras.get("DoctorName").toString(),
					paras.get("NIC").toString(),
					paras.get("DepartmentName").toString(),
					paras.get("Address").toString(),
					paras.get("MobileNo").toString(),
					paras.get("Email").toString(),
					paras.get("Specialization").toString(),
					paras.get("HospitalName").toString());
			
			response.getWriter().write(output);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doDelete(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		Map paras = getParasMap(request);
		
		String output = obj.deleteDoctors(paras.get("Doctor_ID").toString());
		
		response.getWriter().write(output);
	}
	
	private static Map getParasMap(HttpServletRequest request) {
		Map <String, String> map = new HashMap<String, String>();
		
		try {
			Scanner scanner = new Scanner(request.getInputStream(),"UTF-8");
			
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			
			for(String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		}catch(Exception e) {
			
		}
		return map;
	}

}
