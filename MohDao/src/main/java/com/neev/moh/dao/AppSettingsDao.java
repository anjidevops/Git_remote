package com.neev.moh.dao;

import java.util.List;

import com.neev.moh.model.AppSettingVO;

public interface AppSettingsDao {
	List<AppSettingVO> getAppSettings(AppSettingVO appSettingVO);

	AppSettingVO getAppSettingsById(AppSettingVO appSettingVO);

	AppSettingVO getAppSettingsByIndex(int index);

	AppSettingVO getAppSettingValye(String main, String subId, String language, String paramName);
}
