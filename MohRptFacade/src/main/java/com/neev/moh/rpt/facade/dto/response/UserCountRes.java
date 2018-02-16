package com.neev.moh.rpt.facade.dto.response;



import java.util.List;

import com.neev.moh.facade.dto.response.ResponseDto;
import com.neev.moh.rpt.model.UserCount;

public class UserCountRes extends ResponseDto {
	
	private List<UserCount> userCountList;

	public List<UserCount> getUserCountList() {
		return userCountList;
	}

	public void setUserCountList(List<UserCount> userCountList) {
		this.userCountList = userCountList;
	}

	
	
}
