package com.neev.moh.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.neev.moh.model.MedicineInventory;

public interface InventoryDao {

	Integer saveMedicine(MedicineInventory medicineInvertory) throws DataAccessException, Exception;

	List<MedicineInventory> getMedicineList(String searchText) throws DataAccessException, Exception;
	
	List<MedicineInventory> doReport() throws DataAccessException, Exception;
	

}
