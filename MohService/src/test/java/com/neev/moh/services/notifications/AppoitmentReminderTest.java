package com.neev.moh.services.notifications;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.neev.moh.services.AppointmentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:service-test.xml"})
public class AppoitmentReminderTest {

	static {
		System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
    }

		@Autowired
		private AppointmentService appointmentService;
		
		@Before
		public void setUp() throws DataAccessException, Exception{
		}
		
		@Test
		public void test() throws Exception {
			Object o = new Object();
			assertNotNull("AppointmentService test object : ", o);
		}
		
		public static void main(String[] args) {
			AppoitmentReminderTest notificationTest = new AppoitmentReminderTest();
			
			//load configuration xml file to TEST configured cron job
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("service-test.xml");
			
			try {
				//give time to execute cron job configured in xml file.
				//Thread.sleep(10000);
				
				notificationTest.setUp();
				notificationTest.test();
			}  /*catch (InterruptedException e1) {
				e1.printStackTrace();
			} */ catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	
