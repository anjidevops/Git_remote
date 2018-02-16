package com.neev.moh.services.notifications;

import java.util.List;

import com.neev.moh.model.Appointment;

public interface AppointmentReminder {
	public List<Appointment> appointmentNotificationToPatient() throws Exception;
	void autoAppointmentNotification() throws Exception;
}
