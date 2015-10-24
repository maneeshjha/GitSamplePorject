package com.psa.mj.actions.Master;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.psa.mj.Bean.Master.NotificationBean;
import com.psa.mj.DAO.Master.NotificationForm;
import com.psa.mj.actions.ComplianceKeyDispatchAction;

public class NotificationAction extends ComplianceKeyDispatchAction {
	public ActionForward showNotificationPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		NotificationForm	notificationForm	=	(NotificationForm)form;
		notificationForm.reset(mapping, request);
		return mapping.findForward("showNotificationPage");
	}
	public ActionForward saveNotification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		NotificationForm	notificationForm	=	(NotificationForm)form;
		NotificationBean	testimonialsBean	=	new NotificationBean();
		String		saveResult		=	testimonialsBean.saveNotification(notificationForm);
		if(saveResult!= null && saveResult.length() > 0)
		{
			String	msgType		=	saveResult.substring(0,4);
			saveResult			=	saveResult.substring(5,saveResult.length());
			request.setAttribute("msgType",msgType);
			request.setAttribute("saveResult",saveResult);
			notificationForm.reset(mapping, request);
		}
		return mapping.findForward("showNotificationPage");
	}

}
