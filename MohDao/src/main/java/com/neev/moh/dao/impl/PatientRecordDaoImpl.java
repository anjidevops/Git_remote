package com.neev.moh.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.neev.moh.dao.PatientRecordDao;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.PatientRecord;

@Repository
public class PatientRecordDaoImpl implements PatientRecordDao {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(PatientRecordDaoImpl.class.getName());

	protected HibernateTemplate template = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		template = new HibernateTemplate(sessionFactory);
	}

	public Integer createPatientRecord(PatientRecord record) throws DataAccessException {
		logger.log(MohLogger.INFO, "Creating Patient Record");
		try {
			template.saveOrUpdate(record);
			logger.log(MohLogger.INFO, "Success: Creating Patient Record");
		} catch (DataAccessException e) {
			logger.log(MohLogger.ERROR, "DataAccessException: Creating Patient Record");
			throw e;
		}
		return template.get(PatientRecord.class, record.getId()).getId();
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean isDoctorAvailable(String doctor, Date date, int startHour, int StartMin) {
		// TODO
		logger.log(MohLogger.INFO, "isDoctorAvailable(): " + doctor);
		List<PatientRecord> prs = (List<PatientRecord>) template.find("from PatientRecord pr where pr.doctorName=? AND pr.appointmentTime=?", doctor, -1);
		if (prs == null) {
			return true;
		} else if (prs != null && prs.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public PatientRecord getPatientRecordByAppointmentId(int appointmentId) {
		logger.log(MohLogger.INFO, "getPatientRecordByAppointmentId(): " + appointmentId);
		List<PatientRecord> prs = (List<PatientRecord>) template.find("from PatientRecord pr where pr.appopintmentId=?", appointmentId);
		if (prs != null && false == prs.isEmpty()) {
			return prs.get(0);
		}
		return null;
	}

	@Override
	public PatientRecord getPatientRecordById(Integer id) {
		logger.log(MohLogger.INFO, "getPatientRecordById(): " + id);
		return template.get(PatientRecord.class, id);
	}

}
