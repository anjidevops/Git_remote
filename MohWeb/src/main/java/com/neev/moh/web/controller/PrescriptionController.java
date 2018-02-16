package com.neev.moh.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;

@Controller
@RequestMapping(value = "/prescription")
public class PrescriptionController {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(PrescriptionController.class.getName());

}
