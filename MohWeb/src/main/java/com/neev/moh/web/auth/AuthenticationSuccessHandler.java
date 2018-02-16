package com.neev.moh.web.auth;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;

import com.neev.moh.facade.dto.response.LoginRespDto;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.User;

public class AuthenticationSuccessHandler extends
		AbstractAuthenticationTargetUrlRequestHandler
		implements
		org.springframework.security.web.authentication.AuthenticationSuccessHandler {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(AuthenticationSuccessHandler.class.getName());

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		logger.log(MohLogger.DEBUG, "commence()");
		logger.log(MohLogger.DEBUG, "User attempting to login:" + authentication.getName());
		LoginRespDto loginResponse = new LoginRespDto();
		loginResponse.setLoginId(authentication.getName());
		User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
		request.getSession().setAttribute("loggedInUser", user);
		logger.log(MohLogger.DEBUG, "User email: " + user.getEmail());
		loginResponse.setErrorNo(0);
		loginResponse.setSuccessMsg("You have successfully loggedin.");
		loginResponse.setLoginSuccess(Boolean.TRUE);
		loginResponse.setUserRole(user.getRoleType());
		loginResponse.setUserId(user.getId());
		logger.log(MohLogger.DEBUG, "Roles: " + user.getRoles());
		loginResponse.setUserFirstName(user.getFirstName());
		loginResponse.setUserLastName(user.getLastName());
		logger.log(MohLogger.DEBUG, "User Successfully Logged in");
		logger.log(MohLogger.DEBUG, "Exit: login()");
		String loginResponseJson = "{\"errorNo\":0,\"errorMsg\":null,\"successMsg\":\"You have successfully loggedin.\",\"loginId\":\""
				+ authentication.getName()
				+ "\",\"userRole\":\""
				+ user.getRoleType()
				+ "\",\"userFirstName\":\""
				+ user.getFirstName()
				+ "\",\"userLastName\":\""
				+ user.getLastName()
				+ "\",\"userId\":" + user.getId() + ",\"loginSuccess\":true,\"validationFailed\":false}";
		Cookie userCookie = new Cookie("user", URLEncoder.encode(loginResponseJson, "UTF-8"));
		userCookie.setMaxAge(60 * 60 * 24);
		response.addCookie(userCookie);
		logger.log(MohLogger.DEBUG, "loginResponseJson = " + loginResponseJson);
		handle(request, response, authentication);
		postProcessRequest(request, user);
	}

	/**
	 * Removes temporary authentication-related data which may have been stored
	 * in the session during the authentication process.
	 * 
	 * @param request
	 * @param user
	 */
	protected final void postProcessRequest(HttpServletRequest request, User user) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			logger.log(MohLogger.WARN,
					"Current user is NOT attached to the session.");
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		session.setAttribute("CURRENT_USER", user);
		logger.log(MohLogger.DEBUG, "Current user is attached to the session.");
		logger.log(MohLogger.DEBUG, "Roles: " + user.getRoles());
	}
}
