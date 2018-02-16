package com.neev.moh.facade.dto;

public class DoctorInfoDto {
	
	private Integer doctorId;
	
	/* this will be full name User.firstName+User.lastName*/
	private String name;
	
	public DoctorInfoDto(Integer doctorId) {
		super();
		this.doctorId = doctorId;
	}
	
	public DoctorInfoDto() {}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
