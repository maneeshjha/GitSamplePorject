// JavaScript Document
function goBackToMasterMenu(path)
{
	document.UserForm.action	=	path+"/JSP/MiscellaneousMenu.jsp";
	document.UserForm.submit();
}

function saveUserMaster(mode)
{
	var	empName		=	trim(document.UserForm.empName.value);
	var	loginName	=	trim(document.UserForm.loginName.value);
	var	password	=	trim(document.UserForm.password.value);
	
	var	role		=	document.UserForm.roleId.value;
	var	statusFlag	=	false;
	
	if(empName == "")
	{
		alert("Please Enter Full Name.");
		document.UserForm.empName.value	=	"";
		document.UserForm.empName.focus();
		return false;
	}			
	
	if(loginName == "")
	{
		alert("Please Enter User Name.");
		document.UserForm.loginName.value	=	"";
		document.UserForm.loginName.focus();
		return false;
	}
	if(password == "")
	{
		alert("Please Enter Password.");
		document.UserForm.password.value	=	"";
		document.UserForm.password.focus();
		return false;
	}
	if(role == "")
	{
		alert("Please Select Role.");
		document.UserForm.roleId.focus();
		return false;
	}
	for(var i=0;i<document.UserForm.status.length;i++)
	{
		if(document.UserForm.status[i].checked	==	true)
		{
			statusFlag	=	true;
			break;
		}
	}
	if(statusFlag == false)
	{
		alert("Please Select Status.");
		return false;
	}
	document.UserForm.mode.value	=	mode;
	
	if(mode!="" && mode == "MODIFY")
	{
		document.UserForm.empName.disabled		=	false;
		document.UserForm.loginName.disabled	=	false;
		document.UserForm.password.disabled		=	false;
		
		document.UserForm.reqCode.value					=	"modifyUserMaster";
		document.getElementById("btnModify").disabled	=	true;
		document.UserForm.submit();
	}
	else
	{
		document.UserForm.reqCode.value					=	"saveUserMaster";
		document.getElementById("btnSave").disabled		=	true;
		document.getElementById("btnSaveExit").disabled	=	true;
		document.UserForm.submit();
	}
}

function showUserList()
{
	document.UserForm.reqCode.value					=	"showUserMasterList";
	document.UserForm.rarid.value					=	"19";
	document.UserForm.btnCancel.disabled			=	true;
	document.UserForm.submit();
}

function getLoginNameList(docObj,event)
{
	 var keyvalue	=	event.keyCode;
	
	if(keyvalue == 38 || keyvalue == 40 || keyvalue == 13 )
	{
		var chkDivVal ='<IMG style="BORDER-RIGHT: 0px; BORDER-TOP: 0px; FILTER: alpha(opacity=80); VERTICAL-ALIGN: middle; BORDER-LEFT: 0px; WIDTH: 30px; BORDER-BOTTOM: 0px; HEIGHT: 30px; BACKGROUND-COLOR: #cccccc" src="http://localhost:8080/HRINFINITI/images/wait.gif">';
	
		if(document.getElementById("divLoginName").innerHTML != "" && document.getElementById("divLoginName").innerHTML != chkDivVal)
		{			
			if(keyvalue == 13)
			{
				changeLoginNameVal();
			}else
			{
				docObj.value		=	docObj.value; /*to move focus at end when using crome.*/
				var	resultString	=	arrowKeyFunctionForTTS(event,'ajaxLoginNameID');
				if(resultString !="")
				{
					document.UserForm.loginName.value	=	resultString;
				}
			}
		}
	}
	else
	{
		 var reqCode			=	"getStatusWiseLoginName";
		 var url				=	"commonMethodAction.do";
		 var loginName 			= 	trim(docObj.value);
	
		  document.UserForm.loginId.value	=	"";
			
		  showPos(docObj);
		 
		  if(loginName.length > 0)
		  {	 
			 showProcessing("divLoginName");
			  new Ajax.Request
				  ( url , 
					  {
					  method	: 'post',
					  parameters: { reqCode:reqCode,loginName:loginName,status:""},
					  onSuccess	: processLoginNameList,
					  onFailure	: function()
								  { 
								  alert("There was an error with the connection"); 
								  }
								  
					  }
				   );
		  }else
		  {
			  document.getElementById("divLoginName").innerHTML		=	"";
			  document.getElementById("divLoginName").style.display	=	'none';
		  }
	}
}
function processLoginNameList(transprot) 
{	
    var response 											= 	transprot.responseText;
	document.getElementById("divLoginName").innerHTML		=	response;
	document.getElementById("divLoginName").style.top		=	top+20;
	document.getElementById("divLoginName").style.left		=	left;
	document.getElementById("divLoginName").style.display	=	'block';
	
	if(document.UserForm.ajaxLoginNameID.options.length	==	0)
	{
		document.getElementById("divLoginName").innerHTML		=	"";
		document.getElementById("divLoginName").style.display	=	'none';
	}
	
}
function changeLoginNameVal()
{
 for (var i = 0; i < document.UserForm.ajaxLoginNameID.options.length; i++) 
  {
	
	if (document.UserForm.ajaxLoginNameID.options[i].selected) 
	{	 
		 var values	= document.UserForm.ajaxLoginNameID.options[i].text;	 			
		 document.UserForm.loginName.value			=	values;
		 document.UserForm.loginId.value  			=	document.UserForm.ajaxLoginNameID.options[i].value;	
		 
		 document.getElementById("divLoginName").innerHTML		=	"";
		 document.getElementById("divLoginName").style.display	=	'none';	
	
	
		 //NEXT ELEMENT TO FOCUS
		 document.UserForm.status.focus();
		 
	 	 break;
	 }
  }
}

function checkLoginValue(e)
{
	var KeyID = (window.event) ? event.keyCode : e.keyCode;	
	if(KeyID == 13 )
	{ 
		changeLoginNameVal();	
	}
}

function addNewUser()
{
	document.UserForm.reqCode.value						=	"showUserMasterPage";
	document.getElementById("btnAddNew").disabled		=	true;
	document.UserForm.submit();
}

function getSearchList()
{
	var	loginName	=	trim(document.UserForm.loginName.value);
	var	loginId		=	document.UserForm.loginId.value;
	
	if(loginName!="" && loginId == "")
	{
		alert("Please Select Proper User Name.");
		document.UserForm.loginName.value	=	"";
		document.UserForm.loginName.focus();
		return false;
	}
	
	document.UserForm.reqCode.value						=	"showSearchUserMasterList";
	document.getElementById("btnSearch").disabled		=	true;
	document.UserForm.submit();
}
function clearUserListForm()
{
	document.UserForm.loginName.value	=	"";
	document.UserForm.loginId.value		=	"";
	document.UserForm.status.value		=	"";
	document.getElementById("divListData").style.display	=	'none';
	document.UserForm.loginName.focus();
}
function deleteUser(userId,userName)
{
	document.UserForm.user_id.value		=	userId;
	document.UserForm.user_name.value	=	userName;
	if(confirm("Are You Sure,You Want To Delete User : "+userName+" ?"))
	{
		document.UserForm.reqCode.value						=	"deleteUserMaster";
		document.UserForm.submit();
	}
}

function modifyUserName(userId)
{
	document.UserForm.user_id.value		=	userId;
	document.UserForm.reqCode.value		=	"showModifyUserMaster";
	document.UserForm.submit();
}
