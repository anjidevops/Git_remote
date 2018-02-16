package com.neev.moh.dao.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.neev.moh.dao.AppSettingsDao;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.AppSettingVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dao-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager")
public class AppSettingsDaoImplTest {

	static {
		System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
    }
	private static final MohLogger logger = MohLogFactory.getLoggerInstance(AppSettingsDaoImplTest.class.getName());
	
	@Autowired
	private AppSettingsDao appSettingsDao;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAppSettingsByIndex() {
		AppSettingVO vo = appSettingsDao.getAppSettingsByIndex(1);
		logger.log(MohLogger.INFO, vo);
		assertEquals("Oman", vo.getValue());
	}
	
	@Test
	public void testGetAppSettingValye() {
		AppSettingVO vo = appSettingsDao.getAppSettingValye("1" , "1", "English", "Res_Root_Absolute");
		logger.log(MohLogger.INFO, vo);
		assertEquals("/data/tomcat/", vo.getValue());
	}

}
