package com.neev.moh.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import com.neev.moh.facade.InventoryFacade;
import com.neev.moh.facade.dto.MedicineInventoryDto;
import com.neev.moh.facade.dto.response.MedicineInventoryResDto;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.MedicineInventory;
import com.neev.moh.services.InventoryService;

@Transactional(rollbackFor=Exception.class)
public class InventoryFacadeImpl implements InventoryFacade {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(InventoryFacadeImpl.class.getName());

	@Autowired(required = true)
	private InventoryService inventoryService;

	public InventoryService getInventoryService() {
		return inventoryService;
	}

	public void setInventoryService(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@PreAuthorize("hasRole('inventory_add_edit_medicine')")
	public MedicineInventoryResDto saveMedicine(MedicineInventoryDto medicineInventoryDto) {
		logger.log(MohLogger.INFO, "saveMedicine()");
		Integer result = null;
		MedicineInventory inventory = new MedicineInventory();
		MedicineInventoryResDto medicineInventoryResDto = new MedicineInventoryResDto();
		// Copy the data MedicineInventoryDto to MedicineInventory
		if (medicineInventoryDto.getId() > 0) {
			inventory.setId(medicineInventoryDto.getId());
		}
		inventory.setMedicineName(medicineInventoryDto.getMedicineName());
		inventory.setThresoldQuantity(medicineInventoryDto.getThresoldQuantity());
		inventory.setAvailableQuantity(medicineInventoryDto.getAvailableQuantity());
		try {
			result = inventoryService.saveMedicine(inventory);
			logger.log(MohLogger.INFO, "Save Medicine Result = " + result);
		} catch (Exception e) {
			logger.log(MohLogger.ERROR, "Exception: saveMedicine()");
			if (e instanceof DataIntegrityViolationException) {
				medicineInventoryResDto.setErrorNo(1);
				medicineInventoryResDto.setErrorMsg("Duplicate Entry, Mobile or Email Already Exists");
				logger.log(MohLogger.ERROR, "DataIntegrityViolationException: Duplicate Entry, Mobile or Email Already Exists");
			} else {
				medicineInventoryResDto.setErrorMsg(e.getMessage());
				medicineInventoryResDto.setErrorNo(0);
				logger.log(MohLogger.ERROR, "Exception: saveMedicine() - " + e.getMessage());
			}
		}
		if (result != null) {
			if (medicineInventoryResDto.getId() > 0 && medicineInventoryDto.getId() == result) {
				medicineInventoryResDto.setSuccessMsg("New Inventory Edited");
				logger.log(MohLogger.INFO, "New Inventory Edited");
			} else {
				medicineInventoryResDto.setId(result);
				medicineInventoryResDto.setErrorNo(0);
				medicineInventoryResDto.setSuccessMsg("New Inventory Added");
				logger.log(MohLogger.INFO, "New Inventory Added");
			}
		}
		return medicineInventoryResDto;
	}

	@PreAuthorize("hasRole('inventory_get_medicine_list')")
	public MedicineInventoryResDto getMedicineList(String searchText) {
		logger.log(MohLogger.INFO, "getMedicineList()");
		MedicineInventoryResDto inventoryResDto = null;
		List<MedicineInventoryDto> miList = null;
		MedicineInventoryDto mi = null;
		try {
			inventoryResDto = new MedicineInventoryResDto();
			List<MedicineInventory> medList = inventoryService.getMedicineList(searchText);
			logger.log(MohLogger.INFO, "medList: " + medList);
			if (medList != null && medList.size() > 0) {
				miList = new ArrayList<MedicineInventoryDto>();
				for (MedicineInventory med : medList) {
					mi = new MedicineInventoryDto();
					mi.setId(med.getId());
					mi.setMedicineName(med.getMedicineName());
					mi.setThresoldQuantity(med.getThresoldQuantity());
					mi.setAvailableQuantity(med.getAvailableQuantity());
					miList.add(mi);
				}
				if (miList != null && miList.size() > 0) {
					inventoryResDto.setList(miList);
				}
			}
			logger.log(MohLogger.INFO, "Success: getMedicineList()");
		} catch (Exception e) {
			logger.log(MohLogger.ERROR, "Exception: getMedicineList()");
			if (e instanceof DataIntegrityViolationException) {
				inventoryResDto.setErrorNo(1);
				inventoryResDto.setErrorMsg("Search Medicine List Fail");
				logger.log(MohLogger.ERROR, "DataIntegrityViolationException: " + e.getMessage());
			} else {
				inventoryResDto.setErrorMsg(e.getMessage());
				inventoryResDto.setErrorNo(0);
				logger.log(MohLogger.ERROR, "Exception: " + e.getMessage());
			}
		}
		return inventoryResDto;
	}

	@PreAuthorize("hasRole('inventory_send_report')")
	public MedicineInventoryResDto doReport() {
		logger.log(MohLogger.INFO, "doReport()");
		MedicineInventoryResDto response = new MedicineInventoryResDto();
		try {
			inventoryService.doReport();
			response.setErrorNo(0);
			response.setSuccessMsg("Report Sent");
			logger.log(MohLogger.INFO, "Report Sent");
		} catch (Exception e) {
			logger.log(MohLogger.ERROR, "" + e);
			response.setErrorNo(1);
			response.setErrorMsg("Got Exception");
			logger.log(MohLogger.ERROR, "Exception: doReport() - " + e.getMessage());
		}
		return response;
	}

}
