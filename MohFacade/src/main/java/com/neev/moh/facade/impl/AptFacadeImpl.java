package com.neev.moh.facade.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import com.neev.moh.facade.AptFacade;
import com.neev.moh.facade.dto.AppointmentDto;
import com.neev.moh.facade.dto.DoctorInfoDto;
import com.neev.moh.facade.dto.appointment.AptDayDto;
import com.neev.moh.facade.dto.appointment.SlotInfoDto;
import com.neev.moh.facade.dto.response.AppointmentLanes;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.Appointment;
import com.neev.moh.model.Doctor;
import com.neev.moh.services.AppSettingService;
import com.neev.moh.services.AppointmentService;
import com.neev.moh.services.UserService;
import com.neev.moh.facade.impl.ConfigurationHelper;

@Transactional(rollbackFor=Exception.class)
public class AptFacadeImpl implements AptFacade {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(AptFacadeImpl.class.getName());

	@Autowired(required = true)
	private AptHelper aptHelper;

	@Autowired(required = true)
	private AppointmentService aptService;

	@Autowired(required = true)
	private UserService userService;
	
	@Autowired (required = true)
	private AppSettingService appSettingService;

	//unused fn?
	@Override
	public AppointmentLanes getSchedule(Date date, DoctorInfoDto doctorInfo) throws Exception {
		Doctor doctor = new Doctor();
		doctor.setDoc_id(doctorInfo.getDoctorId());
		AppointmentLanes aptsAll = getScduleFromService(date, doctorInfo);
		boolean a = true;// twmp testing
		if (a) {
			return aptsAll;
		}
		AppointmentDto firstA = new AppointmentDto();
		AppointmentDto lastA = new AppointmentDto();
		GregorianCalendar first = new GregorianCalendar();
		first.set(Calendar.HOUR, aptService.getDayStartHour(doctor));
		first.set(Calendar.MINUTE, aptService.getDayStartMinute(doctor));
		int firstEndHour = aptService.getDayEndHour(doctor);
		int firstEndMin = aptService.getDayEndMinute(doctor);
		int lastEndHour = firstEndHour;
		int lastEndMin = firstEndMin;
		boolean needFirst = true;
		if (aptsAll.getLaneApts().size() > 0) {
			boolean needSecond = false;
			GregorianCalendar lastStart = new GregorianCalendar();
			AppointmentDto apt1 = null; // aptsAll.getLaneApts().get(0);
			Date a1Date = apt1.getAppointmentDate();
			if (a1Date.getHours() == first.getTime().getHours() && a1Date.getMinutes() == first.getTime().getMinutes()) {
				needFirst = false;
			} else {
				GregorianCalendar firstend = new GregorianCalendar(a1Date.getYear(), a1Date.getMonth(), a1Date.getDate(), a1Date.getHours(), a1Date.getMinutes());
				firstend.add(Calendar.MINUTE, -1);
				firstEndHour = firstend.get(Calendar.HOUR);
				firstEndMin = firstend.get(Calendar.MINUTE);

				// if we have at least 1 and it does not end at end of day,
				// means there is time between last booked and end if day

				AppointmentDto last = null; // aptsAll.get(aptsAll.size() - 1);
				if (last.getAppointmentEndHour() >= lastEndHour && last.getAppointmentEndMin() >= lastEndMin) {
					// default needSecond = false;
				} else {
					needSecond = true;
					lastStart.set(Calendar.HOUR, last.getAppointmentEndHour());
					lastStart.set(Calendar.MINUTE, last.getAppointmentEndMin());
					lastStart.add(Calendar.MINUTE, 1);
				}
			}
			if (needSecond) {
				lastA.setAppointmentDate(lastStart.getTime());
				lastA.setAppointmentEndHour(lastEndHour);
				lastA.setAppointmentEndMin(lastEndMin);
				// aptsAll.getLaneApts().add(lastA);
			}
		}
		if (needFirst) {
			firstA.setAppointmentDate(first.getTime());
			// firstA.setDoctor(doctor);
			firstA.setAppointmentEndHour(firstEndHour);
			firstA.setAppointmentEndMin(firstEndMin);
			// aptsAll.add(0, firstA);
		}
		return null;
	}

	/**
	 * right now test data, later will take from getSchedule docUserName - email
	 * id or a new property in user.
	 */

	/*@Override
	public AptDayAppoitmentsDto getApts(String docUserName, Date when) {
		AptDayAppoitmentsDto day = new AptDayAppoitmentsDto();
		day.setForDay(when);

		List<AptMinuteSlotDto> asl = new ArrayList<AptMinuteSlotDto>();
		Random rnd = new Random();
		int lnct = rnd.nextInt(2);
		for (int lane = 0; lane < lnct ; lane++) {
			for (int hr = 7; hr < 16; hr++) {
				for (int mi = 0; mi < 60; mi += 5) {
					if (mi > 59) {
						break;
					}
					AptMinuteSlotDto a = new AptMinuteSlotDto();
					//a.setFromHour(hr);
					a.setFromMin(mi);
					int st = rnd.nextInt(APPT_STATUS.length + 2);
					if (st > APPT_STATUS.length - 1) {
						st = 1;// more free
					}
					a.setStatus(APPT_STATUS[st]);
					int xx = 5 + (rnd.nextInt(3) * 5);
					mi += xx - 5;
					int till = mi + xx;
					if (till > 59) {
						till = till % 60;
					}
					asl.add(a);
				}
			}
		}
		 //day.setForDoc(forDoc);
		//day.addSlotsForLane(asl);
		return day;
	}*/

	@PreAuthorize("hasRole('appointment_day_apts')")
	public AptDayDto getApts(String doctorName, Date appointmentDay) {
		AptDayDto day = new AptDayDto();
		day.setDoctor(doctorName);
		day.setForDay(appointmentDay.toString());
		try {
			Integer doctorId = Integer.parseInt(doctorName);
			List<Appointment> apps = aptService.getAllAppointment(appointmentDay, doctorId);
			List<Integer> hours = new ArrayList<Integer>();
			int START_HOUR = Integer.parseInt(appSettingService.getAppSettingValye("1" , "1", "English", "START_HOUR"));
			int END_HOUR = Integer.parseInt(appSettingService.getAppSettingValye("1" , "1", "English", "END_HOUR"));
			int MAX_LANE_NUMBER = Integer.parseInt(appSettingService.getAppSettingValye("1" , "1", "English", "MAX_LANE_NUMBER"));
			int MAX_MINUTE_SLOT_NUMBER_IN_HOUR = Integer.parseInt(appSettingService.getAppSettingValye("1" , "1", "English", "MAX_MINUTE_SLOT_NUMBER_IN_HOUR"));
			int minuteSlotSpanTime = 60 / MAX_MINUTE_SLOT_NUMBER_IN_HOUR;
			List<List<List<SlotInfoDto>>> hourSlots = new ArrayList<List<List<SlotInfoDto>>>();
			for (int hour = START_HOUR; hour < END_HOUR; hour++) {
				hours.add(hour);
				List<List<SlotInfoDto>> lanSlots = new ArrayList<List<SlotInfoDto>>();
				for (int lane = 0; lane < MAX_LANE_NUMBER; lane++) {
					List<SlotInfoDto> minSlots = new ArrayList<SlotInfoDto>();
					for (int i = 0; i < MAX_MINUTE_SLOT_NUMBER_IN_HOUR; i++) {
						int fromMin = minuteSlotSpanTime * i + 1;
						int toMin = (i + 1) * minuteSlotSpanTime;
						String slotStatus = getSlotStatusAvailable(apps, hour, fromMin, hour, toMin, lane);
						SlotInfoDto slot = aptHelper.getSlotInfo(slotStatus, hour, fromMin, hour, toMin, lane);
						logger.log(MohLogger.INFO, "slot = " + slot);
						minSlots.add(slot);
					}
					lanSlots.add(minSlots);
				}
				hourSlots.add(lanSlots);
			}
			day.setSlots(hourSlots);
			day.setHours(hours);
		} catch (Exception e) {
			logger.log(MohLogger.ERROR, "Exception: " + e.getMessage());
			logger.log(MohLogger.ERROR, "" + e);
		}
		day.setErrorNo(0);
		day.setSuccessMsg("fetched appointment timeslot successfully");
		logger.log(MohLogger.INFO, "Fetched appointment timeslot successfully");
		return day;
	}

	private String getSlotStatusAvailable(List<Appointment> apps, int fromHour, int fromMin, int toHour, int toMin, int lane) {
		String slotStatus = null;
		if (apps == null || apps.isEmpty()) {
			slotStatus = "Free";// default slotStatus
		} else if (apps.size() == 1) {
			Appointment app = apps.get(0);
			if (checkConflictTimeSlot(app, fromHour, fromMin, toHour, toMin, lane)) {
				slotStatus = "Busy";// get slotStatus from Appointment
			} else {
				slotStatus = "Free";//hard coding/ other slots?
			}
		} else {
			for (Appointment app : apps) {
				if (checkConflictTimeSlot(app, fromHour, fromMin, toHour, toMin, lane)) {
					slotStatus = "Busy"; // get slotStatus from Appointment
					break;
				} else {
					slotStatus = "Free";
				}
			}
		}
		logger.log(MohLogger.INFO, "slotStatus = " + slotStatus);
		return slotStatus;
	}

	public boolean checkConflictTimeSlot(Appointment app, int fromHour, int fromMin, int toHour, int toMin, int lane) {
		int appStartHour = app.getAppointmentDate().getHours();
		int appStartMin = app.getAppointmentDate().getMinutes();
		if ((fromHour >= appStartHour && fromHour <= app .getAppointmentEndHour()) && app.getLanes() == lane) {
			if (!isBefore(fromHour, fromMin, appStartHour, appStartMin) && isBefore(fromHour, fromMin, app.getAppointmentEndHour(), app.getAppointmentEndMin())) {
				return true;
			} else if (!isBefore(fromHour, toMin, appStartHour, appStartMin) && isBefore(fromHour, toMin, app.getAppointmentEndHour(), app.getAppointmentEndMin())) {
				return true;
			}
		}
		return false;
	}

	private boolean isBefore(int fromHour, int fromMin, int appointmentEndHour, int appointmentEndMin) {
		if (fromHour == appointmentEndHour) {
			if (fromMin < appointmentEndMin) {
				return true;
			}
			return false;
		} else if (fromHour < appointmentEndHour) {
			return true;
		} else {// greater
			return false;
		}
	}

	/*
	 * DayAppoitments { private Date forDay; private DoctorDto forDoc; private List <List <AppoitmentSlots>> laneSlots; private int lanes;
	 * 
	 * 
	 * public List<AppoitmentSlots> getSlotsForLane(int i){ return this.laneSlots.get(i); }
	 */

	@PreAuthorize("hasRole('doctor_appointment_details')")
	public AppointmentLanes getScduleFromService(Date date, DoctorInfoDto doctor) {
		// lst = this.aptService.getSchedule( date, DoctorInfoDto doctor)
		return aptHelper.getScduleFromService("", date);
	}

	public AptHelper getAptHelper() {
		return aptHelper;
	}

	public void setAptHelper(AptHelper aptHelper) {
		this.aptHelper = aptHelper;
	}

	public AppointmentService getAptService() {
		return aptService;
	}

	public void setAptService(AppointmentService aptService) {
		this.aptService = aptService;
	}

}