package com.neev.moh.facade.dto;

import java.util.List;

public class AptLaneSlotDto {
	private AptHourSlotDto parentHour;
	private int laneNo;
	private List<AptMinuteSlotDto> minuteSlots;
	
	public AptLaneSlotDto() {
		super();
	}
	
	public AptLaneSlotDto(AptHourSlotDto parentHour, int laneNo, List<AptMinuteSlotDto> minuteSlots) {
		super();
		this.laneNo = laneNo;
		this.parentHour = parentHour;
		this.minuteSlots = minuteSlots;
	}

	public int getLaneNo() {
		return laneNo;
	}

	public void setLaneNo(int laneNo) {
		this.laneNo = laneNo;
	}

	public AptHourSlotDto getParentHour() {
		return parentHour;
	}

	public void setParentHour(AptHourSlotDto parentHour) {
		this.parentHour = parentHour;
	}

	public List<AptMinuteSlotDto> getMinuteSlots() {
		return minuteSlots;
	}

	public void setMinuteSlots(List<AptMinuteSlotDto> minuteSlots) {
		this.minuteSlots = minuteSlots;
	}
	
	
	
}
