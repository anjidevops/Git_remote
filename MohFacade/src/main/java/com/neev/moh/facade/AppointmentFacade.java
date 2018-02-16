package com.neev.moh.facade;

import java.io.File;

import org.springframework.stereotype.Component;

import com.neev.moh.facade.dto.AppointmentInfoDto;
import com.neev.moh.facade.dto.AppointmentRecordDto;
import com.neev.moh.facade.dto.PatientRecordDto;
import com.neev.moh.facade.dto.response.AppointmentHistoryResDto;
import com.neev.moh.facade.dto.response.AppointmentSeachResultResDto;
import com.neev.moh.facade.dto.response.ResponseDto;
import com.neev.moh.facade.dto.response.ViewPatientRecordResDto;
import com.neev.moh.facade.dto.UserRegDto;

@Component("AppointmentFacade") 
public interface AppointmentFacade {

	ResponseDto createUserAndBookAppointment(UserRegDto userRegDTO, AppointmentRecordDto appointmentDto, boolean isDoctorAssociated) throws Exception;

	ResponseDto bookAppointment(final AppointmentRecordDto appointmentDto);

	AppointmentHistoryResDto loadPatientAppointmentHistory(String patientId);
	
	ResponseDto appointmentNotificationToPatient();

	File getPrescriptionAttachmentLocation(final Integer userId,final String formattedDate);
	
	Integer createPatientRecord(final PatientRecordDto patiendRecordDto) throws Exception;

	ViewPatientRecordResDto viewPatientRecord(int appointmentId);

	PatientRecordDto getPatientRecord(Integer patientRecordId);

	AppointmentInfoDto getAppointmentById(int appointmentId);

	File getPrescriptionAttachmentLocationWithoutRoot(Integer patientId, String formattedTodayDateString);

	AppointmentSeachResultResDto searchAppointmentByUserEmailOrMobile(String userId);

	void deleteUserAndAppointments(UserRegDto userRegDto) throws Exception;
	
}
