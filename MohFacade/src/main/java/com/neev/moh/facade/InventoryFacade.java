package com.neev.moh.facade;

import com.neev.moh.facade.dto.MedicineInventoryDto;
import com.neev.moh.facade.dto.response.MedicineInventoryResDto;

public interface InventoryFacade {

	MedicineInventoryResDto saveMedicine(MedicineInventoryDto medicineInventoryDto);

	MedicineInventoryResDto getMedicineList(String searchText);
	
	MedicineInventoryResDto doReport();

}
