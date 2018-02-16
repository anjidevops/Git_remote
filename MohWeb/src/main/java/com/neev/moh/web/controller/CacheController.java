package com.neev.moh.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neev.moh.facade.UserFacade;
import com.neev.moh.web.logConf.LoggerAspect;

@Controller
@RequestMapping(value = "/cache")
public class CacheController {

	private static final Logger logger = LoggerFactory.getLogger(CacheController.class.getName());

	@Autowired(required = true)
	private UserFacade userFacade;

	// Tacks the method names while executing the project and records to the logs
	@Autowired(required = true)
	LoggerAspect logAspect;

	@PreAuthorize("hasRole('cache_clear')")
	@RequestMapping(value = "/clearCache", method = RequestMethod.POST)
	public ModelAndView clearCache(HttpServletRequest request) {
		logger.info("Enter: clearCache()");
		ModelAndView mav = new ModelAndView("/success.jsp");
		String clearAllCache = request.getParameter("clearAllCache");
		String clearCache = request.getParameter("clearCache");
		String cacheName = request.getParameter("cacheName");
		logger.info("clearAllCache: {}", clearAllCache);
		logger.info("clearCache: {}", clearCache);
		logger.info("cacheName: {}", cacheName);
		String status = userFacade.clearCache(clearAllCache, clearCache, cacheName);
		mav.addObject("status", status);
		logger.info("status: {}", status);
		logger.info("Exit: clearCache()");
		return mav;
	}

}
