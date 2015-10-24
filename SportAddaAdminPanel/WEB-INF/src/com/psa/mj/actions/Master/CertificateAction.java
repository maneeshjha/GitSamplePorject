package com.psa.mj.actions.Master;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.psa.mj.Bean.Master.CertificateBean;
import com.psa.mj.DAO.Master.CertificateForm;
import com.psa.mj.actions.ComplianceKeyDispatchAction;

public class CertificateAction extends ComplianceKeyDispatchAction{
	public ActionForward showCertificatePage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		CertificateForm	customerForm	=	(CertificateForm)form;
		customerForm.reset(mapping, request);
		return mapping.findForward("showCertificatePage");
	}
	public ActionForward saveCertificateMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws IOException,SQLException
	{
		HttpSession	session		=	request.getSession();
		CertificateForm	certForm	=	(CertificateForm)form;
		CertificateBean	testimonialsBean	=	new CertificateBean();
		String		saveResult		=	testimonialsBean.saveCertificateDetails(certForm);
		if(saveResult!= null && saveResult.length() > 0)
		{
			String	msgType		=	saveResult.substring(0,4);
			saveResult			=	saveResult.substring(5,saveResult.length());
			request.setAttribute("msgType",msgType);
			request.setAttribute("saveResult",saveResult);
			certForm.reset(mapping, request);
		}
		return mapping.findForward("showCertificatePage");
	}

}
