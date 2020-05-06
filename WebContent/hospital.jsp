<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.java.hospital" %>
<%@page import="com.servlet.hospitalAPI" %>
    
    
<%	
	if (request.getParameter("Hospital_ID") != null) {
		hospital Hospital = new hospital();
		String
		stsMsg = "";
		
            
		
		//Insert--------------------------

     	if (request.getParameter("hidHospitalIDSave") == "") {
			stsMsg = Hospital.insertHospital(
					request.getParameter("Hospital_ID"),
					request.getParameter("Hospital_Name"),
					request.getParameter("Hospital_Address"), 
					request.getParameter("Hospital_City"),
					request.getParameter("Hospital_Phone"),
					request.getParameter("Hospital_Email"), 
					request.getParameter("Hospital_Description"),
					request.getParameter("Open_Hours")
					
					);
		} else//Update----------------------
		{
			
			stsMsg = Hospital.updateHospitals(request.getParameter("hidHospitalIDSave"),
					request.getParameter("Hospital_Name"), 
					request.getParameter("Hospital_Address"), 
					request.getParameter("Hospital_City"),
					request.getParameter("Hospital_Phone"),
					request.getParameter("Hospital_Email"), 
					request.getParameter("Hospital_Description"),
					request.getParameter("Open_Hours")
					);
		}
		session.setAttribute("statusMsg", stsMsg);
	}
	//Delete--------------------------------
	if (request.getParameter("hidHospitalIDelete") != null) {
		hospital
		Hospital = new hospital();
		String
		stsMsg = Hospital.deleteHospitals(request
				.getParameter("hidHospitalIDelete"));
		session.setAttribute("statusMsg", stsMsg);
	}
%>	

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script type="Components/jquery-3.2.1.js"></script>
<script src="Components/Hospital.js"></script>
</head>
<body>

<div class="container">
	<div class="row">
		<div class="col-8">
			<h1 class="m-3">Hospital Details</h1>
	   <form id="formHospital" name="formHospital" method="post" action="hospital.jsp">
	   
		Hospital Name: 
		<input id="Hospital_Name" name="Hospital_Name" type="text" class="form-control form-control-sm" required> <br>
		
	    Contact No:
	     <input id="Hospital_Phone" name="Hospital_Phone" type="text" placeholder="0xxxxxxxxx" maxlength="10" pattern="^\d{10}$" class="form-control form-control-sm"required > <br>
	      
		Address: 
		<input id="Hospital_Address" name="Hospital_Address" type="text" class="form-control form-control-sm" required> <br> 
		
		E-mail:
		 <input id="Hospital_Email" name="Hospital_Email" type="text"  class="form-control form-control-sm" required> <br> 
		
		Hospital_Description: 
		<input id="Hospital_Description" name="Hospital_Description" type="text"   class="form-control form-control-sm" required> <br>
	   
		Open_Hours:
		<input id="Open_Hours" name="Open_Hours" type="text" class="form-control form-control-sm" required> <br> 
			
		<input id="btnSave" name="btnSave" type="submit" value="Save" 	class="btn btn-primary"  > 
		
		<input type="hidden" id="hidHospitalIDSave" name="hidHospitalIDSave" value="">	
		
	</form>

	<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			
			<br>
			<div id="divHospitalGrid">


	                 <%
	                    hospital h1= new hospital();
	                    out.print(h1.readHospitals());
	                  %>
		   </div>
			
		</div>
	</div>
</div>

		
</body>
</html>