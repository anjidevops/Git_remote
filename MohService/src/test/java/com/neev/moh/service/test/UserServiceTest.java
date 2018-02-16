package com.neev.moh.service.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.neev.moh.dao.UserDao;
import com.neev.moh.model.User;
import com.neev.moh.services.UserService;
import com.neev.moh.services.impl.UserServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:service-context.xml")
public class UserServiceTest {

	static {
		System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
    }

	private static UserDao userDao;
	private static User mockUser1;
	private static User mockUser2;

	private UserService userService;
	
	@Before
	public void setUp() throws DataAccessException, Exception{
		userDao = mock(UserDao.class);
		
		mockUser1 = new User("firstname1","lastname1","test1@email.com",new Date(),91, "001", "mph");
		mockUser2 = new User("firstname2","lastname2","test2@email.com",new Date(),91, "002", "mph");
				
		userService = new UserServiceImpl(userDao);

		when(userDao.createUser(mockUser1)).thenReturn(Integer.getInteger(mockUser1.getMobile()));
		when(userDao.getUserById(001)).thenReturn(mockUser1);
		when(userDao.getUserByEmail("test1@email.com")).thenReturn(mockUser1);
		when(userDao.getUserByMobile("001")).thenReturn(mockUser1);
		when(userDao.listUsers()).thenReturn(Arrays.asList(mockUser1, mockUser2));
		
		
	}
	
	@Test
	public void testGetUserById() {
		User user1 = userService.getUserById(001);
		Assert.assertSame("Get user by Id failed", user1.getMobile() , "001");
	}
	
	@Test
	public void testGetUserByEmail() {
		User user1 = userService.getUserByEmail("test1@email.com");
		Assert.assertSame("Get user by email id failed", user1.getEmail() , "test1@email.com");
	}
	
	@Test
	public void testGetUserByMobile() {
		User user1 = userService.getUserByMobile("001");
		Assert.assertSame("Get user by mobile failed", user1.getMobile() , "001");
	}
	
	@Test
	public void testListUsers() {
		List<User> userList = userService.listUsers();
		assertEquals(2, userList.size());
		
		User user1 = userList.get(0);
		assertEquals("firstname1", user1.getFirstName());
		assertEquals("lastname1", user1.getLastName());
		assertEquals("test1@email.com", user1.getEmail());
		assertEquals("001", user1.getMobile());
		assertEquals("mph", user1.getPassword());
		
	}
	
	@Test
	public void testUpdateUser() {
		mockUser1.setPassword("pwd");
		userService.updateUser(mockUser1);
		Assert.assertSame("Update user failed", userService.getUserById(001).getPassword() , "pwd");
	}
	
	
}
