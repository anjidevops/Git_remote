package com.neev.moh.web.jsp;

import java.io.IOException;
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

public class MohRoles extends SimpleTagSupport {
	private static final MohLogger logger = MohLogFactory.getLoggerInstance(MohRoles.class.getName());
	private MohWebserviceFacade webserviceFacade = new MohWebserviceFacadeImpl();

	public void doTag() throws JspException, IOException {
		try {
			User loggedInUser = (User) getJspContext().getAttribute("CURRENT_USER", PageContext.SESSION_SCOPE);
			Set<String> roles = webserviceFacade.getAllRoles(loggedInUser);
			String roleJson = JsonUtils.objectToJson(roles);
			logger.log(MohLogger.INFO, "roleJson: " + roleJson);
			getJspContext().getOut().write(roleJson);
		} catch (Exception e) {
			throw new SkipPageException("Exception in MohRoles");
		}
	}
}
