package com.neev.moh.facade.convert.populator;

import org.springframework.beans.factory.annotation.Autowired;

import com.neev.moh.facade.dto.DoctorInfoDto;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.Doctor;
import com.neev.moh.services.DepartmentService;

public class DoctorToDto implements Populator<DoctorInfoDto, Doctor> {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(DoctorToDto.class.getName());

	@Autowired(required = true)
	private DepartmentService departmentService;

	@Override
	public void populate(DoctorInfoDto src, Doctor t) throws ConversionException {
		logger.log(MohLogger.DEBUG, "populating...");
		t.setDoc_id(src.getDoctorId());

		// dept = null;//TODO departmentService.getDept(stirng name)
		// t.setDept(dept);

		// User u = new User();
		// u.setFirstName(firstName);
		// src.getName()
	}

}
