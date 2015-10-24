package com.psa.mj.actions.login;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.psa.mj.Bean.CommonMethodBean;
import com.psa.mj.Bean.login.LoginBean;
import com.psa.mj.DAO.CommonForm;
import com.psa.mj.DAO.login.LoginForm;
import com.psa.mj.actions.ComplianceKeyDispatchAction;

public class LoginAction extends ComplianceKeyDispatchAction
{

    public LoginAction()
    {
    }

    public ActionForward login	(	ActionMapping 		mapping, 
    								ActionForm 			form, 
    								HttpServletRequest 	request, 
    								HttpServletResponse response
    							)
        throws IOException, ServletException
    {
    	HttpSession session 	= 	request.getSession();
    	LoginBean LoginBean 	= 	new LoginBean();
		String	status 			=	"";
		LoginBean	loginBean	=	new LoginBean();
		
		String result 			= 	"index";
        LoginForm loginForm 	= 	(LoginForm)form;
		String username			=	loginForm.getUserName();
		String userpass			=	loginForm.getPassword();		
		
		String loginResult		=	"";
		ArrayList resultList 	= 	new ArrayList();
		resultList				=	LoginBean.checkLogin(username,userpass);
		if (resultList!=null && resultList.size()>0)
		{
			loginResult	=	(String)resultList.get(0);
			status		=	(String)resultList.get(3);
		}
		if (loginResult!=null && loginResult.equals("valid"))
		{
			if(status != null && status.length()>0 && status.equalsIgnoreCase("ACTIVE"))
			{
				session.setAttribute("username"	, (String)resultList.get(1));
				session.setAttribute("dispname"	, (String)resultList.get(2));
				session.setAttribute("userID"	, (String)resultList.get(5));
				session.setAttribute("userProcess"	, (String)resultList.get(4));
				
				result =	"homePage";			
			}else
			{
				request.setAttribute("login", "YOUR ACCOUNT IS TEMPORARILY INACTIVATED.<br>KINDLY CONTACT ADMINISTRATOR FOR FURTHER CORRESPONDENCE.");
				result = "index";
			}
		}else
        {
            request.setAttribute("login", "INVALID USER NAME AND PASSWORD.");	
            result = "index";
        } 
       return mapping.findForward(result);
    }
/*
 * 		Methode Name	:-	    loginFromGenlife.
 * 		Purpose			:-		To provide login facility from GENLIFE Application.
 * 		Author			:-		Vitthal.
 * 		Date			:-		05/06/2012.
 */
/*************************************************************************************************************************************************/    
    public ActionForward loginFromGenlife	(	ActionMapping 		mapping, 
												ActionForm 			form, 
												HttpServletRequest 	request, 
												HttpServletResponse response
											)   
											throws IOException, ServletException
/*************************************************************************************************************************************************/ 						
		{
			HttpSession 	session 	= 	request.getSession();
			String 			locPath  	= 	"";
			LoginBean 		LoginBean 	= 	new LoginBean();
			ServletConfig  	s			=	getServlet().getServletConfig();
			String 			path		=	s.getServletContext().getRealPath("/");
			String 			dir			=	"ErrorLog";
			String			status 		=	"";
			
			String 			result 		= 	"index";
			LoginForm 		loginForm 	= 	(LoginForm)form;
			String 			username	=	request.getParameter("username");
			String 			userpass	=	request.getParameter("password");		
			
			String 			loginResult	=	"";
			ArrayList 		resultList 	= 	new ArrayList();
			ArrayList 		RARList 	= 	new ArrayList();
			
			resultList					=	LoginBean.checkLogin(username,userpass);
			
			if (resultList	!=	null && resultList.size()>0)
			{
				loginResult	=	(String)resultList.get(0);
				status		=	(String)resultList.get(3);
			}
			if (loginResult	!=	null && loginResult.equals("valid"))
			{
				if(status != null && status.length()>0 && status.equalsIgnoreCase("ACTIVE"))
				{
					session.setAttribute("username", (String)resultList.get(1));
					session.setAttribute("dispname", (String)resultList.get(2));
					String user_process	=	","+(String)resultList.get(4)+",";
					ArrayList<CommonForm> processList = new ArrayList<CommonForm>();	// LoginBean.getAllProcessList("ALL");
					
					String chkID 			= ",";
					String process_Status 	= "";
					for (int lstCnt=0;lstCnt<processList.size();lstCnt++)
					{
						CommonForm cform 	= 	(CommonForm)processList.get(lstCnt);
						process_Status 		= 	cform.getField3();
						chkID 				= 	",";
						chkID 				+=	lstCnt+",";
						if(user_process	!=	null )
						{
							if(user_process.indexOf(chkID)>=0)// && process_Status!=null && process_Status.equals("ACTIVE"))
							{
								RARList.add("'display:block'");
							}else if(process_Status!=null && process_Status.equals("INACTIVE"))
							{
								RARList.add("'display:block'");
							}
							else
							{
								RARList.add("display:none");
							}
						}else
						{
							RARList.add("display:none");
						}
					}
					
					session.setAttribute("RARList", RARList);
					result =	"homePage";			
				}else
				{
				request.setAttribute("login", "YOUR ACCOUNT IS TEMPORARILY INACTIVATED.<br>KINDLY CONTACT ADMINISTRATOR FOR FURTHER CORRESPONDENCE.");
				result = "index";
				}
			}else
			{
			request.setAttribute("login", "INVALID USER NAME AND PASSWORD.");	
			result = "index";
			} 
			
			locPath		=	path+dir;
			File file	=	new File(locPath);
			if(!file.isDirectory())
			{
			file.mkdir();
			}
			String FILE = locPath+"/HRMGMT.txt";
			FileOutputStream outStr = new FileOutputStream(FILE, true);
			PrintStream printStream = new PrintStream(outStr);
			System.setOut(printStream);
			System.setErr(printStream);
			return mapping.findForward(result);
		}
    
    
	    /**
		 * Action Name 	: logoutProcessing
		 * Purpose		: To showUserMasterList
		 * @author		: Prashant
		 * Date			: 23/10/2012
		 */
		public ActionForward logoutProcessing(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
		{
			LoginBean		loginBean	=	new LoginBean();
			HttpSession		session		=	request.getSession();
			Date date 					= 	Calendar.getInstance().getTime(); 
			DateFormat formatter 		=	new SimpleDateFormat("yyyy/MM/dd");
			String currentDate 			=	formatter.format(date);
			String 	userId				=	(String)session.getAttribute("userID");
			
			loginBean.updateLogoutDetails(currentDate, userId);
			
			
			return mapping.findForward("logout");
		}
		
		/**
		 * Action Name 	: showLoginDetails
		 * Purpose		: To showLoginDetails
		 * @author		: Prashant
		 * Date			: 23/10/2012
		 */
		public ActionForward showLoginDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
		{
			CommonMethodBean	commonMethodBean	= new CommonMethodBean();
			HttpSession			session				=	request.getSession();
			LoginForm	loginForm	=	(LoginForm)form;
			loginForm.setFromDate(commonMethodBean.getDate());
			loginForm.setToDate(commonMethodBean.getDate());
			
			String rarId		=	"38";
			String userProcess	=	(String)session.getAttribute("userProcess");		
			String validLink	=	commonMethodBean.getValidOrNot(userProcess, ","+rarId+",");
			if(validLink!=null && validLink.equalsIgnoreCase("NOTVALID"))
			{
				return mapping.findForward("invalid");
			}
			
			return mapping.findForward("loginDetails");
		}
		
		/**
		 * Action Name 	: showSearchUserMasterList
		 * Purpose		: To showSearchUserMasterList 
		 * @author		: Prashant
		 * Date			: 27/04/2012
		 */
		
		public ActionForward getLoginDetailsSearchList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
		{
			LoginForm loginForm		=	(LoginForm)form;
			LoginBean	loginBean	=	new LoginBean();
			
			ArrayList<CommonForm> dataList		=	loginBean.getSearchLoginDetails(loginForm);
			if(dataList!= null && dataList.size() > 0)
			{
				request.setAttribute("reqLoginDetailsSearchList",dataList);
			}
			else
			{
				request.setAttribute("msgType", "FAIL");
				request.setAttribute("saveResult", "NO DATA FOUND.");
			}
			return mapping.findForward("loginDetails");
		}
		
 }
