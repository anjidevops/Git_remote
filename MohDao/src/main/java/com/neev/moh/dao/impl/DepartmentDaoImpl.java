package com.neev.moh.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.neev.moh.dao.DepartmentDao;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.Department;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(DepartmentDaoImpl.class.getName());

	protected HibernateTemplate template = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		template = new HibernateTemplate(sessionFactory);
	}

	public Integer createDepartment(Department dept) throws DataAccessException {
		logger.log(MohLogger.INFO, "Creating Department with name: " + dept.getDeptName());
		try {
			template.saveOrUpdate(dept);
			logger.log(MohLogger.INFO, "Department Created with id: " + dept.getDept_id());
		} catch (DataAccessException e) {
			logger.log(MohLogger.INFO, "DataAccessException: Creating Department with name: " + dept.getDeptName());
			throw e;
		}
		return template.get(Department.class, dept.getDept_id()).getDept_id();
	}

	public Department getDepartment(Integer dept_id) {
		logger.log(MohLogger.INFO, "getDepartment(" + dept_id + ")");
		return template.get(Department.class, dept_id);
	}

	public void updateDepartment(Department dept) {
		logger.log(MohLogger.INFO, "Updating Department with name: " + dept.getDeptName());
		template.update(dept);

	}

	@SuppressWarnings("unchecked")
	public List<Department> listAllDept() {
		logger.log(MohLogger.INFO, "listAllDept");
		List<Department> departmentList = (List<Department>) template.find("from Department");
		logger.log(MohLogger.INFO, "departmentList: " + departmentList);
		return departmentList;
	}

	@Override
	public Department getDept(String name) {
		logger.log(MohLogger.INFO, "getDept(" + name + ")");
		return (Department) template.find("from Department d where d.deptName = ?", name);
	}
}