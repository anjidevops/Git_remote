package com.neev.moh.facade.convert.populator;

import com.neev.moh.facade.dto.UserInfoDto;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.User;

public class UserInfoPopulator implements Populator<User, UserInfoDto> {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(UserInfoPopulator.class.getName());

	@Override
	public void populate(User source, UserInfoDto target) throws ConversionException {
		logger.log(MohLogger.DEBUG, "populating...");
		target.setEmail(source.getEmail());
		target.setUserId(source.getId());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setMobile(source.getMobile());
		target.setRoleType(source.getRoleType());
		if (source.getRoleType().equals("Doctor") || source.getRoleType().equals("Staff") || source.getRoleType().equals("Admin")) {
			target.setAdmin(Boolean.TRUE);
		}
	}

}
