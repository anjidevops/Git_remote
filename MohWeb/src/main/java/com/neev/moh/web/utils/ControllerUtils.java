package com.neev.moh.web.utils;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neev.moh.facade.dto.UserRegDto;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.Address;

public class ControllerUtils {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(ControllerUtils.class.getName());

	private ControllerUtils() {
	}

	public static final String REDIRECT = "redirect:/";

	public static void addAddressToUserRegDto(final UserRegDto userRegDto, final HttpServletRequest req) {
		Address ad1 = new Address();
		ad1.setStreet1(req.getParameter("ad1.street1"));
		ad1.setStreet2(req.getParameter("ad1.street2"));
		ad1.setCity(req.getParameter("ad1.city"));
		ad1.setCountry(req.getParameter("ad1.country"));
		try {
			Integer pin = Integer.parseInt(req.getParameter("ad1.pincode"));
			ad1.setPincode(pin);
		} catch (NumberFormatException e) {
			logger.log(MohLogger.WARN, "NumberFormatException: " + e.getMessage());
		}
		Address ad2 = new Address();
		ad2.setStreet1(req.getParameter("ad2.street1"));
		ad2.setStreet2(req.getParameter("ad2.street2"));
		ad2.setCity(req.getParameter("ad2.city"));
		ad2.setCountry(req.getParameter("ad2.country"));
		try {
			Integer pin2 = Integer.parseInt(req.getParameter("ad2.pincode"));
			ad2.setPincode(pin2);
		} catch (NumberFormatException e) {
			logger.log(MohLogger.WARN, "NumberFormatException: " + e.getMessage());
		}
		Set<Address> addressSet = new HashSet<Address>();
		addressSet.add(ad1);
		addressSet.add(ad2);
		userRegDto.setAddresses(addressSet);
	}

	public static void addAddressToUserRegDtoForAngularApp(final UserRegDto userRegDto) {
		Address ad1 = new Address();
		ad1.setStreet1(userRegDto.getAddress1Street1());
		ad1.setStreet2(userRegDto.getAddress1Street2());
		ad1.setCity(userRegDto.getAddress1City());
		ad1.setCountry(userRegDto.getAddress1Country());
		try {
			Integer pin = Integer.parseInt(userRegDto.getAddress1Pincode());
			ad1.setPincode(pin);
		} catch (NumberFormatException e) {
			logger.log(MohLogger.WARN, "NumberFormatException: " + e.getMessage());
		}
		Address ad2 = new Address();
		ad2.setStreet1(userRegDto.getAddress2Street1());
		ad2.setStreet2(userRegDto.getAddress2Street2());
		ad2.setCity(userRegDto.getAddress2City());
		ad2.setCountry(userRegDto.getAddress2Country());
		try {
			Integer pin2 = Integer.parseInt(userRegDto.getAddress2Pincode());
			ad2.setPincode(pin2);
		} catch (NumberFormatException e) {
			logger.log(MohLogger.WARN, "NumberFormatException: " + e.getMessage());
		}
		Set<Address> addressSet = new HashSet<Address>();
		addressSet.add(ad1);
		addressSet.add(ad2);
		userRegDto.setAddresses(addressSet);
	}

	public static final String getDownloadReportUrl(final HttpServletRequest req, final String reportName) {
		String reportUrl = null;
		String baseUrl = getBaseUrl(req);
		if (baseUrl.endsWith("/")) {
			reportUrl = baseUrl + reportName;
		} else {
			reportUrl = baseUrl + "/" + reportName;
		}
		logger.log(MohLogger.INFO, "reportUrl: " + reportUrl);
		return reportUrl;
	}

	/*
	 * return base url only without any servlet mapping , parameter
	 */
	private static final String getBaseUrl(final HttpServletRequest req) {
		StringBuilder baseUrlBuilder = new StringBuilder();
		baseUrlBuilder.append(req.getScheme());
		baseUrlBuilder.append("://" + req.getServerName());
		baseUrlBuilder.append(":" + req.getServerPort());
		baseUrlBuilder.append(req.getContextPath());
		logger.log(MohLogger.INFO, "baseUrl: " + baseUrlBuilder.toString());
		return baseUrlBuilder.toString();
	}
	
	public static void deleteAllCookies(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		// Delete all the cookies
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				cookies[i].setValue(null);
				cookies[i].setMaxAge(0);
				response.addCookie(cookie);
				logger.log(MohLogger.INFO, "********************Cookie Deleted: " + cookie.getName());
			}
			logger.log(MohLogger.INFO, "********************All Cookies Deleted");
		} else {
			logger.log(MohLogger.INFO, "********************No Cookie to Delete");
		}
	}

}
