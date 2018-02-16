package com.neev.moh.facade.dto.response;

import java.util.List;

import com.neev.moh.facade.dto.DepartmentInfoDto;

public class DepartmentResDto extends ResponseDto {
	
	private List<DepartmentInfoDto> departments ;

	public List<DepartmentInfoDto> getDepartments() {
		return departments;
	}

	public void setDepartments(List<DepartmentInfoDto> departments) {
		this.departments = departments;
	}
	
}
