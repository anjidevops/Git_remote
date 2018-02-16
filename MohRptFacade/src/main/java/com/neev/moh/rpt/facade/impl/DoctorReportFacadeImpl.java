package com.neev.moh.rpt.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.rpt.facade.DoctorReportFacade;
import com.neev.moh.rpt.facade.dto.response.UserCountRes;
import com.neev.moh.rpt.model.UserCount;
import com.neev.moh.rpt.service.DoctorReportService;

@Transactional(rollbackFor=Exception.class)
public class DoctorReportFacadeImpl implements DoctorReportFacade {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(DoctorReportFacadeImpl.class.getName());

	@Autowired(required = true)
	private DoctorReportService doctorReportService;

	@Override
	public UserCountRes getNumberAptsPerDoctor() {
		logger.log(MohLogger.INFO, "getNumberAptsPerDoctor()");
		UserCountRes userCountRes = new UserCountRes();
		try {
			List<UserCount> list = doctorReportService.getNumberAptsPerDoctor();
			if (list != null && list.size() > 0) {
				userCountRes.setUserCountList(list);
			}
			logger.log(MohLogger.INFO, "Success: getNumberAptsPerDoctor()");
		} catch (Exception e) {
			logger.log(MohLogger.ERROR, "Exception: getNumberAptsPerDoctor()");
			if (e instanceof DataIntegrityViolationException) {
				userCountRes.setErrorNo(1);
				userCountRes.setErrorMsg("Getting User count List Fail");
				logger.log(MohLogger.ERROR, "DataIntegrityViolationException: Getting User count List Fail");
			} else {
				userCountRes.setErrorMsg(e.getMessage());
				userCountRes.setErrorNo(0);
				logger.log(MohLogger.ERROR, "Exception: " + e.getMessage());
			}
		}
		return userCountRes;
	}

}
