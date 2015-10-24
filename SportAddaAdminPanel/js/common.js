var left;
var top;
var height;
function showPos(element)
{

    left = element.offsetLeft;
	top  = element.offsetTop;
	height= element.offsetHeight;
	
	while(element=element.offsetParent)
    {
        left += element.offsetLeft;
        top  += element.offsetTop;
    }

	document.body.onclick= hideTTS;
}
/*----------------------------------------------------------------------------
Function Name	-	allowOnlyFloat
Purpose			-	Allow only Number with/without decimal Number
------------------------------------------------------------------------------*/
function allowOnlyFloat(obj)
{
	var val = trim(obj.value);
	if (isNaN(val))
	{
		alert("Please Enter Proper Numeric Value.");
		obj.value="";
		obj.focus();
		//obj.select();
		return false;
	}
	
}

/*----------------------------------------------------------------------------
Function Name	-	trim
Purpose			-	removes trailing leading spaces.
------------------------------------------------------------------------------*/
function trim(str)
{
	return str.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
}

function isPositiveInteger(obj)
{      
	var val = trim(obj.value);
	if(val==null)
	{
			return true;
	}
	if (val.length==0)
	{
		return true;
	}
	for (var i = 0; i < val.length; i++) 
	{
		var ch = val.charAt(i) 
		if (ch < "0" || ch > "9") 
		{           
			alert("Please Enter Only Whole Number.");
			obj.value="";
			return false;
		}      
	}   
}
function validateEmailID(obj){

		var	str =	trim(obj.value);
		if(str == "")
		{
			obj.value = "";
			return true;	
		}
		
		var at="@"
		var dot="."
		var lat=str.indexOf(at)
		var lstr=str.length
		var ldot=str.indexOf(dot)
		if (str.indexOf(at)==-1){
		   alert("Invalid E-mail ID.");
		   obj.select();
		   obj.focus();
		   return false
		}

		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
		   alert("Invalid E-mail ID.");
		   obj.select();
		   obj.focus();
		   return false
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
		    alert("Invalid E-mail ID.");
			obj.select();
		    obj.focus();
		    return false
		}

		 if (str.indexOf(at,(lat+1))!=-1){
		    alert("Invalid E-mail ID.");
			obj.select();
		   obj.focus();
		    return false
		 }

		 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
		    alert("Invalid E-mail ID.");
			obj.select();
		   obj.focus();
		    return false
		 }

		 if (str.indexOf(dot,(lat+2))==-1){
		    alert("Invalid E-mail ID.");
			obj.select();
		   obj.focus();
		    return false
		 }
		
		 if (str.indexOf(" ")!=-1){
		    alert("Invalid E-mail ID.");
			obj.select();
		   obj.focus();
		    return false
		 }
		  if (str.indexOf(dot) == (lstr-1)){
		    alert("Invalid E-mail ID.")
		    return false
		 }
		 if(str.charAt(lstr-1) == "." )
		 {
			alert("Invalid E-mail ID.")
		    return false 
		 }

 		 return true					
	}
	
function checkenter(event)
{
	 if(window.event) 
	 {
		keychar = String.fromCharCode(event.keyCode)
		
		if (event.keyCode ==13)
			  return false;     
	 }
	 if(event.which)
	 {
	   if (event.which ==13)
			return false;     
	 } 
}		

function isProper(obj) 
{
	
	var result = true;	
	var string = obj.value.length;	
	var val	   = obj.value;		
	var iChars = "\"\'\\";
	for (var i = 0; i < string; i++) 
	{
		if (iChars.indexOf(val.charAt(i)) != -1)
		{
			alert('Special Characters   \' " \\  Not Allowed.');			
			obj.select();
			obj.focus();
			result = false;
			break;
		}
	}
	return result;
}

function allowOnlyFloatGreaterZero(obj)
{
	var val = trim(obj.value);
	if (isNaN(val))
	{
		alert("Please Enter Proper Numeric Value.");
		obj.value="";
		obj.focus();
		obj.select();
		return false;
	}
	if(parseFloat(val) <= 0)
	{
		alert("Entered Value Should Be Greater Than Zero.");
		obj.value="";
		obj.focus();		
		return false;
	}
	
}

function checkDateDifference(fromDate,toDate,monthCntCheck)
{
	var one_day		=	1000*60*60*24;
	var fDateArr	=	fromDate.split("/");
	var fDate		=	new Date(fDateArr[2],fDateArr[1],fDateArr[0]);
	var tDateArr	=	toDate.split("/");
	var tDate		=	new Date(tDateArr[2],tDateArr[1],tDateArr[0]);
	var dayDateDiff	=	Math.ceil((tDate.getTime()-fDate.getTime())/(one_day));
	var noofdays	=	(32 - new Date(fDateArr[2], fDateArr[1], 32).getDate());
	var	monthCnt	=	Math.ceil(dayDateDiff/noofdays) - 1;
	
	
	if(monthCnt < monthCntCheck)
	{
		return true;
	}else
	{
		alert("Difference Between From Date And To Date Should Not Be Greater Than : "+monthCntCheck+" Months. ");
        return false;
	}
	
}
function restrictSpecialChar(obj)
{
	var result = true;	
	var string = obj.value.length;	
	var val	   = obj.value;		
	var iChars = "\"\'\\\/\?\*\<\>\:\|\%\$\#\.";
	for (var i = 0; i < string; i++) 
	{
		if (iChars.indexOf(val.charAt(i)) != -1)
		{
			alert('Special Characters  \\ \/ \* \? \: \< \> \' \| \"  \% \$ \. \# Not Allowed.');
			obj.focus();
			obj.select();
			result = false;
			break;
		}
	}
	return result;	
}
function restrict4SpecialChar(obj)
{
	var result = true;	
	var string = obj.value.length;	
	var val	   = obj.value;		
	var iChars = "\"\'\\\/";
	for (var i = 0; i < string; i++) 
	{
		if (iChars.indexOf(val.charAt(i)) != -1)
		{
			alert('Special Characters  \\ \/ \' \"  Not Allowed.');
			obj.focus();
			obj.select();
			result = false;
			break;
		}
	}
	return result;	
}
function hideTTS()
{	
	$j(".ajaxDiv").html("");	
	$j(".ajaxDiv").hide();	
}

function checkPhoneNumber(obj)
{
	var result = true;
	var val    = trim(obj.value);
	
	var string = val.length;	
	var iChar = /^\(?\ ?\+?\d+\ ?\)?\ ?\-?\d+$/;
	
	if(val.length > 0)
	{
		if (!iChar.test(val)) 
		{
			   alert("Please Enter In Proper Number Format.\nFor example : \n(022)1234567\n +919812345678 \n022-123456");
			  // obj.select();
			   obj.value="";
			   obj.focus();
			   return false;
		}
		result =  true;
	}else
	{
		result =  true;
	}
	return result;
}
function checkMobileNumber(obj)
{
	var result = true;
	var val    = trim(obj.value);
	
	if(val.length > 0)
	{
		if(isPositiveInteger(obj) == false)
		{
			return false;
		}
		if(val.length < 10 || val.length > 12)
		{
			 alert("Mobile Number Must Contain 10 Or 12 Digit.");
			  // obj.select();
			   obj.value="";
			   obj.focus();
			   return false;
		}
		result =  true;
	}else
	{
		result =  true;
	}
	return result;
}

function textAreaMaxlength(field , maxlen)
{
	if(field.value.length > maxlen)
	{
		field.value = field.value.substring(0,(parseInt(maxlen)-1));		
	}
	
}


function showProcessing(elementId)
{		
	$j("#"+elementId).css("top",($j("#"+elementId).parent().position().top+22));
	$j("#"+elementId).css("left",$j("#"+elementId).parent().position().left);
	$j("#"+elementId).html('<div align="left" ><img src="images/wait.gif" vertical-align:middle; width:30px;height:30px;border:0;" /></div>');
	$j("#"+elementId).show();
}

function ValidateWebAddress(obj)
{   	
	var companyUrl = trim(obj.value); 
	
	if(companyUrl == "")
	{
		return true;
	}
	 var RegExp = /^(([\w]+:)?\/\/)?(([\d\w]|%[a-fA-f\d]{2,2})+(:([\d\w]|%[a-fA-f\d]{2,2})+)?@)?([\d\w][-\d\w]{0,253}[\d\w]\.)+[\w]{2,4}(:[\d]+)?(\/([-+_~.\d\w]|%[a-fA-f\d]{2,2})*)*(\?(&?([-+_~.\d\w]|%[a-fA-f\d]{2,2})=?)*)?(#([-+_~.\d\w]|%[a-fA-f\d]{2,2})*)?$/;     
	/*if(RegExp.test(companyUrl))    
	{  		
		return true;    
	}else    
	{   
		alert('Please Enter Valid Web Site.');
		obj.select();
		obj.focus();
		return false;    
	}*/
	if(!RegExp.test(companyUrl))    
	{  		
		
		alert('Please Enter Valid Web Site.');
		obj.select();
		obj.focus();
		return false;    
	}
}

/*
CREATED BY : ASHAY
PURPOSE    : TO CHECK FAX NUMBER (12 DIGITS)
DATE       : 12/08/2011
*/
function checkFaxNumber(obj)
{
	var result = true;
	var val    = trim(obj.value);
	
	if(val.length > 0)
	{
		if(isPositiveInteger(obj) == false)
		{
			return false;
		}
		if(val.length < 10 || val.length > 12)
		{
			 alert("Fax Number Must Contain 10 Or 12 Digit.");			 
			 obj.select();
			 obj.focus();
			 return false;
		}
		result =  true;
	}else
	{
		result =  true;
	}
	return result;
}

function CompareDate(fromDate,toDate)
{
	
		var fromdateArray 		=	new Array();
		var from_date_month 	= 	"";
		var from_date_day 		= 	"";
		var from_date_year 		= 	"";
		
		var to_date_month 		= 	"";
		var to_date_day 		= 	"";
		var to_date_year 		=	"";
		
		fromdateArray 			=	 fromDate.split("/");
		//d-m-y
		from_date_month 		= 	fromdateArray[1];
		from_date_day			= 	fromdateArray[0];
		from_date_year 			=	fromdateArray[2];
		
		fromdateArray 			= 	toDate.split("/");
		
		to_date_month 			=	fromdateArray[1];
		to_date_day 			= 	fromdateArray[0];
		to_date_year 			= 	fromdateArray[2];
		if(from_date_year > to_date_year)
		{
			return false;
		}
		else if((from_date_year >= to_date_year) && (from_date_month > to_date_month))
		{
			return false;
		}
		
		else if((from_date_year >= to_date_year) && (from_date_month >= to_date_month) && from_date_day > to_date_day)
		{
			return false;
		}else 
		{
			return true; 
		}		
}

/**************************************
To Show Common Details Pop Up
Created By : Ashay
Date       : 06/09/2011
***************************************/
function showCommonDetailsPopUp(path,switchName)
{
	var url=path+"/JSP/CommonDescription.jsp?name="+switchName;
	window.open(url,"CommonDescription","height=500,width=800,left=100,top=100,resizable=yes,scrollbars=yes,toolbar=yes,status=yes");
}



/**
* This method will fill the drop down with frovided JSONArray
* Provide JSON Array Of JSON Object with key value and name respectively
* @pram DDList    : JSON Array of object with keys value and name
* @pram fieldName : Name of select drop down element
* @author   	  : Ashay
*/
function fillDDUsingArray(DDList,fieldName)
{	
	if(fieldName != "")
	{
		$j("[name="+fieldName+"] options").length 	= 1;	
		if(DDList.length > 0)
		{
			for(var i=0;i<DDList.length;i++)
			{											
				$j("[name="+fieldName+"]").append("<option value=\""+DDList[i].value+"\">"+DDList[i].name+"</option>");			
			}
		}
	}else
	{
		alert("Field Name Not Provided.");
	}
}

function allowOnlyPositiveFloatValues(obj)
{
	var val = trim(obj.value);
	if (isNaN(val))
	{
		alert("Please Enter Proper Numeric Value.");
		obj.value="";
		obj.focus();
		obj.select();
		return false;
	}
	if(parseFloat(val) < 0)
	{
		alert("Entered Value Should Be Positive Number.");
		obj.value="";
		obj.focus();		
		return false;
	}
	
}

/**************************************
To Clear File Input 
Created By : Ashay
Date       : 07/11/2011
NOTE 	   : passs file input name
***************************************/
function clearFileField(name)
{
    var elm   = document.getElementById(name);
    var elm1 = elm.cloneNode(false);   
    elm.parentNode.replaceChild(elm1,elm);
}

function nextFocus(element)
{
	if(element != undefined)
	{
		var focusables = $j(":focusable");
		var current = focusables.index(element),
		next = focusables.eq(current + 1).length ? focusables.eq(current + 1) : focusables.eq(0);
		next.focus();
	}
}


function allowIntegerGreaterThanZero(obj)
{     
	var flag	=	true;
	var val = trim(obj.value);
	if(val== null)
	{
			flag	=	false;
			return flag;
	}
	if (val.length == 0)
	{
			flag	=	false;
			return flag;
	}
	if(val <= 0)
	{
		alert("Please Enter Value Greater Than Zero.");
		obj.value="";
		obj.focus();
		flag	=	false;
		return flag;
	}
	else
	{
		for (var i = 0; i < val.length; i++) 
		{
			var ch = val.charAt(i) 
			if (ch < "0" || ch > "9") 
			{           
				alert("Please Enter Only Whole Number.");
				obj.value="";
				obj.focus();
				flag	=	false;
				break;
			}
		}  
		return flag;
	}
}


function allowOnlyFloatGreaterZeroAndLessThanHundred(obj)
{
	var	result	=	true;
	var val = trim(obj.value);
	if (isNaN(val))
	{
		alert("Please Enter Proper Numeric Value.");
		obj.value	=	"";
		obj.focus();
		result	=	false;
	}
	if(parseFloat(val) <= 0)
	{
		alert("Entered Value Should Be Greater Than Zero.");
		obj.value	=	"";
		obj.focus();		
		result	=	false;
	}
	if(parseFloat(val) >= 100)
	{
		alert("Entered Value Should Be Less Than 100.");
		obj.value	=	"";
		obj.focus();		
		result	=	false;
	}
	return result;
}

function allowOnlyPercentageGreaterThanZero(obj)
{
	var	result	=	true;
	var val = trim(obj.value);
	if (isNaN(val))
	{
		alert("Please Enter Proper Numeric Value.");
		obj.value	=	"";
		obj.focus();
		result	=	false;
	}
	if(parseFloat(val) <= 0)
	{
		alert("Entered Value Should Be Greater Than Zero.");
		obj.value	=	"";
		obj.focus();		
		result	=	false;
	}
	if(parseFloat(val) > 100)
	{
		alert("Entered Value Should Be Less Than Or Equal To 100.");
		obj.value	=	"";
		obj.focus();		
		result	=	false;
	}
	return result;
}

function arrowKeyFunctionForTTS(event,ajaxDivId)
{
	if(document.getElementById(ajaxDivId).options.length > 0  )
	{	
		var 	keyvalue		=	event.keyCode;
		var		selectedIndex	=	document.getElementById(ajaxDivId).selectedIndex;		
		if(keyvalue == 40)
		{
			selectedIndex	=	selectedIndex + 1;
			if(selectedIndex < document.getElementById(ajaxDivId).options.length)
			{
				document.getElementById(ajaxDivId).options[document.getElementById(ajaxDivId).selectedIndex+1].selected	=	true;
			}
		}else if(keyvalue == 38)
		{
			if(selectedIndex > 0)
			{			
				document.getElementById(ajaxDivId).options[document.getElementById(ajaxDivId).selectedIndex-1].selected	=	true;	
			}
		}
	}
}

/**
* This method will execute on startup
*/
$j(document).ready(function(){
							
	
	$j(function() {
		$j( ".resizableTextarea" ).resizable({			
			maxHeight: 200,
			maxWidth: 200,
			minHeight: 60,
			minWidth: 200});
	});
	
	
	$j(function() {
				
		$j( ".modalDialog" ).dialog({
				autoOpen: false,			
				height: 450,
				width: 800,
				modal: true,
				create:function(event, ui){	
					$j(this).parent().appendTo("form");
				},
				open : function(event, ui){						
										
					$j(this).parent().focus();
					var el = $j(this).find('select, input, textarea').first();
					
					if(!el.is(":hidden") && !el.is(":disabled"))
					{
						el.focus();
					}
					$j(".typeToSearch").each(function() {
						if(trim($j(this).val()) == "" && !$j(this).is(":focus"))
						{
							$j(this).css("background-image","url(images/tts.png)");
						}else {
							$j(this).css("background-image","none");						
						}
					});	
				}				
		});
		
		
		$j("input[type=text], input[type=password], textarea, select, input[type=file]").each(function() {
			if($j(this).attr("title"))
			{
				$j(this).qtip({ style: { name: 'light', tip: true },show: 'focus',
						hide:'blur',position: {corner: {target: 'rightMiddle',tooltip: 'leftMiddle' }  },adjust : {resize : true } 
				});	
				
				if($j(this).is(":focus"))
				{
					$j(this).focus();
				}
			}
		});
		
		$j(".typeToSearch").each(function() {
			
			$j(this).attr("autocomplete","off");
			$j(this).css("background-repeat","no-repeat");
			if(trim($j(this).val()) == "" )
			{
				$j(this).css("background-image","url(images/tts.png)");			
			}
			if($j(this).is(":focus"))
			{
				$j(this).css("background-image","none");
			}		
			$j(this).keyup(function(e){
				if(trim($j(this).val()) == "" && !$j(this).is(":focus"))
				{
					$j(this).css("background-image","url(images/tts.png)");
				}else {
					$j(this).css("background-image","none");				
				}
			});
			$j(this).focus(function(){
				$j(this).css("background-image","none");	
				
				$j(".typeToSearch").each(function() {
					if(trim($j(this).val()) == "" && !$j(this).is(":focus"))
					{
						$j(this).css("background-image","url(images/tts.png)");
					}else {
						$j(this).css("background-image","none");						
					}
				});	
				
			});
			$j(this).blur(function(){
				if(trim($j(this).val()) == "" )
				{
					$j(this).css("background-image","url(images/tts.png)");
				}
			});				
		});
		
		$j(function() {
			$j( ".datePicker" ).datepicker({dateFormat: 'dd/mm/yy',changeMonth: true,
				changeYear: true,constrainInput: false});
			$j( ".datePicker" ).click(function(){ $j(this).datepicker("show"); });
			$j(".datePicker").keypress(function(event) {event.preventDefault();});
		});
		
		$j(".dtable").each(function() {
			if(!($j(this).parent().hasClass("scrollContainer")))
			{
				$j(this).wrap('<div class="scrollContainer" />');				
			}
		});
		
		if($j.browser.msie){
			
			//$j(".longList").wrap("<div style='width:200px;overflow-x:scroll;'  />");
			$j(".longList").mousedown(function(){
				$j(this).css("width","auto");
				$j(this).css("top",($j(this).position().top-1));
				$j(this).css("position","absolute");
			});
			$j(".longList").change(function(){
				$j(this).css("width","200px");
				$j(this).css("top",($j(this).position().top+1));
				$j(this).css("position","");
			});
			$j(".longList").blur(function(){
				$j(this).css("width","200px");
				$j(this).css("top",($j(this).position().top+1));
				$j(this).css("position","");
			});
		
		}else
		{
			$j(".longList").css("width","200px");
		}
		
		
		$j.extend($j.expr[':'], {
			focusable: function(element) {
				var nodeName = element.nodeName.toLowerCase(),
					tabIndex = $j.attr(element, 'tabindex');
				return (/input|select|textarea|button|object/.test(nodeName) ? !element.disabled : 'a' == nodeName || 'area' == nodeName ? element.href || !isNaN(tabIndex) : !isNaN(tabIndex))
				// the element and all of its ancestors must be visible
				// the browser may report that the area is hidden
				&& !$j(element)['area' == nodeName ? 'parents' : 'closest'](':hidden').length;
			}
		});
		
		$j("body:eq(0)").click(function (e){
			
			if($j.browser.msie) {				
				if(!$j(e.target).is("select") && !$j(e.target).hasClass("ajaxDiv")){
					hideTTS();
				}
				
			}else {
				if(!$j(e.target).is("option") && !$j(e.target).hasClass("ajaxDiv") ){
					hideTTS();
				}
			}
		});
		
		
		
		//document.body.ondblclick= hideTTS;
	
	});
	
});
