<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title>Welcome to IDDSHOWROOMSERVER</title>



<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ page import="java.util.*" %>
<%
String path	=	request.getContextPath();
String uname	= (String)session.getAttribute("username");
if (uname==null)
{
	response.sendRedirect(path+"/JSP/index.jsp"); 
}
%>
<%
	
%>

<style>
.menubg{background:url(<%=path%>/images/manu_bgtext.jpg) no-repeat;}
.transparent {
filter:alpha(opacity=50); /* for IE4 - IE7 */
-ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=80)"; /* IE8 */
-moz-opacity:0.5;
-khtml-opacity: 0.5;
opacity: 0.5;
}
</style>


<link href="<%=path%>/css/iddshowroomserver.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.min.js"></script>
<link type="text/css" href="<%=request.getContextPath()%>/css/customTheme/jquery-ui-1.8.21.custom.css" rel="Stylesheet" /> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.8.21.custom.min.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/jquery.qtip-1.0.0-rc3.min.js"></script>
<script>
 var $j = jQuery.noConflict();
</script>

<script type="text/JavaScript">
<!--
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->

function serviceDeskClicked() {

	$j( "<img src='<%=request.getContextPath()%>/images/serviceDesk.png' />" ).dialog({			
			height: 299,
			width: 493,			
			modal: true	,
			resizable: false,
			create: function (event, ui) {
				$j(".ui-widget-header").hide();
				
				$j(this).parent().css("border-style","none");
				$j(this).parent().css("background-color","transparent");
				$j(this).parent().css("background-image","none");
				$j(this).parent().css("padding","0");
				
			}					
            	
		}).click(function () { 
			$j( this ).dialog( "close" );
		});
		
}
function aboutSupportClicked() {

	$j( "<img src='<%=request.getContextPath()%>/images/aboutSupport.png' />" ).dialog({			
			height: 299,
			width: 493,			
			modal: true	,
			resizable: false,
			create: function (event, ui) {
				$j(".ui-widget-header").hide();
				
				$j(this).parent().css("border-style","none");
				$j(this).parent().css("background-color","transparent");
				$j(this).parent().css("background-image","none");
				$j(this).parent().css("padding","0");
				
			}
				
		}).click(function () { 
			$j( this ).dialog( "close" );
		});
}
</script>

</head>

<body class="bodyStyle" >
<table width="1011" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="22">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="<%=path%>/images/top-corner.jpg" width="1011" height="28" /></td>
  </tr>
  <tr>
    <td height="650" align="left" valign="top" background="<%=path%>/images/page-bg.jpg"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="50%" height="95" align="left" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=path%>/images/ecomlogo.jpg" width="167" height="76"> </td>
        <td width="50%" align="right" valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2"><table width="975" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="206" align="left" valign="top">&nbsp;</td>
            <td width="746" align="left" valign="top">
			<!--	file name:-Intermediate.jsp-->
			
			
			
			<html:form action="/login" focus="companyName">
			  <input type="hidden" name="reqCode" />
			  
			  <table width="100%" border="0" cellspacing="1" cellpadding="1">
			    <tr>
			      <td>&nbsp;</td>
                  </tr>
			    <tr>
			      <td>&nbsp;</td>
                  </tr>
			    <tr>
			      <td>&nbsp;</td>
                  </tr>
			    <tr>
			      <td>
			        
			        <table width="75%" border="0" align="center" cellpadding="3" cellspacing="3">
			          <tr>
			            <td width="25%" align="right" valign="middle" class="normaltext">Company</td>
                   		      <td width="75%" align="left" valign="middle">
                   		        <html:select property="companyName" styleClass="list" >
                   		          <html:option value="">--SELECT--</html:option>
                   		          <html:option value="DDPL - AA Division">DDPL - AA Division</html:option>
                   		          <html:option value="DDE - SA Division"> DDE - SA Division</html:option>
           		              </html:select>					 	 </td>
                      </tr>
			          <tr>
			            <td align="left" valign="middle" class="normaltext">&nbsp;</td>
			            <td align="left" valign="middle"><input type="button"  name="btnProceed" id="btnProceed" class="btn"  onClick="return showHomePage()" value="Proceed"/></td>
			            </tr>
			          </table>				  </td>
                  </tr>
			    <tr>
			      <td align="center" valign="middle">&nbsp;</td>
                  </tr>
			    <tr>
			      <td align="center" valign="middle">&nbsp;</td>
                  </tr>
			    <tr>
			      <td>&nbsp;</td>
                  </tr>
			    <tr>
			      <td>&nbsp;</td>
                  </tr>
			    <tr>
			      <td>&nbsp;</td>
                  </tr>
			    <tr>
			      <td>&nbsp;</td>
                  </tr>
			    <tr>
			      <td>&nbsp;</td>
                  </tr>
			    </table>
			    </html:form>
			  <script type="text/javascript">
			  
			  		function showHomePage()
					{
						var companyName	=	document.LoginForm.companyName.value;
						
						if(companyName=="")
						{
							alert("Please Select Company ");
							document.LoginForm.companyName.focus();
							return false;
						}
						
						document.LoginForm.reqCode.value			=	"showHomePage";
						document.LoginForm.btnProceed.disabled		=	true;
						document.LoginForm.submit();
						
					}
			  
			  </script>            </td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td colspan="2"></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><img src="<%=path%>/images/footer.jpg" width="1011" height="80" /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
</body>

</html>
