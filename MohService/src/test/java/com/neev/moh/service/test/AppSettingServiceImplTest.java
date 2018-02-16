package com.neev.moh.service.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.services.AppSettingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class AppSettingServiceImplTest {
	
	private static final MohLogger logger = MohLogFactory.getLoggerInstance(AppSettingServiceImplTest.class.getName());
	
	@Autowired
	private AppSettingService appSettingService ;

	static {
		System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAppSettingsByIndex() {
		String s = appSettingService.getAppSettingsByIndex(1);
		logger.log(MohLogger.INFO, s);
		System.out.println(s);
		assertEquals("Oman", s);
	}
	
	@Test
	public void testGetAppSettingValye() {
		String s = appSettingService.getAppSettingValye("1" , "1", "English", "Res_Root_Absolute");
		logger.log(MohLogger.INFO, s);
		assertEquals("/data/tomcat/", s);
	}
}
