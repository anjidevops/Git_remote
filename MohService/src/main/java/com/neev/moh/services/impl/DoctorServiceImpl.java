package com.neev.moh.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.neev.moh.dao.DoctorDao;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.Doctor;
import com.neev.moh.services.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {
	
	private static final MohLogger logger = MohLogFactory.getLoggerInstance(DoctorServiceImpl.class.getName());

	@Autowired(required = true)
	private DoctorDao doctorDao;

	@Override
	@Cacheable(value="doctorCache", key="T(com.neev.moh.utils.CacheKeyUtils).generateKey( #p0, 'getDoctorById' )")
	public Doctor getDoctorById(Integer doctorId) {
		logger.log(MohLogger.INFO, "getDoctorById(): " + doctorId);
		return doctorDao.getDoctorById(doctorId);
	}

	public DoctorDao getDoctorDao() {
		return doctorDao;
	}

	public void setDoctorDao(DoctorDao doctorDao) {
		this.doctorDao = doctorDao;
	}
}