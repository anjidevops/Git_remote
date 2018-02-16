package com.neev.moh.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.neev.moh.dao.AppointmentsDao;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.Appointment;
import com.neev.moh.model.Doctor;

@Repository
public class AppointmentsDaoImpl implements AppointmentsDao {

	protected HibernateTemplate template = null;

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(AppointmentsDaoImpl.class.getName());

	public void setSessionFactory(SessionFactory sessionFactory) {
		template = new HibernateTemplate(sessionFactory);
	}

	public boolean isDoctorFreeAt(Date date, Doctor doc) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean createAppointment(Appointment apt) throws DataAccessException {
		logger.log(MohLogger.INFO, "Creating Appointment with id: " + apt.getId());
		Boolean result = false;
		try {
			logger.log(MohLogger.INFO, "Creating Appointment with Doctor Id: " + apt.getDoctor().getDoc_id());
			template.saveOrUpdate(apt);
			logger.log(MohLogger.INFO, "Appointment Created: {}" + apt.getId());
			result = true;
		} catch (DataAccessException e) {
			logger.log(MohLogger.ERROR, "DataAccessException: {}" + e.getMessage());
			result = false;
			throw e;
		}
		logger.log(MohLogger.INFO, "result = " + result);
		return result;
	}

	@Override
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Appointment> getAllAppointment(Date date, Integer docId) throws Exception {
		logger.log(MohLogger.INFO, "getAllAppointment(" + date + ", " + docId + ")");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = (Date) date.clone();
			d2 = (Date) date.clone();
		} catch (Exception e) {
			logger.log(MohLogger.ERROR, "Exception in type casting to Date: {}" + e.getMessage());
		}
		d1.setHours(00);
		d1.setMinutes(00);
		d1.setSeconds(01);
		d2.setHours(23);
		d2.setMinutes(59);
		d2.setSeconds(59);
		// TODO : date between in where clause
		List<Appointment> allAppointments = (List<Appointment>) template.find("from Appointment a where a.doctor.doc_id = ? ORDER BY appointmentDate ASC", docId);
		logger.log(MohLogger.INFO, "allAppointments: " + allAppointments);
		return allAppointments;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Appointment> getAllAppointmentByPatient(Integer patientId) {
		logger.log(MohLogger.INFO, "getAllAppointmentByPatient(" + patientId + ")");
		return (List<Appointment>) template.find("from Appointment a where a.user.Id = ? ORDER BY appointmentDate DESC ", patientId);
	}

	@Override
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Appointment> appointmentNotificationToPatient() {
		logger.log(MohLogger.INFO, "All appointment are notifiing to Patients");
		Date d1 = new Date();
		d1.setHours(23);
		d1.setMinutes(59);
		d1.setSeconds(59);
		Date d2 = (Date) d1.clone();
		d2.setHours(47);
		d2.setMinutes(59);
		d2.setSeconds(59);
		List<Appointment> appointmentList =  (List<Appointment>) template.find("from Appointment a where a.appointmentDate BETWEEN ? and ?", d1, d2);
		logger.log(MohLogger.INFO, "appointmentList: " + appointmentList);
		return appointmentList;
	}

	@Override
	public Appointment getAppointmentById(int id) {
		logger.log(MohLogger.INFO, "getAppointmentById(" + id + ")");
		Appointment appointment = template.get(Appointment.class, id);
		return appointment;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Appointment> getAppointmentByUserEmailOrMobile(String userId) {
		// TODO : Where is the email??
		logger.log(MohLogger.INFO, "getAppointmentByUserEmailOrMobile(" + userId + ")");
		List<Appointment> appointmentList = (List<Appointment>) template.find("from Appointment ap where ap.user.mobile = ? OR ap.user.email = ?", userId, userId);
		logger.log(MohLogger.INFO, "appointmentList: " + appointmentList);
		return appointmentList;
	}

	public void deleteAppointments(List<Appointment> appointments) {
		logger.log(MohLogger.INFO, "Deleting all appointments");
		template.deleteAll(appointments);
		logger.log(MohLogger.INFO, "All appointments deleted");
	}
	
}