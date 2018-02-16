package com.neev.moh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medicine_inventory")
public class MedicineInventory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int Id;

	@Column(name = "medicine_name", unique = true)
	private String medicineName;

	@Column(name = "thresold_quantity")
	private int thresoldQuantity;

	@Column(name = "available_quantity")
	private int availableQuantity;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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

	@Override
	public String toString() {
		return "MedicineInventory [Id=" + Id + ", medicineName=" + medicineName
				+ ", thresoldQuantity=" + thresoldQuantity
				+ ", availableQuantity=" + availableQuantity + "]";
	}

}
