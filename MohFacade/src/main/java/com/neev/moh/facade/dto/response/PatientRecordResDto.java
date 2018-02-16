package com.neev.moh.facade.dto.response;

public class PatientRecordResDto extends ResponseDto{

	boolean isUploadMediaSuccess ; 
	
	boolean isPatientCreationSuccess;

	public boolean isUploadMediaSuccess() {
		return isUploadMediaSuccess;
	}

	public void setUploadMediaSuccess(boolean isUploadMediaSuccess) {
		this.isUploadMediaSuccess = isUploadMediaSuccess;
	}

	public boolean isPatientCreationSuccess() {
		return isPatientCreationSuccess;
	}

	public void setPatientCreationSuccess(boolean isPatientCreationSuccess) {
		this.isPatientCreationSuccess = isPatientCreationSuccess;
	}
	
}
