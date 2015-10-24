package com.psa.mj.DAO.Master;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


public class UtilityForm extends ActionForm
{

	private static final long serialVersionUID = 1L;
	private String  oldPassword; 
	private String  newPassword;
	private String  confirmPassword;
	private String	loginUserName;
	private String	loginName;
	private String	loginId;
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		oldPassword		=	null;
		newPassword		=	null;
		confirmPassword	=	null;
		loginUserName	=	null;
		loginName		=	null;
		loginId			=	null;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getLoginUserName() {
		return loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	
	
}
