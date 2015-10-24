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

import com.psa.mj.Bean.Master.GroupBean;
import com.psa.mj.DAO.CommonForm;
import com.psa.mj.DAO.Master.GroupForm;
import com.psa.mj.actions.ComplianceKeyDispatchAction;


public class GroupAction extends  ComplianceKeyDispatchAction
{
	
	/**
	 * Action Name 	: showGroupMasterList
	 * Purpose		: To showGroupMasterList
	 * @author		: Mahesh
	 * Date			: 12/03/2012
	 */
	public ActionForward showGroupMasterList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		GroupForm	groupForm	=	(GroupForm)form;
		
		groupForm.reset(mapping, request);
		
		return mapping.findForward("showGroupListPage");
	}
	
	/**
	 * Action Name 	: showSearchGroupMasterList
	 * Purpose		: To showSearchGroupMasterList 
	 * @author		: Mahesh
	 * Date			: 12/03/2012
	 */
	
	public ActionForward showSearchGroupMasterList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		GroupForm	groupFrom	=	(GroupForm)form;
		GroupBean	groupBean	=	new GroupBean();
		
		ArrayList<CommonForm> dataList		=	groupBean.getSearchGroupList(groupFrom);
		if(dataList!= null && dataList.size() > 0)
		{
			request.setAttribute("reqGroupMasterList",dataList);
		}
		else
		{
			request.setAttribute("msgType", "FAIL");
			request.setAttribute("saveResult", "NO DATA FOUND.");
		}
		
		return mapping.findForward("showGroupListPage");
	}
	
	/**
	 * Action Name 	: showGroupMasterPage
	 * Purpose		: To showGroupMasterPage
	 * @author		: Mahesh
	 * Date			: 12/03/2012
	 */
	
	public ActionForward showGroupMasterPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		GroupForm	groupFrom	=	(GroupForm)form;
		groupFrom.reset(mapping, request);
		
		groupFrom.setStatus("ACTIVE");		
		request.setAttribute("actions","add");
		
		return mapping.findForward("showGroupMasterPage");
	}
	
	/**
	 * Action Name 	: saveGroupMaster
	 * Purpose		: To saveGroupMaster
	 * @author		: Mahesh
	 * Date			: 12/03/2012
	 */
	
	public ActionForward saveGroupMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		HttpSession	session		=	request.getSession();
		
		GroupForm	groupForm	=	(GroupForm)form;
		GroupBean	groupBean	=	new GroupBean();
		
		String		mappingString	=	"ERROR";
		String		mode			=	request.getParameter("mode");
		String		userName		=	(String)session.getAttribute("username");
		
		String		saveResult		=	groupBean.saveGroupMaster(groupForm,userName);
		
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
						mappingString	=	"showGroupMasterPage";
						groupForm.reset(mapping, request);
						
						groupForm.setStatus("ACTIVE");
						request.setAttribute("actions","add");
					}
					else if(mode!= null && mode.length() > 0 && mode.equalsIgnoreCase("SAVEEXIT"))
					{
						mappingString	=	"showGroupListPage";
						groupForm.reset(mapping, request);
						
						request.setAttribute("actions","add");
					}
						
				}
				else if(msgType!= null && msgType.length() > 0 && msgType.equals("FAIL"))
				{
					mappingString	=	"showGroupMasterPage";
					request.setAttribute("actions","add");
				}
		}
		return mapping.findForward(mappingString);
	}
	
	
	/**
	 * Action Name 	: showModifyGroupMaster
	 * Purpose		: To showModifyGroupMaster
	 * @author		: Mahesh
	 * Date			: 12/03/2012
	 */
	
	public ActionForward showModifyGroupMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		GroupForm	groupForm	=	(GroupForm)form;
		GroupBean	groupBean	=	new GroupBean();
		
		String		groupId		=	request.getParameter("group_id")!= null && request.getParameter("group_id").length() > 0 ? request.getParameter("group_id") : "";
		
		ArrayList<CommonForm> modifyData	=	groupBean.getDataForModify(groupId);
		if(modifyData!= null && modifyData.size() > 0)
		{
			CommonForm	cForm	=	modifyData.get(0);
			groupForm.setGroupId(cForm.getField1());//groupid
			groupForm.setGroupName(cForm.getField2());//group_name
			groupForm.setDataType(cForm.getField3());//datatype
			groupForm.setAllowMultiple(cForm.getField4());//allow_multiple
			groupForm.setAllowModify(cForm.getField5());//allow_modify
			groupForm.setStatus(cForm.getField6());//status
		}
		
		request.setAttribute("actions","edit");
		
		return mapping.findForward("showGroupMasterPage");
	}
	
	/**
	 * Action Name 	: modifyGroupMaster
	 * Purpose		: To modifyGroupMaster
	 * @author		: Mahesh
	 * Date			: 12/03/2012
	 */
	
	public ActionForward modifyGroupMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		HttpSession	session		=	request.getSession();
		GroupForm	groupForm	=	(GroupForm)form;
		GroupBean	groupBean	=	new GroupBean();
		
		String		mappingString	=	"ERROR";
		String		userName		=	(String)session.getAttribute("username");
		
		String		saveResult		=	groupBean.getModifyGroupMaster(groupForm,userName);
		
		if(saveResult!= null && saveResult.length() > 0 && !saveResult.equalsIgnoreCase("ERROR"))
		{
				String	msgType		=	saveResult.substring(0,4);
				saveResult			=	saveResult.substring(5,saveResult.length());
				
				request.setAttribute("msgType",msgType);
				request.setAttribute("saveResult",saveResult);
				
				if(msgType!= null && msgType.length() > 0 && msgType.equals("PASS"))
				{
					groupForm.reset(mapping, request);
					
					mappingString	=	"showGroupListPage";
				}
				else if(msgType!= null && msgType.length() > 0 && msgType.equals("FAIL"))
				{
					mappingString	=	"showGroupMasterPage";
					request.setAttribute("actions","edit");
				}
		}
		return mapping.findForward(mappingString);
	}
	
	/**
	 * Action Name 	: deleteGroup
	 * Purpose		: To deleteGroup
	 * @author		: Mahesh
	 * Date			: 12/03/2012
	 */
	
	public ActionForward deleteGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		GroupForm	groupForm	=	(GroupForm)form;
		GroupBean	groupBean	=	new GroupBean();
		
		String		mappingString	=	"ERROR";
		
		String		groupId			=	request.getParameter("group_id");
		String		groupName		=	request.getParameter("group_name");
		
		String		saveResult		=	groupBean.deleteGroup(groupId,groupName);
		
		if(saveResult != null && saveResult.length() > 0 && !saveResult.equalsIgnoreCase("ERROR"))
		{
			String	msgType		=	saveResult.substring(0,4);
			saveResult			=	saveResult.substring(5,saveResult.length());
			
			request.setAttribute("msgType",msgType);
			request.setAttribute("saveResult",saveResult);
			
			if(msgType!= null && msgType.length() > 0 && msgType.equals("PASS"))
			{
				groupForm.reset(mapping, request);
				mappingString	=	"showGroupListPage";
				
				ArrayList<CommonForm> dataList		=	groupBean.getSearchGroupList(groupForm);
				if(dataList!= null && dataList.size() > 0)
				{
					request.setAttribute("reqGroupMasterList",dataList);
				}
				
			}
			else if(msgType!= null && msgType.length() > 0 && msgType.equals("FAIL"))
			{
				mappingString	=	"showGroupListPage";
				
				ArrayList<CommonForm> dataList		=	groupBean.getSearchGroupList(groupForm);
				if(dataList!= null && dataList.size() > 0)
				{
					request.setAttribute("reqGroupMasterList",dataList);
				}
			}
			
		}
		return mapping.findForward(mappingString);
	}
	
}
