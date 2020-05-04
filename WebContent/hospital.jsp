<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.java.hospital" %>
    
    
<%	
	if (request.getParameter("Hospital_Name") != null) {
		hospital Hospital = new hospital();
		String
		stsMsg = "";
		
            
		
		//Insert--------------------------

     	if (request.getParameter("hidHospitalIDSave") == "") {
			stsMsg = Hospital.insertHospital(request.getParameter("Hospital_ID"),
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
<title>Hospital_Insert</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="./WEB-INF/Hospital.js"></script>


</head>
<body>
	<form id="formHospital" name="formHospital" method="post" action="hospital.jsp">
		Hospital Name: 
		<input id="Hospital_Name" name="Hospital_Name" type="text"
			class="form-control form-control-sm" required> <br>
	    Contact No:
	     <input id="Hospital_Phone" name="Hospital_Phone" type="text" placeholder="0xxxxxxxxx" maxlength="10"
			 pattern="^\d{10}$" class="form-control form-control-sm"required > <br> 
		Address: 
		<input id="Hospital_Address" name="Hospital_Address" type="text"
			class="form-control form-control-sm" required> <br> 
		E-mail:
		 <input id="Hospital_Email" name="Hospital_Email" type="text"  
			class="form-control form-control-sm" required> <br> 
		
		Hospital_Description: 
		<input id="Hospital_Description" name="Hospital_Description" type="text"   
			class="form-control form-control-sm" required> <br>
	   
		Open_Hours:
		<input id="Open_Hours" name="Open_Hours" type="text"
			class="form-control form-control-sm" required> <br> 
			
		<input id="btnSave" name="btnSave" type="submit" value="Save" 
			class="btn btn-primary"  > 
		<input type="hidden" id="hidHospitalIDSave" name="hidHospitalIDSave" value="">	
		
	</form>

	<div id"alertSuccess" class="alert alert-success">
		<%
			out.print(session.getAttribute("statusMsg"));
		%>

	</div>

	<%
	 hospital h1= new hospital();
	out.print(h1.readHospitals());
	%>
		<script language="javascript">
		
</body>
</html>