package com.neev.moh.facade.dto.response;

import java.util.List;

import com.neev.moh.facade.dto.AppointmentResultDto;

public class AppointmentSeachResultResDto extends ResponseDto{
	
	List<AppointmentResultDto> appointmentResults ;

	public List<AppointmentResultDto> getAppointmentResults() {
		return appointmentResults;
	}

	public void setAppointmentResults(List<AppointmentResultDto> appointmentResults) {
		this.appointmentResults = appointmentResults;
	} 
	
}
