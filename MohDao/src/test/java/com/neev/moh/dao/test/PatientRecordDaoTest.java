package com.neev.moh.dao.test;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.neev.moh.dao.PatientRecordDao;
import com.neev.moh.dao.UserDao;
import com.neev.moh.model.PatientRecord;
import com.neev.moh.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:dao-context.xml")
public class PatientRecordDaoTest {

	static {
		System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
    }

	@Autowired 
	private PatientRecordDao patientRecordDao = null;
	@Autowired 
	private UserDao userDao = null;
	
	@Test
	public void test(){
		assertNotNull("patientRecordDao is null.", patientRecordDao);
	}
	
	//@Test
	public void testPatientRecord() throws SQLException, DataAccessException, Exception {
		assertNotNull("patientRecordDao is null.", patientRecordDao);
		User user = userDao.getUserByEmail("admin@gmail.com");
		assertNotNull("Get User By Email", user);
		PatientRecord record = new PatientRecord();
		//record.setUser(user);
		Object o = null;
		assertNotNull("TODO", o);

		
		Integer recordID = patientRecordDao.createPatientRecord(record);
		assertNotNull("New Patient Appointment", recordID);
	}
}
