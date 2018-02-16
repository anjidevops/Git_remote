package com.neev.moh.rpt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.rpt.dao.DoctorReportDao;
import com.neev.moh.rpt.model.UserCount;

public class DoctorReportDaoImpl implements DoctorReportDao {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(DoctorReportDaoImpl.class.getName());

	protected HibernateTemplate template = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		template = new HibernateTemplate(sessionFactory);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<UserCount> getNumberAptsPerDoctor() throws DataAccessException, Exception {
		logger.log(MohLogger.INFO, "getNumberAptsPerDoctor()");
		List<UserCount> list = null;
		UserCount userCount = null;
		// Map<Integer,String> m1 = new HashMap<Integer,String>();
		try {
			String query = " SELECT u.fname AS firstName , count(u.fname) AS TOTAL "
					+ " from User As u INNER JOIN Doctor AS doc ON doc.user_id = u.id "
					+ "                INNER JOIN Appointment AS ap ON ap.doc_id = doc.doc_id "
					+ " GROUP BY u.fname ";
			
			List result = template.getSessionFactory().getCurrentSession().createSQLQuery(query).list();
			
			if (result != null && result.size() > 0) {
				list = new ArrayList<UserCount>();
				for (int i=0;i<result.size();i++) {
					Object s[] = (Object [])result.get(i);
					userCount = new UserCount();
					userCount.setDoctorName(s[0].toString());
					userCount.setCount(Integer.parseInt(s[1].toString()));
					list.add(userCount);
				}
			}
			logger.log(MohLogger.INFO, "Success: getNumberAptsPerDoctor()");
		} catch (DataAccessException e) {
			logger.log(MohLogger.INFO, "DataAccessException: " + e.getMessage());
			template.flush();
			throw e;
		}
		logger.log(MohLogger.INFO, "list: " + list);
		return list;
	}
}
