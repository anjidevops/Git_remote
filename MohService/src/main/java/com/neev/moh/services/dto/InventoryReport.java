package com.neev.moh.services.dto;

import java.util.List;

import com.neev.moh.model.MedicineInventory;

public class InventoryReport {
	private List<MedicineInventory> setListObject;

	public List<MedicineInventory> getSetListObject() {
		return setListObject;
	}

	public void setSetListObject(List<MedicineInventory> setListObject) {
		this.setListObject = setListObject;
	}
}
