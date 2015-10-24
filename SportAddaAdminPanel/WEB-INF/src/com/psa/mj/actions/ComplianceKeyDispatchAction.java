package com.psa.mj.actions;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * DispacthAction fix for stack overflow problem.
 * 
 * @author Prashant Adpawar
 */
public class ComplianceKeyDispatchAction extends DispatchAction {

	
	public ActionForward execute(ActionMapping in_mapping, ActionForm in_form, HttpServletRequest in_request, HttpServletResponse in_response) throws Exception {
		String lc_parameterName = in_mapping.getParameter();
		if (lc_parameterName!=null) {
			String lc_methodName = in_request.getParameter(lc_parameterName);
			if (lc_methodName!=null &&
					("execute".equals(lc_methodName) || "perform".equals(lc_methodName))) {
					throw new IllegalArgumentException("illegal parameter");
				}
		}		
		return super.execute(in_mapping, in_form, in_request, in_response);
	}

}
