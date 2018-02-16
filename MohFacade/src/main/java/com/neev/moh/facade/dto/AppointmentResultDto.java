package com.neev.moh.facade.dto;

import java.util.Date;

public class AppointmentResultDto {
	
	/* user full name =  User.firstName+User.lastName */
	private String userName;
	
	private String appointmentDate;
	
	private Integer appointmentId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}
	
}
