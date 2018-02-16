package com.neev.moh.web.controller.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.neev.moh.dao.UserDao;
import com.neev.moh.facade.UserFacade;
import com.neev.moh.facade.impl.UserFacadeImpl;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.User;
import com.neev.moh.services.UserService;
import com.neev.moh.web.controller.AppointmentController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:controller-context.xml")
@WebAppConfiguration
@EnableWebMvc
public class AppointmentControllerTest {

	static {
		System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
	}

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(AppointmentControllerTest.class.getName());

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private AppointmentController appointmentController;

	private UserFacade userFacade;

	private static UserService userService;
	private static UserDao userDao;
	private static User mockUser;
	private static MockHttpServletRequest request;
	private static MockHttpServletResponse response;
	private static MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		logger.log(MohLogger.INFO, "AppointmentControllerTest starts...");
		MohLogFactory.printLogs();
		userDao = mock(UserDao.class);
		userService = mock(UserService.class);
		userFacade = new UserFacadeImpl(userService);
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

		mockUser = new User("firstname", "lastname", "test@email.com", new Date(), 91, "007", "moh");

		when(userFacade.getUserByEmail("test@email.com")).thenReturn(mockUser);
	}
	
	@After
	public void tearDown() throws Exception {
		logger.log(MohLogger.INFO, "AppointmentControllerTest ends.");
	}

	@Test
	public void test() {
		Assert.assertSame("Assertion Test", "test", "test");
	}

	// @Test
	public void testBookAppointment() throws Exception {

	}

}
