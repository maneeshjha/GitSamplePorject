// JavaScript Document
function goBackToMasterMenu(path)
{
	document.CommonMasterForm.action	=	path+"/JSP/MiscellaneousMenu.jsp";
	document.CommonMasterForm.submit();
}
function getSeacrhList()
{
	var	groupValue		=	trim(document.CommonMasterForm.groupValue.value);
	var	groupValueId	=	document.CommonMasterForm.groupValueId.value;
	
	if(groupValueId	==	"" && groupValue!= "")
	{
		alert("Please Select Proper Group Value.");
		document.CommonMasterForm.groupValue.value	=	"";
		document.CommonMasterForm.groupValue.focus();
		return false;
	}
	
	document.CommonMasterForm.reqCode.value				=	"showSearchGroupValueList";
	document.getElementById("btnSearch").disabled		=	true;
	document.CommonMasterForm.submit();
}
function clearCommonListForm()
{
	document.CommonMasterForm.groupId.value			=	"";
	document.CommonMasterForm.groupValue.value		=	"";
	document.CommonMasterForm.groupValueId.value	=	"";
	document.CommonMasterForm.status.value			=	"";
	document.getElementById("divSearchList").style.display		=	'none';
	document.CommonMasterForm.groupId.focus();
}
function addNewCommonMaster()
{
	document.CommonMasterForm.reqCode.value				=	"showCommonMasterPage";
	document.getElementById("btnAddNew").disabled		=	true;
	document.CommonMasterForm.submit();
}
function saveCommonMaster(mode)
{
	var	groupId		=	document.CommonMasterForm.groupId.value;
	var	groupValue	=	trim(document.CommonMasterForm.groupValue.value);
	var	flag		=	false;	
	
	var	dataType	=	document.CommonMasterForm.dataType.value;
	
	//var groupName	=	document.CommonMasterForm.groupId.options[document.CommonMasterForm.groupId.selectedIndex].text;
	//document.CommonMasterForm.groupName.value	=	groupName;
	
	if(groupId	==	"")
	{
		alert("Please Select Group Name.");
		document.CommonMasterForm.groupId.focus();
		return false;
	}
	if(groupValue	==	"")
	{
		alert("Please Enter Group Value.");
		document.CommonMasterForm.groupValue.value	=	"";
		document.CommonMasterForm.groupValue.focus();
		return false;
	}
	if(dataType	==	"NUMERIC")
	{
		if (isNaN(groupValue))
		{
			alert("Please Enter Proper Numeric Value.");
			//document.CommonMasterForm.groupValue.value	=	"";
			document.CommonMasterForm.groupValue.focus();
			return false;
		}
		if(groupValue!= "" && groupValue.length > 10)
		{
			alert("Maximum Length Exceeded Than 10");
			document.CommonMasterForm.groupValue.focus();
			return false;
		}
	}
	/*if(dataType	==	"ALPHANUMERIC")
	{}*/
	for(var i=0;i<document.CommonMasterForm.status.length;i++)
	{
		if(document.CommonMasterForm.status[i].checked	==	true)
		{
			flag	=	true;
			break;
		}
	}
	if(flag	==	false)
	{
		alert("Please Select Status.");
		document.CommonMasterForm.status[0].focus();
		return false;
		
		
	}
	
	var groupName	=	document.CommonMasterForm.groupId.options[document.CommonMasterForm.groupId.selectedIndex].text;
	document.CommonMasterForm.groupName.value	=	groupName;
	
	document.CommonMasterForm.mode.value	=	mode;
	
	if(mode != "" && mode	==	"MODIFY")
	{
		document.CommonMasterForm.reqCode.value				=	"modifyGroupValue";
		document.getElementById("btnModify").disabled		=	true;
		document.CommonMasterForm.submit();
	}
	else
	{
		document.CommonMasterForm.reqCode.value				=	"saveGroupValueMaster";
		document.getElementById("btnSave").disabled			=	true;
		document.getElementById("btnSaveExit").disabled		=	true;
		document.CommonMasterForm.submit();
	}
	
}
function showCommonMasterList()
{
	document.CommonMasterForm.reqCode.value				=	"showCommonMasterList";
	document.CommonMasterForm.rarid.value				=	"16";
	document.CommonMasterForm.btnCancel.disabled		=	true;
	document.CommonMasterForm.submit();
}
function getGroupDataType(docobj)
{
	
	var reqCode		=	"getGroupWiseGroupDataType";
	var url			=	"commonMasterAction.do";
	
	var	groupId		=	docobj.value;
	
	if(groupId.length > 0)
	{
		new Ajax.Request
			  ( url , 
				  {
				  method	: 'post',
				  parameters: {reqCode:reqCode,groupId:groupId},
				  onSuccess	: processGroupValueList,
				  onFailure	: function()
							  { 
							  alert("There was an error with the connection"); 
							  }
							  
				  }
			   );	
	}
}
function processGroupValueList(transprot)
{
	var response 	= 	transprot.responseText;
	var temp 		= 	response.split("#@#");
	document.CommonMasterForm.dataType.value		=	temp[0];
	document.CommonMasterForm.allowMultiple.value	=	temp[1];
	document.CommonMasterForm.allowModify.value		=	temp[2];
}

function getGroupValueList(docObj,event)
{
 	 var keyvalue	=	event.keyCode;
	
	if(keyvalue == 38 || keyvalue == 40 || keyvalue == 13 )
	{
		var chkDivVal ='<IMG style="BORDER-RIGHT: 0px; BORDER-TOP: 0px; FILTER: alpha(opacity=80); VERTICAL-ALIGN: middle; BORDER-LEFT: 0px; WIDTH: 30px; BORDER-BOTTOM: 0px; HEIGHT: 30px; BACKGROUND-COLOR: #cccccc" src="http://localhost:8080/HRINFINITI/images/wait.gif">';
	
		if(document.getElementById("divGroupValue").innerHTML != "" && document.getElementById("divGroupValue").innerHTML != chkDivVal)
		{			
			if(keyvalue == 13)
			{
				changeGroupValueNameVal();
			}else
			{
				docObj.value		=	docObj.value; /*to move focus at end when using crome.*/
				var	resultString	=	arrowKeyFunctionForTTS(event,'ajaxGroupValueNameID');
				if(resultString !="")
				{
					document.CommonMasterForm.groupValue.value	=	resultString;
				}
			}
		}
	}
	else
	{
		 var reqCode			=	"getStatusWiseGroupValue";
		 var url				=	"commonMethodAction.do";
		 var groupValueName 	= 	trim(docObj.value);
		 var groupId			=	 document.CommonMasterForm.groupId.value;
		
		 document.CommonMasterForm.groupValueId.value	=	"";
			
		  showPos(docObj);
		 
		  if(groupValueName.length > 0)
		  {	 
			 showProcessing("divGroupValue");
			  new Ajax.Request
				  ( url , 
					  {
					  method	: 'post',
					  parameters: { reqCode:reqCode,groupValueName:groupValueName,status:"",groupId:groupId},
					  onSuccess	: processGroupValueNameList,
					  onFailure	: function()
								  { 
								  alert("There was an error with the connection"); 
								  }
								  
					  }
				   );
		  }else
		  {
			  document.getElementById("divGroupValue").innerHTML		=	"";
			  document.getElementById("divGroupValue").style.display	=	'none';
		  }
	}
}
function processGroupValueNameList(transprot) 
{	
    var response 											= 	transprot.responseText;
	document.getElementById("divGroupValue").innerHTML		=	response;
	document.getElementById("divGroupValue").style.top		=	top+20;
	document.getElementById("divGroupValue").style.left		=	left;
	document.getElementById("divGroupValue").style.display	=	'block';
	
	if(document.CommonMasterForm.ajaxGroupValueNameID.options.length	==	0)
	{
		document.getElementById("divGroupValue").innerHTML		=	"";
		document.getElementById("divGroupValue").style.display	=	'none';
	}
	
}
function changeGroupValueNameVal()
{
 for (var i = 0; i < document.CommonMasterForm.ajaxGroupValueNameID.options.length; i++) 
  {
	
	if (document.CommonMasterForm.ajaxGroupValueNameID.options[i].selected) 
	{	 
		 var values	= document.CommonMasterForm.ajaxGroupValueNameID.options[i].text;	 			
		 document.CommonMasterForm.groupValue.value			=	values;
		 
		 document.CommonMasterForm.groupValueId.value  		=	document.CommonMasterForm.ajaxGroupValueNameID.options[i].value;	
		 
		 document.getElementById("divGroupValue").innerHTML		=	"";
		 document.getElementById("divGroupValue").style.display	=	'none';	
	
	
		 //NEXT ELEMENT TO FOCUS
		 document.CommonMasterForm.status.focus();
	 break;
	 }
  }
}
function checkGroupValue(e)
{
	var KeyID = (window.event) ? event.keyCode : e.keyCode;	
	if(KeyID == 13 )
	{ 
		changeGroupValueNameVal();	
	}
}
function modifyGroupValue(groupValueId)
{
	document.CommonMasterForm.group_Value_id.value		=	groupValueId;
	document.CommonMasterForm.reqCode.value				=	"showModifyGroupValueMaster";
	document.CommonMasterForm.submit();
}
function deleteGroupValue(groupValueId,groupValueName)
{
	document.CommonMasterForm.group_Value_id.value		=	groupValueId;
	document.CommonMasterForm.group_value_name.value	=	groupValueName;
	
	if(confirm("Are You Sure, You Want To Delete The Group Value : "+groupValueName+" ?"))
	{
		document.CommonMasterForm.reqCode.value		=	"deleteGroupValue";
		document.CommonMasterForm.submit();
	}
}