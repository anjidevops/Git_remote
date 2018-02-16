package com.neev.moh.facade.dto.response;

import java.util.List;

import com.neev.moh.facade.dto.AppointmentDto;

public class AppointmentLanes extends ResponseDto {

	private List<List<AppointmentDto>> laneApts;

	public List<List<AppointmentDto>> getLaneApts() {
		return laneApts;
	}

	public void setLaneApts(List<List<AppointmentDto>> laneApts) {
		this.laneApts = laneApts;
	}

	
	
}
