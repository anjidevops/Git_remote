package com.neev.moh.facade.dto;

import java.util.List;

public class AptHourSlotDto {
	private AptDayAppoitmentsDto parentApt;
	private int hour;
	private List<AptLaneSlotDto> laneSlots;

	public AptHourSlotDto() {
	}

	public AptHourSlotDto(AptDayAppoitmentsDto parentApt, int hour, List<AptLaneSlotDto> laneSlots) {
		super();
		this.setParentApt(parentApt);
		this.hour = hour;
		this.laneSlots = laneSlots;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public List<AptLaneSlotDto> getLaneSlots() {
		return laneSlots;
	}

	public void setLaneSlots(List<AptLaneSlotDto> laneSlots) {
		this.laneSlots = laneSlots;
	}

	public AptDayAppoitmentsDto getParentApt() {
		return parentApt;
	}

	public void setParentApt(AptDayAppoitmentsDto parentApt) {
		this.parentApt = parentApt;
	}

}
