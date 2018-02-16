package com.neev.moh.facade.convert.populator;

import com.neev.moh.facade.dto.AppointmentInfoDto;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.Appointment;

public class AppointmentPopulator implements Populator<Appointment, AppointmentInfoDto> {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(AppointmentPopulator.class.getName());

	@Override
	public void populate(Appointment source, AppointmentInfoDto target) throws ConversionException {
		logger.log(MohLogger.DEBUG, "populating...");
		target.setAppointmentId(source.getId());
		target.setVisitedDate(source.getAppointmentDate().toString());
		target.setDoctorName(source.getDoctor().getUser().getFullName());
		target.setPatientId(source.getUser().getId());
	}

}
