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
import com.psa.mj.Bean.Master.CountryBean;
import com.psa.mj.DAO.CommonForm;
import com.psa.mj.DAO.Master.CountryForm;
import com.psa.mj.actions.ComplianceKeyDispatchAction;


public class CountryAction extends  ComplianceKeyDispatchAction
{
	
	/**
	 * Action Name 	: showCountryMasterList
	 * Purpose		: To showCountryMasterList
	 * @author		: Prashant
	 * Date			: 07/03/2012
	 */
	public ActionForward showCountryMasterList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		HttpSession			session			  =	request.getSession();
		CommonMethodBean	commonMethodBean  =	new CommonMethodBean();
		CountryForm			countryForm		  =	(CountryForm)form;
		countryForm.reset(mapping, request);
		
		String				rarId		      =	"3";
		String				userProcess		  =	(String)session.getAttribute("userProcess");
		String				validateLink	  = commonMethodBean.getValidOrNot(userProcess, ","+rarId+",");
		if(validateLink != null && validateLink.equalsIgnoreCase("NOTVALID"))
		{
			return mapping.findForward("invalid");
		}
		return mapping.findForward("showCountryListPage");
	}
	
	/**
	 * Action Name 	: showSearchRoleMasterList
	 * Purpose		: To showSearchRoleMasterList 
	 * @author		: Prashant
	 * Date			: 06/03/2012
	 */
	
	public ActionForward showSearchCountryMasterList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		CountryForm		countryForm		=	(CountryForm)form;
		CountryBean		countryBean		=	new CountryBean();
		
		ArrayList<CommonForm> dataList		=	countryBean.getSearchCountryList(countryForm);
		if(dataList!= null && dataList.size() > 0)
		{
			request.setAttribute("reqCountryMasterList",dataList);
		}
		else
		{
			request.setAttribute("msgType", "FAIL");
			request.setAttribute("saveResult", "NO DATA FOUND.");
		}
		return mapping.findForward("showCountryListPage");
	}
	
	/**
	 * Action Name 	: showCountryMasterPage
	 * Purpose		: To showCountryMasterPage
	 * @author		: Prashant
	 * Date			: 07/03/2012
	 */
	
	public ActionForward showCountryMasterPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		CountryForm		countryForm		=	(CountryForm)form;
		countryForm.reset(mapping, request);
		
		countryForm.setStatus("ACTIVE");
		request.setAttribute("actions","add");
		
		return mapping.findForward("showCountryMasterPage");
	}
	
	/**
	 * Action Name 	: saveCountryMaster
	 * Purpose		: To saveCountryMaster
	 * @author		: Prashant
	 * Date			: 07/03/2012
	 */
	
	public ActionForward saveCountryMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		HttpSession	session		=	request.getSession();
		
		CountryForm	countryForm	=	(CountryForm)form;
		CountryBean	countryBean	=	new CountryBean();
		
		String		mappingString	=	"ERROR";
		String		mode			=	request.getParameter("mode");
		String		userName		=	(String)session.getAttribute("username");
		
		String		saveResult		=	countryBean.saveCountryMaster(countryForm,userName);
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
						mappingString	=	"showCountryMasterPage";
						countryForm.reset(mapping, request);
						countryForm.setStatus("ACTIVE");
						request.setAttribute("actions","add");
					}
					else if(mode!= null && mode.length() > 0 && mode.equalsIgnoreCase("SAVEEXIT"))
					{
						mappingString	=	"showCountryListPage";
						countryForm.reset(mapping, request);
						request.setAttribute("actions","add");
					}
						
				}
				else if(msgType!= null && msgType.length() > 0 && msgType.equals("FAIL"))
				{
					mappingString	=	"showCountryMasterPage";
					request.setAttribute("actions","add");
				}
		}
		return mapping.findForward(mappingString);
	}
	
	
	/**
	 * Action Name 	: showModifyCountryMaster
	 * Purpose		: To showModifyCountryMaster
	 * @author		: Prashant
	 * Date			: 07/03/2012
	 */
	
	public ActionForward showModifyCountryMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		
		CountryForm	countryForm	=	(CountryForm)form;
		CountryBean	countryBean	=	new CountryBean();
		
		String		countryId	=	request.getParameter("country_id")!= null && request.getParameter("country_id").length() > 0 ? request.getParameter("country_id") : "";
		
		ArrayList<CommonForm> modifyData	=	countryBean.getDataForModify(countryId);
		if(modifyData!= null && modifyData.size() > 0)
		{
			CommonForm	cForm	=	modifyData.get(0);
			countryForm.setCountryId(cForm.getField1());//country_id
			countryForm.setCountryCode(cForm.getField2());//country_code
			countryForm.setCountryName(cForm.getField3());//countryName
			countryForm.setStatus(cForm.getField4());//status
		}
		
		request.setAttribute("actions","edit");
		
		return mapping.findForward("showCountryMasterPage");
	}
	
	/**
	 * Action Name 	: modifyCountryMaster
	 * Purpose		: To modifyCountryMaster
	 * @author		: Prashant
	 * Date			: 07/03/2012
	 */
	
	public ActionForward modifyCountryMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		HttpSession	session		=	request.getSession();
		
		CountryForm	countryForm	=	(CountryForm)form;
		CountryBean	countryBean	=	new CountryBean();
		
		String		mappingString	=	"ERROR";
		String		userName		=	(String)session.getAttribute("username");
		
		String		saveResult		=	countryBean.getModifyCountryMaster(countryForm,userName);
		
		if(saveResult!= null && saveResult.length() > 0 && !saveResult.equalsIgnoreCase("ERROR"))
		{
				String	msgType		=	saveResult.substring(0,4);
				saveResult			=	saveResult.substring(5,saveResult.length());
				request.setAttribute("msgType",msgType);
				request.setAttribute("saveResult",saveResult);
				
				if(msgType!= null && msgType.length() > 0 && msgType.equals("PASS"))
				{
					countryForm.reset(mapping, request);
					mappingString	=	"showCountryListPage";
				}
				else if(msgType!= null && msgType.length() > 0 && msgType.equals("FAIL"))
				{
					mappingString	=	"showCountryMasterPage";
					request.setAttribute("actions","edit");
				}
		}
		return mapping.findForward(mappingString);
	}
	
	/**
	 * Action Name 	: deleteCountry
	 * Purpose		: To deleteCountry
	 * @author		: Prashant
	 * Date			: 07/03/2012
	 */
	
	public ActionForward deleteCountry(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		CountryForm	countryForm	=	(CountryForm)form;
		CountryBean	countryBean	=	new CountryBean();
		
		String		mappingString	=	"ERROR";
		String		countryId		=	request.getParameter("country_id");	
		String		countryName		=	request.getParameter("country_name");
		
		String		saveResult		=	countryBean.deleteCountry(countryId,countryName);
		
		if(saveResult != null && saveResult.length() > 0 && !saveResult.equalsIgnoreCase("ERROR"))
		{
			String	msgType		=	saveResult.substring(0,4);
			saveResult			=	saveResult.substring(5,saveResult.length());
			request.setAttribute("msgType",msgType);
			request.setAttribute("saveResult",saveResult);
			
			if(msgType!= null && msgType.length() > 0 && msgType.equals("PASS"))
			{
				//countryForm.reset(mapping, request);
				mappingString	=	"showCountryListPage";
				
				ArrayList<CommonForm> dataList		=	countryBean.getSearchCountryList(countryForm);
				if(dataList!= null && dataList.size() > 0)
				{
					request.setAttribute("reqCountryMasterList",dataList);
				}
			}
			else if(msgType!= null && msgType.length() > 0 && msgType.equals("FAIL"))
			{
				mappingString	=	"showCountryListPage";
				
				ArrayList<CommonForm> dataList		=	countryBean.getSearchCountryList(countryForm);
				if(dataList!= null && dataList.size() > 0)
				{
					request.setAttribute("reqCountryMasterList",dataList);
				}
			}
			
		}
		return mapping.findForward(mappingString);
	}
	
}
