<!-- InstanceBegin template="/Templates/main.dwt" codeOutsideHTMLIsLocked="false" --><head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<!-- InstanceBeginEditable name="doctitle" -->
<title>Welcome to IDDSHOWROOMSERVER</title>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="head" -->
<!-- InstanceEndEditable -->
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ page import="java.util.*" %>
<%
String path	=	request.getContextPath();
String uname	= (String)session.getAttribute("username");
String dispname	=	(String)session.getAttribute("dispname");
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
        <td width="50%" height="95" align="left" valign="middle"><h2><font color="#999999">&nbsp;&nbsp;ADMIN PANEL</font></h2> </td>
        <td width="50%" align="right" valign="middle"><font color="#0099FF">SPORTS ADDA</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2"><table width="975" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="9" align="left" valign="middle"><img src="<%=path%>/images/menulft-corner.jpg" width="9" height="45" /></td>
            <td align="left" valign="top" background="<%=path%>/images/menu-bg.jpg" >
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >
              <tr>
                <td width="284" height="41" align="center" valign="middle"><span class="normaltext">Welcome <%=dispname%></span></td>
                <td width="107" align="center" valign="middle" ><a href="<%=path%>/JSP/HomePage.jsp" class="sidemenu" >Home</a></td>
                <td width="10" align="center" valign="middle" ><img src="<%=path%>/images/divider.jpg" width="5" height="40" /></td>
                <td width="162" align="center" valign="middle" ><a href="<%=path%>/JSP/MasterMenu.jsp" class="sidemenu" >Masters</a></td>
                <td width="10" align="center" valign="middle" ><img src="<%=path%>/images/divider.jpg" width="5" height="40" /></td>
                <td width="148" align="center" valign="middle" ><a href="#" class="sidemenu" >Transaction</a></td>
                <td width="10" align="center" valign="middle" ><img src="<%=path%>/images/divider.jpg" width="5" height="40" /></td>
				<td width="120" align="center" valign="middle" ><a href="<%=path%>/JSP/UtilityMenu.jsp" class="sidemenu" >Utility</a></td>
				<td width="10" align="center" valign="middle" ><img src="<%=path%>/images/divider.jpg" width="5" height="40" /></td>
                <td width="179" align="center" valign="middle" ><a href="<%=path%>/JSP/logout.jsp" class="sidemenu">Logout</a></td>                
              </tr>
            </table></td>
            <td width="10" align="left" valign="middle"><img src="<%=path%>/images/menulright-corner.jpg" width="9" height="45" /></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td colspan="2"><table width="975" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>            
            <td  align="left" valign="top"><!-- InstanceBeginEditable name="EditRegion4" -->
<script language="JavaScript" src="<%=request.getContextPath()%>/js/prototype.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/common.js"></script>			
<script language="JavaScript" src="<%=request.getContextPath()%>/js/UserMaster.js"></script>			
<%
	String    saveResult	=    (String)request.getAttribute("saveResult");
	String    msgType		=    (String)request.getAttribute("msgType");
	String    actions		=    (String)request.getAttribute("actions");
%>
<html:form action="/userAction" focus="loginName">
<input type="hidden" name="reqCode"/>
<input type="hidden" name="user_id"/>
<input type="hidden" name="user_name" />

<html:hidden property="loginId" />	

	<table width="100%" border="0" cellspacing="1" cellpadding="1">
	
	<tr>
		<td align="left" valign="middle">&nbsp;</td>
	</tr>
	  <tr>
        <td align="left" valign="middle" class="pagetitle">User List</td>
      </tr>
	 
      <tr>
        <td align="center" valign="middle">&nbsp;</td>
      </tr>
      
      <tr>
        <td align="left" valign="middle">&nbsp;</td>
      </tr>
	  <% if (saveResult!=null && saveResult.length() >0){ %>
      <tr>
        <td align="center" valign="middle" class="<%=""+msgType%>"><%=""+saveResult%></td>
      </tr>
      <tr>
        <td align="left" valign="middle">&nbsp;</td>
      </tr>
	  <%}%>

	 <tr>
	 	<td align="left" valign="middle"><table width="100%" border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td width="18%" align="left" valign="middle" class="normaltext">User Name</td>
            <td width="2%" align="center" valign="middle">&nbsp;</td>
            <td width="80%" align="left" valign="middle">
			<html:text property="loginName" onkeyup="return getLoginNameList(this,event);" onkeypress="return checkenter(event);" styleClass="textbox"  />
				<div class="mandatory" id="msgTTS">Type to Search</div>						
				<div id="divLoginName" class="ajaxDiv"></div>
			</td>
          </tr>
		  <tr>
           <td width="18%" align="left" valign="middle" class="normaltext">Status</td>
          <td align="center" valign="middle" class="mandatory">&nbsp;</td>
          <td width="80%" align="left" valign="middle">
		  <html:select property="status" styleClass="list">
			 <html:option value="ACTIVE">ACTIVE</html:option>
			 <html:option value="INACTIVE">INACTIVE </html:option>
			 <html:option value="">ALL</html:option>
          </html:select>		  </td>
          </tr>
        </table></td>
	 </tr>
	  <tr>
        <td align="left" valign="middle">          
          <input name="btnSearch" type="button" id="btnSearch" class="btn" value="Search" onClick="return getSearchList()"/>&nbsp;
          <input name="btnClear" type="button" id="btnClear" class="btn"  value="Clear" onClick="return clearUserListForm()"/>&nbsp;
          <input name="btnAddNew" type="button" id="btnAddNew" class="btn"  value="Add New" onClick="return addNewUser()"/>            
		</td>         
      </tr>
	 
	 <tr>
	 	<td align="left" valign="middle">
		<div id="divListData">
			
		<logic:notEmpty name="reqUserSearchList">
		<logic:present name="reqUserSearchList">
		<display:table style="width:100%;" id="list" name="requestScope.reqUserSearchList"  requestURI="/userAction.do" pagesize="20" summary="User Master List" class="dtable"  export="false">
		
			<bean:define name="list" type="java.lang.String" property="field2" id="userId"/>
			<bean:define name="list" type="java.lang.String" property="field3" id="userName"/>
			
	
			<display:column title="#" property="field1" style="width:5%;"/>
			<display:column title="Login Name" property="field3" />
			<display:column title="Role" property="field6" />
			<display:column title="Status" property="field7"/>
			
			<display:column title="Modify" style="width:5%;" >
			<center>
			<a href="javascript:modifyUserName('<%=userId%>')">
				<img src="<%=request.getContextPath()%>/images/modify_icon.png" border="0"/>
			</a>
			</center>
			</display:column>
			
			<display:column title="Delete" style="width:5%;">
			<center>
				<a href="javascript:deleteUser('<%=userId%>','<%=userName%>')">
					<img src="<%=request.getContextPath()%>/images/delete_icon.png" border="0" />
				</a>
			</center>
			</display:column>

			
			<display:setProperty name="export.excel.filename" value="UserList.xls"/>
			<display:setProperty name="export.pdf.filename" value="UserList.pdf"/>
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel" value="true" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.xml" value="false" />
	
		</display:table>
		</logic:present>
		</logic:notEmpty>
		
		</div>
		</td>
	 </tr> 
    </table>
</html:form>
			
			
			
			
			<!-- InstanceEndEditable --></td>
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

<!-- InstanceEnd --></html>
