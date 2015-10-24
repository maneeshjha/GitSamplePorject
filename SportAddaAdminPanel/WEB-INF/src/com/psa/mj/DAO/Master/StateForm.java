package com.psa.mj.DAO.Master;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class StateForm extends ActionForm
{

	private static final long serialVersionUID = 1L;
	private	String	countryId;
	private String	countryName;
	private	String	stateId;
	private	String	stateName;
	private String	stateCode;
	private String	status;
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		countryId		=	null;
		countryName		=	null;
		stateId			=	null;
		stateName		=	null;
		stateCode		=	null;
		status			=	null;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
