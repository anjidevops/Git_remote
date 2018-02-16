package com.neev.moh.services.notifications.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.neev.moh.dao.InventoryDao;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.MedicineInventory;
import com.neev.moh.services.dto.InventoryReport;
import com.neev.moh.services.notifications.LowInventoryLevelNotifier;

public class LowInventoryLevelNotificationImpl implements LowInventoryLevelNotifier {
	
	private static final MohLogger logger = MohLogFactory.getLoggerInstance(LowInventoryLevelNotificationImpl.class.getName());

	@Autowired(required = true)
	private InventoryDao inventoryDao;

	@Autowired(required = true)
	private JavaMailSender mailSender;

	@Autowired(required = true)
	private VelocityEngine velocityEngine;

	@Override
	public List<MedicineInventory> processInventoryReport() throws Exception {
		logger.log(MohLogger.DEBUG, "LowInventoryLevelNotificationImpl.processInventoryReport()");
		List<MedicineInventory> medlist = null;
		try {
			medlist = inventoryDao.doReport();
			if (medlist != null) {
				logger.log(MohLogger.DEBUG, "Medicine Inventory list: " + medlist);
				final InventoryReport repot = new InventoryReport();
				repot.setSetListObject(medlist);
				sendReport("moh29Dec@gmail.com", "moh29Dec@gmail.com", repot);
			}
			logger.log(MohLogger.INFO, "Success: LowInventoryLevelNotificationImpl.processInventoryReport()");
		} catch (Exception e) {
			logger.log(MohLogger.ERROR, "Exception: LowInventoryLevelNotificationImpl.processInventoryReport()");
			throw new Exception(e.getMessage());
		}
		return medlist;
	}

	@Override
	public void autoProcessInventoryReport() {
		logger.log(MohLogger.DEBUG, "LowInventoryLevelNotificationImpl.processInventoryReport()");
		logger.log(MohLogger.INFO, "lowInventorylevel Notification report Started executing...");
		List<MedicineInventory> medlist = null;
		try {
			medlist = inventoryDao.doReport();
			if (medlist != null) {
				logger.log(MohLogger.DEBUG, "Scheduler List Medicine Inventory list: " + medlist);
				final InventoryReport repot = new InventoryReport();
				repot.setSetListObject(medlist);
				sendReport("moh29Dec@gmail.com", "moh29Dec@gmail.com", repot);
			}
			logger.log(MohLogger.INFO, "Success: LowInventoryLevelNotificationImpl.processInventoryReport()");
		} catch (Exception e) {
			logger.log(MohLogger.ERROR, "Exception: LowInventoryLevelNotificationImpl.processInventoryReport()");
			logger.log(MohLogger.ERROR, "" + e);
		}
	}

	public void sendReport(final String from, final String to, final Object data) {
		logger.log(MohLogger.DEBUG, "LowInventoryLevelNotificationImpl.sendReport()");
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws VelocityException {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				try {
					message.setFrom(from);
					message.setTo(to);
					message.setSubject("Inventory Notification");
					Map<String, Object> model = new HashMap<String, Object>();
					model.put("key", data);
					@SuppressWarnings("deprecation")
					String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "inventoryReportTemplate.vm", model);
					message.setText(text, true);
					logger.log(MohLogger.INFO, "Success: LowInventoryLevelNotificationImpl.sendReport()");
				} catch (MessagingException e) {
					logger.log(MohLogger.ERROR, "MessagingException: LowInventoryLevelNotificationImpl.sendReport()");
					logger.log(MohLogger.ERROR, "" + e);
				}
			}
		};
		this.mailSender.send(preparator);
	}
}