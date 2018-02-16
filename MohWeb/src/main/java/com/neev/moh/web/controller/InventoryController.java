package com.neev.moh.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neev.moh.facade.InventoryFacade;
import com.neev.moh.facade.dto.MedicineInventoryDto;
import com.neev.moh.facade.dto.response.MedicineInventoryResDto;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;

@Controller
@RequestMapping(value = "/inventory")
public class InventoryController {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(DoctorController.class.getName());

	@Autowired(required = true)
	private InventoryFacade inventoryFacade;

	@PreAuthorize("hasRole('inventory_get_medicine_list')")
	@RequestMapping(value = "getMedicineList", method = RequestMethod.GET)
	public @ResponseBody MedicineInventoryResDto getMedicineList(final HttpServletRequest req) {
		String searchText = req.getParameter("searchText");
		logger.log(MohLogger.INFO, "searchText: " + searchText);
		MedicineInventoryResDto medicineInventoryResDto = inventoryFacade.getMedicineList(searchText);
		return medicineInventoryResDto;
	}

	@PreAuthorize("hasRole('inventory_add_edit_medicine')")
	@RequestMapping(value = "addEditMedicine", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody MedicineInventoryResDto addEditMedicine(@RequestBody MedicineInventoryDto medicineInventoryDto) {
		MedicineInventoryResDto medicineInventoryResDto = inventoryFacade.saveMedicine(medicineInventoryDto);
		return medicineInventoryResDto;
	}

	@PreAuthorize("hasRole('inventory_send_report')")
	@RequestMapping(value = "sendReport", method = RequestMethod.POST)
	public @ResponseBody MedicineInventoryResDto doReport() {
		MedicineInventoryResDto medicineInventoryResDto = inventoryFacade.doReport();
		return medicineInventoryResDto;
	}

}
