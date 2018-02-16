package com.neev.moh.facade;

import java.util.Date;

import com.neev.moh.facade.dto.DoctorInfoDto;
import com.neev.moh.facade.dto.appointment.AptDayDto;
import com.neev.moh.facade.dto.response.AppointmentLanes;

public interface AptFacade {
	AppointmentLanes getSchedule(Date date, DoctorInfoDto doctor) throws Exception ;

	AppointmentLanes getScduleFromService(Date date, DoctorInfoDto doctor);

	AptDayDto getApts(String doctorId, Date appointmentDay);
}
