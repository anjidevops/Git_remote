package com.neev.moh.rpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.rpt.dao.DoctorReportDao;
import com.neev.moh.rpt.model.UserCount;
import com.neev.moh.rpt.service.DoctorReportService;

public class DoctorReportServiceImpl implements DoctorReportService {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(DoctorReportServiceImpl.class.getName());

	@Autowired(required = true)
	private DoctorReportDao doctorReportDao;

	public DoctorReportServiceImpl() {
	}

	public DoctorReportServiceImpl(DoctorReportDao doctorReportDao) {
		this.doctorReportDao = doctorReportDao;
	}

	public DoctorReportDao getDoctorReportDao() {
		return doctorReportDao;
	}

	public void setDoctorReportDao(DoctorReportDao doctorReportDao) {
		this.doctorReportDao = doctorReportDao;
	}

	@Override
	public List<UserCount> getNumberAptsPerDoctor() throws Exception {
		logger.log(MohLogger.INFO, "getNumberAptsPerDoctor()");
		List<UserCount> userList = null;
		try {
			userList = doctorReportDao.getNumberAptsPerDoctor();
			logger.log(MohLogger.INFO, "Success: getNumberAptsPerDoctor()");
		} catch (Exception e) {
			logger.log(MohLogger.ERROR, "Exception: " + e.getMessage());
			throw new Exception(e.getMessage());
		}
		logger.log(MohLogger.INFO, "userList: " + userList);
		return userList;
	}

}
