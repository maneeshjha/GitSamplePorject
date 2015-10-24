package com.psa.mj.actions.Master;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.psa.mj.DAO.Master.SpeakerForm;
import com.psa.mj.actions.ComplianceKeyDispatchAction;

public class CustomerAction extends ComplianceKeyDispatchAction{
	public ActionForward showCustomerPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		SpeakerForm	userForm	=	(SpeakerForm)form;
		System.out.println("Hi");
		userForm.reset(mapping, request);
		return mapping.findForward("showSpeakerMasterList");
	}

}
