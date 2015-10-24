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
import com.psa.mj.Bean.Master.UserBean;
import com.psa.mj.DAO.CommonForm;
import com.psa.mj.DAO.Master.UserForm;
import com.psa.mj.actions.ComplianceKeyDispatchAction;


public class UserAction extends  ComplianceKeyDispatchAction
{
	
	/**
	 * Action Name 	: showUserMasterList
	 * Purpose		: To showUserMasterList
	 * @author		: Prashant
	 * Date			: 27/04/2012
	 */
	public ActionForward showUserMasterList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		UserForm	userForm	=	(UserForm)form;
		userForm.reset(mapping, request);
		HttpSession				session				=	request.getSession();
		CommonMethodBean		commonMethodBean	=	new CommonMethodBean();
		String rarId		=	"1";
		String userProcess	=	(String)session.getAttribute("userProcess");
		String validLink	=	commonMethodBean.getValidOrNot(","+userProcess+",", ","+rarId+",");
		if(validLink!=null && validLink.equalsIgnoreCase("NOTVALID"))
		{
			return mapping.findForward("invalid");
		}
		return mapping.findForward("showUserMasterListPage");
	}
	
	/**
	 * Action Name 	: showSearchUserMasterList
	 * Purpose		: To showSearchUserMasterList 
	 * @author		: Prashant
	 * Date			: 27/04/2012
	 */
	
	public ActionForward showSearchUserMasterList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		UserForm	userForm	=	(UserForm)form;
		UserBean	userBean	=	new UserBean();
		
		ArrayList<CommonForm> dataList		=	userBean.getSearchUserList(userForm);
		if(dataList!= null && dataList.size() > 0)
		{
			request.setAttribute("reqUserSearchList",dataList);
		}
		else
		{
			request.setAttribute("msgType", "FAIL");
			request.setAttribute("saveResult", "NO DATA FOUND.");
		}
		return mapping.findForward("showUserMasterListPage");
	}
	
	/**
	 * Action Name 	: showUserMasterPage
	 * Purpose		: To showUserMasterPage
	 * @author		: Prashant
	 * Date			: 27/04/2012
	 */
	
	public ActionForward showUserMasterPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		UserForm	userForm	=	(UserForm)form;
		UserBean	userBean	=	new UserBean();
		userForm.reset(mapping, request);
		
		userForm.setStatus("ACTIVE");
		
		ArrayList<CommonForm> roleList	=	userBean.getRoleList("ACTIVE");
		if(roleList!= null && roleList.size() > 0)
		{
			request.setAttribute("reqRoleList",roleList);
		}
		
		request.setAttribute("actions","add");
		
		return mapping.findForward("showUserMasterPage");
	}
	
	/**
	 * Action Name 	: saveUserMaster
	 * Purpose		: To saveUserMaster
	 * @author		: Prashant
	 * Date			: 27/04/2012
	 */
	
	public ActionForward saveUserMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		HttpSession	session		=	request.getSession();
		UserForm	userForm	=	(UserForm)form;
		UserBean	userBean	=	new UserBean();
		CommonMethodBean		commonMethodBean	=	new CommonMethodBean();
		
		String		mappingString	=	"ERROR";
		String		mode			=	request.getParameter("mode");
		String		userName		=	(String)session.getAttribute("username");
		
		String		saveResult		=	userBean.saveUserMaster(userForm,userName);
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
						mappingString	=	"showUserMasterPage";
						userForm.reset(mapping, request);
						
						userForm.setStatus("ACTIVE");
						
						ArrayList<CommonForm> roleList	=	userBean.getRoleList("ACTIVE");
						if(roleList!= null && roleList.size() > 0)
						{
							request.setAttribute("reqRoleList",roleList);
						}
						
						request.setAttribute("actions","add");
					}
					else if(mode!= null && mode.length() > 0 && mode.equalsIgnoreCase("SAVEEXIT"))
					{
						mappingString	=	"showUserMasterListPage";
						userForm.reset(mapping, request);
						request.setAttribute("actions","add");
					}
						
				}
				else if(msgType!= null && msgType.length() > 0 && msgType.equals("FAIL"))
				{
					mappingString	=	"showUserMasterPage";
					
					ArrayList<CommonForm> roleList	=	userBean.getRoleList("ACTIVE");
					if(roleList!= null && roleList.size() > 0)
					{
						request.setAttribute("reqRoleList",roleList);
					}
					
					request.setAttribute("actions","add");
				}
		}
		return mapping.findForward(mappingString);
	}
	
	
	/**
	 * Action Name 	: showModifyUserMaster
	 * Purpose		: To showModifyUserMaster
	 * @author		: Prashant
	 * Date			: 27/04/2012
	 */
	
	public ActionForward showModifyUserMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		UserForm	userForm	=	(UserForm)form;
		UserBean	userBean	=	new UserBean();
		CommonMethodBean		commonMethodBean	=	new CommonMethodBean();
		
		String		userId		=	request.getParameter("user_id")!= null && request.getParameter("user_id").length() > 0 ? request.getParameter("user_id") : "";
		
		ArrayList<CommonForm> modifyData	=	userBean.getDataForModify(userId);
		if(modifyData!= null && modifyData.size() > 0)
		{
			//umst.userid, umst.user_name, umst.display_name,umst.roleid,umst.employeeid, em.employee_name,umst.status
			CommonForm	cForm	=	modifyData.get(0);
			userForm.setLoginId(cForm.getField1());
			userForm.setLoginName(cForm.getField2());
			userForm.setEmpName(cForm.getField3());
			userForm.setRoleId(cForm.getField4());
			userForm.setStatus(cForm.getField5());
			userForm.setPassword(cForm.getField6());
		}
		
		ArrayList<CommonForm> roleList	=	userBean.getRoleList("ACTIVE");
		if(roleList!= null && roleList.size() > 0)
		{
			request.setAttribute("reqRoleList",roleList);
		}
		
		request.setAttribute("actions","edit");
		
		return mapping.findForward("showUserMasterPage");
	}
	
	/**
	 * Action Name 	: modifyUserMaster
	 * Purpose		: To modifyUserMaster
	 * @author		: Prashant
	 * Date			: 28/04/2012
	 */
	
	public ActionForward modifyUserMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		HttpSession		session			=	request.getSession();
		UserForm		userForm		=	(UserForm)form;
		CommonMethodBean		commonMethodBean	=	new CommonMethodBean();
		UserBean		userBean		=	new UserBean();
		String			mappingString	=	"ERROR";
		String			userName		=	(String)session.getAttribute("username");
		
		String			saveResult		=	userBean.modifyUserMaster(userForm,userName);
		
		if(saveResult!= null && saveResult.length() > 0 && !saveResult.equalsIgnoreCase("ERROR"))
		{
				String	msgType		=	saveResult.substring(0,4);
				saveResult			=	saveResult.substring(5,saveResult.length());
				
				request.setAttribute("msgType",msgType);
				request.setAttribute("saveResult",saveResult);
				
				if(msgType!= null && msgType.length() > 0 && msgType.equals("PASS"))
				{
					userForm.reset(mapping, request);
					mappingString	=	"showUserMasterListPage";
				}
				else if(msgType!= null && msgType.length() > 0 && msgType.equals("FAIL"))
				{
					mappingString	=	"showUserMasterPage";
					
					ArrayList<CommonForm> roleList	=	userBean.getRoleList("ACTIVE");
					if(roleList!= null && roleList.size() > 0)
					{
						request.setAttribute("reqRoleList",roleList);
					}
					
					request.setAttribute("actions","edit");
				}
		}
		return mapping.findForward(mappingString);
	}
	/**
	 * Action Name 	: deleteUserMaster
	 * Purpose		: To deleteUserMaster
	 * @author		: Prashant
	 * Date			: 27/04/2012
	 */
	
	public ActionForward deleteUserMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		UserForm	userForm			=	(UserForm)form;
		UserBean	userBean			=	new UserBean();
		
		String		mappingString		=	"ERROR";
		String		userId				=	request.getParameter("user_id");
		String		userName			=	request.getParameter("user_name");
		
		String		saveResult			=	userBean.deleteUserMaster(userId,userName);
		
		if(saveResult != null && saveResult.length() > 0 && !saveResult.equalsIgnoreCase("ERROR"))
		{
			String	msgType		=	saveResult.substring(0,4);
			saveResult			=	saveResult.substring(5,saveResult.length());
			
			request.setAttribute("msgType",msgType);
			request.setAttribute("saveResult",saveResult);
			
			if(msgType!= null && msgType.length() > 0 && msgType.equals("PASS"))
			{
				mappingString	=	"showUserMasterListPage";
				
				ArrayList<CommonForm> dataList		=	userBean.getSearchUserList(userForm);
				if(dataList!= null && dataList.size() > 0)
				{
					request.setAttribute("reqUserSearchList",dataList);
				}
			}
			else if(msgType!= null && msgType.length() > 0 && msgType.equals("FAIL"))
			{
				mappingString	=	"showUserMasterListPage";
				
				ArrayList<CommonForm> dataList		=	userBean.getSearchUserList(userForm);
				if(dataList!= null && dataList.size() > 0)
				{
					request.setAttribute("reqUserSearchList",dataList);
				}
				
			}
			
		}
		return mapping.findForward(mappingString);
	}
	
}
