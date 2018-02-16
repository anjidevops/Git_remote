package com.neev.moh.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.neev.moh.dao.DoctorDao;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.Doctor;

@Repository
public class DoctorDaoImpl implements DoctorDao {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(DoctorDaoImpl.class.getName());

	protected HibernateTemplate template = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		template = new HibernateTemplate(sessionFactory);
	}

	public Integer createDoctor(Doctor doctor) throws DataAccessException {
		logger.log(MohLogger.INFO, "Creating Doctor with id: {}" + doctor.getDoc_id());
		try {
			template.saveOrUpdate(doctor);
			logger.log(MohLogger.INFO, "Doctor Created with id: " + doctor.getDoc_id());
		} catch (DataAccessException e) {
			logger.log(MohLogger.ERROR, "DataAccessException: Creating Doctor with id: " + doctor.getDoc_id());
			throw e;
		}
		return template.get(Doctor.class, doctor.getDoc_id()).getDoc_id();
	}

	public Doctor getDoctorById(Integer docId) {
		logger.log(MohLogger.INFO, "getDoctorById(" + docId + ")");
		return template.get(Doctor.class, docId);
	}

	@SuppressWarnings("unchecked")
	public List<Doctor> loadDoctorsByDepartment(int departmentId) {
		logger.log(MohLogger.INFO, "loadDoctorsByDepartment(" + departmentId + ")");
		List<Doctor> doctors = (List<Doctor>) template.find("from Doctor d where d.dept.id = ?", departmentId);
		logger.log(MohLogger.INFO, "doctors: " + doctors);
		return doctors;
	}

	@SuppressWarnings("unchecked")
	public Doctor getDoctorByEmail(String email) {
		logger.log(MohLogger.INFO, "getDoctorByEmail(" + email + ")");
		List<Doctor> doctors = (List<Doctor>) template.find("from Doctor d where d.user.email = ?", email);
		logger.log(MohLogger.INFO, "doctors: " + doctors);
		if (doctors != null && !doctors.isEmpty()) {
			return doctors.get(0);
		} else {
			logger.log(MohLogger.WARN, "No Doctor Found with email id: " + email);
			return null;
		}
	}
}