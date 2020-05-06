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
import com.java.admin;


/**
 * Servlet implementation class AdminAPI
 */
@WebServlet("/AdminAPI")
public class AdminAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	admin obj = new admin();
    
    public AdminAPI() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String output = obj.insertAdmin(
					request.getParameter("Admin_ID"),
					request.getParameter("Admin_Name"), 
					request.getParameter("Admin_Gender"),
					request.getParameter("Admin_Address"),
					request.getParameter("Admin_Password"), 
					request.getParameter("Admin_Phone"),
					request.getParameter("Admin_Email")
				);
			
			response.getWriter().write(output);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	protected void doPut(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		Map paras = getParasMap(request);
		
		try {
			String output = obj.updateAdmins(paras.get("hidAdminIDSave").toString(),
					paras.get("Admin_Name").toString(),
					paras.get("Admin_Gender").toString(),
					paras.get("Admin_Address").toString(),
					paras.get("Admin_Password").toString(),
					paras.get("Admin_Phone").toString(),
					paras.get("Admin_Email").toString());
			
			response.getWriter().write(output);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doDelete(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		Map paras = getParasMap(request);
		
		String output = obj.deleteAdmin(paras.get("Admin_ID").toString());
		
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
