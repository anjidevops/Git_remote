package com.neev.moh.facade.dto;

import java.util.Date;

import com.neev.moh.model.Doctor;
import com.neev.moh.model.User;

public class AppointmentDto {

	/** 
	 * see xls
	 * */
	private String bookType;
	
	private int lane;//1,2 or 3
	
	private boolean booked;
	
	//DTO TODO?
	private String doctor;
	
	private String doctorDept;

	private Date appointmentDate;

	private int appointmentEndHour;

	private int appointmentEndMin;
	//UserDTO TODO the patiend
	private String patient;
	
	public AppointmentDto() {
	}

	public AppointmentDto(Date date, int endHour, int endMin) {
		booked = false;
		this.appointmentDate = date;
		this.appointmentEndHour = endHour;
		this.appointmentEndMin = endMin;
	}
	
	public AppointmentDto(boolean booked, String doctor, String doctorDept, Date appointmentDate, int appointmentEndHour,
			int appointmentEndMin, String user) {
		super();
		this.booked = booked;
		this.doctorDept = doctorDept;
		this.appointmentDate = appointmentDate;
		this.appointmentEndHour = appointmentEndHour;
		this.appointmentEndMin = appointmentEndMin;
	}

	public boolean isBooked() {
		return booked;
	}

	public void setBooked(boolean booked) {
		this.booked = booked;
	}

	public String getDoctorDept() {
		return doctorDept;
	}

	public void setDoctorDept(String doctorDept) {
		this.doctorDept = doctorDept;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public int getAppointmentEndHour() {
		return appointmentEndHour;
	}

	public void setAppointmentEndHour(int appointmentEndHour) {
		this.appointmentEndHour = appointmentEndHour;
	}

	public int getAppointmentEndMin() {
		return appointmentEndMin;
	}

	public void setAppointmentEndMin(int appointmentEndMin) {
		this.appointmentEndMin = appointmentEndMin;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String user) {
		this.patient = user;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public int getLane() {
		return lane;
	}

	public void setLane(int lane) {
		this.lane = lane;
	}

}
