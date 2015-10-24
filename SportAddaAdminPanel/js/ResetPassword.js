// JavaScript Document
function goBackToUtilityMenu(path)
{
	document.UtilityForm.action	=	path+"/JSP/UtilityMenu.jsp";
	document.UtilityForm.submit();
}
function resetPassword()
{
	var	userName	=	trim(document.UtilityForm.loginName.value);
	var	userId		=	document.UtilityForm.loginId.value;
	
	if(userName == "")
	{
		alert("Please Enter User Name.");
		document.UtilityForm.loginName.value	=	"";
		document.UtilityForm.loginName.focus();
		return false;
	}
	if(userName!="" && userId=="")
	{
		alert("Please Select Proper User Name.");
		document.UtilityForm.loginName.value	=	"";
		document.UtilityForm.loginName.focus();
		return false;
	}
	
	if(confirm("Are You Sure, You Want To Reset Password ?"))
	{
		document.UtilityForm.reqCode.value				=	"resetPassword";
		document.getElementById("btnReset").disabled	=	true;
		document.UtilityForm.submit();
	}
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
					document.UtilityForm.loginName.value	=	resultString;
				}
			}
		}
	}
	else
	{
		 var reqCode			=	"getStatusWiseLoginName";
		 var url				=	"commonMethodAction.do";
		 var loginName 			= 	trim(docObj.value);
	
		  document.UtilityForm.loginId.value	=	"";
			
		  showPos(docObj);
		 
		  if(loginName.length > 0)
		  {	 
			 showProcessing("divLoginName");
			  new Ajax.Request
				  ( url , 
					  {
					  method	: 'post',
					  parameters: { reqCode:reqCode,loginName:loginName,status:"ACTIVE"},
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
	
	if(document.UtilityForm.ajaxLoginNameID.options.length	==	0)
	{
		document.getElementById("divLoginName").innerHTML		=	"";
		document.getElementById("divLoginName").style.display	=	'none';
	}
	
}
function changeLoginNameVal()
{
 for (var i = 0; i < document.UtilityForm.ajaxLoginNameID.options.length; i++) 
  {
	
	if (document.UtilityForm.ajaxLoginNameID.options[i].selected) 
	{	 
		 var values	= document.UtilityForm.ajaxLoginNameID.options[i].text;	 			
		 document.UtilityForm.loginName.value			=	values;
		 document.UtilityForm.loginId.value  			=	document.UtilityForm.ajaxLoginNameID.options[i].value;	
		 
		 document.getElementById("divLoginName").innerHTML		=	"";
		 document.getElementById("divLoginName").style.display	=	'none';	
	
	
		 //NEXT ELEMENT TO FOCUS
		 document.UtilityForm.btnReset.focus();
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
function clearForm()
{
	document.UtilityForm.loginName.value	=	"";
	document.UtilityForm.loginId.value		=	"";
	document.UtilityForm.loginName.focus();
}
