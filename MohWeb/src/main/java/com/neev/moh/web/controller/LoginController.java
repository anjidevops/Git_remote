package com.neev.moh.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.neev.moh.facade.UserFacade;
import com.neev.moh.facade.dto.LoginDto;
import com.neev.moh.facade.dto.response.LoginRespDto;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.User;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(LoginController.class.getName());

	@Autowired(required = true)
	private UserFacade userFacade;

	@RequestMapping(value = "login", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody LoginRespDto login(@RequestBody final LoginDto loginDto) {
		// Print test logs
		MohLogFactory.printLogs();
		logger.log(MohLogger.INFO, "java.util.logging.manager=" + System.getProperty("java.util.logging.manager"));
		logger.log(MohLogger.INFO, "User attempting to login: " + loginDto.getLoginId());
		LoginRespDto loginResponse = new LoginRespDto();
		loginResponse.setLoginId(loginDto.getLoginId());
		User user = userFacade.getUserByEmail(loginDto.getLoginId());
		if (user != null && user.getPassword().equals(loginDto.getPassword())) {
			logger.log(MohLogger.INFO, "User email: " + user.getEmail());
			loginResponse.setErrorNo(0);
			loginResponse.setSuccessMsg("You have successfully loggedin.");
			loginResponse.setLoginSuccess(Boolean.TRUE);
			loginResponse.setUserRole(user.getRoleType());
			loginResponse.setUserId(user.getId());
			loginResponse.setUserFirstName(user.getFirstName());
			loginResponse.setUserLastName(user.getLastName());
			logger.log(MohLogger.INFO, "User Successfully Logged in");
		} else {
			loginResponse.setErrorNo(1);
			loginResponse.setErrorMsg("Invalid Credentials");
			logger.log(MohLogger.WARN, "Invalid Credentials");
		}
		return loginResponse;
	}
	
	@RequestMapping(value = "/clearCache", method = RequestMethod.POST)
	public ModelAndView clearCache(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/success.jsp");
		String clearAllCache = request.getParameter("clearAllCache");
		String clearCache = request.getParameter("clearCache");
		String cacheName = request.getParameter("cacheName");
		logger.log(MohLogger.INFO, "clearAllCache: " + clearAllCache);
		logger.log(MohLogger.INFO, "clearCache: " + clearCache);
		logger.log(MohLogger.INFO, "cacheName: " + cacheName);
		String status = userFacade.clearCache(clearAllCache, clearCache, cacheName);
		mav.addObject("status", status);
		logger.log(MohLogger.INFO, "status: " + status);
		return mav;
	}

}
