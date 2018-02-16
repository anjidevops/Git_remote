package com.neev.moh.services;

import java.util.List;

import com.neev.moh.model.AppSettingVO;

public interface AppSettingService {
	public AppSettingVO getAppSetting(String mainId);
	public List<AppSettingVO> getAppSettings(String mainId);
	AppSettingVO getAppSettingsById(String main, String subId, String language, String paramName);
	String getAppSettingsByIndex(int index);
	String getAppSettingValye(String main, String subId, String language, String paramName);
}
