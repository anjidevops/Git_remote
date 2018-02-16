package com.neev.moh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.neev.moh.dao.AppSettingsDao;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.AppSettingVO;

public class AppSettingsDaoImpl implements AppSettingsDao {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(AppSettingsDaoImpl.class.getName());

	protected HibernateTemplate template = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		template = new HibernateTemplate(sessionFactory);
	}

	@Override
	@SuppressWarnings("unchecked")
	public AppSettingVO getAppSettingsById(AppSettingVO appSettingVO) {
		logger.log(MohLogger.INFO, "getAppSettingsById(" + appSettingVO.getMainId() + ")");
		List<AppSettingVO> appSetingList = (List<AppSettingVO>) template.find("from AppSettingVO a where a.mainId = ?", appSettingVO.getMainId());
		logger.log(MohLogger.INFO, "appSetingList: " + appSetingList);
		AppSettingVO appSettingVO2 = (AppSettingVO) appSetingList.get(0);
		logger.log(MohLogger.INFO, "appSettingVO: " + appSettingVO2.toString());
		return appSettingVO2;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<AppSettingVO> getAppSettings(AppSettingVO appSettingVO) {
		logger.log(MohLogger.INFO, "getAppSettings()");
		List<AppSettingVO> result = new ArrayList<AppSettingVO>();
		// Criteria criteria = session.createCriteria(AppSettingVO.class);
		if ((appSettingVO.getMainId() != null) && (!appSettingVO.getMainId().isEmpty())) {
			logger.log(MohLogger.INFO, "Find by main id");
			result = (List<AppSettingVO>) template.find("from AppSettingVO a where a.mainId = ?", appSettingVO.getMainId());
			if (result != null) {
				return result;
			}
		}
		if ((appSettingVO.getSubId() != null) && (!appSettingVO.getSubId().isEmpty())) {
			logger.log(MohLogger.INFO, "Find by index no");
			result = (List<AppSettingVO>) template.find("from AppSettingVO a where a.indexNo = ?", appSettingVO.getSubId());
			if (result != null) {
				return result;
			}
		}

		if ((appSettingVO.getLanguage() != null) && (!appSettingVO.getLanguage().isEmpty())) {
			logger.log(MohLogger.INFO, "Find by language");
			result = (List<AppSettingVO>) template.find("from AppSettingVO a where a.language = ?", appSettingVO.getLanguage());
			if (result != null) {
				return result;
			}
		}

		if ((appSettingVO.getParamName() != null) && (!appSettingVO.getParamName().isEmpty())) {
			logger.log(MohLogger.INFO, "Find by param name");
			result = (List<AppSettingVO>) template.find("from AppSettingVO a where a.paramName = ?", appSettingVO.getParamName());
			if (result != null) {
				return result;
			}
		}
		logger.log(MohLogger.INFO, "result: " + result);
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public AppSettingVO getAppSettingsByIndex(int index) {
		logger.log(MohLogger.INFO, "getAppSettingsById(" + index + ")");
		List<AppSettingVO> appSetingList = (List<AppSettingVO>) template.find("from AppSettingVO a where a.indexNo = ?", index);
		AppSettingVO appSettingVO = new AppSettingVO();
		if (appSetingList != null && !appSetingList.isEmpty()) {
			appSettingVO = appSetingList.get(0);
		}
		logger.log(MohLogger.INFO, "appSettingVO: " + appSettingVO);
		return appSettingVO;
	}

	@Override
	@SuppressWarnings("unchecked")
	public AppSettingVO getAppSettingValye(String mainId, String subId, String language, String paramName) {
		logger.log(MohLogger.INFO, "getAppSettingsById(" + mainId + ", "+subId+", "+language+", "+paramName+ ")");
		List<AppSettingVO> appSetingList = (List<AppSettingVO>) template.find("from AppSettingVO a where a.mainId = ? and a.subId = ? and a.language = ? and a.paramName = ?", mainId, subId, language, paramName);
		AppSettingVO appSettingVO = new AppSettingVO();
		if (appSetingList != null && !appSetingList.isEmpty()) {
			appSettingVO = appSetingList.get(0);
		}
		logger.log(MohLogger.INFO, "appSettingVO: " + appSettingVO);
		return appSettingVO;
	}

}
