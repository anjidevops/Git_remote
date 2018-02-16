package com.neev.moh.facade.dto;


public class AppointmentInfoDto {

	private String visitedDate;

	private String doctorName;

	private String summary;

	private Integer appointmentId;
	
	private Integer patientId;

	public String getVisitedDate() {
		return visitedDate;
	}

	public void setVisitedDate(String visitedDate) {
		this.visitedDate = visitedDate;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

}
