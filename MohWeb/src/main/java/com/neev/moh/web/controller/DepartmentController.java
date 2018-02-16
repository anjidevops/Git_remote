package com.neev.moh.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neev.moh.facade.DepartmentFacade;
import com.neev.moh.facade.dto.response.DepartmentResDto;
import com.neev.moh.facade.dto.response.DoctorListResDto;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;

@Controller
@RequestMapping(value = "/department")
public class DepartmentController {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(DepartmentController.class.getName());

	@Autowired(required = true)
	private DepartmentFacade departmentFacade;

	@PreAuthorize("hasRole('department_all')")
	@RequestMapping(value = "allDepartment", method = RequestMethod.GET)
	public @ResponseBody DepartmentResDto getAllDepartment() {
		DepartmentResDto response = departmentFacade.getAllDepartment();
		return response;
	}

	@PreAuthorize("hasRole('department_all_doctor_by_department')")
	@RequestMapping(value = "allDoctorByDepartment", method = RequestMethod.GET)
	public @ResponseBody DoctorListResDto getDoctorListByDepartment(final HttpServletRequest req) {
		String departmentId = req.getParameter("departmentId");
		logger.log(MohLogger.INFO, "departmentId: " + departmentId);
		DoctorListResDto response = departmentFacade.loadDoctorsByDepartment(departmentId);
		return response;
	}

	public DepartmentFacade getDepartmentFacade() {
		return departmentFacade;
	}

	public void setDepartmentFacade(DepartmentFacade departmentFacade) {
		this.departmentFacade = departmentFacade;
	}

}
