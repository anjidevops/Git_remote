package com.neev.moh.web.utils;

import java.util.Set;

import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.Privilege;
import com.neev.moh.model.Role;

public class StringUtils {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(StringUtils.class.getName());

	private StringUtils() {
	}

	public static boolean isNullOrEmpty(String data) {
		logger.log(MohLogger.DEBUG, "data: " + data);
		return data == null || data.isEmpty();
	}

	public static Integer getInt(String data) {
		logger.log(MohLogger.DEBUG, "data: " + data);
		Integer dataInt = null;
		try {
			dataInt = Integer.parseInt(data);
		} catch (NumberFormatException e) {
		}
		logger.log(MohLogger.DEBUG, "dataInt: " + dataInt);
		return dataInt;
	}
	
	public static String getRoleString(Set<Role> roles) {
		String roleString = "";
		if (roles != null && !roles.isEmpty()) {
			for (Role role : roles) {
				roleString = "," + role.getName();
			}
		}
		if (roleString.startsWith(",")) {
			roleString = roleString.substring(1);
		}
		logger.log(MohLogger.DEBUG, "roleString: " + roleString);
		return roleString;
	}

	public static String getPrivilegeString(Set<Privilege> privileges) {
		String privilegeString = "";
		if (privileges != null && !privileges.isEmpty()) {
			for (Privilege privilege : privileges) {
				privilegeString = "," + privilege.getName();
			}
		}
		if (privilegeString.startsWith(",")) {
			privilegeString = privilegeString.substring(1);
		}
		logger.log(MohLogger.DEBUG, "roleString: " + privilegeString);
		return privilegeString;
	}

}
