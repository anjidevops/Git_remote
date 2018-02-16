package com.neev.moh.rpt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.neev.moh.rpt.model.UserCount;

public interface DoctorReportDao {
	
	public  List<UserCount> getNumberAptsPerDoctor() throws DataAccessException, Exception;
	
	

}
