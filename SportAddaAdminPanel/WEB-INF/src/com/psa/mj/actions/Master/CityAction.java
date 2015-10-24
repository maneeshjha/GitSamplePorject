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
import com.psa.mj.Bean.Master.CityBean;
import com.psa.mj.DAO.CommonForm;
import com.psa.mj.DAO.Master.CityForm;
import com.psa.mj.actions.ComplianceKeyDispatchAction;


public class CityAction extends  ComplianceKeyDispatchAction
{
	
	/**
	 * Action Name 	: showCityMasterList
	 * Purpose		: To showCityMasterList
	 * @author		: Prashant
	 * Date			: 09/03/2012
	 */
	public ActionForward showCityMasterList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		HttpSession			session			  =	request.getSession();
		CommonMethodBean	commonMethodBean  =	new CommonMethodBean();
		CityForm	cityForm		=	(CityForm)form;
		cityForm.reset(mapping, request);
		String				rarId		      =	"5";
		String				userProcess		  =	(String)session.getAttribute("userProcess");
		String				validateLink	  = commonMethodBean.getValidOrNot(userProcess, ","+rarId+",");
		if(validateLink != null && validateLink.equalsIgnoreCase("NOTVALID"))
		{
			return mapping.findForward("invalid");
		}
		return mapping.findForward("showCityListPage");
	}
	
	/**
	 * Action Name 	: showSearchCityMasterList
	 * Purpose		: To showSearchCityMasterList 
	 * @author		: Prashant
	 * Date			: 09/03/2012
	 */
	
	public ActionForward showSearchCityMasterList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		CityForm	cityForm		=	(CityForm)form;
		CityBean	cityBean		=	new CityBean();
		ArrayList<CommonForm> dataList		=	cityBean.getSearchCityList(cityForm);
		if(dataList!= null && dataList.size() > 0)
		{
			request.setAttribute("reqCityMasterList",dataList);
		}
		else
		{
			request.setAttribute("msgType", "FAIL");
			request.setAttribute("saveResult", "NO DATA FOUND.");
		}
		return mapping.findForward("showCityListPage");
	}
	
	/**
	 * Action Name 	: showCityMasterPage
	 * Purpose		: To showCityMasterPage
	 * @author		: Prashant
	 * Date			: 09/03/2012
	 */
	
	public ActionForward showCityMasterPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		CityForm	cityForm		=	(CityForm)form;
		cityForm.reset(mapping, request);
		
		cityForm.setStatus("ACTIVE");		
		request.setAttribute("actions","add");
		
		return mapping.findForward("showCityMasterPage");
	}
	
	/**
	 * Action Name 	: saveCityMaster
	 * Purpose		: To saveCityMaster
	 * @author		: Prashant
	 * Date			: 09/03/2012
	 */
	
	public ActionForward saveCityMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		HttpSession	session		=	request.getSession();
		
		CityForm	cityForm		=	(CityForm)form;
		CityBean	cityBean		=	new CityBean();
		
		String		mappingString	=	"ERROR";
		String		mode			=	request.getParameter("mode");
		String		userName		=	(String)session.getAttribute("username");
		
		String		saveResult		=	cityBean.saveCityMaster(cityForm,userName);
		
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
						mappingString	=	"showCityMasterPage";
						cityForm.reset(mapping, request);
						cityForm.setStatus("ACTIVE");
						request.setAttribute("actions","add");
					}
					else if(mode!= null && mode.length() > 0 && mode.equalsIgnoreCase("SAVEEXIT"))
					{
						mappingString	=	"showCityListPage";
						cityForm.reset(mapping, request);
						request.setAttribute("actions","add");
					}
						
				}
				else if(msgType!= null && msgType.length() > 0 && msgType.equals("FAIL"))
				{
					mappingString	=	"showCityMasterPage";
					request.setAttribute("actions","add");
				}
		}
		return mapping.findForward(mappingString);
	}
	
	
	/**
	 * Action Name 	: showModifyCityMaster
	 * Purpose		: To showModifyCityMaster
	 * @author		: Prashant
	 * Date			: 09/03/2012
	 */
	
	public ActionForward showModifyCityMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		CityForm	cityForm	=	(CityForm)form;
		CityBean	cityBean	=	new CityBean();
		
		String		cityId	=	request.getParameter("city_id")!= null && request.getParameter("city_id").length() > 0 ? request.getParameter("city_id") : "";
		
		ArrayList<CommonForm> modifyData	=	cityBean.getDataForModify(cityId);
		if(modifyData!= null && modifyData.size() > 0)
		{
			//cm.cityid,cm.countryid,cnmst.country_name,cm.stateid,sm.state_name,cm.city_name,cm.city_desc,cm.status
			CommonForm	cForm	=	modifyData.get(0);
			cityForm.setCityId(cForm.getField1());
			cityForm.setCountryId(cForm.getField2());
			cityForm.setCountryName(cForm.getField3());
			cityForm.setStateId(cForm.getField4());
			cityForm.setStateName(cForm.getField5());
			cityForm.setCityName(cForm.getField6());
			cityForm.setCityDesc(cForm.getField7());
			cityForm.setStatus(cForm.getField8());
			
		}
		
		request.setAttribute("actions","edit");
		
		return mapping.findForward("showCityMasterPage");
	}
	
	/**
	 * Action Name 	: modifyCityMaster
	 * Purpose		: To modifyCityMaster
	 * @author		: Prashant
	 * Date			: 09/03/2012
	 */
	
	public ActionForward modifyCityMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		HttpSession	session		=	request.getSession();
		CityForm	cityForm	=	(CityForm)form;
		CityBean	cityBean	=	new CityBean();
		
		String		mappingString	=	"ERROR";
		String		userName		=	(String)session.getAttribute("username");
		
		String		saveResult		=	cityBean.getModifyCityMaster(cityForm,userName);
		
		if(saveResult!= null && saveResult.length() > 0 && !saveResult.equalsIgnoreCase("ERROR"))
		{
				String	msgType		=	saveResult.substring(0,4);
				saveResult			=	saveResult.substring(5,saveResult.length());
				request.setAttribute("msgType",msgType);
				request.setAttribute("saveResult",saveResult);
				
				if(msgType!= null && msgType.length() > 0 && msgType.equals("PASS"))
				{
					cityForm.reset(mapping, request);
					mappingString	=	"showCityListPage";
				}
				else if(msgType!= null && msgType.length() > 0 && msgType.equals("FAIL"))
				{
					mappingString	=	"showCityMasterPage";
					request.setAttribute("actions","edit");
				}
		}
		return mapping.findForward(mappingString);
	}
	
	/**
	 * Action Name 	: deleteCity
	 * Purpose		: To deleteCity
	 * @author		: Prashant
	 * Date			: 09/03/2012
	 */
	
	public ActionForward deleteCity(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		CityForm	cityForm	=	(CityForm)form;
		CityBean	cityBean	=	new CityBean();
		
		String		mappingString	=	"ERROR";
		
		String		cityId			=	request.getParameter("city_id");
		String		cityName		=	request.getParameter("city_name");
		
		String		saveResult		=	cityBean.deleteCity(cityId,cityName);
		
		if(saveResult != null && saveResult.length() > 0 && !saveResult.equalsIgnoreCase("ERROR"))
		{
			String	msgType		=	saveResult.substring(0,4);
			saveResult			=	saveResult.substring(5,saveResult.length());
			request.setAttribute("msgType",msgType);
			request.setAttribute("saveResult",saveResult);
			
			if(msgType!= null && msgType.length() > 0 && msgType.equals("PASS"))
			{
				//cityForm.reset(mapping, request);
				mappingString	=	"showCityListPage";
				
				ArrayList<CommonForm> dataList		=	cityBean.getSearchCityList(cityForm);
				if(dataList!= null && dataList.size() > 0)
				{
					request.setAttribute("reqCityMasterList",dataList);
				}
			}
			else if(msgType!= null && msgType.length() > 0 && msgType.equals("FAIL"))
			{
				mappingString	=	"showCityListPage";
				
				ArrayList<CommonForm> dataList		=	cityBean.getSearchCityList(cityForm);
				if(dataList!= null && dataList.size() > 0)
				{
					request.setAttribute("reqCityMasterList",dataList);
				}
				
			}
			
		}
		return mapping.findForward(mappingString);
	}
	
}
