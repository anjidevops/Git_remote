package com.neev.moh.facade;

import org.springframework.stereotype.Service;

import com.neev.moh.facade.dto.response.DepartmentResDto;
import com.neev.moh.facade.dto.response.DoctorListResDto;

@Service
public interface DepartmentFacade {

	DepartmentResDto getAllDepartment();

	DoctorListResDto loadDoctorsByDepartment(String departmentId);

}
