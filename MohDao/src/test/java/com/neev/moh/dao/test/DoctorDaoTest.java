/*package com.neev.moh.dao.test;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.neev.moh.dao.DepartmentDao;
import com.neev.moh.dao.DoctorDao;

import com.neev.moh.model.Address;
import com.neev.moh.model.Department;
import com.neev.moh.model.Doctor;
import com.neev.moh.model.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:dao-context.xml")
@TransactionConfiguration
@Transactional
public class DoctorDaoTest {

	static {
		System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
    }

	@Autowired 
	private DepartmentDao departmentDao = null;
	
	@Autowired 
	private DoctorDao doctorDao = null;
	
	@Test
	public void testDoctorDao() throws SQLException {
		assertNotNull("deptDao is null.", departmentDao);
		assertNotNull("DoctorDao is null.", doctorDao);
	}
	
	@Test
	public void ManyDoctorsToOneDept() throws SQLException, DataAccessException, Exception {
		
		Department dept = new Department();
		dept.setDeptName("TESTDEPT");
		dept.setDeptDesc("TEST Description");
		
		User user1 = buildDoctorUser();
		user1.setFirstName("Doctor1");
		user1.setLastName("Doctor1");
		user1.setEmail("testdoctor1@gmail.com");
		user1.setMobile("00011234");
		
		User user2 = buildDoctorUser();
		user2.setFirstName("Doctor2");
		user2.setLastName("Doctor2");
		user2.setEmail("testdoctor2@gmail.com");
		user2.setMobile("0001123");
		
		Doctor d1 = new Doctor();
		d1.setUser(user1);
		d1.setRegNumber(9999);
		d1.setDoctorSpec("spec1");
		d1.setDept(dept);
		
		Doctor d2 = new Doctor();
		d2.setUser(user2);
		d2.setRegNumber(9090);
		d2.setDoctorSpec("spec2");
		d2.setDept(dept);
		
		
		Integer result1 = departmentDao.createDepartment(dept);
		assertNotNull("New Department Created", result1);
		
		Integer result2 = doctorDao.createDoctor(d1);
		assertNotNull("New Doctor1 Created", result2);	
		
		Integer result3 = doctorDao.createDoctor(d2);
		assertNotNull("New Doctor2 Created", result3);
	}
	
	private User buildDoctorUser() {
		User user1 = new User();
		
		user1.setDob(new Date());
		user1.setMobileCode(91);
		user1.setPassword("moh");
		user1.setRoleType("Doctor");

		Address home = new Address();
		home.setStreet1("RT Nagar");
		home.setStreet2("Hebbal");
		home.setCity("bangalore");
		home.setCountry("india");
		home.setPincode(560024);
		home.setUser(user1);

		Address office = new Address();
		office.setStreet1("Mindspcace");
		office.setStreet2("madhapur");
		office.setCity("hyderabad");
		office.setCountry("india");
		office.setPincode(524201);
		office.setUser(user1);

		Set<Address> addresses = new HashSet<Address>();

		addresses.add(home);
		addresses.add(office);

		user1.setAddress(addresses);
		return user1;
	}	



}
*/