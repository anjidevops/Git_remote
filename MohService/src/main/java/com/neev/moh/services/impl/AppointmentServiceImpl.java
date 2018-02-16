package com.neev.moh.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.neev.moh.dao.AppointmentsDao;
import com.neev.moh.dao.DepartmentDao;
import com.neev.moh.dao.PatientRecordDao;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.Appointment;
import com.neev.moh.model.Doctor;
import com.neev.moh.model.PatientRecord;
import com.neev.moh.services.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(AppointmentServiceImpl.class.getName());

	@Autowired(required = true)
	private PatientRecordDao patientRecordDao;

	@Autowired(required = true)
	private DepartmentDao departmentDao;

	@Autowired(required = true)
	private AppointmentsDao appointmentsDao;

	@Autowired(required = true)
	private JavaMailSender mailSender;

	@Autowired(required = true)
	private VelocityEngine velocityEngine;

	public AppointmentServiceImpl() {
	}

	public AppointmentServiceImpl(PatientRecordDao patientRecordDao) {
		this.patientRecordDao = patientRecordDao;
	}

	public PatientRecordDao getPatientRecordDao() {
		return patientRecordDao;
	}

	public void setPatientRecordDao(PatientRecordDao patientRecordDao) {
		this.patientRecordDao = patientRecordDao;
	}

	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public AppointmentsDao getAppointmentDao() {
		return appointmentsDao;
	}

	public void setAppointmentDao(AppointmentsDao appointmentDao) {
		this.appointmentsDao = appointmentDao;
	}

	@CacheEvict(value="userCache", allEntries=true)
	public Integer createPatientRecord(PatientRecord record) throws Exception {
		logger.log(MohLogger.INFO, "createPatientRecord() called with id = " + record.getId());
		Integer result = patientRecordDao.createPatientRecord(record);
		logger.log(MohLogger.INFO, "result = " + result);
		return result;
	}

	@Cacheable(value="doctorCache", key="T(com.neev.moh.utils.CacheKeyUtils).generateKey( #p0, #p1, 'checkDoctorAvailability' )")
	public boolean checkDoctorAvailability(Doctor doctor, String timeslot) {
		logger.log(MohLogger.INFO, "checkDoctorAvailability()");
		// TODO Auto-generated method stub
		return false;
	}

	@Cacheable(value="patientCache", key="T(com.neev.moh.utils.CacheKeyUtils).generateKey( #p0, 'getRecordsByPatient' )")
	public List<PatientRecord> getRecordsByPatient(String patientName) {
		logger.log(MohLogger.INFO, "getRecordsByPatient()");
		// TODO Auto-generated method stub
		return null;
	}

	@CacheEvict(value="appointmentCache", allEntries=true)
	public boolean createAppointment(Appointment appointment) {
		logger.log(MohLogger.INFO, "createAppointment()");
		boolean status = appointmentsDao.createAppointment(appointment);
		logger.log(MohLogger.INFO, "result = " + status);
		return status;
	}

	public int getDayStartHour(Doctor doctor) {
		return 7;
	}

	public int getDayStartMinute(Doctor doctor) {
		return 30;
	}

	public int getDayEndHour(Doctor doctor) {
		return 15;
	}


	public int getDayEndMinute(Doctor doctor) {
		return 0;
	}

	@Cacheable(value="appointmentCache", key="T(com.neev.moh.utils.CacheKeyUtils).generateKey( #p0, #p1, 'getAllAppointment' )")
	public List<Appointment> getAllAppointment(Date date, Integer doctorId) throws Exception {
		logger.log(MohLogger.INFO, "getAllAppointment()");
		List<Appointment> appointments = appointmentsDao.getAllAppointment(date, doctorId);
		logger.log(MohLogger.INFO, "appointments: " + appointments);
		return appointments;
	}

	@Cacheable(value="appointmentCache", key="T(com.neev.moh.utils.CacheKeyUtils).generateKey( #p0, 'getAllAppointmentByPatient' )")
	public List<Appointment> getAllAppointmentByPatient(Integer patientId) throws Exception {
		logger.log(MohLogger.INFO, "getAllAppointmentByPatient()");
		List<Appointment> appointments = appointmentsDao.getAllAppointmentByPatient(patientId);
		logger.log(MohLogger.INFO, "appointments: " + appointments);
		return appointments;
	}

	public List<Appointment> appointmentNotificationToPatient() throws Exception {
		logger.log(MohLogger.INFO, "appointmentNotificationToPatient()");
		List<Appointment> appointments = null;
		try {
			appointments = appointmentsDao.appointmentNotificationToPatient();
			if (appointments != null && !appointments.isEmpty()) {
				logger.log(MohLogger.INFO, "Appointment Size: " + appointments.size());
				for (Appointment app : appointments) {
					sendReport("moh29Dec@gmail.com", app.getUser().getEmail(), app);
				}
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		logger.log(MohLogger.INFO, "appointments: " + appointments);
		return appointments;
	}

	public void sendReport(final String from, final String to, final Object data) {
		logger.log(MohLogger.INFO, "getRecordsByPatient()");
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
					logger.log(MohLogger.INFO, "Success: Send Report");
				} catch (MessagingException e) {
					logger.log(MohLogger.ERROR, "MessagingException: Send Report");
					logger.log(MohLogger.ERROR, "" + e);
				}
			}
		};
		this.mailSender.send(preparator);
	}
	
	//DONOT cache this method because AppointmentId is used to check for patientRecord for update/creating. 
	//@Cacheable(value="patientCache", key="T(com.neev.moh.utils.CacheKeyUtils).generateKey( #p0, 'getPatientRecordByAppointmentId' )")
	public PatientRecord getPatientRecordByAppointmentId(int appointmentId) {
		logger.log(MohLogger.INFO, "getPatientRecordByAppointmentId()");
		return patientRecordDao.getPatientRecordByAppointmentId(appointmentId);
	}

	@Cacheable(value="patientCache", key="T(com.neev.moh.utils.CacheKeyUtils).generateKey( #p0, 'getPatientRecordById' )")
	public PatientRecord getPatientRecordById(Integer id) {
		logger.log(MohLogger.INFO, "getPatientRecordById()");
		return patientRecordDao.getPatientRecordById(id);
	}

	@Cacheable(value="appointmentCache", key="T(com.neev.moh.utils.CacheKeyUtils).generateKey( #p0, 'getAppointmentById' )")
	public Appointment getAppointmentById(int id) {
		logger.log(MohLogger.INFO, "getAppointmentById()");
		return appointmentsDao.getAppointmentById(id);
	}

	@Cacheable(value="appointmentCache", key="T(com.neev.moh.utils.CacheKeyUtils).generateKey( #p0, 'getAppointmentByUserEmailOrMobile' )")
	public List<Appointment> getAppointmentByUserEmailOrMobile(String userId) {
		logger.log(MohLogger.INFO, "getAppointmentByUserEmailOrMobile()");
		List<Appointment> appointments = appointmentsDao.getAppointmentByUserEmailOrMobile(userId);
		logger.log(MohLogger.INFO, "appointments: " + appointments);
		return appointments;
	}
	
	public void deleteAppointments(List<Appointment> appointments) {
		logger.log(MohLogger.INFO, "Deleting all appointments");
		appointmentsDao.deleteAppointments(appointments);
		logger.log(MohLogger.INFO, "All appointments deleted");
	}
}