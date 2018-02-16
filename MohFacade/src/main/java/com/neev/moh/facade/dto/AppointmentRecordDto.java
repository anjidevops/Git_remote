package com.neev.moh.facade.dto;

import java.util.Date;

public class AppointmentRecordDto {
	
	private Integer departmentId;
	
	private Integer doctorId;
	
	private Integer patientId;
	
	private Date appointmentDay;
	
	private Integer fromHour;
	
	private Integer fromMin;
	
	private Integer toHour;
	
	private Integer toMin;
	
	private Integer lane;

	private String slotStatus;

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Date getAppointmentDay() {
		return appointmentDay;
	}

	public void setAppointmentDay(Date appointmentDay) {
		this.appointmentDay = appointmentDay;
	}

	public Integer getLane() {
		return lane;
	}

	public void setLane(Integer lane) {
		this.lane = lane;
	}

	public Integer getFromHour() {
		return fromHour;
	}

	public void setFromHour(Integer fromHour) {
		this.fromHour = fromHour;
	}

	public Integer getFromMin() {
		return fromMin;
	}

	public void setFromMin(Integer fromMin) {
		this.fromMin = fromMin;
	}

	public Integer getToHour() {
		return toHour;
	}

	public void setToHour(Integer toHour) {
		this.toHour = toHour;
	}

	public Integer getToMin() {
		return toMin;
	}

	public void setToMin(Integer toMin) {
		this.toMin = toMin;
	}

	public String getSlotStatus() {
		return slotStatus;
	}

	public void setSlotStatus(String slotStatus) {
		this.slotStatus = slotStatus;
	}
}
