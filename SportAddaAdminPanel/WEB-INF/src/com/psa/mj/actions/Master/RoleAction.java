package com.psa.mj.actions.Master;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.psa.mj.Bean.CommonMethodBean;
import com.psa.mj.Bean.Master.RoleBean;
import com.psa.mj.DAO.CommonForm;
import com.psa.mj.DAO.Master.RoleForm;
import com.psa.mj.actions.ComplianceKeyDispatchAction;


public class RoleAction extends  ComplianceKeyDispatchAction
{
	
	/**
	 * Action Name 	: showRoleMasterList
	 * Purpose		: To showRoleMasterList
	 * @author		: Prashant
	 * Date			: 06/03/2012
	 */
	public ActionForward showRoleMasterList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		RoleForm	roleForm	=	(RoleForm)form;
		roleForm.reset(mapping, request);
		HttpSession				session				=	request.getSession();
		CommonMethodBean		commonMethodBean	=	new CommonMethodBean();
		String rarId		=	"2";
		String userProcess	=	(String)session.getAttribute("userProcess");
		String validLink	=	commonMethodBean.getValidOrNot(","+userProcess+",", ","+rarId+",");
		if(validLink!=null && validLink.equalsIgnoreCase("NOTVALID"))
		{
			return mapping.findForward("invalid");
		}
		return mapping.findForward("showRoleMasterListPage");
	}
	
	/**
	 * Action Name 	: showRoleMasterPage
	 * Purpose		: To showRoleMasterPage
	 * @author		: Prashant
	 * Date			: 06/03/2012
	 */
	
	public ActionForward showRoleMasterPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		RoleForm	roleForm	=	(RoleForm)form;
		RoleBean	roleBean	=	new RoleBean();
		String		status		=	"";
		roleForm.reset(mapping, request);
		
		roleForm.setStatus("ACTIVE");
		ArrayList<CommonForm> dataList	=	roleBean.getResponsibilityList(roleForm,"ACTIVE");
		if(dataList!= null && dataList.size() > 0)
		{
			request.setAttribute("reqResponsibilityList",dataList);
		}
		request.setAttribute("actions","add");
		
		return mapping.findForward("showRoleMasterPage");
	}
	
	/**
	 * Action Name 	: saveRoleMaster
	 * Purpose		: To saveRoleMaster
	 * @author		: Prashant
	 * Date			: 06/03/2012
	 */
	
	public ActionForward saveRoleMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		HttpSession	session		=	request.getSession();
		RoleForm	roleForm	=	(RoleForm)form;
		RoleBean	roleBean	=	new RoleBean();
		String		mappingString	=	"ERROR";
		String		mode			=	request.getParameter("mode");
		String		userName		=	(String)session.getAttribute("username");
		
		String		saveResult		=	roleBean.saveRoleMaster(roleForm,userName);
		if(saveResult!= null && saveResult.length() > 0 && !saveResult.equalsIgnoreCase("ERROR"))
		{
				String	msgType		=	saveResult.substring(0,4);
				saveResult			=	saveResult.substring(5,saveResult.length());
				
				request.setAttribute("msgType",msgType);
				request.setAttribute("saveResult",saveResult);
				
				if(msgType!= null && msgType.length() > 0 && msgType.equals("PASS"))
				{
					if(mode!= null && mode.length() > 0 && mode.equalsIgnoreCase("SAVE"))
					{
						mappingString	=	"showRoleMasterPage";
						roleForm.reset(mapping, request);
						
						ArrayList<CommonForm> dataList	=	roleBean.getResponsibilityList(roleForm,"ACTIVE");
						if(dataList!= null && dataList.size() > 0)
						{
							request.setAttribute("reqResponsibilityList",dataList);
						}
						request.setAttribute("actions","add");
					}
					else if(mode!= null && mode.length() > 0 && mode.equalsIgnoreCase("SAVEEXIT"))
					{
						mappingString	=	"showRoleMasterListPage";
						roleForm.reset(mapping, request);
						request.setAttribute("actions","add");
					}
						
				}
				else if(msgType!= null && msgType.length() > 0 && msgType.equals("FAIL"))
				{
					mappingString	=	"showRoleMasterPage";
					ArrayList<CommonForm> dataList	=	roleBean.getResponsibilityList(roleForm,"ACTIVE");
					if(dataList!= null && dataList.size() > 0)
					{
						request.setAttribute("reqResponsibilityList",dataList);
					}
					request.setAttribute("actions","add");
				}
		}
		return mapping.findForward(mappingString);
	}
	
	
	/**
	 * Action Name 	: showModifyRoleMaster
	 * Purpose		: To showModifyRoleMaster
	 * @author		: Prashant
	 * Date			: 06/03/2012
	 */
	
	public ActionForward showModifyRoleMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		RoleForm	roleForm	=	(RoleForm)form;
		RoleBean	roleBean	=	new RoleBean();
		
		String		roleId			=	request.getParameter("role_id")!= null && request.getParameter("role_id").length() > 0 ? request.getParameter("role_id") : "";
		
		ArrayList<CommonForm> modifyData	=	roleBean.getDataForModify(roleId);
		if(modifyData!= null && modifyData.size() > 0)
		{
			CommonForm	cForm	=	modifyData.get(0);
			roleForm.setRoleId(cForm.getField1());//role_id
			roleForm.setRoleName(cForm.getField2());//role_name
			roleForm.setDescription(cForm.getField3());//role_description
			roleForm.setStatus(cForm.getField4());//status
			
			String responsibilityId		=	cForm.getField5();
			if(responsibilityId!= null && responsibilityId.length() > 0)
			{
				String [] chkArray		=	responsibilityId.split(",");
				roleForm.setResponsibilityArray(chkArray);
			}	
		}
		
		ArrayList<CommonForm> dataList	=	roleBean.getResponsibilityList(roleForm,"ACTIVE");
		if(dataList!= null && dataList.size() > 0)
		{
			request.setAttribute("reqResponsibilityList",dataList);
		}
		request.setAttribute("actions","edit");
		
		return mapping.findForward("showRoleMasterPage");
	}
	
	/**
	 * Action Name 	: modifyRoleMaster
	 * Purpose		: To modifyRoleMaster
	 * @author		: Prashant
	 * Date			: 06/03/2012
	 */
	
	public ActionForward modifyRoleMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		HttpSession	session		=	request.getSession();
		RoleForm	roleForm	=	(RoleForm)form;
		RoleBean	roleBean	=	new RoleBean();
		String		mappingString	=	"ERROR";
		String		userName		=	(String)session.getAttribute("username");
		
		String		saveResult		=	roleBean.getModifyRoleMaster(roleForm,userName);
		
		if(saveResult!= null && saveResult.length() > 0 && !saveResult.equalsIgnoreCase("ERROR"))
		{
				String	msgType		=	saveResult.substring(0,4);
				saveResult			=	saveResult.substring(5,saveResult.length());
				request.setAttribute("msgType",msgType);
				request.setAttribute("saveResult",saveResult);
				
				if(msgType!= null && msgType.length() > 0 && msgType.equals("PASS"))
				{
					roleForm.reset(mapping, request);
					mappingString	=	"showRoleMasterListPage";
				}
				else if(msgType!= null && msgType.length() > 0 && msgType.equals("FAIL"))
				{
					mappingString	=	"showRoleMasterPage";
					
					ArrayList<CommonForm> dataList	=	roleBean.getResponsibilityList(roleForm,"ACTIVE");
					if(dataList!= null && dataList.size() > 0)
					{
						request.setAttribute("reqResponsibilityList",dataList);
					}
					
					request.setAttribute("actions","edit");
				}
		}
		return mapping.findForward(mappingString);
	}
	
	/**
	 * Action Name 	: showSearchRoleMasterList
	 * Purpose		: To showSearchRoleMasterList 
	 * @author		: Prashant
	 * Date			: 06/03/2012
	 */
	
	public ActionForward showSearchRoleMasterList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		RoleForm	roleForm	=	(RoleForm)form;
		RoleBean	roleBean	=	new RoleBean();
		
		ArrayList<CommonForm> dataList		=	roleBean.getSearchRoleList(roleForm);
		if(dataList!= null && dataList.size() > 0)
		{
			request.setAttribute("reqSearchList",dataList);
		}
		else
		{
			request.setAttribute("msgType", "FAIL");
			request.setAttribute("saveResult", "NO DATA FOUND.");
		}
		return mapping.findForward("showRoleMasterListPage");
	}
	
}
