package com.psa.mj.DAO.Master;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


public class CountryForm extends ActionForm
{

	private static final long serialVersionUID = 1L;
	private String	countryId;
	private String	countryCode;	
	private String	countryName;
	private String	status;
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		countryId			=	null;
		countryCode			=	null;
		countryName			=	null;
		status				=	null;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	
	
	
}
