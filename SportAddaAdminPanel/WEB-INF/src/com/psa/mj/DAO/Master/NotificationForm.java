package com.psa.mj.DAO.Master;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class NotificationForm  extends ActionForm{
	String email_id;
	String notification;
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getNotification() {
		return notification;
	}
	public void setNotification(String notification) {
		this.notification = notification;
	}
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		email_id				=	null;
		notification				=	null;
	}


}
