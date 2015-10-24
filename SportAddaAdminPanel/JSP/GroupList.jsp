<head>
<%@ page import="java.util.ArrayList,com.inf.dvo.mbm.Bean.*" %>
<jsp:useBean id="cBean" scope="session" class="com.inf.dvo.mbm.Bean.CommonMethodBean"></jsp:useBean>
<%
	String contextPath = request.getContextPath();
	String username	=	(String)session.getAttribute("username");
	if(username==null || (username!=null && username.length()==0))
	{
		response.sendRedirect(contextPath+"/JSP/index.jsp");
	}

	String userProcess	=	(String)session.getAttribute("userProcess");
	String dispname	=	(String)session.getAttribute("dispname");
%>
<%
	ArrayList RARList = (ArrayList)session.getAttribute("RARList");
	
	if(RARList == null)
	{
		RARList = new ArrayList();
	}
%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>..::Welcome to HR Management System::..</title>



<link href="<%=contextPath%>/css/cms.css" rel="stylesheet" type="text/css" />

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/JavaScript">
<!--
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->
</script>

<script language="javascript">history.forward(-1); </script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.min.js"></script>
<link type="text/css" href="<%=request.getContextPath()%>/css/custom-theme/jquery-ui-1.8.21.custom.css" rel="Stylesheet" /> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.8.21.custom.min.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/jquery.qtip-1.0.0-rc3.min.js"></script>
<script>
 var $j = jQuery.noConflict();
</script>

<script language="JavaScript" src="<%=request.getContextPath()%>/js/prototype.js"></script>	
</head>

<body class="bodyCSS">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <th colspan="2" align="left" valign="top" scope="col"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="89">&nbsp;<img src="<%=contextPath%>/images/logo.png" width="296" height="45"></td>
        <td align="center"><img src="<%=contextPath%>/images/cms_image.png" width="269" height="38"></td>
        <td align="right" class="top_menu">Welcome <%=dispname%>  |  <a href="<%=request.getContextPath()%>/login.do?reqCode=logoutProcessing" class="top_menu">Log Out</a>  |  <a href="#" class="top_menu">Options</a>  |  <a href="#" class="top_menu">Help</a></td>
      </tr>
      <tr>
        <td height="33" colspan="3" align="left" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="14%" height="33">&nbsp;</td>
            <td width="73%" background="<%=contextPath%>/images/menu_bg1.jpg"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="14%"><table width="100%" align="center" cellpadding="0" cellspacing="0">
                  <tr>
				  	<td align="center"><a href="<%=contextPath%>/JSP/Home.jsp" class="main_menu">Home</a></td>
				  </tr>
				  </table></td>
                
				 <td width="14%"><table width="100%" align="center" cellpadding="0" cellspacing="0">
                  <tr style="<%=RARList.get(1)%>">
				  <td align="center"><a href="<%=contextPath%>/JSP/MasterMenu.jsp" class="main_menu">Masters</a></td></tr></table></td>
				  
                 <td width="14%"><table width="100%" align="center" cellpadding="0" cellspacing="0">
                  <tr style="<%=RARList.get(20)%>">
				  <td align="center"><a href="<%=contextPath%>/JSP/TranscationMenu.jsp" class="main_menu">HR Activities</a></td>
				  </tr></table></td>
				
                 <td width="14%"><table width="100%" align="center" cellpadding="0" cellspacing="0">
                  <tr style="<%=RARList.get(25)%>">
				  <td align="center"><a href="<%=contextPath%>/JSP/UtilityMenu.jsp" class="main_menu">Utilities</a></td>
				  </tr></table></td>
				
                 <td width="14%"><table width="100%" align="center" cellpadding="0" cellspacing="0">
                  <tr style="<%=RARList.get(29)%>">
				  <td align="center"><a href="#" class="main_menu">Reports</a></td>
				  </tr></table></td>
                
				 <td width="14%"><table width="100%" align="center" cellpadding="0" cellspacing="0">
                  <tr><td align="center"><a href="#" class="main_menu">Help</a></td>
				  </tr></table></td>
                
				 <td width="14%"><table width="100%" align="center" cellpadding="0" cellspacing="0">
                  <tr><td align="center"><a href="<%=request.getContextPath()%>/login.do?reqCode=logoutProcessing" class="main_menu">Log Out</a></td></tr>
				  </table>				  </td>
              </tr>
            </table></td>
            <td width="13%">&nbsp;</td>
          </tr>
        </table></td>
        </tr>
    </table></th>
  </tr>
  
  <tr>
    <th width="2%" height="450" align="left" valign="top" bgcolor="#FFFFFF" scope="col">&nbsp;</th>
    <th width="98%" align="left" valign="top" bgcolor="#FFFFFF" scope="col">	<!--File Name="GroupList.jsp"-->    <script language="JavaScript" src="<%=request.getContextPath()%>/js/GroupMaster.js"></script>    <script language="javascript" src="<%=request.getContextPath()%>/js/common.js"></script>    <%
	String	path		=	request.getContextPath();
	String  saveResult	=	(String)request.getAttribute("saveResult");
	String	msgType		=	(String)request.getAttribute("msgType");
%>    <html:form action="/groupAction" focus="groupName">
<input type="hidden" name="reqCode"/>
<input type="hidden" name="group_id" />
<input type="hidden" name="group_name" />

<html:hidden property="groupId" />

	<table width="100%" border="0" cellspacing="1" cellpadding="1">
		<tr>
        	<td align="left" valign="middle" class="submaintitle">Group List</td>
      	</tr>
		<tr>
		  <td align="center" valign="middle">&nbsp;</td>
		</tr>
		<tr>
        <td align="center" valign="middle">
			<input type="button" id="btnMenu" name="btnMenu" class="buttontext" onClick="return goBackToMasterMenu('<%=path%>')" value="Master"/>		</td>
      </tr>
	  <tr>
		  <td align="center" valign="middle">&nbsp;</td>
	  </tr>
	  <% if(saveResult != null && saveResult.length() >0 )  {%>
	  	<tr>
			<td align="center" valign="middle" class="<%=msgType%>"><%=saveResult%></td>
		</tr>
		<tr>
			<td align="center" valign="middle">&nbsp;</td>
		</tr>
	  <%}%>
	 <tr>
	 	<td align="center" valign="middle">&nbsp;</td>
	 </tr> 
	 <tr>
        <td align="left" valign="middle">
		<table width="100%" border="0" cellspacing="1" cellpadding="1">
		  <tr>
            <td width="43%" align="right" valign="middle" class="normaltext">Group Name</td>
            <td width="2%" align="center" valign="middle">&nbsp;</td>
            <td width="55%" align="left" valign="middle">
				<html:text property="groupName" onkeyup="return getGroupNameList(this);" onkeypress="return checkenter(event);" styleClass="textbox"  />
				<div class="mandetorymsg" id="msgTTS">Type to Search</div>						
				<div id="divGroupName" class="ajaxDiv"></div>			</td>
          </tr>
		  <tr>
		    <td width="43%" align="right" valign="middle" class="normaltext">Data Type</td>
		    <td width="2%" align="center" valign="middle">&nbsp;</td>
		    <td width="55%" align="left" valign="middle" >
			<html:select property="dataType" styleClass="listbox">
					<html:option value="">--SELECT--</html:option>
					<html:option value="NUMERIC">NUMERIC</html:option>
					<html:option value="ALPHANUMERIC">ALPHANUMERIC</html:option>
				</html:select>	
			</td>
		   </tr>
		  <tr>
            <td width="43%" align="right" valign="middle" class="normaltext">Allow Multiple</td>
            <td width="2%" align="center" valign="middle">&nbsp;</td>
            <td width="55%" align="left" valign="middle" >
				<html:select property="allowMultiple" styleClass="listbox">
					<html:option value="">--SELECT--</html:option>
					<html:option value="Y">YES</html:option>
					<html:option value="N">NO</html:option>
				</html:select>			</td>
          </tr>
		  <tr>
            <td width="43%" align="right" valign="middle" class="normaltext">Allow Modify</td>
            <td width="2%" align="center" valign="middle">&nbsp;</td>
            <td width="55%" align="left" valign="middle" >
				<html:select property="allowModify" styleClass="listbox">
					<html:option value="">--SELECT--</html:option>
					<html:option value="Y">YES</html:option>
					<html:option value="N">NO</html:option>
				</html:select>			</td>
          </tr>
          <tr>
            <td width="43%" align="right" valign="middle" class="normaltext">Status</td>
            <td width="2%" align="center" valign="middle">&nbsp;</td>
            <td width="55%" align="left" valign="middle" >
				<html:select property="status" styleClass="listbox">
					<html:option value="">--SELECT--</html:option>
					<html:option value="ACTIVE">ACTIVE</html:option>
					<html:option value="INACTIVE">INACTIVE</html:option>
				</html:select>			</td>
          </tr>
        </table>		
		</td>
      </tr>
	  
	  <tr>
	  	<td align="center" valign="middle">&nbsp;</td>
	  </tr>
	 
	 <tr>
        <td align="center" valign="middle" >
		<input name="btnSearch" type="button" id="btnSearch" class="buttontext" onClick="return getSearchList()" value="Search"/>&nbsp;
		<input name="btnClear" type="button" id="btnClear" class="buttontext" onClick="return clearGroupMasterListForm()" value="Clear"/>&nbsp;
		<input name="btnAddNew" type="button" id="btnAddNew" class="buttontext" onClick="return addNewGroup()" value="Add New"/>
		</td>
	</tr>
	
<tr>
	  	<td align="center" valign="middle">
		<div id="divSearchList">
		
		<logic:notEmpty name="reqGroupMasterList">
		<logic:present name="reqGroupMasterList">
	
		<div style="width:950px;overflow-x:auto;overflow-y:hidden);">
			
		<display:table id="clist" name="requestScope.reqGroupMasterList" requestURI="/groupAction.do" class="dtable" summary="Group List" pagesize="20" style="width:100%;">	
		<bean:define name="clist" type="java.lang.String" property="field2" id="groupId"/>
		<bean:define name="clist" type="java.lang.String" property="field3" id="groupName"/>
		
		<display:column title="#" property="field1" style="width:5%;"/>
		
		<display:column title="Group Name" property="field3" />
		<display:column title="Data Type" property="field4" />
		<display:column title="Allow Multiple" property="field5" />
		<display:column title="Allow Modify" property="field6" />
		<display:column title="Status" property="field7"/>
		<display:column title="Modify" style="width:5%;" >
		<center>
		<a href="javascript:modifyGroup('<%=groupId%>')">
			<img src="<%=request.getContextPath()%>/images/modify_icon.jpg" border="0"/>
		</a>
		</center>
		</display:column>
		
		<display:column title="Delete" style="width:5%;">
		<center>
			<a href="javascript:deleteGroup('<%=groupId%>','<%=groupName%>')">
				<img src="<%=request.getContextPath()%>/images/delete_icon.jpg" border="0" />
			</a>
		</center>
		</display:column>
		
		</display:table>
		
		</div>
			
		</logic:present>
		</logic:notEmpty>
		</div>
		</td>
	  </tr>	
	  			 
	  
	</table>

    </html:form>	</th>
  </tr>
</table>
</body>
</html>
