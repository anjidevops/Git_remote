package com.neev.moh.facade.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.neev.moh.facade.AppointmentFacade;
import com.neev.moh.facade.convert.populator.AppointmentPopulator;
import com.neev.moh.facade.convert.populator.AppointmentSearchResultPopulator;
import com.neev.moh.facade.convert.populator.PatientRecordPopulator;
import com.neev.moh.facade.dto.AppointmentRecordDto;
import com.neev.moh.facade.dto.UserRegDto;
import com.neev.moh.facade.dto.response.AppointmentSeachResultResDto;
import com.neev.moh.facade.impl.AppointmentFacadeImpl;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.services.AppointmentService;
import com.neev.moh.services.DepartmentService;
import com.neev.moh.services.DoctorService;
import com.neev.moh.services.UserService;
import com.neev.moh.services.impl.AppointmentServiceImpl;
import com.neev.moh.services.impl.DepartmentServiceImpl;
import com.neev.moh.services.impl.DoctorServiceImpl;
import com.neev.moh.services.impl.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:facade-context.xml" })
public class AppointmentFacadeTest extends AbstractJUnit4SpringContextTests {
	
	static {
		System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
    }

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(AppointmentFacadeTest.class.getName());
	private UserRegDto userRegDto1;
	private Date dateToday;
	private AppointmentRecordDto appointment;
	private String userEmail1;

	private UserService userService;
	private AppointmentService appointmentService;
	private DepartmentService departmentService;
	private DoctorService doctorService;
	private AppointmentPopulator appointmentPopulator;
	private PatientRecordPopulator patientRecordPopulator;
	private AppointmentSearchResultPopulator appointmentSearchResultPopulator;

	private AppointmentFacade appointmentFacadeMock;

	@Before
	public void setUp() throws Exception {
		logger.log(MohLogger.INFO, "AppointmentFacadeTest starts...");
		MohLogFactory.printLogs();
		userEmail1 = "patient1@gmail.com";
		userService = mock(UserServiceImpl.class);
		appointmentService = mock(AppointmentServiceImpl.class);
		departmentService = mock(DepartmentServiceImpl.class);
		doctorService = mock(DoctorServiceImpl.class);
		appointmentPopulator = mock(AppointmentPopulator.class);
		patientRecordPopulator = mock(PatientRecordPopulator.class);
		appointmentSearchResultPopulator = mock(AppointmentSearchResultPopulator.class);

		appointmentFacadeMock = new AppointmentFacadeImpl(userService,
				appointmentService, departmentService, doctorService,
				appointmentPopulator, patientRecordPopulator,
				appointmentSearchResultPopulator);

		userRegDto1 = getUserRegDtoOne();
	}

	@After
	public void tearDown() throws Exception {
		logger.log(MohLogger.INFO, "AppointmentFacadeTest ends.");
	}

	@Test
	public void testSearchAppointmentByUserEmailOrMobile() {
		AppointmentSeachResultResDto responseDto = appointmentFacadeMock.searchAppointmentByUserEmailOrMobile(userEmail1);
		assertNotNull(responseDto);
		assertEquals(responseDto.getErrorNo(), 0);
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
