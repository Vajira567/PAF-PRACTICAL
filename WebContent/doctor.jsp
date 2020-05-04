<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.java.doctor" %>
<%@page import="com.servlet.DoctorAPI" %>
<%	
	if (request.getParameter("Hospital_Name") != null) {
		doctor obj = new doctor();
		String
		stsMsg = "";
		
            
		
		//Insert--------------------------

     	if (request.getParameter("hidDoctor_IDSave") == "") {
			stsMsg = obj.insertDoctors(
					request.getParameter("DoctorName"),
					request.getParameter("NIC"), 
					request.getParameter("DepartmentName"),
					request.getParameter("Address"),
					request.getParameter("MobileNo"), 
					request.getParameter("Email"),
					request.getParameter("Specialization"),
					request.getParameter("HospitalName")
					
					);
		} else//Update----------------------
		{
			
			stsMsg = obj.updateDoctors(request.getParameter("hidDoctor_IDSave"),
					request.getParameter("DoctorName"),
					request.getParameter("NIC"), 
					request.getParameter("DepartmentName"),
					request.getParameter("Address"),
					request.getParameter("MobileNo"), 
					request.getParameter("Email"),
					request.getParameter("Specialization"),
					request.getParameter("HospitalName")
					);
		}
		session.setAttribute("statusMsg", stsMsg);
	}
	//Delete--------------------------------
	if (request.getParameter("hidDoctorIDelete") != null) {
		doctor  obj = new doctor();
		String
		stsMsg = obj.deleteDoctors(request
				.getParameter("hidDoctorIDelete"));
		session.setAttribute("statusMsg", stsMsg);
	}
%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctors</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script type="Components/jquery-3.2.1.js"></script>
<script src="Components/Doctor.js"></script>
</head>
<body>

<div class="container">
	<div class="row">
		<div class="col-8">
			<h1 class="m-3">Doctor Details</h1>
			<form id="formDoctor" name="formDoctor">
				Doctor Name:
				<input id="doctor_name" name="doctor_name" type="text" class="form-control form-control-sm" required>
				
				<br> NIC:
				<input id="NIC" name="NIC" type="text" class="form-control form-control-sm" required>
				
				<br> DepartmentName:
				<input id="DeName" name="DeName" type="text" class="form-control form-control-sm" required>
				
				<br> Address:
				<input id="address" name="address" type="text" class="form-control form-control-sm" required>
				
				<br> Phone Number:
				<input id="MobileNo" name="MobileNo" type="text" class="form-control form-control-sm" required>
				
				<br> Email:
				<input id="Email" name="Email" type="text" class="form-control form-control-sm" required>
				
				<br> Specialization:
				<input id="Specialization" name="Specialization" type="text" class="form-control form-control-sm" required>
				
				<br> HospitalName:
				<input id="HospitalName" name="HospitalName" type="text" class="form-control form-control-sm" required>
				
				<br>
				<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary" >
				
				<input type="hidden" id="hidDoctor_IDSave" name="hidDoctor_IDSave" value="">
				
				
			</form>
			
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			
			<br>
			<div id="divDoctorGrid">
				<%
					doctor obj = new doctor();
					out.print(obj.readDoctors());
				%>
			</div>
			
		</div>
	</div>
</div>

</body>
</html>