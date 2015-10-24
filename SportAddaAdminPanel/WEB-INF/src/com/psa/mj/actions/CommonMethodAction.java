package com.psa.mj.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.util.JSONStringer;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.psa.mj.Bean.CommonMethodBean;
import com.psa.mj.DAO.CommonForm;


public class CommonMethodAction extends ComplianceKeyDispatchAction
{
	
	/**
	 * Action Name 	: getRoleNameList
	 * Purpose		: To Get Role Name List .trim().toUpperCase()
	 * @author		: Prashant
	 * Date			: 06/03/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getStatusWiseRoleName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				roleName			=	request.getParameter("roleName")!= null && request.getParameter("roleName").length() > 0 ? request.getParameter("roleName").trim().toUpperCase() : "";
		String				status				=	request.getParameter("status")!= null && request.getParameter("status").length() > 0 ? request.getParameter("status") : "";
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getRoleNameList(roleName,status);
		try
		{

			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select name='ajaxRoleNameID' id='ajaxRoleNameID' size='5' ondblclick=changeRoleNameVal() onkeypress=checkRoleName(event) class='multilist'>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getShadeNameList: "+e); 
		}
		return mapping.findForward(null);
	}
	/**
	 * Action Name 	: getStatusWiseCountryName
	 * Purpose		: To getStatusWiseCountryName
	 * @author		: Prashant
	 * Date			: 07/03/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getStatusWiseCountryName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				countryName			=	request.getParameter("countryName")!= null && request.getParameter("countryName").length() > 0 ? request.getParameter("countryName").trim().toUpperCase() : "";
		String				status				=	request.getParameter("status")!= null && request.getParameter("status").length() > 0 ? request.getParameter("status").trim().toUpperCase() : "";
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getCountryNameList(countryName,status);
		try
		{
			
			
			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select id='ajaxCountryNameID' id='ajaxCountryNameID' size='5' ondblclick=changeCountryNameVal() onkeypress=checkCountryName(event) class='multilist'>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getStatusWiseCountryName: "+e); 
		}
		return mapping.findForward(null);
	}	
	
	/**
	 * Action Name 	: getStatusWiseDeaprtmentName
	 * Purpose		: To getStatusWiseDeaprtmentName
	 * @author		: Prashant
	 * Date			: 09/03/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getStatusWiseDeaprtmentName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				departmentName		=	request.getParameter("departmentName")!= null && request.getParameter("departmentName").length() > 0 ? request.getParameter("departmentName").trim().toUpperCase() : "";
		String				status				=	request.getParameter("status")!= null && request.getParameter("status").length() > 0 ? request.getParameter("status").trim().toUpperCase() : "";
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getDepartmentNameList(departmentName,status);
		try
		{
			
			
			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select id='ajaxDepartmentNameID' id='ajaxDepartmentNameID' size='5' ondblclick=changeDeaprtmentNameVal() onkeypress=checkDepartmentName(event) class='multilist'>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getStatusWiseDeaprtmentName: "+e); 
		}
		return mapping.findForward(null);
	}
	
	/**
	 * Action Name 	: getStatusWiseCityName
	 * Purpose		: To getStatusWiseCityName
	 * @author		: Prashant
	 * Date			: 09/03/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getStatusWiseCityName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				cityName			=	request.getParameter("cityName")!= null && request.getParameter("cityName").length() > 0 ? request.getParameter("cityName").trim().toUpperCase() : "";
		String				status				=	request.getParameter("status")!= null && request.getParameter("status").length() > 0 ? request.getParameter("status").trim().toUpperCase() : "";
		String				countryId			=	request.getParameter("countryId")!= null && request.getParameter("countryId").length() > 0 ? request.getParameter("countryId") : "";
		String				stateId				=	request.getParameter("stateId")!= null && request.getParameter("stateId").length() > 0 ? request.getParameter("stateId") : "";
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getCityNameList(cityName,status,countryId,stateId);
		try
		{
			
			
			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select id='ajaxCityNameID' id='ajaxCityNameID' size='5' ondblclick=changeCityNameVal() onkeypress=checkCityName(event) class='multilist'>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getStatusWiseDeaprtmentName: "+e); 
		}
		return mapping.findForward(null);
	}
	
	/**
	 * Action Name 	: getStatusWiseLeaveType
	 * Purpose		: To getStatusWiseLeaveType
	 * @author		: Prashant
	 * Date			: 12/03/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getStatusWiseLeaveType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				leaveType			=	request.getParameter("leaveType")!= null && request.getParameter("leaveType").length() > 0 ? request.getParameter("leaveType").trim().toUpperCase() : "";
		String				status				=	request.getParameter("status")!= null && request.getParameter("status").length() > 0 ? request.getParameter("status").trim().toUpperCase() : "";
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getLeaveTypeList(leaveType,status);
		try
		{
			
			
			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select id='ajaxLeaveTypeID' id='ajaxLeaveTypeID' size='5' ondblclick=changeLeaveTypeVal() onkeypress=checkLeaveType(event) class='multilist'>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getStatusWiseLeaveType : "+e); 
		}
		return mapping.findForward(null);
	}
	
	/**
	 * Action Name 	: getStatusWiseGroupName
	 * Purpose		: To getStatusWiseGroupName
	 * @author		: Prashant
	 * Date			: 13/03/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getStatusWiseGroupName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				groupName			=	request.getParameter("groupName")!= null && request.getParameter("groupName").length() > 0 ? request.getParameter("groupName").trim().toUpperCase() : "";
		String				status				=	request.getParameter("status")!= null && request.getParameter("status").length() > 0 ? request.getParameter("status").trim().toUpperCase() : "";
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getGroupNameList(groupName,status);
		try
		{
			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select id='ajaxGroupNameID' id='ajaxGroupNameID' size='5' ondblclick=changeGroupNameVal() onkeypress=checkGroupName(event) class='multilist'>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getStatusWiseGroupName : "+e); 
		}
		return mapping.findForward(null);
	}
	
	/**
	 * Action Name 	: getStatusWiseGroupValue
	 * Purpose		: To getStatusWiseGroupValue
	 * @author		: Prashant
	 * Date			: 14/03/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getStatusWiseGroupValue(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				groupValueName		=	request.getParameter("groupValueName")!= null && request.getParameter("groupValueName").length() > 0 ? request.getParameter("groupValueName").trim().toUpperCase() : "";
		String				groupId				=	request.getParameter("groupId");
		String				status				=	request.getParameter("status")!= null && request.getParameter("status").length() > 0 ? request.getParameter("status").trim().toUpperCase() : "";
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getGroupValueNameList(groupValueName,groupId,status);
		try
		{
			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select name='ajaxGroupValueNameID' id='ajaxGroupValueNameID' size='5' ondblclick=changeGroupValueNameVal() onkeypress=checkGroupValue(event) class='multilist'>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getStatusWiseGroupName : "+e); 
		}
		return mapping.findForward(null);
	}
	
	/**
	 * Action Name 	: getStatusWiseEmployeeName
	 * Purpose		: To getStatusWiseEmployeeName
	 * @author		: Prashant
	 * Date			: 19/03/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getStatusWiseEmployeeName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				empName				=	request.getParameter("empName")!= null && request.getParameter("empName").length() > 0 ? request.getParameter("empName").trim().toUpperCase() : "";
		String				status				=	request.getParameter("status")!= null && request.getParameter("status").length() > 0 ? request.getParameter("status").trim().toUpperCase() : "";
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getEmployeeNameList(empName,status);
		try
		{
			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select name='ajaxEmployeeNameID' id='ajaxEmployeeNameID' size='5' ondblclick=changeEmployeeNameVal() onkeypress=checkEmployeeValue(event) class='multilist'>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getStatusWiseEmployeeName : "+e); 
		}
		return mapping.findForward(null);
	}
	
	/**
	 * Action Name 	: getStatusWiseStateName
	 * Purpose		: To getStatusWiseStateName
	 * @author		: Prashant
	 * Date			: 20/03/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getStatusWiseStateName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				stateName			=	request.getParameter("stateName")!= null && request.getParameter("stateName").length() > 0 ? request.getParameter("stateName").trim().toUpperCase() : "";
		String				status				=	request.getParameter("status")!= null && request.getParameter("status").length() > 0 ? request.getParameter("status").trim().toUpperCase() : "";
		String				countryId			=	request.getParameter("countryId")!= null && request.getParameter("countryId").length() > 0 ? request.getParameter("countryId") : "";
		
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getStatusWiseStateName(stateName,status,countryId);
		try
		{
			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select name='ajaxStateNameID' id='ajaxStateNameID' size='5' ondblclick=changeStateNameVal() onkeypress=checkStateValue(event) class='multilist'>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getStatusWiseStateName : "+e); 
		}
		return mapping.findForward(null);
	}
	
	/**
	 * Action Name 	: getStatusWiseBankName
	 * Purpose		: To getStatusWiseBankName
	 * @author		: Prashant
	 * Date			: 21/03/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getStatusWiseBankName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				bankName			=	request.getParameter("bankName")!= null && request.getParameter("bankName").length() > 0 ? request.getParameter("bankName").trim().toUpperCase() : "";
		String				status				=	request.getParameter("status")!= null && request.getParameter("status").length() > 0 ? request.getParameter("status").trim().toUpperCase() : "";
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getStatusWiseBankName(bankName,status);
		try
		{
			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select name='ajaxBankNameID' id='ajaxBankNameID' size='5' ondblclick=changeBankNameVal() onkeypress=checkBankValue(event) class='multilist'>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getStatusWiseBankName : "+e); 
		}
		return mapping.findForward(null);
	}
	
	/**
	 * Action Name 	: getStatusWiseBranchName
	 * Purpose		: To getStatusWiseBranchName
	 * @author		: Prashant
	 * Date			: 21/03/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getStatusWiseBranchName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				branchName			=	request.getParameter("branchName")!= null && request.getParameter("branchName").length() > 0 ? request.getParameter("branchName").trim().toUpperCase() : "";
		String				status				=	request.getParameter("status")!= null && request.getParameter("status").length() > 0 ? request.getParameter("status").trim().toUpperCase() : "";
		String				bankId				=	request.getParameter("bankId")!= null && request.getParameter("bankId").length() > 0 ? request.getParameter("bankId").trim().toUpperCase() : "";
		
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getStatusWiseBranchName(branchName,status,bankId);
		try
		{
			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select name='ajaxBranchNameID' id='ajaxBranchNameID' size='5' ondblclick=changeBranchNameVal() onkeypress=checkBranchValue(event) class='multilist'>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getStatusWiseBankName : "+e); 
		}
		return mapping.findForward(null);
	}
	
	
	/**
	 * Action Name 	: getBankIdWiseBranchName
	 * Purpose		: To getBankIdWiseBranchName
	 * @author		: Prashant
	 * Date			: 26/03/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getBankIdWiseBranchName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String				bankId				=	request.getParameter("bankId")!= null && request.getParameter("bankId").length() > 0 ? request.getParameter("bankId").trim().toUpperCase() : "";
		String				mode				=	request.getParameter("mode")!= null && request.getParameter("mode").length() > 0 ? request.getParameter("mode").trim().toUpperCase() : "";
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getModeWiseBankIdWiseBranchName(bankId,mode);
		try
		{
			outResult	+=	"<select id='bankBranchId' name ='bankBranchId' class='listbox'>";
			outResult	+=	"<option value=''>--SELECT--</option>";
			if(dataList!= null && dataList.size() > 0)
			{
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				
			}
			outResult	+=	"</select>";
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getStatusWiseBankName : "+e); 
		}
		return mapping.findForward(null);
	}
	
	/**
	 * Action Name 	: getHeadNameList
	 * Purpose		: To getHeadNameList
	 * @author		: Prashant
	 * Date			: 29/03/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getHeadNameList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				headName			=	request.getParameter("headName")!= null && request.getParameter("headName").length() > 0 ? request.getParameter("headName").trim().toUpperCase() : "";
		
		
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getHeadNameList(headName);
		try
		{
			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select name='ajaxHeadNameID' id='ajaxHeadNameID' size='5' ondblclick=changeHeadNameVal() onkeypress=checkHeadValue(event) class='multilist'>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getHeadNameList : "+e); 
		}
		return mapping.findForward(null);
	}
	
	/**
	 * Action Name 	: getSrtuctureNameList
	 * Purpose		: To getSrtuctureNameList
	 * @author		: Prashant
	 * Date			: 05/04/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getSrtuctureNameList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				structName			=	request.getParameter("structName")!= null && request.getParameter("structName").length() > 0 ? request.getParameter("structName").trim().toUpperCase() : "";
		
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getStructureNameList(structName);
		try
		{
			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select name='ajaxStructNameID' id='ajaxStructNameID' size='5' ondblclick=changeStructNameVal() onkeypress=checkStructValue(event) class='multilist'>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getSrtuctureNameList : "+e); 
		}
		return mapping.findForward(null);
	}
	
	/**
	 * Action Name 	: getStatusWiseCompanyName
	 * Purpose		: To getStatusWiseCompanyName
	 * @author		: Prashant
	 * Date			: 20/04/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getStatusWiseCompanyName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				companyName			=	request.getParameter("companyName")!= null && request.getParameter("companyName").length() > 0 ? request.getParameter("companyName").trim().toUpperCase() : "";
		String				status				=	request.getParameter("status")!= null && request.getParameter("status").length() > 0 ? request.getParameter("status").trim().toUpperCase() : "";
		
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getStatusWiseCompanyName(companyName,status);
		try
		{
			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select name='ajaxCompanyNameID' id='ajaxCompanyNameID' size='5' ondblclick=changeCompanyNameVal() onkeypress=checkCompanyValue(event) class='multilist'>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getStatusWiseCompanyName : "+e); 
		}
		return mapping.findForward(null);
	}
	
	/**
	 * Action Name 	: getStatusWiseLoginName
	 * Purpose		: To getStatusWiseLoginName
	 * @author		: Prashant
	 * Date			: 27/04/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getStatusWiseLoginName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				loginName			=	request.getParameter("loginName")!= null && request.getParameter("loginName").length() > 0 ? request.getParameter("loginName").trim().toUpperCase() : "";
		String				status				=	request.getParameter("status")!= null && request.getParameter("status").length() > 0 ? request.getParameter("status").trim().toUpperCase() : "";
		
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getStatusWiseLoginName(loginName,status);
		try
		{
			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select name='ajaxLoginNameID' id='ajaxLoginNameID' size='5' ondblclick=changeLoginNameVal() onkeypress=checkLoginValue(event) class='multilist'>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getStatusWiseLoginName : "+e); 
		}
		return mapping.findForward(null);
	}
	
	/**
	 * Action Name 	: getStatusWiseDocumentName
	 * Purpose		: To getStatusWiseDocumentName
	 * @author		: Prashant
	 * Date			: 03/05/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getStatusWiseDocumentName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				docName				=	request.getParameter("docName")!= null && request.getParameter("docName").length() > 0 ? request.getParameter("docName").trim().toUpperCase() : "";
		String				status				=	request.getParameter("status")!= null && request.getParameter("status").length() > 0 ? request.getParameter("status").trim().toUpperCase() : "";
		String				empId				=	request.getParameter("empId")!=null && request.getParameter("empId").length() > 0 ? request.getParameter("empId") : "";
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getStatusWiseDocumentName(docName,status,empId);
		try
		{
			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select name='ajaxDocumentId' id='ajaxDocumentId' size='5' ondblclick=changeDocumentNameVal() onkeypress=checkDocumentVal(event) class='multilist'>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getStatusWiseDocumentName : "+e); 
		}
		return mapping.findForward(null);
	}
	
	/**
	 * Action Name 	: getStatusWiseDocumentName
	 * Purpose		: To getStatusWiseDocumentName
	 * @author		: Prashant
	 * Date			: 03/05/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getStatusWiseProjectName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				projectName			=	request.getParameter("projectName")!= null && request.getParameter("projectName").length() > 0 ? request.getParameter("projectName").trim().toUpperCase() : "";
		String				status				=	request.getParameter("status")!= null && request.getParameter("status").length() > 0 ? request.getParameter("status").trim().toUpperCase() : "";
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getStatusWiseProjectName(projectName,status);
		try
		{
			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select name='ajaxProjectId' id='ajaxProjectId' size='5' ondblclick=changeProjectNameVal() onkeypress=checkProjectVal(event) class='multilist'>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getStatusWiseProjectName : "+e); 
		}
		return mapping.findForward(null);
	}
	
	/**
	 * Action Name 	: getprojectWiseStatusWiseClient
	 * Purpose		: To getprojectWiseStatusWiseClient
	 * @author		: Gaurav
	 * Date			: 17/09/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getprojectWiseStatusWiseClient(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean		commonMethodBean	=	new CommonMethodBean();
		PrintWriter				writer				=	response.getWriter();
		JSONStringer			clientArray			=	new JSONStringer();
		String					projectId			=	request.getParameter("projectId") == null ? "":request.getParameter("projectId");
		String					status				=	request.getParameter("status");
		
		ArrayList<CommonForm>   dataList			=	commonMethodBean.getProjrctWiseClientList(projectId,status);
		try
		{
			clientArray.array();
			if(dataList != null && dataList.size() > 0)
			{
				for(CommonForm  commonForm : dataList)
				{
					clientArray.object();
					clientArray.key("value").value(commonForm.getField1());
					clientArray.key("name").value(commonForm.getField2());
					clientArray.endObject();
				}
			}
			clientArray.endArray();
			
			JSONStringer	object	=	new 	JSONStringer();
			object.object();
			object.key("clientArray").value(clientArray);
			object.endObject();
			
			String  	result	=	object.toString();
			writer.println(result);
		}
		catch (JSONException e) 
		{
			System.out.println("Error In CommonMethodAction.getprojectWiseStatusWiseClient: "+e);
		}
		return mapping.findForward(null);
	}
	
	
	/**
	 * Action Name 	: getStatusWiseClientName
	 * Purpose		: To getStatusWiseClientName
	 * @author		: Prashant
	 * Date			: 27/09/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getStatusWiseClientName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				clientName			=	request.getParameter("clientName")!= null && request.getParameter("clientName").length() > 0 ? request.getParameter("clientName").trim().toUpperCase() : "";
		String				status				=	request.getParameter("status")!= null && request.getParameter("status").length() > 0 ? request.getParameter("status").trim().toUpperCase() : "";
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getStatusWiseClientName(clientName);
		try
		{
			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select name='ajaxClientNameID' id='ajaxClientNameID' size='5' ondblclick=changeClientNameVal() onkeypress=checkClientValue(event) class='multilist'>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getStatusWiseClientName : "+e); 
		}
		return mapping.findForward(null);
	}
	
	
	
	
	/**
	 * Action Name 	: getPersonNameList
	 * Purpose		: To getPersonNameList in DD
	 * @author		: Prashant
	 * Date			: 29/09/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getPersonNameList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				clientId			=	request.getParameter("client_Id")!= null && request.getParameter("client_Id").length() > 0 ? request.getParameter("client_Id").trim() : "";
		//String				status				=	request.getParameter("status")!= null && request.getParameter("status").length() > 0 ? request.getParameter("status").trim().toUpperCase() : "";
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getPersonNameList(clientId);
		try
		{
			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select id='contactParsonId' name='contactParsonId' class='listbox'>";
				outResult	+=	"<option value=''>--SELECT--</option>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i); 
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getPersonNameList : "+e); 
		}
		return mapping.findForward(null);
	}
	
	/**
	 * Action Name 	: getStatusWiseEmployeeName
	 * Purpose		: To getStatusWiseEmployeeName
	 * @author		: Prashant
	 * Date			: 19/03/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getStatusWiseEmployeeNameForLoginDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				empName				=	request.getParameter("empName")!= null && request.getParameter("empName").length() > 0 ? request.getParameter("empName").trim().toUpperCase() : "";
		String				status				=	request.getParameter("status")!= null && request.getParameter("status").length() > 0 ? request.getParameter("status").trim().toUpperCase() : "";
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getEmployeeNameListForLoginDetails(empName,status);
		try
		{
			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select name='ajaxEmployeeNameID' id='ajaxEmployeeNameID' size='5' ondblclick=changeEmployeeNameVal() onkeypress=checkEmployeeValue(event) class='multilist'>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getStatusWiseEmployeeNameForLoginDetails : "+e); 
		}
		return mapping.findForward(null);
	}

	
	/**
	 * Action Name 	: getStatusWiseSpeakerName
	 * Purpose		: To getStatusWiseSpeakerName
	 * @author		: Prashant
	 * Date			: 27/04/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getStatusWiseSpeakerName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				speakerName			=	request.getParameter("speakerName")!= null && request.getParameter("speakerName").length() > 0 ? request.getParameter("speakerName").trim().toUpperCase() : "";
		String				status				=	request.getParameter("status")!= null && request.getParameter("status").length() > 0 ? request.getParameter("status").trim().toUpperCase() : "";
		
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getStatusWiseSpeakerName(speakerName,status);
		try
		{
			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select name='ajaxSpeakerNameID' id='ajaxSpeakerNameID' size='5' ondblclick=changeSpeakerNameVal() onkeypress=checkSpeakerValue(event) class='multilist'>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getStatusWiseLoginName : "+e); 
		}
		return mapping.findForward(null);
	}
	
	
	/**
	 * Action Name 	: getStatusWiseIndustryName
	 * Purpose		: To getStatusWiseIndustryName
	 * @author		: Prashant
	 * Date			: 27/04/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getStatusWiseDeptName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				deptName			=	request.getParameter("deptName")!= null && request.getParameter("deptName").length() > 0 ? request.getParameter("deptName").trim().toUpperCase() : "";
		String				status				=	request.getParameter("status")!= null && request.getParameter("status").length() > 0 ? request.getParameter("status").trim().toUpperCase() : "";
		
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getStatusWiseDeptName(deptName,status);
		try
		{
			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select name='ajaxDeptNameID' id='ajaxDeptNameID' size='5' ondblclick=changeDeptNameVal() onkeypress=checkDeptValue(event) class='multilist'>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getStatusWiseIndustryName : "+e); 
		}
		return mapping.findForward(null);
	}
	
	public ActionForward getStatusWiseArticleName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
	// -----------------------------------------------------------------------------    
		{
			CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
			String 				articleName			=	request.getParameter("articleName")!= null && request.getParameter("articleName").length() > 0 ? request.getParameter("articleName").trim().toUpperCase() : "";
			String				status				=	request.getParameter("status")!= null && request.getParameter("status").length() > 0 ? request.getParameter("status").trim().toUpperCase() : "";
			PrintWriter			writer				=	response.getWriter();
			String				outResult			=	"";
			ArrayList<CommonForm> dataList			=	commonMethodBean.getStatusWiseArticlerName(articleName,status);
			try
			{
				if(dataList!= null && dataList.size() > 0)
				{
					outResult	+=	"<select name='ajaxArticleNameID' id='ajaxArticleNameID' size='5' ondblclick=changeArticlerNameVal() onkeypress=checkArticleValue(event) class='multilist'>";
					for(int i=0 ;i<dataList.size();i++)
					{
						CommonForm	cForm	=	dataList.get(i);
						outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
					}
					outResult	+=	"</select>";
				}
				
				writer.println(outResult);
			}
			catch(Exception e)
			{
				System.out.println("Error In CommonMethodAction.getStatusWiseArticleName : "+e); 
			}
			return mapping.findForward(null);
		}
	
	/**
	 * Action Name 	: getStatusWiseWebinarName
	 * Purpose		: To getStatusWiseWebinarName
	 * @author		: Prashant
	 * Date			: 27/04/2012
	 */
// -----------------------------------------------------------------------------	
	public ActionForward getStatusWiseWebinarName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
// -----------------------------------------------------------------------------    
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String 				webinarName			=	request.getParameter("webinarName")!= null && request.getParameter("webinarName").length() > 0 ? request.getParameter("webinarName").trim().toUpperCase() : "";
		String				status				=	request.getParameter("status")!= null && request.getParameter("status").length() > 0 ? request.getParameter("status").trim().toUpperCase() : "";
		
		PrintWriter			writer				=	response.getWriter();
		String				outResult			=	"";
		
		ArrayList<CommonForm> dataList			=	commonMethodBean.getStatusWiseWebinarName(webinarName,status);
		try
		{
			if(dataList!= null && dataList.size() > 0)
			{
				outResult	+=	"<select name='ajaxWebinarNameID' id='ajaxWebinarNameID' size='5' ondblclick=changeWebinarNameVal() onkeypress=checkWebinarValue(event) class='multilist'>";
				for(int i=0 ;i<dataList.size();i++)
				{
					CommonForm	cForm	=	dataList.get(i);
					outResult	+=	"<option value='"+cForm.getField1()+"'>"+cForm.getField2()+"</option>";
				}
				outResult	+=	"</select>";
			}
			
			writer.println(outResult);
		}
		catch(Exception e)
		{
			System.out.println("Error In CommonMethodAction.getStatusWiseLoginName : "+e); 
		}
		return mapping.findForward(null);
	}
}
