package com.neev.moh.facade.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AptDayAppoitmentsDto {
	private Date forDay;
	private DoctorDto forDoc;
	private List <AptHourSlotDto> hourSlots;
	
	public AptDayAppoitmentsDto(){
		
	}

	public AptDayAppoitmentsDto(Date forDay, DoctorDto forDoc, List<AptHourSlotDto> hourSlots) {
		super();
		this.forDay = forDay;
		this.forDoc = forDoc;
		this.hourSlots = hourSlots;
	}

	public Date getForDay() {
		return forDay;
	}

	public void setForDay(Date forDay) {
		this.forDay = forDay;
	}

	public DoctorDto getForDoc() {
		return forDoc;
	}

	public void setForDoc(DoctorDto forDoc) {
		this.forDoc = forDoc;
	}

	public List<AptHourSlotDto> getHourSlots() {
		return hourSlots;
	}

	public void setHourSlots(List<AptHourSlotDto> hourSlots) {
		this.hourSlots = hourSlots;
	} 
	
	
		
}
