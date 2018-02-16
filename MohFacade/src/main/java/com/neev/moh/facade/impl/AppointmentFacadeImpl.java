package com.neev.moh.facade.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neev.moh.facade.AppointmentFacade;
import com.neev.moh.facade.convert.populator.AppointmentPopulator;
import com.neev.moh.facade.convert.populator.AppointmentSearchResultPopulator;
import com.neev.moh.facade.convert.populator.ConversionException;
import com.neev.moh.facade.convert.populator.PatientRecordPopulator;
import com.neev.moh.facade.dto.AppointmentInfoDto;
import com.neev.moh.facade.dto.AppointmentRecordDto;
import com.neev.moh.facade.dto.AppointmentResultDto;
import com.neev.moh.facade.dto.PatientRecordDto;
import com.neev.moh.facade.dto.UserRegDto;
import com.neev.moh.facade.dto.response.AppointmentHistoryResDto;
import com.neev.moh.facade.dto.response.AppointmentSeachResultResDto;
import com.neev.moh.facade.dto.response.ResponseDto;
import com.neev.moh.facade.dto.response.ViewPatientRecordResDto;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.Address;
import com.neev.moh.model.Appointment;
import com.neev.moh.model.Doctor;
import com.neev.moh.model.PatientRecord;
import com.neev.moh.model.User;
import com.neev.moh.services.AppSettingService;
import com.neev.moh.services.AppointmentService;
import com.neev.moh.services.DepartmentService;
import com.neev.moh.services.DoctorService;
import com.neev.moh.services.UserService;
import com.neev.moh.facade.impl.ConfigurationHelper;
import com.neev.moh.utils.DateUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class AppointmentFacadeImpl implements AppointmentFacade {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(AppointmentFacadeImpl.class.getName());

	@Autowired(required = true)
	private UserService userService;

	@Autowired(required = true)
	private AppointmentService appointmentService;

	@Autowired(required = true)
	private DepartmentService departmentService;

	@Autowired(required = true)
	private DoctorService doctorService;
	
	@Autowired(required = true)
	private AppSettingService appSettingService;

	@Autowired(required = true)
	private AppointmentPopulator appointmentPopulator;

	@Autowired(required = true)
	private PatientRecordPopulator patientRecordPopulator;

	@Autowired(required = true)
	private AppointmentSearchResultPopulator appointmentSearchResultPopulator;

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public DoctorService getDoctorService() {
		return doctorService;
	}

	public void setDoctorService(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	public AppointmentFacadeImpl() {
	}

	public AppointmentFacadeImpl(UserService userService, AppointmentService appointmentService, DoctorService doctorService) {
		this.userService = userService;
		this.appointmentService = appointmentService;
		this.doctorService = doctorService;
	}

	public AppointmentFacadeImpl(UserService userService,
			AppointmentService appointmentService,
			DepartmentService departmentService, DoctorService doctorService,
			AppointmentPopulator appointmentPopulator,
			PatientRecordPopulator patientRecordPopulator,
			AppointmentSearchResultPopulator appointmentSearchResultPopulator) {
		super();
		this.userService = userService;
		this.appointmentService = appointmentService;
		this.departmentService = departmentService;
		this.doctorService = doctorService;
		this.appointmentPopulator = appointmentPopulator;
		this.patientRecordPopulator = patientRecordPopulator;
		this.appointmentSearchResultPopulator = appointmentSearchResultPopulator;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public AppointmentService getAppointmentService() {
		return appointmentService;
	}

	public void setAppointmentService(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	/**
	 * This method converts a UserRegDto to User
	 */
	private User prepareUserForCreate(UserRegDto userRegDTO) {
		logger.log(MohLogger.INFO, "Preparing For User Creation...");
		User newUser = new User();
		Integer result = null;
		newUser.setFirstName(userRegDTO.getFirstName());
		newUser.setLastName(userRegDTO.getLastName());
		newUser.setEmail(userRegDTO.getEmail());
		newUser.setDob(userRegDTO.getDob());
		newUser.setMobileCode(userRegDTO.getMobileCode());
		newUser.setMobile(userRegDTO.getMobile());
		newUser.setPassword("moh");
		newUser.setRoleType(userRegDTO.getRoleType());
		newUser.setAddress(userRegDTO.getAddresses());
		if (userRegDTO.getAddresses() != null) {
			Iterator<Address> iterable = userRegDTO.getAddresses().iterator();
			while (iterable.hasNext()) {
				iterable.next().setUser(newUser);
			}
		}
		logger.log(MohLogger.INFO, "User Object is Ready.");
		return newUser;
	}

	/**
	 * This method converts a AppointmentRecordDto to Appointment. It does not
	 * set a Doctor object to the Appointment.
	 */
	private Appointment prepareAppointmentForCreate(AppointmentRecordDto appointmentDto) {
		logger.log(MohLogger.INFO, "Preparing For Appointment Creation...");
		Date appointmentTime = DateUtils.getEndDate(appointmentDto.getAppointmentDay(), appointmentDto.getFromHour(), appointmentDto.getFromMin());
		appointmentDto.setAppointmentDay(appointmentTime);
		Appointment appointment = new Appointment();
		appointment.setAppointmentDate(appointmentDto.getAppointmentDay());
		appointment.setAppointmentEndHour(appointmentDto.getToHour());
		appointment.setAppointmentEndMin(appointmentDto.getToMin());
		appointment.setLanes(appointmentDto.getLane());
		logger.log(MohLogger.INFO, "Appointment Object is Ready.");
		return appointment;
	}

	public void deleteUser(UserRegDto userRegDTO) {
		logger.log(MohLogger.INFO, "deleteUser()");
		try {
			User user = userService.getUserByEmail(userRegDTO.getEmail());
			logger.log(MohLogger.INFO, "Deleting user with id " + user.getId());
			userService.deleteUser(user);
			logger.log(MohLogger.INFO, "User deleted");
		} catch (Exception e) {
			logger.log(MohLogger.ERROR, "Exception: deleteUser() - {}" + e.getMessage());
		}
	}

	public ResponseDto createUserAndBookAppointment(UserRegDto userRegDTO, AppointmentRecordDto appointmentDto, boolean isDoctorAssociated) throws Exception {
		ResponseDto responseDto = new ResponseDto();
		StringBuilder response = new StringBuilder();
		// Create User
		User newUser = prepareUserForCreate(userRegDTO);
		Integer newUserId = null;
		newUserId = userService.createUser(newUser); // Service Call
		logger.log(MohLogger.INFO, "newUserId: " + newUserId);
		response.append("User Created. ");
		logger.log(MohLogger.INFO, "User created");
		Appointment appointment = prepareAppointmentForCreate(appointmentDto);
		appointment.setUser(newUser);
		
		// Check Appointment Availability
		boolean isAppointmentAvailable = false;
		isAppointmentAvailable = isAppointmentAvailable(appointmentDto); // Service Call
		logger.log(MohLogger.INFO, "isAppointmentAvailable = " + isAppointmentAvailable);
		response.append("Appointment Availability Known. ");
		//TODO logger after logger config working
		logger.log(MohLogger.INFO, "Appointment Availability: " + isAppointmentAvailable);
		// Create Appointment
		boolean result = false;
		if (isAppointmentAvailable) {
			response.append("Appointment is Available. ");
			Doctor doctor = doctorService.getDoctorById(appointmentDto.getDoctorId());
			logger.log(MohLogger.INFO, "doctor: " + doctor);
			appointment.setDoctor(doctor);
			response.append("Doctor is associated with Appointment. ");
			logger.log(MohLogger.INFO, "Creating Appointment: " + appointment);
			result = appointmentService.createAppointment(appointment); // Service Call
			logger.log(MohLogger.INFO, "Appointment Created: " + result);
			response.append("Appointment Created. ");
		} else {
			logger.log(MohLogger.WARN, "Appointment Not Available. No Appointment Created.");
			response.append("Appointment is NOT Available. ");
			responseDto.setErrorNo(1);
			throw new Exception("Appointment is not available");
		}
		responseDto.setSuccessMsg(response.toString());
		responseDto.setErrorNo(0);
		return responseDto;
	}

	@PreAuthorize("hasRole('appointment_book_appointment')")
	public ResponseDto bookAppointment(AppointmentRecordDto appointmentDto) {
		ResponseDto responseDto = new ResponseDto();
		logger.log(MohLogger.INFO, "appointmentDto.getAppointmentDay() = " + appointmentDto.getAppointmentDay());
		logger.log(MohLogger.INFO, "appointmentDto.getFromHour() = " + appointmentDto.getFromHour());
		logger.log(MohLogger.INFO, "appointmentDto.getFromMin() = " + appointmentDto.getFromMin());
		logger.log(MohLogger.INFO, "appointmentDto.getPatientId() = " + appointmentDto.getPatientId());
		logger.log(MohLogger.INFO, "appointmentDto.getDoctorId() = " + appointmentDto.getDoctorId());
		logger.log(MohLogger.INFO, "appointmentDto.getToHour() = " + appointmentDto.getToHour());
		logger.log(MohLogger.INFO, "appointmentDto.getToMin() = " + appointmentDto.getToMin());
		logger.log(MohLogger.INFO, "appointmentDto.getLane() = " + appointmentDto.getLane());
		Date appointmentTime = DateUtils.getEndDate(appointmentDto.getAppointmentDay(), appointmentDto.getFromHour(), appointmentDto.getFromMin());
		appointmentDto.setAppointmentDay(appointmentTime);
		User patient = userService.getUserById(appointmentDto.getPatientId());
		logger.log(MohLogger.INFO, "patient: " + patient);
		Doctor doctor = doctorService.getDoctorById(appointmentDto.getDoctorId());
		logger.log(MohLogger.INFO, "doctor: " + doctor);
		boolean result = false;
		try {
			boolean isAppointmentAvailable = isAppointmentAvailable(appointmentDto);
			logger.log(MohLogger.INFO, "isAppointmentAvailable = " + isAppointmentAvailable);
			if (isAppointmentAvailable) {
				Appointment appointment = new Appointment();
				appointment.setDoctor(doctor);
				appointment.setUser(patient);
				appointment.setAppointmentDate(appointmentDto.getAppointmentDay());
				appointment.setAppointmentEndHour(appointmentDto.getToHour());
				appointment.setAppointmentEndMin(appointmentDto.getToMin());
				appointment.setLanes(appointmentDto.getLane());
				appointment.setSlotStatus(appointmentDto.getSlotStatus());
				logger.log(MohLogger.INFO, "Creating Appointment: " + appointment);
				result = appointmentService.createAppointment(appointment);
				logger.log(MohLogger.INFO, "Appointment Created: " + result);
			} else {
				logger.log(MohLogger.WARN, "Appointment Not Available");
				responseDto.setErrorNo(1);
				responseDto.setErrorMsg("Doctor is already booked at this appointment timing.Please select another appointment timing");
			}
		} catch (Exception e) {
			logger.log(MohLogger.ERROR, "Exception: Creating Appointment: " + e.getMessage());
			responseDto.setErrorNo(1);
			responseDto.setErrorMsg(e.getMessage());
		}
		if (result) {
			responseDto.setErrorNo(0);
			responseDto.setSuccessMsg("Booking Appointment is done successfully");
		}
		return responseDto;
	}

	private boolean isAppointmentAvailable(AppointmentRecordDto appointmentDto) throws Exception {
		List<Appointment> appointments = appointmentService.getAllAppointment(appointmentDto.getAppointmentDay(), appointmentDto.getDoctorId());
		boolean isAppointmentAvailable = false;
		if (appointments.isEmpty()) {
			isAppointmentAvailable = true;
		} else {
			for (Appointment appointment : appointments) {
				isAppointmentAvailable = isAppointmentAvail(appointmentDto, appointment);
			}
		}
		logger.log(MohLogger.INFO, "isAppointmentAvailable = " + isAppointmentAvailable);
		return isAppointmentAvailable;
	}

	private boolean isAppointmentAvail(AppointmentRecordDto appointmentDto, Appointment app) {
		Date appEndDate = DateUtils.getEndDate(app.getAppointmentDate(), app.getAppointmentEndHour(), app.getAppointmentEndMin());
		Date appointmentDtoEndDate = DateUtils.getEndDate(appointmentDto.getAppointmentDay(), appointmentDto.getToHour(), appointmentDto.getToMin());
		boolean isFromDateCross = DateUtils.isDateExistInDateRange(app.getAppointmentDate(), appEndDate, appointmentDto.getAppointmentDay());
		if (isFromDateCross) {
			return false;
		}
		boolean isToDateCross = DateUtils.isDateExistInDateRange(app.getAppointmentDate(), appEndDate, appointmentDtoEndDate);
		if (isToDateCross) {
			return false;
		}
		if (appointmentDto.getAppointmentDay().before(app.getAppointmentDate()) && appointmentDtoEndDate.after(appEndDate)) {
			return false;
		}
		return true;
	}

	@PreAuthorize("hasRole('user_visits')")
	public AppointmentHistoryResDto loadPatientAppointmentHistory(String patientIdString) {
		logger.log(MohLogger.INFO, "loadPatientAppointmentHistory(): " + patientIdString);
		AppointmentHistoryResDto response = new AppointmentHistoryResDto();
		if (patientIdString != null) {
			try {
				Integer patientId = Integer.parseInt(patientIdString);
				response.setPatientId(patientId);
				List<Appointment> appointments = appointmentService.getAllAppointmentByPatient(patientId);
				logger.log(MohLogger.INFO, "appointments: " + appointments);
				List<AppointmentInfoDto> appointmentInfoDtos = new ArrayList<AppointmentInfoDto>();
				for (Appointment appointment : appointments) {
					AppointmentInfoDto appointmentDto = new AppointmentInfoDto();
					appointmentPopulator.populate(appointment, appointmentDto);
					appointmentInfoDtos.add(appointmentDto);
				}
				response.setAppointments(appointmentInfoDtos);
				response.setErrorNo(0);
				response.setSuccessMsg("patient visits history is loaded successfully");
				logger.log(MohLogger.INFO, "Patient visits history is loaded successfully");
			} catch (NumberFormatException e) {
				logger.log(MohLogger.ERROR, "NumberFormatException: Load Patient Appointment History. " + e.getMessage());
				response.setErrorNo(1);
				response.setErrorMsg("INVALID PatientId");
			} catch (ConversionException e) {
				logger.log(MohLogger.ERROR, "ConversionException: Load Patient Appointment History. " + e.getMessage());
				response.setErrorNo(1);
				response.setErrorMsg("Error While conveting model to dto ");
			} catch (Exception e) {
				logger.log(MohLogger.ERROR, "Exception: Load Patient Appointment History. " + e.getMessage());
				response.setErrorNo(1);
				response.setErrorMsg("Error While getting Appointment List");
				logger.log(MohLogger.ERROR, "" + e);
			}
		} else {
			logger.log(MohLogger.WARN, "PatientId is required to get patient all visits.");
			response.setErrorNo(1);
			response.setErrorMsg("PatientId is required to get patient all visits.");
		}
		return response;
	}

	@PreAuthorize("hasRole('patient_create_record')")
	public Integer createPatientRecord(PatientRecordDto patiendRecordDto) throws Exception {
		logger.log(MohLogger.INFO, "Creating Patient Record...");
		// here all data are valid.
		PatientRecord patientRecord = null;
		if (patiendRecordDto.getPatientRecordId() != null) { // EDIT PR
			patientRecord = appointmentService.getPatientRecordById(patiendRecordDto.getPatientRecordId());
		} else if(patiendRecordDto.getAppointmentId() != null) { // EDIT PR
			patientRecord = appointmentService.getPatientRecordByAppointmentId(patiendRecordDto.getAppointmentId() );
		}
		if(patientRecord == null) { // NEW PR
			patientRecord = new PatientRecord();
		}
		patientRecord.setAppopintmentId(patiendRecordDto.getAppointmentId());
		patientRecord.setSummary(patiendRecordDto.getSummary());
		patientRecord.setPrescription(patiendRecordDto.getPrescription());
		patientRecord.setAttach1(patiendRecordDto.getAttach1Path());
		patientRecord.setAdviseAdmit(Boolean.TRUE);
		return appointmentService.createPatientRecord(patientRecord);
	}

	@Override
	public File getPrescriptionAttachmentLocation(Integer userId, String formattedTodayDate) {
		logger.log(MohLogger.INFO, "getPrescriptionAttachmentLocation()");
		User patient = userService.getUserById(userId);
		patient.init();
		File prescriptionFolderpath = ConfigurationHelper.getFolderPrescriptionPath(appSettingService,patient.getUserNamePlain(), formattedTodayDate);
		return prescriptionFolderpath;
	}

	@PreAuthorize("hasRole('appointment_notification_to_patients')")
	public ResponseDto appointmentNotificationToPatient() {
		logger.log(MohLogger.INFO, "appointmentNotificationToPatient({})");
		ResponseDto response = new ResponseDto();
		try {
			appointmentService.appointmentNotificationToPatient();
			response.setErrorNo(0);
			response.setSuccessMsg("notification Sent");
			logger.log(MohLogger.INFO, "Success: appointmentNotificationToPatient()");
		} catch (Exception e) {
			logger.log(MohLogger.ERROR, "Exception: appointmentNotificationToPatient()");
			logger.log(MohLogger.ERROR, "" + e);
			response.setErrorNo(1);
			response.setErrorMsg("Got Exception");
		}
		logger.log(MohLogger.INFO, "response = " + response);
		return response;
	}

    @PreAuthorize("hasRole('patient_view_record')")
	public ViewPatientRecordResDto viewPatientRecord(int appointmentId) {
		logger.log(MohLogger.INFO, "viewPatientRecord(): " + appointmentId);
		ViewPatientRecordResDto response = new ViewPatientRecordResDto();
		PatientRecord patientRecord = appointmentService.getPatientRecordByAppointmentId(appointmentId);
		Appointment appointment = appointmentService.getAppointmentById(appointmentId);
		if (patientRecord != null) {
			PatientRecordDto patientRecordDto = new PatientRecordDto();
			try {
				patientRecordPopulator.populate(patientRecord, patientRecordDto);
				// Normalize path for downloading report
				if (patientRecord.getAttach1() != null) {
					patientRecordDto.setAttach1Path(ConfigurationHelper.getFileDownloadUrl(appSettingService,patientRecord.getAttach1()));
				}
				if (patientRecord.getAttach2() != null) {
					patientRecordDto.setAttach2Path(ConfigurationHelper.getFileDownloadUrl(appSettingService,patientRecord.getAttach2()));
				}
				String dateOfVisit = DateUtils.toString(appSettingService.getAppSettingValye("1" , "1", "English", "PRESCRIPTION_DIR_DATE_FORMAT"), appointment.getAppointmentDate());
				String doctorName = appointment.getDoctor().getUser().getFullName();
				patientRecordDto.setDateOfVisit(dateOfVisit);
				patientRecordDto.setDoctorName(doctorName);
				response.setPatientRecordDto(patientRecordDto);
				response.setErrorNo(0);
				response.setErrorMsg("getting patient Record is done successfully");
				logger.log(MohLogger.INFO, "Getting patient Record is done successfully");
			} catch (ConversionException e) {
				logger.log(MohLogger.ERROR, "" + e);
				response.setErrorNo(1);
				response.setErrorMsg("Conversion failed in populator");
				logger.log(MohLogger.ERROR, "ConversionException: Conversion failed in populator");
			}
		} else {
			response.setErrorNo(0);
			response.setSuccessMsg("No Patient Record is founded for appointmentId:" + appointmentId);
			logger.log(MohLogger.WARN, "No Patient Record is founded for appointmentId: {}" + appointmentId);
		}
		logger.log(MohLogger.INFO, "response = " + response);
		return response;
	}

	@Override
	public PatientRecordDto getPatientRecord(Integer patientRecordId) {
		logger.log(MohLogger.INFO, "getPatientRecord()" + patientRecordId);
		PatientRecord patientRecord = appointmentService.getPatientRecordById(patientRecordId);
		if (patientRecord == null) {
			return null;
		}
		PatientRecordDto patientRecordDto = new PatientRecordDto();
		try {
			patientRecordPopulator.populate(patientRecord, patientRecordDto);
			// Normalize path for downloading report
			if (patientRecord.getAttach1() != null) {
				patientRecordDto.setAttach1Path(ConfigurationHelper.getFileDownloadUrl(appSettingService,patientRecord.getAttach1()));
			}
			if (patientRecord.getAttach2() != null) {
				patientRecordDto.setAttach2Path(ConfigurationHelper.getFileDownloadUrl(appSettingService,patientRecord.getAttach2()));
			}
			logger.log(MohLogger.INFO, "Success: getPatientRecord()");
		} catch (ConversionException e) {
			logger.log(MohLogger.ERROR, "ConversionException: " + e.getMessage());
			logger.log(MohLogger.ERROR, "" + e);
		}
		return patientRecordDto;
	}

	@Override
	public AppointmentInfoDto getAppointmentById(int appointmentId) {
		logger.log(MohLogger.INFO, "getAppointmentById(): " + appointmentId);
		AppointmentInfoDto appointmentInfoDto = null;
		Appointment appointment = appointmentService.getAppointmentById(appointmentId);
		if (appointment != null) {
			appointmentInfoDto = new AppointmentInfoDto();
			try {
				appointmentPopulator.populate(appointment, appointmentInfoDto);
				logger.log(MohLogger.INFO, "Success: appointmentPopulator.populate()");
			} catch (ConversionException e) {
				logger.log(MohLogger.ERROR, "ConversionException: getAppointmentById()");
				logger.log(MohLogger.ERROR, "" + e);
			}
		}
		return appointmentInfoDto;
	}

	@Override
	public File getPrescriptionAttachmentLocationWithoutRoot(Integer patientId, String formattedTodayDateString) {
		logger.log(MohLogger.INFO, "getPrescriptionAttachmentLocationWithoutRoot()");
		User patient = userService.getUserById(patientId);
		logger.log(MohLogger.INFO, "patient: " + patient);
		patient.init();
		File prescriptionFolderpath = ConfigurationHelper.getFolderPrescriptionPathWithoutRoot(appSettingService,patient.getUserNamePlain(), formattedTodayDateString);
		return prescriptionFolderpath;
	}

	@PreAuthorize("hasRole('appointment_search')")
	public AppointmentSeachResultResDto searchAppointmentByUserEmailOrMobile(String userId) {
		logger.log(MohLogger.INFO, "searchAppointmentByUserEmailOrMobile()");
		AppointmentSeachResultResDto responseDto = new AppointmentSeachResultResDto();
		List<Appointment> appointments = null;
		try {
			appointments = appointmentService.getAppointmentByUserEmailOrMobile(userId);
			logger.log(MohLogger.INFO, "appointments: " + appointments);
		} catch (Exception e) {
			logger.log(MohLogger.ERROR, "Failed to get Appointment list for userID: " + userId);
			logger.log(MohLogger.ERROR, e.getMessage());
			responseDto.setErrorNo(1);
			responseDto.setErrorMsg("failed to get Appointment list for userID:" + userId);
		}
		List<AppointmentResultDto> appointmentResults = new ArrayList<AppointmentResultDto>();
		if (appointments != null && appointments.isEmpty() == false) {
			for (Appointment app : appointments) {
				AppointmentResultDto appointmentResultDto = new AppointmentResultDto();
				try {
					appointmentSearchResultPopulator.populate(app, appointmentResultDto);
					logger.log(MohLogger.INFO, "Success: appointmentSearchResultPopulator.populate()");
				} catch (ConversionException e) {
					logger.log(MohLogger.ERROR, "ConversionException: conversion failed in populator");
					logger.log(MohLogger.ERROR, "" + e);
					responseDto.setErrorMsg("conversion failed in populator");
					responseDto.setErrorNo(1);
				}
				appointmentResults.add(appointmentResultDto);
			}
		}
		responseDto.setAppointmentResults(appointmentResults);
		responseDto.setErrorNo(0);
		responseDto.setSuccessMsg("fetched appointment list SUCCESS");
		logger.log(MohLogger.INFO, "Success: searchAppointmentByUserEmailOrMobile()");
		return responseDto;
	}

	@Override
	public void deleteUserAndAppointments(UserRegDto userRegDto) throws Exception {
		String email = userRegDto.getEmail();
		User user = userService.getUserByEmail(email);
		if (user != null) {
			Integer patientId = user.getId();
			List<Appointment> appointments = appointmentService.getAllAppointmentByPatient(patientId);
			appointmentService.deleteAppointments(appointments);
			userService.deleteUser(user);
		}

	}
}
