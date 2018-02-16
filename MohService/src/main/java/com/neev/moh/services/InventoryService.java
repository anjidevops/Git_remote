package com.neev.moh.services;

import java.util.List;

import com.neev.moh.model.MedicineInventory;

public interface InventoryService {

	Integer saveMedicine(MedicineInventory medicineInventory) throws Exception;

	List<MedicineInventory> getMedicineList(String searchText) throws Exception;
	
	List<MedicineInventory> doReport() throws Exception;

}
