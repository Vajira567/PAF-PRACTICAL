<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.java.payment" %>
<%@page import="com.servlet.PaymentAPI" %>
    
    
<%	
	if (request.getParameter("paymentID") != null) {
		payment pay = new payment();
		String
		stsMsg = "";
		
            
		
		//Insert--------------------------

     	if (request.getParameter("hidPaymentIDSave") == "") {
			stsMsg = pay.insertPayment(
					request.getParameter("paymentID"),
					request.getParameter("paymentType"), 
					request.getParameter("paymentAmount"),
					request.getParameter("appointmentID")
				
					
					);
		} else//Update----------------------
		{
			
			stsMsg = pay.updatePayment(request.getParameter("hidPaymentIDSave"),
				    request.getParameter("paymentType"), 
					request.getParameter("paymentAmount"),
					request.getParameter("appointmentID")
					);
		}
		session.setAttribute("statusMsg", stsMsg);
	}
	//Delete--------------------------------
	if (request.getParameter("hidPaymentIDelete") != null) {
		payment pay = new payment();
		String
		stsMsg = pay.deletePayment(request
				.getParameter("hidPaymentIDelete"));
		session.setAttribute("statusMsg", stsMsg);
	}
%>	

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script type="Components/jquery-3.2.1.js"></script>
<script src="Components/Payment.js"></script>
</head>
<body>

<div class="container">
	<div class="row">
		<div class="col-8">
			<h1 class="m-3">Payment Details</h1>
	   <form id="formHospital" name="formHospital" method="post" action="payment.jsp">
	   
		Payment ID: 
		<input id="paymentID" name="paymentID" type="text" class="form-control form-control-sm" required> <br>
		
	    
		Payment Type: 
		<input id="paymentType" name="paymentType" type="text" class="form-control form-control-sm" required> <br> 
		
        Payment Amount:
 		 <input id="paymentAmount" name="paymentAmount" type="text"  class="form-control form-control-sm" required> <br> 
		
		Appointment ID: 
		<input id="appointmentID" name="appointmentID" type="text"   class="form-control form-control-sm" required> <br>
	   
		
			
		<input id="btnSave" name="btnSave" type="submit" value="Save" 	class="btn btn-primary"  > 
		
		<input type="hidden" id="hidPaymentIDSave" name="hidPaymentIDSave" value="">	
		
	</form>

	<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			
			<br>
			<div id="divPaymentGrid">


	                 <%
	                    payment pay= new payment();
	                    out.print(pay.readPayment());
	                  %>
		   </div>
			
		</div>
	</div>
</div>

		
</body>
</html>