<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.java.admin" %>
<%@page import="com.servlet.AdminAPI" %>
    
    
<%	
	if (request.getParameter("Admin_ID") != null) {
		admin obj = new admin();
		String
		stsMsg = "";
		
            
		
		//Insert--------------------------

     	if (request.getParameter("hidAdminIDSave") == "") {
			stsMsg = obj.insertAdmin(
					request.getParameter("Admin_ID"),
					request.getParameter("Admin_Name"), 
					request.getParameter("Admin_Gender"),
					request.getParameter("Admin_Address"),
					request.getParameter("Admin_Password"), 
					request.getParameter("Admin_Phone"),
					request.getParameter("Admin_Email")
				
					
					);
		} else//Update----------------------
		{
			
			stsMsg = obj.updateAdmins(request.getParameter("hidAdminIDSave"),
					request.getParameter("Admin_Name"), 
					request.getParameter("Admin_Gender"),
					request.getParameter("Admin_Address"),
					request.getParameter("Admin_Password"), 
					request.getParameter("Admin_Phone"),
					request.getParameter("Admin_Email")
					);
		}
		session.setAttribute("statusMsg", stsMsg);
	}
	//Delete--------------------------------
	if (request.getParameter("hidPaymentIDelete") != null) {
		admin obj = new admin();
		String
		stsMsg = obj.deleteAdmin(request
				.getParameter("hidAdminIDelete"));
		session.setAttribute("statusMsg", stsMsg);
	}
%>	

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script type="Components/jquery-3.2.1.js"></script>
<script src="Components/Admin.js"></script>
</head>
<body>

<div class="container">
	<div class="row">
		<div class="col-8">
			<h1 class="m-3">Admin Details</h1>
	   <form id="formHospital" name="formHospital" method="post" action="admin.jsp">
	   
		Admin ID: 
		<input id="Admin_ID" name="Admin_ID" type="text" class="form-control form-control-sm" required> <br>
		
	    Admin Name: 
		<input id="Admin_Name" name="Admin_Name" type="text" class="form-control form-control-sm" required> <br> 
		
        Admin Gender:
 		 <input id="Admin_Gender" name="Admin_Gender" type="text"  class="form-control form-control-sm" required> <br> 
		
		Admin Address: 
		<input id="Admin_Address" name="Admin_Address" type="text"   class="form-control form-control-sm" required> <br>
	   
	    Admin Password: 
		<input id="Admin_Password" name="Admin_ID" type="text" class="form-control form-control-sm" required> <br>
		
		Admin Phone: 
		<input id="Admin_Phone" name="Admin_Phone" type="text" class="form-control form-control-sm" required> <br>
		
		Admin Email: 
		<input id="Admin_Email" name="Admin_Email" type="text" class="form-control form-control-sm" required> <br>
		
			
		<input id="btnSave" name="btnSave" type="submit" value="Save" 	class="btn btn-primary"  > 
		
		<input type="hidden" id="hidAdminIDSave" name="hidAdminIDSave" value="">	
		
	</form>

	<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			
			<br>
			<div id="divAdminGrid">


	                 <%
	                    admin obj = new admin();
	                    out.print(obj.readAdmin());
	                  %>
		   </div>
			
		</div>
	</div>
</div>

		
</body>
</html>