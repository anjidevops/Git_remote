package com.neev.moh.facade.dto.response;

import com.neev.moh.facade.dto.PatientRecordDto;

public class ViewPatientRecordResDto extends ResponseDto{
	
	private PatientRecordDto patientRecordDto;

	public PatientRecordDto getPatientRecordDto() {
		return patientRecordDto;
	}

	public void setPatientRecordDto(PatientRecordDto patientRecordDto) {
		this.patientRecordDto = patientRecordDto;
	}
	
}
