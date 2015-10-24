package com.psa.mj.DAO.Master;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CertificateForm extends ActionForm{
	String customer_name;
	String testimonials;
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getTestimonials() {
		return testimonials;
	}
	public void setTestimonials(String testimonials) {
		this.testimonials = testimonials;
	}
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		testimonials				=	null;
		customer_name				=	null;
	}


}
