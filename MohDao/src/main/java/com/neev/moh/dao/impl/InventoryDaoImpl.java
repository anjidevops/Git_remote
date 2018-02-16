package com.neev.moh.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.neev.moh.dao.InventoryDao;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.MedicineInventory;

@Repository
public class InventoryDaoImpl implements InventoryDao {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(InventoryDaoImpl.class.getName());

	protected HibernateTemplate template = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		template = new HibernateTemplate(sessionFactory);
	}

	/**
	 * This method is used to ADD and EDIT the Medicine Inventory in Database.
	 */
	public Integer saveMedicine(MedicineInventory medicineInventory) throws DataAccessException, Exception {
		logger.log(MohLogger.INFO, "Saving Medicine");
		try {
			template.saveOrUpdate(medicineInventory);
			logger.log(MohLogger.INFO, "Success: Saving Medicine");
		} catch (DataAccessException e) {
			logger.log(MohLogger.ERROR, "DataAccessException: Saving Medicine");
			template.flush();
			throw e;
		}
		return template.get(MedicineInventory.class, medicineInventory.getId()).getId();
	}

	/**
	 * This method is used to get the Medicine Inventory List from the dataBase
	 * Table. Using with Free Search or get all the List.
	 */
	@SuppressWarnings("unchecked")
	public List<MedicineInventory> getMedicineList(String searchText) throws DataAccessException, Exception {
		logger.log(MohLogger.INFO, "getMedicineList(): " + searchText);
		List<MedicineInventory> list = null;
		try {
			if (searchText != null && searchText.trim().length() > 0) {
				list = (List<MedicineInventory>) template.find("from MedicineInventory mi where mi.medicineName like '" + searchText + "%'");
			} else {
				list = (List<MedicineInventory>) template.find("from MedicineInventory");
			}
		} catch (DataAccessException e) {
			logger.log(MohLogger.ERROR, "DataAccessException: getMedicineList(): " + searchText);
			template.flush();
			throw e;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MedicineInventory> doReport() throws DataAccessException, Exception {
		logger.log(MohLogger.INFO, "Do Report");
		List<MedicineInventory> list = null;
		try {
			list = (List<MedicineInventory>) template.find("from MedicineInventory m WHERE m.availableQuantity <= m.thresoldQuantity");
		} catch (DataAccessException e) {
			logger.log(MohLogger.ERROR, "DataAccessException: Do Report");
			template.flush();
			throw e;
		}
		return list;
	}
}
