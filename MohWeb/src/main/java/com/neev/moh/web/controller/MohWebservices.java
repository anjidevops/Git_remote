package com.neev.moh.web.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neev.moh.facade.MohWebserviceFacade;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.User;

@RestController
@RequestMapping(value = "/rest")
public class MohWebservices {
	private static final MohLogger logger = MohLogFactory.getLoggerInstance(MohWebservices.class.getName());

	@Autowired(required = true)
	private MohWebserviceFacade webserviceFacade;

	/**
	 * Returns all available roles for the current user in session.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getAllRoles", method = RequestMethod.GET)
	public Set<String> getAllRoles(HttpSession session) {
		logger.log(MohLogger.INFO, "getAllRoles()");
		User loggedInUser = (User) session.getAttribute("CURRENT_USER");
		Set<String> roleSet = webserviceFacade.getAllRoles(loggedInUser);
		logger.log(MohLogger.INFO, "roleSet = " + roleSet);
		return roleSet;
	}
	
	/**
	 * Returns all available privileges for the current user in session.
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getAllPrivileges", method = RequestMethod.GET)
	public Set<String> getAllPrivileges(HttpSession session) {
		logger.log(MohLogger.INFO, "getAllPrivileges()");
		User loggedInUser = (User) session.getAttribute("CURRENT_USER");
		Set<String> privilegeSet = webserviceFacade.getAllPrivileges(loggedInUser);
		logger.log(MohLogger.INFO, "privilegeSet = " + privilegeSet);
		return privilegeSet;
	}
	
	/**
	 * Returns all available privileges for the current user in session.
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getAllPrivileges/{group}", method = RequestMethod.GET)
	public Set<String> getAllPrivileges(HttpSession session, @PathVariable("group") String group) {
		logger.log(MohLogger.INFO, "getAllPrivileges()");
		User loggedInUser = (User) session.getAttribute("CURRENT_USER");
		Set<String> privilegeSet = webserviceFacade.getAllPrivileges(loggedInUser, group);
		logger.log(MohLogger.INFO, "privilegeSet = " + privilegeSet);
		return privilegeSet;
	}

	/**
	 * Returns true if a particular privilege is present for the current user in
	 * session, otherwise returns false.
	 * 
	 * @param session
	 * @param privilege
	 * @return
	 */
	@RequestMapping(value = "/hasPrivilege/{privilege}", method = RequestMethod.GET)
	public boolean hasPrivilege(HttpSession session, @PathVariable String privilege) {
		logger.log(MohLogger.INFO, "hasPrivilege()");
		User loggedInUser = (User) session.getAttribute("CURRENT_USER");
		boolean privilegeFound = webserviceFacade.hasPrivilege(loggedInUser, privilege);
		logger.log(MohLogger.INFO, "privilegeFound = " + privilegeFound);
		return privilegeFound;
	}

	/**
	 * Returns true if all the privileges are present for the current user in
	 * session, otherwise returns false.
	 * 
	 * @param session
	 * @param privileges
	 * @return
	 */
	@RequestMapping(value = "/hasAllPrivileges", method = RequestMethod.POST)
	public boolean hasAllPrivileges(HttpSession session, @RequestBody List<String> privileges) {
		logger.log(MohLogger.INFO, "hasAllPrivileges()");
		User loggedInUser = (User) session.getAttribute("CURRENT_USER");
		boolean privilegeFound = webserviceFacade.hasAllPrivileges(loggedInUser, privileges);
		logger.log(MohLogger.INFO, "privilegeFound = " + privilegeFound);
		return privilegeFound;
	}
}
