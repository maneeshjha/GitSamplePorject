// JavaScript Document
function goBackToUtilityMenu(path)
{
	document.UtilityForm.action	=	path+"/JSP/UtilityMenu.jsp";
	document.UtilityForm.submit();
}
function changePassword()
{
	var	oldPassword		=	trim(document.UtilityForm.oldPassword.value);
	var	newPassword		=	trim(document.UtilityForm.newPassword.value);
	var	confirmPass		=	trim(document.UtilityForm.confirmPassword.value);	
	
	if(oldPassword == "")
	{
		alert("Please Enter Old Password.");
		document.UtilityForm.oldPassword.value	=	"";
		document.UtilityForm.oldPassword.focus();
		return false;
	}
	if(newPassword == "")
	{
		alert("Please Enter New Password.");
		document.UtilityForm.newPassword.value	=	"";
		document.UtilityForm.newPassword.focus();
		return false;
	}
	if(confirmPass == "")
	{
		alert("Please Enter Confirm Password.");
		document.UtilityForm.confirmPassword.value	=	"";
		document.UtilityForm.confirmPassword.focus();
		return false;
	}
	if(newPassword != confirmPass)
	{
		alert("New Password And Confirm Password Must Be Same.");
		document.UtilityForm.confirmPassword.value	=	"";
		document.UtilityForm.confirmPassword.focus();
		return false;
	}
	
		var reqCode		=	"checkLogingUserPassword";
		var url			=	"utilityAction.do";	
		new Ajax.Request
		( 
			url , 
			{
				method		: 'post',
				parameters	: {reqCode:reqCode,oldPassword:oldPassword},
				onSuccess	: processCheckLogingUserPassword,
				onFailure	: function()
							  { 
									alert("There was an error with the connection"); 
							  }
	
			}
		);
}

function processCheckLogingUserPassword(transport)
{
	var	response	=	trim(transport.responseText);
	
	if(response == "YES")
	{
		if(confirm("Are You Sure,You Want To Change The Password ?"))
		{
			document.UtilityForm.reqCode.value				=	"changePassword";
			document.getElementById("btnPassword").disabled	=	true;
			document.UtilityForm.submit();
		}
	}
	if(response == "NO")
	{
		alert("Entered Old Password Not Match With Logging User Password.");
		document.UtilityForm.oldPassword.value	=	"";
		document.UtilityForm.oldPassword.focus();
		return false;
	}
}

function clearForm()
{
	document.UtilityForm.oldPassword.value		=	"";
	document.UtilityForm.newPassword.value		=	"";
	document.UtilityForm.confirmPassword.value	=	"";
	document.UtilityForm.oldPassword.focus();
}