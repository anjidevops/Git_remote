package com.neev.moh.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.neev.moh.model.Appointment;
import com.neev.moh.model.Doctor;

public interface AppointmentsDao {
	/**
	 * returns list from start time to end time of a day. with free slots
	 * filling up any non booked slots. So if full day free will return one
	 * Appointment where booked=false and start time is opening day time and end
	 * is last time of day
	 */
	List<Appointment> getAllAppointment(Date date, Integer DocID) throws Exception;

	boolean isDoctorFreeAt(Date date, Doctor doc);

	boolean createAppointment(Appointment apt) throws DataAccessException;

	List<Appointment> getAllAppointmentByPatient(Integer patientId);
	
	List<Appointment> appointmentNotificationToPatient();

	Appointment getAppointmentById(int id);

	List<Appointment> getAppointmentByUserEmailOrMobile(String userId);
	
	void deleteAppointments(List<Appointment> appointments);

}
