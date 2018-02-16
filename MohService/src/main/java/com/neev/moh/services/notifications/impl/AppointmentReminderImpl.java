package com.neev.moh.services.notifications.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.neev.moh.dao.AppointmentsDao;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.Appointment;
import com.neev.moh.services.notifications.AppointmentReminder;

public class AppointmentReminderImpl implements AppointmentReminder {
	
	private static final MohLogger logger = MohLogFactory.getLoggerInstance(AppointmentReminderImpl.class.getName());

	@Autowired(required = true)
	private AppointmentsDao appointmentsDao;

	@Autowired(required = true)
	private JavaMailSender mailSender;

	@Autowired(required = true)
	private VelocityEngine velocityEngine;

	@Override
	public List<Appointment> appointmentNotificationToPatient() throws Exception {
		logger.log(MohLogger.DEBUG, "AppointmentReminderImpl.appointmentNotificationToPatient()");
		List<Appointment> appointments = null;
		try {
			appointments = appointmentsDao.appointmentNotificationToPatient();
			logger.log(MohLogger.INFO, "Appointment Size: " + appointments.size());
			if (appointments != null && !appointments.isEmpty()) {
				logger.log(MohLogger.INFO, "Appointment Size: " + appointments.size());
				for (Appointment app : appointments) {
					sendReport("moh29Dec@gmail.com", app.getUser().getEmail(), app);
				}
			}
			logger.log(MohLogger.INFO, "Success: AppointmentReminderImpl.appointmentNotificationToPatient()");
		} catch (Exception e) {
			logger.log(MohLogger.ERROR, "Exception: AppointmentReminderImpl.appointmentNotificationToPatient()");
			throw new Exception(e.getMessage());
		}
		return appointments;
	}

	@Override
	public void autoAppointmentNotification() throws Exception {
		logger.log(MohLogger.DEBUG, "AppointmentReminderImpl.autoAppointmentNotification()");
		List<Appointment> appointments = null;
		try {
			appointments = appointmentsDao.appointmentNotificationToPatient();
			if (appointments != null && !appointments.isEmpty()) {
				logger.log(MohLogger.DEBUG, "Appointment Size: " + appointments.size());
				for (Appointment app : appointments) {
					sendReport("moh29Dec@gmail.com", app.getUser().getEmail(), app);
				}
			}
			logger.log(MohLogger.INFO, "Success: AppointmentReminderImpl.autoAppointmentNotification()");
		} catch (Exception e) {
			logger.log(MohLogger.ERROR, "Exception: AppointmentReminderImpl.autoAppointmentNotification()");
			throw new Exception(e.getMessage());
		}
	}

	public void sendReport(final String from, final String to, final Object data) {
		logger.log(MohLogger.DEBUG, "AppointmentReminderImpl.sendReport()");
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws VelocityException {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				try {
					message.setFrom(from);
					message.setTo(to);
					message.setSubject("Notification for Doctor Visiting");
					Map<String, Object> model = new HashMap<String, Object>();
					model.put("key", data);
					@SuppressWarnings("deprecation")
					String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "notificationToPatientTemplate.vm", model);
					message.setText(text, true);
					logger.log(MohLogger.INFO, "Success: AppointmentReminderImpl.sendReport()");
				} catch (MessagingException e) {
					logger.log(MohLogger.ERROR, "MessagingException: AppointmentReminderImpl.sendReport()");
					logger.log(MohLogger.ERROR, "" + e);
				}
			}
		};
		this.mailSender.send(preparator);
	}
}