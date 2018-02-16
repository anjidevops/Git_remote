package com.neev.moh.logger;

import java.util.logging.Logger;

public class MohLogFactory {

	public static MohLogger getLoggerInstance(final String className) {
		MohLogger logger = new MohLog4j2LoggerImpl(className);
		return logger;
	}

	public static MohLogger getLoggerInstance(final String className, final String contractName) {
		MohLogger logger = new MohLog4j2LoggerImpl(className);
		return logger;
	}
	
	public static void printLogs() {
		printSlf4jLogs();
		printLog4j2Logs();
		printJulLogs();
	}
	
	public static void printSlf4jLogs() {
		final MohLogger logger = new MohSlf4jLoggerImpl(MohLogFactory.class.getName());
		logger.log(MohLogger.INFO,  "--------- Slf4j INFO  Log ---------");
		logger.log(MohLogger.DEBUG, "--------- Slf4j DEBUG Log ---------");
		logger.log(MohLogger.WARN,  "--------- Slf4j WARN  Log ---------");
		logger.log(MohLogger.ERROR, "--------- Slf4j ERROR Log ---------");
	}
	
	public static void printLog4j2Logs() {
		final MohLogger logger = new MohLog4j2LoggerImpl(MohLogFactory.class.getName());
		logger.log(MohLogger.INFO,  "--------- Log4j2 INFO  Log ---------");
		logger.log(MohLogger.DEBUG, "--------- Log4j2 DEBUG Log ---------");
		logger.log(MohLogger.WARN,  "--------- Log4j2 WARN  Log ---------");
		logger.log(MohLogger.ERROR, "--------- Log4j2 ERROR Log ---------");
	}
	
	public static void printJulLogs() {
		final Logger jul = Logger.getLogger("com/neev/moh/logger/MohLogFactory.getName()");
		jul.info(   "--------- J.U.L. INFO    Log ---------");
		jul.warning("--------- J.U.L. WARNING Log ---------");
		jul.severe( "--------- J.U.L. SEVERE  Log ---------");
	}
}