// JavaScript Document

function saveRoleMaster(mode)
{
	var	roleName	=	trim(document.RoleForm.roleName.value);
	var	description	=	trim(document.RoleForm.description.value);
	var	statusFlag	=	false;
	
	
	if(roleName == "")
	{
		alert("Please Enter Role Name.");
		document.RoleForm.roleName.value	=	"";
		document.RoleForm.roleName.focus();
		return false;
	}
	if(description == "")
	{
		alert("Please Enter Description.");
		document.RoleForm.description.value	=	"";
		document.RoleForm.description.focus();
		return false;
	}
	
	for(var i=0;i<document.RoleForm.status.length;i++)
	{
		if(document.RoleForm.status[i].checked	==	true)
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
	
	var flag			=	false;
	for(var i=0;i<document.RoleForm.responsibilityArray.length;i++)
	{
		if(document.RoleForm.responsibilityArray[i].checked	==	true)
		{
			flag	=	true;
			break;
		}
	}
	
	if(flag	==	false)
	{
		alert("Please Select At Least One Responsibility.");
		return false;
	}
	document.RoleForm.mode.value	=	mode;
	
	if(mode!="" && mode == "MODIFY")
	{
		document.RoleForm.reqCode.value					=	"modifyRoleMaster";
		document.getElementById("btnModify").disabled	=	true;
		document.RoleForm.submit();
	}
	else
	{
		document.RoleForm.reqCode.value					=	"saveRoleMaster";
		document.getElementById("btnSave").disabled		=	true;
		document.getElementById("btnSaveExit").disabled	=	true;
		document.RoleForm.submit();
	}
}
function chkRoleDetailsClicked(obj)
{
	var tr = obj.parentNode.parentNode;	
			
	if(obj.checked)
	{
		flag++;
		tr.style.color = "RED";
	}else
	{
		tr.style.color = "";
	}	
}
function showRoleList()
{
	document.RoleForm.reqCode.value					=	"showRoleMasterList";
	document.RoleForm.btnCancel.disabled			=	true;
	document.RoleForm.submit();
}

function getRoleNameList(docObj,event)
{
	 var keyvalue	=	event.keyCode;
	
	if(keyvalue == 38 || keyvalue == 40 || keyvalue == 13 )
	{
		var chkDivVal ='<IMG style="BORDER-RIGHT: 0px; BORDER-TOP: 0px; FILTER: alpha(opacity=80); VERTICAL-ALIGN: middle; BORDER-LEFT: 0px; WIDTH: 30px; BORDER-BOTTOM: 0px; HEIGHT: 30px; BACKGROUND-COLOR: #cccccc" src="http://localhost:8080/HMS/images/wait.gif">';
	
		if(document.getElementById("divRoleName").innerHTML != "" && document.getElementById("divRoleName").innerHTML != chkDivVal)
		{			
			if(keyvalue == 13)
			{
				changeRoleNameVal();
			}else
			{
				docObj.value		=	docObj.value; /*to move focus at end when using crome.*/
				var	resultString	=	arrowKeyFunctionForTTS(event,'ajaxRoleNameID');
				if(resultString !="")
				{
					document.RoleForm.roleName.value	=	resultString;
				}
			}
		}
	}
	else
	{
		 var reqCode			=	"getStatusWiseRoleName";
		 var url				=	"commonMethodAction.do";
		 var roleName 			= 	trim(docObj.value);
	
		  document.RoleForm.roleId.value	=	"";
			
		  showPos(docObj);
		 
		  if(roleName.length > 0)
		  {	 
			 showProcessing("divRoleName");
			  new Ajax.Request
				  ( url , 
					  {
					  method	: 'post',
					  parameters: { reqCode:reqCode,roleName:roleName,status:""},
					  onSuccess	: processRoleNameList,
					  onFailure	: function()
								  { 
								  alert("There was an error with the connection"); 
								  }
								  
					  }
				   );
		  }else
		  {
			  document.getElementById("divRoleName").innerHTML		=	"";
			  document.getElementById("divRoleName").style.display	=	'none';
		  }
	}
}
function processRoleNameList(transprot) 
{	
    var response 											= 	transprot.responseText;
	document.getElementById("divRoleName").innerHTML		=	response;
	document.getElementById("divRoleName").style.top		=	top+20;
	document.getElementById("divRoleName").style.left		=	left;
	document.getElementById("divRoleName").style.display	=	'block';
	
	if(document.RoleForm.ajaxRoleNameID.options.length	==	0)
	{
		document.getElementById("divRoleName").innerHTML		=	"";
		document.getElementById("divRoleName").style.display	=	'none';
	}
	
}
function changeRoleNameVal()
{
 for (var i = 0; i < document.RoleForm.ajaxRoleNameID.options.length; i++) 
  {
	
	if (document.RoleForm.ajaxRoleNameID.options[i].selected) 
	{	 
		 var values	= document.RoleForm.ajaxRoleNameID.options[i].text;	 			
		 document.RoleForm.roleName.value			=	values;
		 document.RoleForm.roleId.value  			=	document.RoleForm.ajaxRoleNameID.options[i].value;	
		 
		 document.getElementById("divRoleName").innerHTML		=	"";
		 document.getElementById("divRoleName").style.display	=	'none';	
	
	
		 //NEXT ELEMENT TO FOCUS
		 document.RoleForm.status.focus();
		 
	 	 break;
	 }
  }
}

function checkRoleName(e)
{
	var KeyID = (window.event) ? event.keyCode : e.keyCode;	
	if(KeyID == 13 )
	{ 
		changeRoleNameVal();	
	}
}

function addNewRole()
{
	document.RoleForm.reqCode.value						=	"showRoleMasterPage";
	document.getElementById("btnAddNew").disabled		=	true;
	document.RoleForm.submit();
}

function getSearchList()
{
	var	roleName	=	trim(document.RoleForm.roleName.value);
	var	roleId		=	document.RoleForm.roleId.value;
	
	if(roleName!="" && roleId == "")
	{
		alert("Please Select Proper Role Name.");
		document.RoleForm.roleName.value	=	"";
		document.RoleForm.roleName.focus();
		return false;
	}
	
	document.RoleForm.reqCode.value						=	"showSearchRoleMasterList";
	document.getElementById("btnSearch").disabled		=	true;
	document.RoleForm.submit();
}
function clearRoleListForm()
{
	document.RoleForm.roleName.value	=	"";
	document.RoleForm.roleId.value		=	"";
	document.RoleForm.status.value		=	"";
	document.getElementById("divListData").style.display	=	'none';
	document.RoleForm.roleName.focus();
}
function deleteRole(roleId,roleName)
{
	document.RoleForm.role_id.value		=	roleId;
	document.RoleForm.role_name.value	=	roleName;
	if(confirm("Are You Sure,You Want To Delete Role : "+roleName+" ?"))
	{
		document.RoleForm.reqCode.value		=	"deleteUserMaster";
		document.RoleForm.submit();
	}
}

function modifyRoleName(userId)
{
	document.RoleForm.role_id.value		=	userId;
	document.RoleForm.reqCode.value		=	"showModifyRoleMaster";
	document.RoleForm.submit();
}

function responsibilitySelected(obj)
{
	var tr = obj.parentNode.parentNode;	
	tr.style.color = obj.checked ? "RED" : "";
}
