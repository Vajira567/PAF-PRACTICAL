$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
			$("#alertSuccess").hide();
	}
	$("#alertError").hide();
}); 

//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	 
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validatePaymentForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("hidPaymentIDSave").val() == "") ? "POST" : "PUT";

	
	event.preventDefault();
	$.ajax(
			{
			 url : "PaymentAPI",
			 type : type,
			 data : $("#formPayment").serialize(),
			 dataType : "text",
			 complete : function(response, status)
			 {
			 onPaymentSaveComplete(response.responseText, status);
			 }
	});
	
	
}); 

function onPaymentSaveComplete(response, status)
{
	if (status == "success")
	{
			var resultSet = JSON.parse(response);
			if (resultSet.status.trim() == "success")
			{
				$("#alertSuccess").text("Successfully saved.");
				$("#alertSuccess").show();
				
				$("#divPaymentGrid").html(resultSet.data);
			} else if (resultSet.status.trim() == "error")
			{
				$("#alertError").text(resultSet.data);
				$("#alertError").show();
			}
	} else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidPaymentIDSave").val("");
	$("#formPayment")[0].reset();
}


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidPaymentIDSave").val($(this).closest("tr").find('#hidPaymentIDUpdate').val());
	$("#paymentID").val($(this).closest("tr").find('td:eq(0)').text());
	$("#paymentType").val($(this).closest("tr").find('td:eq(1)').text());
	$("#paymentAmount").val($(this).closest("tr").find('td:eq(2)').text());
	$("#appointmentID").val($(this).closest("tr").find('td:eq(3)').text());
		
	
});

$(document).on("click", ".btnRemove", function(event)
		{
	event.preventDefault();
	 
		 $.ajax(
		 {
			 url : 'PaymentAPI',
			 type : 'DELETE',
			 data : "paymentID=" + $(this).data("paymentID"),
			 dataType : 'text',
			 complete : function(response, status)
			 {onDoctorDeleteComplete(response.responseText, status);}
		 });
		});

function onPaymentDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			
			$("#divDoctorGrid").html(resultSet.data);
		}else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	}else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}


function validatePaymentForm()
{
	 if ($("#paymentID").val().trim() == "") {
	        return "Insert Payment ID .";
	    }
	    
	    if ($("#paymentType").val().trim() == "") {
	        return "Insert Payment Type.";
	    }
	    

	    if ($("#paymentAmount").val().trim() == "") {
	        return "Insert Payment Amount .";
	    }

	    if ($("#appointmentID").val().trim() == "") {
	        return "Insert Appointment ID.";
	    }
	    
	    
	    	    
	
	return true;
	

}





















