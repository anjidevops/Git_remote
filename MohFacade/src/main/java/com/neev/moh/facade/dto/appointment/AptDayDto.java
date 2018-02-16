package com.neev.moh.facade.dto.appointment;

import java.util.ArrayList;
import java.util.List;

import com.neev.moh.facade.dto.response.ResponseDto;

public class AptDayDto extends ResponseDto{
	
	private List<List<List<SlotInfoDto>>> slots = new ArrayList<List<List<SlotInfoDto>>>();
	private String forDay;
	private String doctor;
	
	private List<Integer> hours;
	
	public List<List<List<SlotInfoDto>>> getSlots() {
		return slots;
	}
	public void setSlots(List<List<List<SlotInfoDto>>> slots) {
		this.slots = slots;
	}
	public String getForDay() {
		return forDay;
	}
	public void setForDay(String forDay) {
		this.forDay = forDay;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public List<Integer> getHours() {
		return hours;
	}
	public void setHours(List<Integer> hours) {
		this.hours = hours;
	}
	
}
