package com.neev.moh.rpt.service;

import java.util.List;

import com.neev.moh.rpt.model.UserCount;

public interface DoctorReportService {
	
	public List<UserCount> getNumberAptsPerDoctor() throws Exception;

}
