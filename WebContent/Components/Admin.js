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
	var status = validateAdminForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("hidAdminIDSave").val() == "") ? "POST" : "PUT";

	
	event.preventDefault();
	$.ajax(
			{
			 url : "AdminAPI",
			 type : type,
			 data : $("#formAdmin").serialize(),
			 dataType : "text",
			 complete : function(response, status)
			 {
			 onAdminSaveComplete(response.responseText, status);
			 }
	});
	
	
}); 

function onAdminSaveComplete(response, status)
{
	if (status == "success")
	{
			var resultSet = JSON.parse(response);
			if (resultSet.status.trim() == "success")
			{
				$("#alertSuccess").text("Successfully saved.");
				$("#alertSuccess").show();
				
				$("#divAdminGrid").html(resultSet.data);
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
	$("#hidAdminIDSave").val("");
	$("#formPayment")[0].reset();
}


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidAdminIDSave").val($(this).closest("tr").find('#hidAdminIDUpdate').val());
	$("#Admin_Name").val($(this).closest("tr").find('td:eq(0)').text());
    $("#Admin_Gender").val($(this).closest("tr").find('td:eq(1)').text());
    $("#Admin_Address").val($(this).closest("tr").find('td:eq(2)').text());
    $("#Admin_Password").val($(this).closest("tr").find('td:eq(3)').text());
    $("#Admin_Phone").val($(this).closest("tr").find('td:eq(4)').text());
    $("#Admin_Email").val($(this).closest("tr").find('td:eq(5)').text());
	
});

$(document).on("click", ".btnRemove", function(event)
		{
	event.preventDefault();
	 
		 $.ajax(
		 {
			 url : 'AdminAPI',
			 type : 'DELETE',
			 data : "Admin_ID=" + $(this).data("Admin_ID"),
			 dataType : 'text',
			 complete : function(response, status)
			 {onDoctorDeleteComplete(response.responseText, status);}
		 });
		});

function onAdminDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			
			$("#divAdminGrid").html(resultSet.data);
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


function validateAdminForm()
{
	if ($("#Admin_Name").val().trim() == "") {
        return "Insert Admin Name.";
    }
    
    if ($("#Admin_Gender").val().trim() == "") {
        return "Insert Admin Gender.";
    }
    

    if ($("#Admin_Address").val().trim() == "") {
        return "Insert Admin Address.";
    }

    if ($("#Admin_Password").val().trim() == "") {
        return "Insert Admin Password.";
    }
    
    if ($("#Admin_Phone").val().trim() == "") {
        return "Insert Admin Phone.";
    }
    
    if ($("#Admin_Email").val().trim() == "") {
        return "Insert Admin Email.";
    }
    
	    
	    	    
	
	return true;
	

}





















