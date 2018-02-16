package com.neev.moh.facade.dto;


public class MedicineInventoryDto {
	
	private int id;
	private String medicineName;
	private int thresoldQuantity;
	private int availableQuantity;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public int getThresoldQuantity() {
		return thresoldQuantity;
	}

	public void setThresoldQuantity(int thresoldQuantity) {
		this.thresoldQuantity = thresoldQuantity;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	
}
