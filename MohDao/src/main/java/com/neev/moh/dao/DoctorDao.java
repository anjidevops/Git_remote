package com.neev.moh.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.neev.moh.model.Doctor;

public interface DoctorDao {

	Integer createDoctor(Doctor doctor) throws DataAccessException, Exception;

	Doctor getDoctorById(Integer DocId);

	Doctor getDoctorByEmail(String email);

	List<Doctor> loadDoctorsByDepartment(int departmentId);

}
