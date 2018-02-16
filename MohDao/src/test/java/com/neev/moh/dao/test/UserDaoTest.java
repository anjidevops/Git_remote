package com.neev.moh.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.neev.moh.dao.UserDao;

import com.neev.moh.model.Address;
import com.neev.moh.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:dao-context.xml")
@TransactionConfiguration
@Transactional
public class UserDaoTest{

	static {
		System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
    }

	@Autowired 
	private UserDao userDao = null;
	
	
	@Test
	public void testUserDao() throws SQLException {
		assertNotNull("UserDao is null.", userDao);
		List<User> users = userDao.listUsers();
		assertNotNull("User list is null.", users);
		assertEquals("Minimum Users in DB",true,users.size() > 0);
		for (User user : users) {
			assertNotNull("User is null", user);			
		}		
	}
	
	@Test
	public void testUserDaoUpdate() throws SQLException{
		final String tempMobile = "001122";
		User user = userDao.getUserByEmail("admin@gmail.com");
		user.setMobile(tempMobile);
		userDao.updateUser(user);
		User user2 = userDao.getUserByEmail("admin@gmail.com");
		assertEquals("User Mobile Updated",tempMobile,user2.getMobile());		
	}
	
	@Test
	public void testUserDaoDelete() throws SQLException{
		User user = userDao.getUserByEmail("admin@gmail.com");
		userDao.deleteUser(user);
		User user3 = userDao.getUserByEmail("admin@gmail.com");
		assertEquals("Delete User",null,user3);	
	}
	
	@Test
	public void testUserDaoCreate() throws SQLException, DataAccessException, Exception {

		User user = buildUser();
		Integer result = userDao.createUser(user);
		assertNotNull("New User Created", result);
		
		}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void testUserDaoCreateException() throws SQLException, DataAccessException, Exception {

		User user = buildUser();
		user.setEmail("admin@gmail.com");
		Integer result = userDao.createUser(user);
		assertEquals("Duplicate User Created",null,result);	
		
		}

	private User buildUser() {
		User user1 = new User();
		user1.setFirstName("testUser");
		user1.setLastName("testUser");
		user1.setEmail("test@gmail.com");
		user1.setDob(new Date());
		user1.setMobileCode(91);
		user1.setMobile("0001");
		user1.setPassword("moh");
		user1.setRoleType("Admin");

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
