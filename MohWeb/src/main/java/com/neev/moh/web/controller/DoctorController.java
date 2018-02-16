package com.neev.moh.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neev.moh.facade.AptFacade;
import com.neev.moh.facade.dto.AppointmentRecordDto;
import com.neev.moh.facade.dto.DoctorInfoDto;
import com.neev.moh.facade.dto.response.AppointmentLanes;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;

@Controller
@RequestMapping(value = "/doctor")
public class DoctorController {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(DoctorController.class.getName());

	@Autowired(required = true)
	private AptFacade aptFacade;

	@PreAuthorize("hasRole('doctor_schedule')")
	@RequestMapping(value = "schedule", method = RequestMethod.POST)
	public void visit() {
		// TODO
	}

	@PreAuthorize("hasRole('doctor_appointment_details')")
	@RequestMapping(value = "appointmentDetails", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody AppointmentLanes appointmentDetails(@RequestBody AppointmentRecordDto appointmentDto) {
		DoctorInfoDto doctorInfoDto = new DoctorInfoDto(appointmentDto.getDoctorId());
		AppointmentLanes response = aptFacade.getScduleFromService(appointmentDto.getAppointmentDay(), doctorInfoDto);
		response.setErrorNo(0);
		response.setSuccessMsg("fetched schedule successfully");
		logger.log(MohLogger.INFO, "Fetched schedule successfully");
		return response;
	}

}
