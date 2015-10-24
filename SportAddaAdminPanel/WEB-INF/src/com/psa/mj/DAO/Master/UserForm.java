package com.psa.mj.DAO.Master;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


public class UserForm extends ActionForm
{

	private static final long serialVersionUID = 1L;
	
	private String	empId;
	private String	empName;
	private String	loginName;
	private String	loginId;	
	private String	password;
	private String	status;
	private String	roleId;
	private String [] responsibilityArray;
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		empId				=	null;
		empName				=	null;
		loginName			=	null;
		password			=	null;
		status				=	null;
		responsibilityArray	=	null;
		roleId				=	null;
		loginId				=	null;
		
	}
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String[] getResponsibilityArray() {
		return responsibilityArray;
	}
	public void setResponsibilityArray(String[] responsibilityArray) {
		this.responsibilityArray = responsibilityArray;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	
}
