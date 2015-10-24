package com.psa.mj.actions.Master;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.psa.mj.Bean.CommonMethodBean;
import com.psa.mj.Bean.Master.CommonMasterBean;
import com.psa.mj.DAO.CommonForm;
import com.psa.mj.DAO.Master.CommonMasterForm;
import com.psa.mj.actions.ComplianceKeyDispatchAction;


public class CommonMasterAction extends  ComplianceKeyDispatchAction
{
	
	/**
	 * Action Name 	: showCommonMasterList
	 * Purpose		: To showCommonMasterList
	 * @author		: Mahesh
	 * Date			: 12/03/2012
	 */
	public ActionForward showCommonMasterList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		HttpSession				session				=	request.getSession();
		CommonMethodBean		commonMethodBean	=	new CommonMethodBean();
		CommonMasterForm	commonForm	=	(CommonMasterForm)form;
		CommonMasterBean	commonBean	=	new CommonMasterBean();
		commonForm.reset(mapping, request);
		
		String rarId		=	"5";
		String userProcess	=	(String)session.getAttribute("userProcess");
		String validLink	=	commonMethodBean.getValidOrNot(","+userProcess+",", ","+rarId+",");
		if(validLink!=null && validLink.equalsIgnoreCase("NOTVALID"))
		{
			return mapping.findForward("invalid");
		}
		
		ArrayList<CommonForm> dataList	=	commonBean.getGroupName();
		if(dataList!= null && dataList.size() > 0)
		{
			request.setAttribute("reqgroupName",dataList);
		}
		
		return mapping.findForward("showCommonListPage");
	}
	
	/**
	 * Action Name 	: showSearchGroupMasterList
	 * Purpose		: To showSearchGroupMasterList 
	 * @author		: Mahesh
	 * Date			: 12/03/2012
	 */
	
	public ActionForward showSearchGroupValueList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		CommonMasterForm	commonMasterForm	=	(CommonMasterForm)form;
		CommonMasterBean	commonMasterBean	=	new CommonMasterBean();
		
		ArrayList<CommonForm> dataList		=	commonMasterBean.getSearchGroupValueList(commonMasterForm);
		if(dataList!= null && dataList.size() > 0)
		{
			request.setAttribute("reqGroupValueMasterList",dataList);
		}
		else
		{
			request.setAttribute("msgType", "FAIL");
			request.setAttribute("saveResult", "NO DATA FOUND.");
		}
		
		ArrayList<CommonForm> groupName		=	commonMasterBean.getGroupName();
		if(groupName!= null && groupName.size() > 0)
		{
			request.setAttribute("reqgroupName",groupName);
		}
		
		return mapping.findForward("showCommonListPage");
	}
	
	/**
	 * Action Name 	: showCommonMasterPage
	 * Purpose		: To showCommonMasterPage
	 * @author		: Mahesh
	 * Date			: 12/03/2012
	 */
	
	public ActionForward showCommonMasterPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		CommonMasterForm	commonMasterForm	=	(CommonMasterForm)form;
		CommonMasterBean	commonBean			=	new CommonMasterBean();
		
		commonMasterForm.reset(mapping, request);
		commonMasterForm.setStatus("ACTIVE");
		
		ArrayList<CommonForm> dataList	=	commonBean.getGroupName();
		if(dataList!= null && dataList.size() > 0)
		{
			request.setAttribute("reqgroupName",dataList);
		}		
		
		request.setAttribute("actions","add");
		
		return mapping.findForward("showCommonMasterPage");
	}
	
	/**
	 * Action Name 	: saveGroupValueMaster
	 * Purpose		: To saveGroupValueMaster
	 * @author		: Mahesh
	 * Date			: 13/03/2012
	 */
	
	public ActionForward saveGroupValueMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		HttpSession	session		=	request.getSession();
		CommonMasterForm	commonMasterForm	=	(CommonMasterForm)form;
		CommonMasterBean	commonBean			=	new CommonMasterBean();
				
		String		mappingString	=	"ERROR";
		String		mode			=	request.getParameter("mode");
		String		userName		=	(String)session.getAttribute("username");
		
		String		saveResult		=	commonBean.saveGroupValueMaster(commonMasterForm,userName);
		
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
						mappingString	=	"showCommonMasterPage";
						commonMasterForm.reset(mapping, request);
						
						ArrayList<CommonForm> dataList	=	commonBean.getGroupName();
						if(dataList!= null && dataList.size() > 0)
						{
							request.setAttribute("reqgroupName",dataList);
						}
						
						commonMasterForm.setStatus("ACTIVE");
						request.setAttribute("actions","add");
					}
					else if(mode!= null && mode.length() > 0 && mode.equalsIgnoreCase("SAVEEXIT"))
					{
						mappingString	=	"showCommonListPage";
						commonMasterForm.reset(mapping, request);
						
						ArrayList<CommonForm> dataList	=	commonBean.getGroupName();
						if(dataList!= null && dataList.size() > 0)
						{
							request.setAttribute("reqgroupName",dataList);
						}
						request.setAttribute("actions","add");
					}
						
				}
				else if(msgType!= null && msgType.length() > 0 && msgType.equals("FAIL"))
				{
					mappingString	=	"showCommonMasterPage";
					
					ArrayList<CommonForm> dataList	=	commonBean.getGroupName();
					if(dataList!= null && dataList.size() > 0)
					{
						request.setAttribute("reqgroupName",dataList);
					}
					
					request.setAttribute("actions","add");
				}
		}
		return mapping.findForward(mappingString);
	}
	
	
	/**
	 * Action Name 	: showModifyGroupMaster
	 * Purpose		: To showModifyGroupMaster
	 * @author		: Mahesh
	 * Date			: 13/03/2012
	 */
	
	public ActionForward showModifyGroupValueMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		CommonMasterForm	commonMasterForm	=	(CommonMasterForm)form;
		CommonMasterBean	commonMasterBean	=	new CommonMasterBean();
	
		String		groupValueId			=	request.getParameter("group_Value_id")!= null && request.getParameter("group_Value_id").length() > 0 ? request.getParameter("group_Value_id") : "";
		
		ArrayList<CommonForm> modifyData	=	commonMasterBean.getDataForModify(groupValueId);
		
		if(modifyData!= null && modifyData.size() > 0)
		{
			
			CommonForm	cForm	=	modifyData.get(0);
			commonMasterForm.setGroupValueId(cForm.getField1());//groupvalueid
			commonMasterForm.setGroupId(cForm.getField2());//groupid
			commonMasterForm.setGroupValue(cForm.getField3());//groupvalue_name
			commonMasterForm.setDescription(cForm.getField4());//description
			commonMasterForm.setStatus(cForm.getField5());//status
			
			commonMasterForm.setDataType(cForm.getField6());//datatype
			commonMasterForm.setAllowMultiple(cForm.getField7());//allow_multiple
			commonMasterForm.setAllowModify(cForm.getField8());//allow_modify
			
		}
		
		ArrayList<CommonForm> dataList	=	commonMasterBean.getGroupName();
		if(dataList!= null && dataList.size() > 0)
		{
			request.setAttribute("reqgroupName",dataList);
		}
		
		request.setAttribute("actions","edit");
		
		return mapping.findForward("showCommonMasterPage");
	}
	
	/**
	 * Action Name 	: modifyGroupValue
	 * Purpose		: To modifyGroupValue
	 * @author		: Mahesh
	 * Date			: 13/03/2012
	 */
	
	public ActionForward modifyGroupValue(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		HttpSession	session		=	request.getSession();
		
		CommonMasterForm	commonMasterForm	=	(CommonMasterForm)form;
		CommonMasterBean	commonMasterBean	=	new CommonMasterBean();
		
		String		mappingString	=	"ERROR";
		String		userName		=	(String)session.getAttribute("username");
		
		String		saveResult		=	commonMasterBean.getModifyGroupValueMaster(commonMasterForm,userName);
		
		if(saveResult!= null && saveResult.length() > 0 && !saveResult.equalsIgnoreCase("ERROR"))
		{
				String	msgType		=	saveResult.substring(0,4);
				saveResult			=	saveResult.substring(5,saveResult.length());
				
				request.setAttribute("msgType",msgType);
				request.setAttribute("saveResult",saveResult);
				
				if(msgType!= null && msgType.length() > 0 && msgType.equals("PASS"))
				{
					commonMasterForm.reset(mapping, request);
					
					ArrayList<CommonForm> dataList	=	commonMasterBean.getGroupName();
					if(dataList!= null && dataList.size() > 0)
					{
						request.setAttribute("reqgroupName",dataList);
					}
					mappingString	=	"showCommonListPage";
					
				}
				else if(msgType!= null && msgType.length() > 0 && msgType.equals("FAIL"))
				{
					mappingString	=	"showCommonMasterPage";
					
					ArrayList<CommonForm> dataList	=	commonMasterBean.getGroupName();
					if(dataList!= null && dataList.size() > 0)
					{
						request.setAttribute("reqgroupName",dataList);
					}
					request.setAttribute("actions","edit");
				}
		}
		return mapping.findForward(mappingString);
	}
	
	/**
	 * Action Name 	: deleteGroupValue
	 * Purpose		: To deleteGroupValue
	 * @author		: Mahesh
	 * Date			: 14/03/2012
	 */
	
	public ActionForward deleteGroupValue(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		CommonMasterForm	commonMasterForm	=	(CommonMasterForm)form;
		CommonMasterBean	commonMasterBean	=	new CommonMasterBean();
		
		String		mappingString	=	"ERROR";
		
		String		groupId			=	request.getParameter("group_Value_id");
		String		groupName		=	request.getParameter("group_value_name");
		
		String		saveResult		=	commonMasterBean.deleteGroupValue(groupId,groupName);
		
		if(saveResult != null && saveResult.length() > 0 && !saveResult.equalsIgnoreCase("ERROR"))
		{
			String	msgType		=	saveResult.substring(0,4);
			saveResult			=	saveResult.substring(5,saveResult.length());
			
			request.setAttribute("msgType",msgType);
			request.setAttribute("saveResult",saveResult);
			
			if(msgType!= null && msgType.length() > 0 && msgType.equals("PASS"))
			{
				//commonMasterForm.reset(mapping, request);
				mappingString	=	"showCommonListPage";
				
				ArrayList<CommonForm> dataList		=	commonMasterBean.getSearchGroupValueList(commonMasterForm);
				if(dataList!= null && dataList.size() > 0)
				{
					request.setAttribute("reqGroupValueMasterList",dataList);
				}
				
				ArrayList<CommonForm> groupNameList		=	commonMasterBean.getGroupName();
				if(groupNameList!= null && groupNameList.size() > 0)
				{
					request.setAttribute("reqgroupName",groupNameList);
				}
				
				
			}
			else if(msgType!= null && msgType.length() > 0 && msgType.equals("FAIL"))
			{
				mappingString	=	"showCommonListPage";
				
				ArrayList<CommonForm> dataList		=	commonMasterBean.getSearchGroupValueList(commonMasterForm);
				if(dataList!= null && dataList.size() > 0)
				{
					request.setAttribute("reqGroupValueMasterList",dataList);
				}
				
				ArrayList<CommonForm> groupNameList		=	commonMasterBean.getGroupName();
				if(groupNameList!= null && groupNameList.size() > 0)
				{
					request.setAttribute("reqgroupName",groupNameList);
				}
			}
			
		}
		return mapping.findForward(mappingString);
	}
	
	
	/**
	 * Action Name 	: getGroupWiseGroupDataType
	 * Purpose		: To getGroupWiseGroupDataType
	 * @author		: Mahesh
	 * Date			: 13/03/2012
	 */
	
	public ActionForward getGroupWiseGroupDataType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		
		CommonMasterBean	commonBean			=	new CommonMasterBean();
		PrintWriter			writer				=	response.getWriter();
		
		String				dataType			=	"";
		String				allowMultiple		=	"";
		String				allowModify			=	"";
		String				outputString		=	"";
		String				groupId		=	request.getParameter("groupId")!= null && request.getParameter("groupId").length() > 0 ? request.getParameter("groupId"): "";
		
		ArrayList<CommonForm>dataList	=	commonBean.getGroupWiseGroupDataType(groupId);
		if(dataList!= null && dataList.size() > 0)
		{
			CommonForm	cForm	=	dataList.get(0);
			dataType			=	cForm.getField2();//datatype
			allowMultiple		=	cForm.getField3();//allow_multiple
			allowModify			=	cForm.getField4();//allow_modify
						
			outputString		=	dataType+"#@#"+allowMultiple+"#@#"+allowModify;
		}
		
		writer.println(outputString);
		
		return mapping.findForward(null);
	}
	
}
