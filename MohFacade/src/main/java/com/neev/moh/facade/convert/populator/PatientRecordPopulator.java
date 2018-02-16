package com.neev.moh.facade.convert.populator;

import com.neev.moh.facade.dto.PatientRecordDto;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.PatientRecord;
import com.neev.moh.facade.impl.ConfigurationHelper;

public class PatientRecordPopulator implements Populator<PatientRecord, PatientRecordDto> {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(PatientRecordPopulator.class.getName());

	@Override
	public void populate(PatientRecord source, PatientRecordDto target) throws ConversionException {
		logger.log(MohLogger.DEBUG, "populating...");
		target.setPatientRecordId(source.getId());
		target.setAppointmentId(source.getAppopintmentId());
		target.setSummary(source.getSummary());
		target.setPrescription(source.getPrescription());

	}

}
