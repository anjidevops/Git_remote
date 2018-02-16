package com.neev.moh.dao.test;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.neev.moh.dao.AppointmentsDao;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.Appointment;
import com.neev.moh.model.Doctor;
import com.neev.moh.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dao-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager")
public class AppointmentsDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	static {
		System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
    }

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(AppointmentsDaoTest.class.getName());
	private static Integer doctorId = 2;
	private static Integer patientId = 5;
	private static Integer appointmentId = 4;

	@Autowired
	private AppointmentsDao appointmentsDao;

	@Before
	public void setUp() throws Exception {
		logger.log(MohLogger.INFO, "AppointmentsDaoTest starts...");
		MohLogFactory.printLogs();
		assertNotNull("AppointmentsDao is NULL.", appointmentsDao);
	}

	@After
	public void tearDown() throws Exception {
		logger.log(MohLogger.INFO, "AppointmentsDaoTest ends.");
	}

	@Test
	public void testIsDoctorFreeAt() {
		Date date = getDate();
		Doctor doc = getDoctor();
		boolean status = appointmentsDao.isDoctorFreeAt(date, doc);
		assertNotNull(status);
	}

	// @Test
	public void testCreateAppointment() {
		Appointment appointment = getAppointment();
		Doctor doctor = getDoctor();
		doctor.setDoctorSpec("spec 1");
		User user = getUser();
		user.setFirstName("user1");
		doctor.setUser(user);
		appointment.setDoctor(doctor);
		appointment.setUser(user);
		boolean status = appointmentsDao.createAppointment(appointment);
		assertNotNull(status);
	}

	@Test
	public void testGetAllAppointment() throws Exception {
		Date date = getDate();
		List<Appointment> appointmentList = appointmentsDao.getAllAppointment(date, doctorId);
		assertNotNull(appointmentList);
	}

	@Test
	public void testGetAllAppointmentByPatient() {
		List<Appointment> appointmentList = appointmentsDao.getAllAppointmentByPatient(patientId);
		assertNotNull(appointmentList);
	}

	@Test
	public void testAppointmentNotificationToPatient() {
		List<Appointment> appointmentList = appointmentsDao.appointmentNotificationToPatient();
		assertNotNull(appointmentList);
	}

	@Test
	public void testGetAppointmentById() {
		Appointment appointment = appointmentsDao.getAppointmentById(appointmentId);
		assertNotNull(appointment);
	}

	@Test
	public void testGetAppointmentByUserEmailOrMobile() {
		List<Appointment> appointmentList = appointmentsDao.getAppointmentByUserEmailOrMobile(patientId.toString());
		assertNotNull(appointmentList);
	}

	private Date getDate() {
		Date date = new Date();
		return date;
	}
	
	private Appointment getAppointment(){
		Appointment appointment = new Appointment();
		return appointment;
	}
	
	private Doctor getDoctor() {
		Doctor doctor = new Doctor();
		return doctor;
	}
	
	private User getUser() {
		User user = new User();
		return user;
	}

}
