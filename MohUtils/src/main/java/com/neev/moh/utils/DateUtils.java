package com.neev.moh.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;

public class DateUtils {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(DateUtils.class.getName());

	private DateUtils() {
	}

	public static Date toDate(final String format, final String textDate) {
		logger.log(MohLogger.INFO, "toDate()");
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(textDate);
		} catch (ParseException e) {
			logger.log(MohLogger.ERROR, "Exception: " + e.getMessage());
		}
		logger.log(MohLogger.INFO, "date = " + date);
		return date;
	}

	public static String toString(final String format, final Date date) {
		String dateString = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			dateString = sdf.format(date);
		} catch (Exception e) {
			logger.log(MohLogger.ERROR, "Exception: " + e.getMessage());
		}
		logger.log(MohLogger.INFO, "dateString = " + dateString);
		return dateString;
	}

	public static boolean isDateExistInDateRange(Date date1, Date date2, Date targetDate) {
		if (targetDate.after(date1) && targetDate.before(date2)) {
			return true;
		}
		return false;
	}

	public static Date getEndDate(Date startDate, int toHour, int toMin) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.HOUR_OF_DAY, toHour);
		cal.set(Calendar.MINUTE, toMin);
		return cal.getTime();
	}

}
