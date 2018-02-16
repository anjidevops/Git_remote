package com.neev.moh.facade.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neev.moh.facade.UserFacade;
import com.neev.moh.facade.convert.populator.ConversionException;
import com.neev.moh.facade.convert.populator.UserInfoPopulator;
import com.neev.moh.facade.dto.AppointmentDto;
import com.neev.moh.facade.dto.UserInfoDto;
import com.neev.moh.facade.dto.UserRegDto;
import com.neev.moh.facade.dto.response.ResponseDto;
import com.neev.moh.facade.dto.response.SearchResultResDto;
import com.neev.moh.facade.dto.response.UserRegRespDto;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.Address;
import com.neev.moh.model.PatientRecord;
import com.neev.moh.model.User;
import com.neev.moh.services.UserService;

@Service
@Transactional(rollbackFor=Exception.class)
public class UserFacadeImpl implements UserFacade {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(UserFacadeImpl.class.getName());

	@Autowired(required = true)
	private UserService userService;

	@Autowired(required = true)
	private UserInfoPopulator userInfoPopulator;

	public UserFacadeImpl() {
	}

	public UserFacadeImpl(UserService userService) {
		this.userService = userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@PreAuthorize("hasRole('user_registration')")
	public UserRegRespDto createUser(UserRegDto userRegDTO) {
		logger.log(MohLogger.INFO, "createUser()");
		UserRegRespDto responseDto = new UserRegRespDto();
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
		Iterator<Address> iterable = userRegDTO.getAddresses().iterator();
		while (iterable.hasNext()) {
			iterable.next().setUser(newUser);
		}
		try {
			result = userService.createUser(newUser);
			logger.log(MohLogger.INFO, "result: " + result);
		} catch (Exception e) {
			logger.log(MohLogger.ERROR, "Exception: createUser()");
			if (e instanceof DataIntegrityViolationException) {
				responseDto.setErrorMsg("Duplicate Entry, Mobile or Email Already Exists");
				logger.log(MohLogger.ERROR, "DataIntegrityViolationException: Duplicate Entry, Mobile or Email Already Exists");
			} else {
				responseDto.setErrorMsg(e.getMessage());
				logger.log(MohLogger.ERROR, "Exception: createUser() - " + e.getMessage());
			}
			responseDto.setErrorNo(1);
		}
		if (result != null) {
			responseDto.setErrorNo(0);
			responseDto.setSuccessMsg("New User is Created");
			logger.log(MohLogger.INFO, "New User is Created");
			responseDto.setRegistrationSuccess(Boolean.TRUE);
			responseDto.setUserId(result);
		}
		return responseDto;
	}

	public User getUserByEmail(String email) {
		logger.log(MohLogger.INFO, "getUserByEmail parameter value is: " + email);
		return userService.getUserByEmail(email);
	}

	@PreAuthorize("hasRole('user_search')")
	public SearchResultResDto searchUserByEmailOrMobile(String searchText) {
		logger.log(MohLogger.INFO, "searchUserByEmailOrMobile()");
		SearchResultResDto response = new SearchResultResDto();
		response.setSearchText(searchText);
		List<User> users = userService.searchUsersByEmailOrMobile(searchText);
		List<UserInfoDto> userDtos = new ArrayList<UserInfoDto>();
		if (users != null && !users.isEmpty()) {
			try {
				for (User user : users) {
					UserInfoDto userInfoDto = new UserInfoDto();
					userInfoPopulator.populate(user, userInfoDto);
					userDtos.add(userInfoDto);
				}
				response.setErrorNo(0);
				response.setSuccessMsg("Searching Done");
				logger.log(MohLogger.INFO, "Searching Done");
			} catch (ConversionException e) {
				logger.log(MohLogger.ERROR, "" + e);
				response.setErrorNo(1);
				response.setErrorMsg("Error While conveting model to dto");
				logger.log(MohLogger.ERROR, "ConversionException: Error While conveting model to dto");
			}
		} else {
			response.setErrorNo(0);
			response.setSuccessMsg("No Result Found");
			logger.log(MohLogger.INFO, "No Result Found");
		}
		response.setResult(userDtos);
		return response;
	}

	public ResponseDto createAppointment(final AppointmentDto appointmentDto) {
		logger.log(MohLogger.INFO, "createAppointment()");
		PatientRecord record = new PatientRecord();
		ResponseDto responseDto = new ResponseDto();
		/*
		if(appointmentDto.getPatientId() != null){
			User patient  = userService.getUserById(appointmentDto.getPatientId());
			if(patient !=null){
				record.setUser(patient);
				record.setDoctorName(appointmentDto.getDoctor());
				record.setDoctorDept(appointmentDto.getDepartment());
				record.setAppointmentDate(appointmentDto.getBookingAppointmentDate());
				record.setAppointmentTime(appointmentDto.getBookedSlot());
				
				boolean isDoctorAvailable = userService.isDoctorAvailableForTimeSlot(appointmentDto.getDoctor(), appointmentDto.getBookedSlot());
				if(isDoctorAvailable){
					String result = null;
					try {
						result = userService.createAppointment(record);
					} catch (Exception e) {
						if(e instanceof DataIntegrityViolationException){
							responseDto.setErrorMsg("Duplicate appointment or other Db issue");
						}else{
							responseDto.setErrorMsg(e.getMessage());
						}
						responseDto.setErrorNo(1);
					}
					if(result!=null) {
						responseDto.setErrorNo(0);
						responseDto.setSuccessMsg("booking Appointment is done successfully");
					}
				}else{
					responseDto.setErrorNo(1);
					responseDto.setErrorMsg("Appointment for Doctor "+appointmentDto.getDoctor() +
							" and timeslot "+appointmentDto.getBookedSlot()+" is already booked.");
				}
				
			}else{
				responseDto.setErrorNo(1);
				responseDto.setErrorMsg("Wrong Patient Id");
			}
		}else{
			responseDto.setErrorNo(1);
			responseDto.setErrorMsg("Patient Id is mandatory for appointment");
		}
		*/
		return responseDto;
	}

	@PreAuthorize("hasRole('cache_clear')")
	public String clearCache(String clearAllCache, String clearCache, String cacheName) {
		String status = null;
		if (clearAllCache != null && !clearAllCache.isEmpty() && !"null".equalsIgnoreCase(clearAllCache)) {
			status = userService.clearAllCache();
		} else if (clearCache != null && !clearCache.isEmpty()) {
			if (cacheName != null && !cacheName.isEmpty()) {
				if ("addressCache".equals(cacheName)) {
					status = userService.clearAddressCache();
				} else if ("appointmentCache".equals(cacheName)) {
					status = userService.clearAppointmentCache();
				} else if ("departmentCache".equals(cacheName)) {
					status = userService.clearDepartmentCache();
				} else if ("doctorCache".equals(cacheName)) {
					status = userService.clearDoctorCache();
				} else if ("genderCache".equals(cacheName)) {
					status = userService.clearGenderCache();
				} else if ("medicineInventoryCache".equals(cacheName)) {
					status = userService.clearMedicineInventoryCache();
				} else if ("patientCache".equals(cacheName)) {
					status = userService.clearPatientCache();
				} else if ("userCache".equals(cacheName)) {
					status = userService.clearUserCache();
				} else {
					logger.log(MohLogger.INFO, "Cache Name Not Found");
				}
			} else {
				logger.log(MohLogger.INFO, "Cache Name Is Empty");
			}
		} else {
			logger.log(MohLogger.INFO, "Cache Not Cleared");
		}
		return status;
	}

}