package com.psa.mj.DAO.Master;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class GroupForm extends ActionForm
{

	private static final long serialVersionUID = 1L;
	private String	groupId;
	private String	groupName;
	private	String	dataType;
	private String	allowMultiple;
	private String	allowModify;
	private String	status;
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		groupId			=	null;
		groupName		=	null;
		dataType		=	null;
		allowMultiple	=	null;
		allowModify		=	null;
		status			=	null;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getAllowMultiple() {
		return allowMultiple;
	}

	public void setAllowMultiple(String allowMultiple) {
		this.allowMultiple = allowMultiple;
	}

	public String getAllowModify() {
		return allowModify;
	}

	public void setAllowModify(String allowModify) {
		this.allowModify = allowModify;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
