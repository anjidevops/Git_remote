package com.neev.moh.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Appointment")
public class Appointment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int Id;

	@OneToOne //(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "doc_id", nullable = false)
	private Doctor doctor;

	@Column(name = "appointment_date")
	private Date appointmentDate;

	@Column(name = "appointment_end_hour")
	private int appointmentEndHour;

	@Column(name = "appointment_end_min")
	private int appointmentEndMin;

	@Column(name = "lanes")
	private int lanes;

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name="slot_status")
	private String slotStatus;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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

	public int getLanes() {
		return lanes;
	}

	public void setLanes(int lanes) {
		this.lanes = lanes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getSlotStatus() {
		return slotStatus;
	}

	public void setSlotStatus(String slotStatus) {
		this.slotStatus = slotStatus;
	}

	@Override
	public String toString() {
		return "Appointment [Id=" + Id + ", doctor=" + doctor
				+ ", appointmentDate=" + appointmentDate
				+ ", appointmentEndHour=" + appointmentEndHour
				+ ", appointmentEndMin=" + appointmentEndMin + ", lanes="
				+ lanes + ", user=" + user + "]";
	}
}