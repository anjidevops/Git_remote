package com.neev.moh.rpt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.rpt.facade.DoctorReportFacade;
import com.neev.moh.rpt.facade.dto.response.UserCountRes;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/doctorReport")
public class DoctorReportController {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(DoctorReportController.class.getName());

	@Autowired(required = true)
	private DoctorReportFacade reportFacade;

	@RequestMapping(value = "getNumberAptsPerDoctor", method = RequestMethod.POST)
	public @ResponseBody UserCountRes getNumberAptsPerDoctor(final HttpServletRequest req) {
		UserCountRes userCountRes = null;
		// logger.log(MohLogger.INFO, "Search text :: " + searchText);
		userCountRes = reportFacade.getNumberAptsPerDoctor();
		return userCountRes;
	}

}
