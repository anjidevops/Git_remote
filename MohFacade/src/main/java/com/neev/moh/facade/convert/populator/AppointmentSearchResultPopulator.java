package com.neev.moh.facade.convert.populator;

import com.neev.moh.facade.dto.AppointmentResultDto;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.Appointment;

public class AppointmentSearchResultPopulator implements Populator<Appointment, AppointmentResultDto> {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(AppointmentSearchResultPopulator.class.getName());

	@Override
	public void populate(Appointment source, AppointmentResultDto target) throws ConversionException {
		logger.log(MohLogger.DEBUG, "populating...");
		target.setUserName(source.getUser().getFullName());
		target.setAppointmentDate(source.getAppointmentDate().toString());
		target.setAppointmentId(source.getId());
	}

}
