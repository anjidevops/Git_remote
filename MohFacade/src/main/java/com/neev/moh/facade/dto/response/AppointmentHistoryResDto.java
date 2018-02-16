package com.neev.moh.facade.dto.response;

import java.util.List;

import com.neev.moh.facade.dto.AppointmentInfoDto;

public class AppointmentHistoryResDto extends ResponseDto {

	private Integer patientId;

	private List<AppointmentInfoDto> appointments;

	public List<AppointmentInfoDto> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<AppointmentInfoDto> appointments) {
		this.appointments = appointments;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

}
