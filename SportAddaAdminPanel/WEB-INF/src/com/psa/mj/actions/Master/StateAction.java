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
import com.psa.mj.Bean.Master.StateBean;
import com.psa.mj.DAO.CommonForm;
import com.psa.mj.DAO.Master.StateForm;
import com.psa.mj.actions.ComplianceKeyDispatchAction;


public class StateAction extends  ComplianceKeyDispatchAction
{
	
	/**
	 * Action Name 	: showStateMasterList
	 * Purpose		: To showStateMasterList
	 * @author		: Prashant
	 * Date			: 20/03/2012
	 */
	public ActionForward showStateMasterList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		HttpSession			session			  =	request.getSession();
		CommonMethodBean	commonMethodBean  =	new CommonMethodBean();
		StateForm	stateForm	=	(StateForm)form;
		stateForm.reset(mapping, request);
		String				rarId		      =	"4";
		String				userProcess		  =	(String)session.getAttribute("userProcess");
		String				validateLink	  = commonMethodBean.getValidOrNot(userProcess, ","+rarId+",");
		if(validateLink != null && validateLink.equalsIgnoreCase("NOTVALID"))
		{
			return mapping.findForward("invalid");
		}
		return mapping.findForward("showStateListPage");
	}
	
	/**
	 * Action Name 	: showSearchStateMasterList
	 * Purpose		: To showSearchStateMasterList 
	 * @author		: Prashant
	 * Date			: 20/03/2012
	 */
	
	public ActionForward showSearchStateMasterList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		StateForm		stateForm			=	(StateForm)form;
		StateBean		stateBean			=	new StateBean();
		
		ArrayList<CommonForm> dataList		=	stateBean.getSearchStateList(stateForm);
		if(dataList!= null && dataList.size() > 0)
		{
			request.setAttribute("reqStateMasterList",dataList);
		}
		else
		{
			request.setAttribute("msgType", "FAIL");
			request.setAttribute("saveResult", "NO DATA FOUND.");
		}
		return mapping.findForward("showStateListPage");
	}
	
	/**
	 * Action Name 	: showCityMasterPage
	 * Purpose		: To showCityMasterPage
	 * @author		: Prashant
	 * Date			: 20/03/2012
	 */
	
	public ActionForward showStateMasterPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		StateForm	stateForm		=	(StateForm)form;
		stateForm.reset(mapping, request);
		
		stateForm.setStatus("ACTIVE");		
		request.setAttribute("actions","add");
		
		return mapping.findForward("showStateMasterPage");
	}
	
	/**
	 * Action Name 	: saveStateMaster
	 * Purpose		: To saveStateMaster
	 * @author		: Prashant
	 * Date			: 20/03/2012
	 */
	
	public ActionForward saveStateMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		HttpSession	session		=	request.getSession();
		
		StateForm	stateForm	=	(StateForm)form;
		StateBean	stateBean	=	new StateBean();
		
		String		mappingString	=	"ERROR";
		String		mode			=	request.getParameter("mode");
		String		userName		=	(String)session.getAttribute("username");
		
		String		saveResult		=	stateBean.saveStateMaster(stateForm,userName);
		
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
						mappingString	=	"showStateMasterPage";
						stateForm.reset(mapping, request);
						stateForm.setStatus("ACTIVE");
						request.setAttribute("actions","add");
					}
					else if(mode!= null && mode.length() > 0 && mode.equalsIgnoreCase("SAVEEXIT"))
					{
						mappingString	=	"showStateListPage";
						stateForm.reset(mapping, request);
						request.setAttribute("actions","add");
					}
						
				}
				else if(msgType!= null && msgType.length() > 0 && msgType.equals("FAIL"))
				{
					mappingString	=	"showStateMasterPage";
					request.setAttribute("actions","add");
				}
		}
		return mapping.findForward(mappingString);
	}
	
	
	/**
	 * Action Name 	: showModifyMasterMaster
	 * Purpose		: To showModifyMasterMaster
	 * @author		: Prashant
	 * Date			: 20/03/2012
	 */
	
	public ActionForward showModifyMasterMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		StateForm	stateForm		=	(StateForm)form;
		StateBean	stateBean		=	new StateBean();
		
		String		stateId			=	request.getParameter("state_Id")!= null && request.getParameter("state_Id").length() > 0 ? request.getParameter("state_Id") : "";
		
		ArrayList<CommonForm> modifyData	=	stateBean.getDataForModify(stateId);
		if(modifyData!= null && modifyData.size() > 0)
		{
			CommonForm	cForm	=	modifyData.get(0);
			stateForm.setStateId(cForm.getField1());//stateid
			stateForm.setCountryId(cForm.getField2());//countryid
			stateForm.setCountryName(cForm.getField3());//country_name
			stateForm.setStateCode(cForm.getField4());//state_code
			stateForm.setStateName(cForm.getField5());//state_name
			stateForm.setStatus(cForm.getField6());//status
		}
		
		request.setAttribute("actions","edit");
		
		return mapping.findForward("showStateMasterPage");
	}
	
	/**
	 * Action Name 	: modifyStateMaster
	 * Purpose		: To modifyStateMaster
	 * @author		: Prashant
	 * Date			: 20/03/2012
	 */
	
	public ActionForward modifyStateMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		HttpSession	session		=	request.getSession();
		StateForm	stateFrom	=	(StateForm)form;
		StateBean	stateBean	=	new StateBean();
		
		String		mappingString	=	"ERROR";
		String		userName		=	(String)session.getAttribute("username");
		
		String		saveResult		=	stateBean.getModifyStateMaster(stateFrom,userName);
		
		if(saveResult!= null && saveResult.length() > 0 && !saveResult.equalsIgnoreCase("ERROR"))
		{
				String	msgType		=	saveResult.substring(0,4);
				saveResult			=	saveResult.substring(5,saveResult.length());
				request.setAttribute("msgType",msgType);
				request.setAttribute("saveResult",saveResult);
				
				if(msgType!= null && msgType.length() > 0 && msgType.equals("PASS"))
				{
					stateFrom.reset(mapping, request);
					mappingString	=	"showStateListPage";
				}
				else if(msgType!= null && msgType.length() > 0 && msgType.equals("FAIL"))
				{
					mappingString	=	"showStateMasterPage";
					request.setAttribute("actions","edit");
				}
		}
		return mapping.findForward(mappingString);
	}
	
	/**
	 * Action Name 	: deleteState
	 * Purpose		: To deleteState
	 * @author		: Prashant
	 * Date			: 09/03/2012
	 */
	
	public ActionForward deleteState(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		StateForm	stateForm		=	(StateForm)form;
		StateBean	stateBean		=	new StateBean();
		
		String		mappingString	=	"ERROR";
		
		String		stateId			=	request.getParameter("state_Id");
		String		stateName		=	request.getParameter("state_name");
		
		String		saveResult		=	stateBean.deleteCity(stateId,stateName);
		
		if(saveResult != null && saveResult.length() > 0 && !saveResult.equalsIgnoreCase("ERROR"))
		{
			String	msgType		=	saveResult.substring(0,4);
			saveResult			=	saveResult.substring(5,saveResult.length());
			request.setAttribute("msgType",msgType);
			request.setAttribute("saveResult",saveResult);
			
			if(msgType!= null && msgType.length() > 0 && msgType.equals("PASS"))
			{
				//stateForm.reset(mapping, request);
				mappingString	=	"showStateListPage";
				
				ArrayList<CommonForm> dataList		=	stateBean.getSearchStateList(stateForm);
				if(dataList!= null && dataList.size() > 0)
				{
					request.setAttribute("reqStateMasterList",dataList);
				}
				
			}
			else if(msgType!= null && msgType.length() > 0 && msgType.equals("FAIL"))
			{
				mappingString	=	"showStateListPage";
				
				ArrayList<CommonForm> dataList		=	stateBean.getSearchStateList(stateForm);
				if(dataList!= null && dataList.size() > 0)
				{
					request.setAttribute("reqStateMasterList",dataList);
				}
				
				
			}
			
		}
		return mapping.findForward(mappingString);
	}
	
}
