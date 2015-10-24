// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LoginForm.java

package com.psa.mj.DAO.login;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class LoginForm extends ActionForm
{
    protected String login;
    protected String password;
    private String userName;
    private String dbName;
    private String userID;
    private String empName;
    private String fromDate;
    private String toDate;
    
    
    
    public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public LoginForm()
    {
    }

    public String getPassword()
    {
        return password;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String in_login)
    {
        login = in_login;
    }

    public void setPassword(String in_password)
    {
        password = in_password;
    }
    
    


    
    @Override
	public void reset(ActionMapping mapping, HttpServletRequest request) 
    {
    	userID 		= 	"";
    	login		= 	"";
    	userName 	= 	"";
    }
    
   



	
}
