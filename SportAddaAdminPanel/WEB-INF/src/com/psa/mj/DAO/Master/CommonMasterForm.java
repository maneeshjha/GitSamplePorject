package com.psa.mj.DAO.Master;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


public class CommonMasterForm extends ActionForm
{

	private static final long serialVersionUID = 1L;
	private String	groupId;
	private	String	groupValueId;
	private String	groupValue;
	private	String	dataType;
	private String	allowMultiple;
	private String	allowModify;
	private String	description;
	private String	status;
	private String	groupName;
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		groupId			=	null;
		groupValueId	=	null;
		groupValue		=	null;
		dataType		=	null;
		allowMultiple	=	null;
		allowModify		=	null;
		status			=	null;
		description		=	null;
		groupName		=	null;	
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupValue() {
		return groupValue;
	}

	public void setGroupValue(String groupValue) {
		this.groupValue = groupValue;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGroupValueId() {
		return groupValueId;
	}

	public void setGroupValueId(String groupValueId) {
		this.groupValueId = groupValueId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
}
