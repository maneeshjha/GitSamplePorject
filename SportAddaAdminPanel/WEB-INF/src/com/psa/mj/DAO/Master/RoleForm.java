package com.psa.mj.DAO.Master;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


public class RoleForm extends ActionForm
{

	private static final long serialVersionUID = 1L;
	private String roleName;
	private String description;
	private String status;
	private String roleId;
	private String [] responsibilityArray;
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		roleName		=	null;
		description		=	null;
		status			=	null;
		roleId			=	null;
		responsibilityArray	=	null;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public String[] getResponsibilityArray() {
		return responsibilityArray;
	}

	public void setResponsibilityArray(String[] responsibilityArray) {
		this.responsibilityArray = responsibilityArray;
	}
}
