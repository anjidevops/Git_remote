package com.neev.moh.facade.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.neev.moh.facade.AppointmentFacade;
import com.neev.moh.facade.dto.AppointmentRecordDto;
import com.neev.moh.facade.dto.UserRegDto;
import com.neev.moh.facade.dto.response.ResponseDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:facade-context.xml" })
public class TransactionalBehaviorTest extends AbstractJUnit4SpringContextTests {
	
	static {
		System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
    }

	private UserRegDto userRegDto1;
	private Date dateToday;
	private AppointmentRecordDto appointmentSame;

	@Autowired
	private AppointmentFacade appointmentFacade;

	@Before
	public void setUp() throws Exception {
		initSameAppointment();

		userRegDto1 = getUserRegDtoOne();
		appointmentFacade.deleteUserAndAppointments(userRegDto1);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * This method is created to test transactional behavior. It does not use
	 * mock objects to hit the actual database and check appointment
	 * availability, delete user, etc.
	 * 
	 * @throws Exception
	 */
	@Test(expected = java.lang.Exception.class)
	public void testCreateUserAndBookAppointment() throws Exception {
		boolean isDoctorAssociated = true;
		ResponseDto responseDto = appointmentFacade.createUserAndBookAppointment(userRegDto1, appointmentSame, isDoctorAssociated);
		assertNotNull(responseDto);
		assertEquals(responseDto.getErrorNo(), 0);
		
		UserRegDto userRegDto2 = getUserRegDtoTwo();
		isDoctorAssociated = true;
		responseDto = appointmentFacade.createUserAndBookAppointment(userRegDto2, appointmentSame, isDoctorAssociated);
		assertNotNull(responseDto);
		assertEquals(responseDto.getErrorNo(), 1);
	}
	
	/**
	 * Creates an  Appointment object with time slot only. 
	 * Neither Doctor nor a Patient is associated with it.
	 * Doctor and Patient needs to be associated with it by the caller method.
	 */
	private void initSameAppointment() {
		dateToday = new Date();
		appointmentSame = new AppointmentRecordDto();
		appointmentSame.setDoctorId(3);
		
		//Fyi not using here but general guidance:
		// Don't set patient id like this, look up patient
		//by email or user name and set that id. If 
		//we delete users from table (but not recreate table,
		//then same user made later will have different id and this will fail.)
		// appointmentOne.setPatientId(5);
		
		appointmentSame.setAppointmentDay(dateToday); // Today
		appointmentSame.setFromHour(12);
		appointmentSame.setFromMin(15);
		appointmentSame.setToHour(13);
		appointmentSame.setToMin(45);
		appointmentSame.setLane(1);
	}
	
	private UserRegDto getUserRegDtoOne() {
		UserRegDto userRegDto = new UserRegDto("First", "One", "first@first.com", new Date(), 1, "1234567890", "moh1");
		return userRegDto;
	}
	
	private UserRegDto getUserRegDtoTwo() {
		UserRegDto userRegDto = new UserRegDto("Second", "Two", "second@second.com", new Date(), 91, "9876543210", "moh2");
		return userRegDto;
	}

}
