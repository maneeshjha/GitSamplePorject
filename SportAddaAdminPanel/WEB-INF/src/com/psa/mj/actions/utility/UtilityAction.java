package com.psa.mj.actions.utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.psa.mj.Bean.CommonMethodBean;
import com.psa.mj.Bean.utility.UtilityBean;
import com.psa.mj.DAO.Master.UtilityForm;
import com.psa.mj.actions.ComplianceKeyDispatchAction;

public class UtilityAction extends ComplianceKeyDispatchAction
{
	/**
	 * Action Name 	: showChangePasswordPage
	 * Purpose		: To showChangePasswordPage
	 * @author		: Prashant
	 * Date			: 28/04/2012
	 */
	public ActionForward showChangePasswordPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		UtilityForm		utilityForm	=	(UtilityForm)form;
		utilityForm.reset(mapping, request);
		HttpSession				session				=	request.getSession();
		CommonMethodBean		commonMethodBean	=	new CommonMethodBean();
		String rarId		=	"3";
		String userProcess	=	(String)session.getAttribute("userProcess");
		String validLink	=	commonMethodBean.getValidOrNot(","+userProcess+",", ","+rarId+",");
		if(validLink!=null && validLink.equalsIgnoreCase("NOTVALID"))
		{
			return mapping.findForward("invalid");
		}
		
		return mapping.findForward("showChangePasswordPage");
	}
	/**
	 * Action Name 	: checkLogingUserPassword
	 * Purpose		: To checkLogingUserPassword
	 * @author		: Prashant
	 * Date			: 28/04/2012
	 */
	public ActionForward checkLogingUserPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		UtilityBean			utilityBean			=	new UtilityBean();
		PrintWriter			writer				=	response.getWriter();
		HttpSession			session				=	request.getSession();
		String				outputString		=	"ERROR";
		String				oldPassword			=	request.getParameter("oldPassword")!=null && request.getParameter("oldPassword").length() > 0 ? request.getParameter("oldPassword") : "";
		String				userName			=	(String)session.getAttribute("username");
		String				userId				=	commonMethodBean.getDataOnOneCondition("usermst", "userid", "user_name", userName);
		
		String				password			=	utilityBean.getLogingUserPassword(userId,userName);
		
		if(password!=null && password.length() > 0)
		{
			if(password.equals(oldPassword))
			{
				outputString	=	"YES";
			}
			else
			{
				outputString	=	"NO";
			}
		}
		writer.print(outputString);
		return mapping.findForward(null);
	}
	
	/**
	 * Action Name 	: changePassword
	 * Purpose		: To changePassword
	 * @author		: Prashant
	 * Date			: 28/04/2012
	 */
	public ActionForward changePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		HttpSession		session			=	request.getSession();
		
		UtilityForm		utilityForm		=	(UtilityForm)form;
		UtilityBean		utilityBean		=	new UtilityBean();
		
		String			mappingString	=	"ERROR";
		String			userName		=	(String)session.getAttribute("username");
		
		String		saveResult			=	utilityBean.changePassword(utilityForm,userName);
		
		if(saveResult!= null && saveResult.length() > 0 && !saveResult.equalsIgnoreCase("ERROR"))
		{
				String	msgType		=	saveResult.substring(0,4);
				saveResult			=	saveResult.substring(5,saveResult.length());
				
				request.setAttribute("msgType",msgType);
				request.setAttribute("saveResult",saveResult);
				
				if(msgType!= null && msgType.length() > 0 && msgType.equals("PASS"))
				{
					utilityForm.reset(mapping, request);
					mappingString	=	"showChangePasswordPage";
				}
				
				else if(msgType!= null && msgType.length() > 0 && msgType.equals("FAIL"))
				{
					mappingString	=	"showChangePasswordPage";
				}
		}
		return mapping.findForward(mappingString);
	}
	/**
	 * Action Name 	: showResetPasswordPage
	 * Purpose		: To showResetPasswordPage
	 * @author		: Prashant
	 * Date			: 28/04/2012
	 */
	public ActionForward showResetPasswordPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		UtilityForm		utilityForm	=	(UtilityForm)form;
		utilityForm.reset(mapping, request);
		HttpSession				session				=	request.getSession();
		CommonMethodBean		commonMethodBean	=	new CommonMethodBean();
		String rarId		=	"4";
		String userProcess	=	(String)session.getAttribute("userProcess");
		String validLink	=	commonMethodBean.getValidOrNot(","+userProcess+",", ","+rarId+",");
		if(validLink!=null && validLink.equalsIgnoreCase("NOTVALID"))
		{
			return mapping.findForward("invalid");
		}
		
		return mapping.findForward("showResetPasswordPage");
	}
	
	/**
	 * Action Name 	: resetPassword
	 * Purpose		: To resetPassword
	 * @author		: Prashant
	 * Date			: 28/04/2012
	 */
	public ActionForward resetPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		HttpSession		session			=	request.getSession();
		
		UtilityForm		utilityForm		=	(UtilityForm)form;
		UtilityBean		utilityBean		=	new UtilityBean();
		
		String			mappingString	=	"ERROR";
		String			userName		=	(String)session.getAttribute("username");
		
		String			saveResult		=	utilityBean.resetPassword(utilityForm,userName);
		
		if(saveResult!= null && saveResult.length() > 0 && !saveResult.equalsIgnoreCase("ERROR"))
		{
				String	msgType		=	saveResult.substring(0,4);
				saveResult			=	saveResult.substring(5,saveResult.length());
				
				request.setAttribute("msgType",msgType);
				request.setAttribute("saveResult",saveResult);
				
				if(msgType!= null && msgType.length() > 0 && msgType.equals("PASS"))
				{
					utilityForm.reset(mapping, request);
					mappingString	=	"showResetPasswordPage";
				}
				else if(msgType!= null && msgType.length() > 0 && msgType.equals("FAIL"))
				{
					mappingString	=	"showResetPasswordPage";
				}
		}
		return mapping.findForward(mappingString);
	}
	
}
