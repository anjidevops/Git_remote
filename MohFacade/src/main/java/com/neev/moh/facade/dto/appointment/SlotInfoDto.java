package com.neev.moh.facade.dto.appointment;

import java.io.Serializable;


//will be cached
//across all users and days

public final class SlotInfoDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String status;// final okay as we set only once in constructor spl Java final without initial value
	private final int hourStart;
	private final int minuteStart;
	
	private final int hourEnd;
	private final int minuteEnd;
	
	private final int lane;
	
	public SlotInfoDto(String status, int hourStart, int minuteStart, int hourEnd, int minuteEnd, int lane) {
		this.status = status;
		this.hourStart = hourStart;
		this.hourEnd = hourEnd;
		this.minuteStart = minuteStart;
		this.minuteEnd = minuteEnd;
		this.lane = lane;
	}
	
	public String getStatus() {
		return status;// only get
	}

	public int getHourStart() {
		return hourStart;
	}

	public int getMinuteStart() {
		return minuteStart;
	}

	public int getHourEnd() {
		return hourEnd;
	}

	public int getMinuteEnd() {
		return minuteEnd;
	}

	public int getLane() {
		return lane;
	}
}

