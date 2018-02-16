package com.neev.moh.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.neev.moh.dao.DepartmentDao;
import com.neev.moh.dao.DoctorDao;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.Department;
import com.neev.moh.model.Doctor;
import com.neev.moh.services.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	private static final MohLogger logger = MohLogFactory.getLoggerInstance(DepartmentServiceImpl.class.getName());

	@Autowired(required = true)
	private DepartmentDao departmentDao;

	@Autowired(required = true)
	private DoctorDao doctorDao;

	public DepartmentServiceImpl() {
	}

	public DepartmentServiceImpl(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Override
	@Cacheable(value="departmentCache", key="T(com.neev.moh.utils.CacheKeyUtils).generateKey( 'listAllDept' )")
	public List<Department> listAllDept() {
		logger.log(MohLogger.DEBUG, "listAllDept()");
		return departmentDao.listAllDept();
	}

	@Override
	@Cacheable(value="doctorCache", key="T(com.neev.moh.utils.CacheKeyUtils).generateKey( #p0, 'loadDoctorsByDepartment' )")
	public List<Doctor> loadDoctorsByDepartment(int departmentId) {
		logger.log(MohLogger.DEBUG, "loadDoctorsByDepartment(): " + departmentId);
		return doctorDao.loadDoctorsByDepartment(departmentId);
	}

	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public DoctorDao getDoctorDao() {
		return doctorDao;
	}

	public void setDoctorDao(DoctorDao doctorDao) {
		this.doctorDao = doctorDao;
	}
}