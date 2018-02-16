package com.neev.moh.facade.impl;

import java.io.File;

import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.services.AppSettingService;

public class ConfigurationHelper {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(ConfigurationHelper.class.getName());

	/*
	 * DO NOT change call to appSettingService.appSettingService(int index). The
	 * value is referred in appServlet.xml for defining as staticResource, for
	 * downloading report.
	 */

	public static final File getFolderPrescriptionPath(final AppSettingService appSettingService , final String username, final String todayDateString) {
		logger.log(MohLogger.INFO, "getFolderPrescriptionPath()");
		File f = new File(appSettingService.getAppSettingValye("1" , "1", "English", "Res_Root_Absolute"), appSettingService.getAppSettingValye("1" , "1", "English", "Attachment_Dir_Location"));
		String attachmentDirPath = username + "/" + todayDateString;
		File mediaDir = new File(f, attachmentDirPath);
		if (false == mediaDir.exists()) {
			logger.log(MohLogger.INFO, "Directory Not found. creating mediaDir ");
			mediaDir.mkdirs();
		} else {
			logger.log(MohLogger.INFO, "Skipping, directory exists");
		}
		return mediaDir;
	}

	public static final File getFolderPrescriptionPathWithoutRoot(final AppSettingService appSettingService , final String username, final String todayDateString) {
		logger.log(MohLogger.INFO, "getFolderPrescriptionPathWithoutRoot()");
		String attachmentDirPath = username + "/" + todayDateString;
		File mediaDir = new File(appSettingService.getAppSettingValye("1" , "1", "English", "Attachment_Dir_Location"), attachmentDirPath);
		return mediaDir;
	}

	public static final String getFileDownloadUrl(final AppSettingService appSettingService , String reportPath) {
		logger.log(MohLogger.INFO, "getFileDownloadUrl(): " + reportPath);
		return appSettingService.getAppSettingValye("1" , "1", "English", "DOWNLOAD_REPORT_PREFIX") + reportPath;
	}

}
