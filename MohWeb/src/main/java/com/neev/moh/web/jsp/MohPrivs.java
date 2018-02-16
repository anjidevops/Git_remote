package com.neev.moh.web.jsp;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;


import com.neev.moh.facade.MohWebserviceFacade;
import com.neev.moh.facade.impl.MohWebserviceFacadeImpl;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.User;
import com.neev.moh.utils.JsonUtils;

public class MohPrivs extends SimpleTagSupport {
	private static final MohLogger logger = MohLogFactory.getLoggerInstance(MohPrivs.class.getName());
	private MohWebserviceFacade webserviceFacade = new MohWebserviceFacadeImpl();
	private String group;
	
	public void setGroup(String group) {
		this.group = group;
	}

	public void doTag() throws JspException, IOException {
		logger.log(MohLogger.INFO, "Group: " + group);
		try {
			User loggedInUser = (User) getJspContext().getAttribute("CURRENT_USER", PageContext.SESSION_SCOPE);
			Set<String> privs = new HashSet<String>();
			if(group == null) {
				privs = webserviceFacade.getAllPrivileges(loggedInUser);
			} else {
				privs = webserviceFacade.getAllPrivileges(loggedInUser, group);
			}
			String privJson = JsonUtils.objectToJson(privs);
			logger.log(MohLogger.INFO, "privJson: " + privJson);
			getJspContext().getOut().write(privJson);
		} catch (Exception e) {
			logger.log(MohLogger.ERROR, e, e);
			throw new SkipPageException("Exception in MohPrivs " + e);
		}
	}
}
