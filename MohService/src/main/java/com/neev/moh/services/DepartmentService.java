package com.neev.moh.services;

import java.util.List;

import com.neev.moh.model.Department;
import com.neev.moh.model.Doctor;

public interface DepartmentService {

	List<Department> listAllDept();

	List<Doctor> loadDoctorsByDepartment(int departmentId);

}
