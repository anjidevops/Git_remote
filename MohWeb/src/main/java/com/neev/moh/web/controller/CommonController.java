package com.neev.moh.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.User;
import com.neev.moh.web.utils.ControllerUtils;

@Controller
public class CommonController {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(CommonController.class.getName());

	@RequestMapping(value = "/")
	public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("redirect:/login.jsp");
		User loggedInUser = (User) request.getSession().getAttribute("CURRENT_USER");
		if(loggedInUser != null) {
			logger.log(MohLogger.INFO, "********************User FOUND in Session");
			logger.log(MohLogger.INFO, "loggedInUser: " + loggedInUser.toString());
			mav = new ModelAndView("/resources/angular/index.jsp");
		} else {
			logger.log(MohLogger.INFO, "********************User NOT found in Session");
			if(request.getSession() != null) {
				request.getSession().invalidate();
				logger.log(MohLogger.INFO, "********************Session invalidated**");
			}
			logger.log(MohLogger.INFO, "loggedInUser is NULL");
			ControllerUtils.deleteAllCookies(request, response);
		}
		logger.log(MohLogger.INFO, "\n\n"+mav.getViewName()+"\n\n");
		return mav;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("redirect:/login.jsp");
		logger.log(MohLogger.INFO, "********************Logout called");
		request.getSession().removeAttribute("CURRENT_USER");
		logger.log(MohLogger.INFO, "********************User removed from session");
		request.getSession().invalidate();
		logger.log(MohLogger.INFO, "********************Session invalidated");
		ControllerUtils.deleteAllCookies(request, response);
		return mav;
	}

	@RequestMapping(value = "/loginerr", method = RequestMethod.GET)
	public ModelAndView loginerr(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/loginerr.jsp");
		mav.addObject("loginerr", "Invalid username or password!");
		return mav;
	}

}
