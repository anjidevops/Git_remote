package com.neev.moh.services.notifications;

import java.util.List;

import com.neev.moh.model.MedicineInventory;

public interface LowInventoryLevelNotifier {
	List<MedicineInventory> processInventoryReport() throws Exception;
	void autoProcessInventoryReport();
}
