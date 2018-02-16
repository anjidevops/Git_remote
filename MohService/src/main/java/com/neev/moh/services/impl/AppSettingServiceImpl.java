package com.neev.moh.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import com.neev.moh.dao.AppSettingsDao;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.AppSettingVO;
import com.neev.moh.services.AppSettingService;

public class AppSettingServiceImpl implements AppSettingService {
	
	private static final MohLogger logger = MohLogFactory.getLoggerInstance(AppSettingServiceImpl.class.getName());

	@Autowired(required = true)
	AppSettingsDao appSettingsDao;

	public AppSettingsDao getAppSettingsDao() {
		return appSettingsDao;
	}

	public void setAppSettingsDao(AppSettingsDao appSettingsDao) {
		this.appSettingsDao = appSettingsDao;
	}

	/*
	 * @Override
	 * 
	 * public List<AppSetting> getServiceByObject(AppSetting
	 * appSettingsObj) {
	 * 
	 * return appSettingsDao.getAppsettingsByObject(appSettingsObj); }
	 * 
	 * @Override
	 * 
	 * public AppSetting getServiceById(String indexNo) {
	 * return
	 * appSettingsDao.getAppsettingsById(indexNo).get(0); // will chage in
	 * future }
	 */

	@Override
	@Cacheable(value="appointmentCache", key="T(com.neev.moh.utils.CacheKeyUtils).generateKey( #p0, 'getAppSetting' )")
	public AppSettingVO getAppSetting(String mainId) {
		logger.log(MohLogger.DEBUG, "getAppSetting()");
		AppSettingVO appSettingVO = new AppSettingVO();
		appSettingVO.setMainId(mainId);
		return appSettingsDao.getAppSettingsById(appSettingVO);
	}

	@Override
	@Cacheable(value="appointmentCache", key="T(com.neev.moh.utils.CacheKeyUtils).generateKey( #p0, 'getAppSettings' )")
	public List<AppSettingVO> getAppSettings(String mainId) {
		logger.log(MohLogger.DEBUG, "getAppSettings()");
		AppSettingVO appSettingVO = new AppSettingVO();
		appSettingVO.setMainId(mainId);
		return appSettingsDao.getAppSettings(appSettingVO);
	}

	@Override
	@Cacheable(value="appointmentCache", key="T(com.neev.moh.utils.CacheKeyUtils).generateKey( #p0, 'getAppSettingsById' )")
	public AppSettingVO getAppSettingsById(String main, String subId, String language, String paramName) {
		logger.log(MohLogger.DEBUG, "getAppSettingsById()");
		return null;// TODO
	}

	@Override
	public String getAppSettingsByIndex(int index) {
		logger.log(MohLogger.DEBUG, "getAppSettingsByIndex()");
		AppSettingVO appSettingVO = appSettingsDao.getAppSettingsByIndex(index);
		logger.log(MohLogger.DEBUG, "appSettingVO: " + appSettingVO);
		return appSettingVO.getValue();
	}

	@Override
	public String getAppSettingValye(String main, String subId, String language, String paramName) {
		logger.log(MohLogger.DEBUG, "getAppSettingsByIndex()");
		AppSettingVO appSettingVO = appSettingsDao.getAppSettingValye(main, subId, language, paramName);
		logger.log(MohLogger.DEBUG, "appSettingVO: " + appSettingVO);
		return appSettingVO.getValue();
	}

}
