<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ page import="java.util.*" %>
<%
String path	=	request.getContextPath();
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>HMS Login</title>
<!-- Javascript goes in the document HEAD -->

<link href="<%=path%>/css/iddshowroomserver.css" rel="stylesheet" type="text/css">

</head>

<%
	String	 loginResult = (String)request.getAttribute("userName");
%>

<body class="bodyStyle" ><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="30" align="left" valign="bottom" bgcolor="#DCDCDC">&nbsp;</td>
      </tr>
      <tr>
        <td height="110" align="center" valign="bottom" bgcolor="#FFFFFF"><h2><font color="#999999">SPORTS ADDA ADMIN PANEL</font></h2></td>
      </tr>
      <tr>
        <td height="33" align="left" valign="middle" bgcolor="#F8F8F8">
		<html:form action="/login.do?reqCode=login" focus="userName" >
		
		<table width="499" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td><img src="<%=path%>/images/submenutopcorner.jpg" width="499" height="17"></td>
          </tr>
          <tr>
            <td align="left" valign="top" background="<%=path%>/images/bg.jpg"><table width="97%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="115"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="top" class="normaltext">Login</td>
                        <td align="left" valign="top" class="normaldata">&nbsp;</td>
                      </tr>
                      <tr>
                        <td height="1" colspan="2" align="center" valign="top" bgcolor="#edeff0"></td>
                      </tr>
                      <tr>
                        <td align="center" valign="top">&nbsp;</td>
                        <td align="left" valign="top" class="normaldata">&nbsp;</td>
                      </tr>
                      <tr>
                        <td width="31%" height="115" align="center" valign="top"><img src="<%=path%>/images/login_image.png" width="89" height="84"></td>
                        <td width="69%" align="left" valign="top"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
						<%if(loginResult!=null && loginResult.length()>0 ){%>
							<tr>
                              <td class="FAIL"><%=loginResult%></td>
                            </tr>
						<%}%>	
                            <tr>
                              <td class="normaltext">User Name</td>
                            </tr>
                            <tr>
                              <td><html:text  property="userName" styleClass="loginTextBox"  value=""/></td>
                            </tr>
                            <tr>
                              <td class="normaltext">&nbsp;</td>
                            </tr>
                            <tr>
                              <td class="normaltext">Password</td>
                            </tr>
                            <tr>
                              <td><html:password property="password" styleClass="loginTextBox" value=""/></td>
                            </tr>
                            <tr>
                              <td>&nbsp;</td>
                            </tr>
                            <tr>
                              <td><input name="submit" type="submit" class="btn"  onclick="return validateLogin()" value="Login"/></td>
                            </tr>
                        </table></td>
                      </tr>
                  </table></td>
                </tr>
            </table></td>
          </tr>
          <tr>
            <td><img src="<%=path%>/images/submenu_Bottomcorner.jpg" width="499" height="17"></td>
          </tr>
          <tr>
            <td height="100" bgcolor="#F8F8F8">&nbsp;</td>
          </tr>
        </table>
		</html:form>
		
		
		</td>
      </tr>
    </table>
  <!-- Table goes in the document BODY -->
  <!--  The table code can be found here: http://www.textfixer/resources/css-tables.php#css-table03 -->
<script language="javascript">
function validateLogin()
{
	var userName = trim(document.LoginForm.userName.value);
	var password = trim(document.LoginForm.password.value);
	if(userName=="")
	{
		alert("Please Enter User Name.");
		document.LoginForm.userName.value = "";
		document.LoginForm.userName.focus();
		return false;
	}
	if(password=="")
	{
		alert("Please Enter Password.");
		document.LoginForm.password.value = "";
		document.LoginForm.password.focus();
		return false;
	}				  
}

function trim(str)
{
	return str.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
}
</script>  
</body>
</html>
