package com.neev.moh.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neev.moh.facade.AppointmentFacade;
import com.neev.moh.facade.UserFacade;
import com.neev.moh.facade.dto.UserRegDto;
import com.neev.moh.facade.dto.response.AppointmentHistoryResDto;
import com.neev.moh.facade.dto.response.SearchResultResDto;
import com.neev.moh.facade.dto.response.UserRegRespDto;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.web.utils.ControllerUtils;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(UserController.class.getName());

	@Autowired(required = true)
	private UserFacade userFacade;

	@Autowired(required = true)
	private AppointmentFacade appointmentFacade;

	@PreAuthorize("hasRole('user_registration')")
	@RequestMapping(value = "registration", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody UserRegRespDto registration(@RequestBody final UserRegDto userRegDto) {
		ControllerUtils.addAddressToUserRegDtoForAngularApp(userRegDto);
		UserRegRespDto responseDto = userFacade.createUser(userRegDto);
		return responseDto;
	}

    @PreAuthorize("hasRole('user_search')")
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public @ResponseBody SearchResultResDto search(final HttpServletRequest req) {
		String searchText = req.getParameter("searchText");
		logger.log(MohLogger.INFO, "searchText: " + searchText);
		SearchResultResDto responseDto = userFacade.searchUserByEmailOrMobile(searchText);
		logger.log(MohLogger.INFO, "responseDto.getResult(): " + responseDto.getResult());
		return responseDto;
	}

    @PreAuthorize("hasRole('user_profile')")
	@RequestMapping(value = "profile", method = RequestMethod.POST)
	public void profile() {
		// TODO
	}

    @PreAuthorize("hasRole('user_visits')")
	@RequestMapping(value = "visits", method = RequestMethod.GET)
	public @ResponseBody AppointmentHistoryResDto visit(final HttpServletRequest req) {
		String patientId = req.getParameter("patientId");
		logger.log(MohLogger.INFO, "patientId: " + patientId);
		AppointmentHistoryResDto response = appointmentFacade.loadPatientAppointmentHistory(patientId);
		return response;
	}

}
