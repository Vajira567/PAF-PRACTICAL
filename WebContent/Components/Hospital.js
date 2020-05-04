$(document).ready(function() {

    $("#alertSuccess").hide();
    $("#alertError").hide();

});

// Save
$(document).on("click", "#btnSave", function(event) {

    // Clear alerts
    $("#alertSuccess").text("");
    $("#alertSuccess").hide();
    $("#alertError").text("");
    $("#alertError").hide();

    // Form validation
    var status = validateHospitalForm();
    if (status != true) {
        $("#alertError").text(status);
        $("#alertError").show();
        return;
    }

    // If valid
    var type = ($("#hidHospitalIDSave").val() == "") ? "POST" : "PUT";

    $.ajax(
        {
            url : "hospitalAPI",
            type : type,
            data : $("#formHospital").serialize(),
            dataType : "text",
            complete : function(response, status)
            {
                onUserSaveComplete(response.responseText, status);
            }
        });

});

function onUserSaveComplete(response, status) {

    if (status == "success") {

        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() == "success") {

            $("#alertSuccess").text("Successfully saved.");
            $("#alertSuccess").show();
            $("#divUsersGrid").html(resultSet.data);

        } else if (resultSet.status.trim() == "error") {

            $("#alertError").text(resultSet.data);
            $("#alertError").show();

        }
    } else if (status == "error") {

        $("#alertError").text("Error while saving.");
        $("#alertError").show();

    } else {

        $("#alertError").text("Unknown error while saving..");
        $("#alertError").show();

    }

    $("#hidHospitalIDSave").val("");
    $("#formUser")[0].reset();

}
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {
    $("#hidHospitalIDSave").val($(this).closest("tr").find('#hidHospitalIDUpdate').val());
    $("#Hospital_Name").val($(this).closest("tr").find('td:eq(0)').text());
    $("#Hospital_Address").val($(this).closest("tr").find('td:eq(1)').text());
    $("#Hospital_City").val($(this).closest("tr").find('td:eq(2)').text());
    $("#Hospital_Phone").val($(this).closest("tr").find('td:eq(3)').text());
    $("#Hospital_Email").val($(this).closest("tr").find('td:eq(4)').text());
    $("#Hospital_Description").val($(this).closest("tr").find('td:eq(5)').text());
    $("#Open_Hours").val($(this).closest("tr").find('td:eq(6)').text());
});

//Remove
$(document).on("click", ".btnRemove", function(event)
{
    $.ajax(
        {
            url : "hospitalAPI",
            type : "DELETE",
            data : "DoctorID=" + $(this).data("DoctorID"),
            dataType : "text",
            complete : function(response, status)
            {
                onUserDeleteComplete(response.responseText, status);
            }
        });
});

function onHospitalDeleteComplete(response, status) {

    if (status == "success") {

        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() == "success") {

            $("#alertSuccess").text("Successfully deleted.");
            $("#alertSuccess").show();
            $("#divHospitalGrid").html(resultSet.data);

        } else if (resultSet.status.trim() == "error") {

            $("#alertError").text(resultSet.data);
            $("#alertError").show();

        }

    } else if (status == "error") {

        $("#alertError").text("Error while deleting.");
        $("#alertError").show();

    } else {

        $("#alertError").text("Unknown error while deleting..");
        $("#alertError").show();

    }

}



// CLIENTMODEL=========================================================================
function validateHospitalForm() {
    
    if ($("#Hospital_Name").val().trim() == "") {
        return "Insert Hospital name.";
    }
    
    if ($("#Hospital_Address").val().trim() == "") {
        return "Insert Hospital Address.";
    }
    

    if ($("#Hospital_City").val().trim() == "") {
        return "Insert Hospital City.";
    }

    if ($("#Hospital_Phone").val().trim() == "") {
        return "Insert Hospital Phone.";
    }
    
    if ($("#Hospital_Email").val().trim() == "") {
        return "Insert Hospital email.";
    }
    
    if ($("#Hospital_Description").val().trim() == "") {
        return "Insert Hospital Description.";
    }
    
    if ($("#Open_Hours").val().trim() == "") {
        return "Insert Hospital Open_Hours.";
    }
    return true;
}
