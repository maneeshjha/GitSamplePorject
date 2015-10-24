package com.psa.mj.DAO;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SyncForm extends ActionForm 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName	;
	private String password	;

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) 
	{
		userName	=	null;
		password	=	null;		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
