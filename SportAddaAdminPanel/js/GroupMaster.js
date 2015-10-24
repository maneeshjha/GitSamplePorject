// JavaScript Document
function goBackToMasterMenu(path)
{
	document.GroupForm.action	=	path+"/JSP/OfficalMenu.jsp";
	document.GroupForm.submit();
}
function getSearchList()
{
	var	 groupId	=	document.GroupForm.groupId.value;	
	var  groupName	=	trim(document.GroupForm.groupName.value);
	
	if(groupId == "" && groupName!= "")
	{
		alert("Please Select Proper Group Name.");
		document.GroupForm.groupName.value	=	"";
		document.GroupForm.groupName.focus();
		return false;
	}
	
	document.GroupForm.reqCode.value				=	"showSearchGroupMasterList";
	document.getElementById("btnSearch").disabled	=	true;
	document.GroupForm.submit();
}
function clearGroupMasterListForm()
{
	document.GroupForm.groupId.value		=	"";
	document.GroupForm.groupName.value		=	"";
	document.GroupForm.dataType.value		=	"";
	document.GroupForm.allowMultiple.value	=	"";
	document.GroupForm.allowModify.value	=	"";
	document.GroupForm.status.value			=	"";
	document.getElementById("divSearchList").style.display	=	'none';
	document.GroupForm.groupName.focus();
}
function addNewGroup()
{
	document.GroupForm.reqCode.value				=	"showGroupMasterPage";
	document.getElementById("btnAddNew").disabled	=	true;
	document.GroupForm.submit();
}
function saveGroupMaster(mode)
{
	var groupName	=	trim(document.GroupForm.groupName.value);
	var dataType	=	document.GroupForm.dataType.value;
	
	var flagForAllowMultiple	=	false;
	var flagForAllowModify		=	false;
	var flagForStatus			=	false;
	
	if(groupName	==	"")
	{
		alert("Please Enter Group Name.");
		document.GroupForm.groupName.value	=	"";
		document.GroupForm.groupName.focus();
		return false;
	}
	if(dataType	==	"")
	{
		alert("Please Select Data Type.");
		document.GroupForm.dataType.value	=	"";
		document.GroupForm.dataType.focus();
		return false;
	}
	
	for(var i=0;i<document.GroupForm.allowMultiple.length;i++)
	{
		if(document.GroupForm.allowMultiple[i].checked	==	true)
		{
			flagForAllowMultiple	=	true;
			break;
		}
	}
	if(flagForAllowMultiple	==	false)
	{
		alert("Please Select Allow Multiple.");
		document.GroupForm.allowMultiple[0].focus();
		return false;
	}
	
	for(var i=0;i<document.GroupForm.allowModify.length;i++)
	{
		if(document.GroupForm.allowModify[i].checked	==	true)
		{
			flagForAllowModify	=	true;
			break;
		}
	}
	if(flagForAllowModify	==	false)
	{
		alert("Please Select Allow Modify.");
		document.GroupForm.allowModify[0].focus();
		return false;
	}
	
	for(var i=0;i<document.GroupForm.status.length;i++)
	{
		if(document.GroupForm.status[i].checked	==	true)
		{
			flagForStatus	=	true;
			break;
		}
	}
	
	if(flagForStatus	==	false)
	{
		alert("Please Select Status.");
		document.GroupForm.status[0].focus();
		return false;
	}
	
	document.GroupForm.mode.value	=	mode;
	if(mode !=  "" && mode == "MODIFY")
	{
		document.GroupForm.reqCode.value				=	"modifyGroupMaster";
		document.getElementById("btnModify").disabled	=	true;
		document.GroupForm.submit();
	}
	else
	{
		document.GroupForm.reqCode.value				=	"saveGroupMaster";
		document.getElementById("btnSave").disabled		=	true;
		document.getElementById("btnSaveExit").disabled	=	true;
		document.GroupForm.submit();
	}
}
function showGroupList()
{
	document.GroupForm.reqCode.value					=	"showGroupMasterList";
	document.getElementById("btnCancel").disabled		=	true;
	document.GroupForm.submit();
}

function modifyGroup(groupId)
{
	document.GroupForm.group_id.value	=	groupId;
	document.GroupForm.reqCode.value	=	"showModifyGroupMaster";
	document.GroupForm.submit();
}
function deleteGroup(groupId,groupName)
{
	document.GroupForm.group_id.value	=	groupId;
	document.GroupForm.group_name.value	=	groupName;
	
	if(confirm("Are You Sure, You Want To Delete Group Name : "+groupName+" ? "))
	{
		document.GroupForm.reqCode.value	=	"deleteGroup";
		document.GroupForm.submit();
	}
}
//Group Name TTS
function getGroupNameList(docObj)
{
	 var reqCode		=	"getStatusWiseGroupName";
	 var url			=	"commonMethodAction.do";
	 var groupName 		= 	trim(docObj.value);
     
	 document.GroupForm.groupId.value	=	"";
		
	  showPos(docObj);
	 
	  if(groupName.length > 0)
	  {	 
	  	 showProcessing("divGroupName");
		  new Ajax.Request
			  ( url , 
				  {
				  method	: 'post',
				  parameters: { reqCode:reqCode,groupName:groupName,status:"ACTIVE"},
				  onSuccess	: processGroupNameList,
				  onFailure	: function()
							  { 
							  alert("There was an error with the connection"); 
							  }
							  
				  }
			   );
	  }else
	  {
	      document.getElementById("divGroupName").innerHTML		=	"";
       	  document.getElementById("divGroupName").style.display	=	'none';
	  }
}
function processGroupNameList(transprot)
{
	var response 											= 	transprot.responseText;
	document.getElementById("divGroupName").innerHTML		=	response;
	document.getElementById("divGroupName").style.top		=	top+20;
	document.getElementById("divGroupName").style.left		=	left;
	document.getElementById("divGroupName").style.display	=	'block';
	
	if(document.GroupForm.ajaxGroupNameID.options.length	==	0)
	{
		document.getElementById("divGroupName").innerHTML		=	"";
		document.getElementById("divGroupName").style.display	=	'none';
	}
	
}
function changeGroupNameVal()
{
 for (var i = 0; i < document.GroupForm.ajaxGroupNameID.options.length; i++) 
  {
	if (document.GroupForm.ajaxGroupNameID.options[i].selected) 
	{	 
		
		 var values	= document.GroupForm.ajaxGroupNameID.options[i].text;	 
		 document.GroupForm.groupName.value		=	values;
		 
		 document.GroupForm.groupId.value  		=	document.GroupForm.ajaxGroupNameID.options[i].value;	
		 
		 document.getElementById("divGroupName").innerHTML		=	"";
		 document.getElementById("divGroupName").style.display	=	'none';	
	
	
		 //NEXT ELEMENT TO FOCUS
		 document.GroupForm.dataType.focus();
	 break;
	 }
  }
}
function checkGroupName(e)
{
	var KeyID = (window.event) ? event.keyCode : e.keyCode;	
	if(KeyID == 13 )
	{ 
		changeGroupNameVal();	
	}
}