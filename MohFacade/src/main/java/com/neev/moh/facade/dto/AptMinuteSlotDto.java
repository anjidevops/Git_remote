package com.neev.moh.facade.dto;

public class AptMinuteSlotDto {
	
	private AptLaneSlotDto parentLane;
	private int fromMin;
	private int toMin;
	private String status;
	private UserDto patient;
	
	public AptMinuteSlotDto(){}

	public AptMinuteSlotDto(AptLaneSlotDto parentLane, int fromMin, int toMin, String status, UserDto patient) {
		super();
		this.parentLane = parentLane;
		this.fromMin = fromMin;
		this.toMin = toMin;
		this.status = status;
		this.patient= patient; 
	}

	public AptLaneSlotDto getParentLane() {
		return parentLane;
	}

	public void setParentLane(AptLaneSlotDto parentLane) {
		this.parentLane = parentLane;
	}

	public int getFromMin() {
		return fromMin;
	}

	public void setFromMin(int fromMin) {
		this.fromMin = fromMin;
	}

	public int getToMin() {
		return toMin;
	}

	public void setToMin(int toMin) {
		this.toMin = toMin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserDto getPatient() {
		return patient;
	}

	public void setPatient(UserDto patient) {
		this.patient = patient;
	}

	
	
	
}
