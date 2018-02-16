package com.neev.moh.facade.dto;


public class PatientRecordDto {
	
	private Integer patientRecordId;
	
	private Integer appointmentId; 
	
	private String summary;
	
	private String prescription;
	
	private String attach1Path;
	
	private String attach2Path;
	
	private String doctorName;
	
	private String dateOfVisit;

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	
	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getAttach1Path() {
		return attach1Path;
	}

	public void setAttach1Path(String attach1Path) {
		this.attach1Path = attach1Path;
	}

	public String getAttach2Path() {
		return attach2Path;
	}

	public void setAttach2Path(String attach2Path) {
		this.attach2Path = attach2Path;
	}

	public Integer getPatientRecordId() {
		return patientRecordId;
	}

	public void setPatientRecordId(Integer patientRecordId) {
		this.patientRecordId = patientRecordId;
	}

	public String getDateOfVisit() {
		return dateOfVisit;
	}

	public void setDateOfVisit(String dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	@Override
	public String toString() {
		StringBuilder infoBuilder = new StringBuilder();
		infoBuilder.append("[appointmentId=");
		if(appointmentId !=null)
			infoBuilder.append(appointmentId);
		
		infoBuilder.append(",summary=");
		if(summary !=null)
			infoBuilder.append(summary);
		
		infoBuilder.append(",prescription=");	
		if(prescription !=null)
			infoBuilder.append(prescription);
		infoBuilder.append("]");
		
		return infoBuilder.toString();
	}
	
}
