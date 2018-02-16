/*package com.neev.moh.facade.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.neev.moh.facade.UserFacade;
import com.neev.moh.facade.impl.UserFacadeImpl;
import com.neev.moh.model.User;
import com.neev.moh.services.UserService;
import com.neev.moh.services.impl.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:facade-context.xml")
public class UserFacadeTest {

	static {
		System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
    }
	
	private static UserService userService;	
	private static User mockUser1;	
	private UserFacade userFacade;
	
	@Before
	public void setUp() throws DataAccessException, Exception{

		userService = mock(UserServiceImpl.class);
		
		mockUser1 = new User("firstname1","lastname1","test1@email.com",new Date(),91, "001", "mph");
		userFacade = new UserFacadeImpl(userService);

		when(userService.createUser(mockUser1)).thenReturn(Integer.getInteger(mockUser1.getMobile()));
		when(userService.getUserById(001)).thenReturn(mockUser1);
		when(userService.getUserByEmail("test1@email.com")).thenReturn(mockUser1);
		when(userService.getUserByMobile("001")).thenReturn(mockUser1);			
		
	}
	
	@Test
	public void testGetUserByEmail() {
		User user1 = userFacade.getUserByEmail("test1@email.com");
		Assert.assertSame("Get user by email id failed", user1.getEmail() , "test1@email.com");
	}
	
	// implement other test cases for other facade methods

}
*/