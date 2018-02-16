package com.neev.moh.facade;

import org.springframework.stereotype.Component;

import com.neev.moh.facade.dto.AppointmentDto;
import com.neev.moh.facade.dto.UserRegDto;
import com.neev.moh.facade.dto.response.ResponseDto;
import com.neev.moh.facade.dto.response.SearchResultResDto;
import com.neev.moh.facade.dto.response.UserRegRespDto;
import com.neev.moh.model.User;


@Component("UserFacade") 
public interface UserFacade {

	UserRegRespDto createUser(UserRegDto userRegDTO);

	User getUserByEmail(String email);

	SearchResultResDto searchUserByEmailOrMobile(String emailOrMobile);

	ResponseDto createAppointment(final AppointmentDto appointmentDto);

	String clearCache(String clearAllCache, String clearCache, String cacheName);

}
