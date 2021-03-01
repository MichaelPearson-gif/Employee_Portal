package com.app.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.exceptions.BusinessException;
import com.app.model.EmployeeManager;
import com.app.model.Managers;
import com.app.repository.EmployeeManagerRepo;
import com.app.repository.util.HibernateSessionFactory;

public class EmployeeManagerRepoImpl implements EmployeeManagerRepo {
	
	// Logger variable
	Logger log = Logger.getLogger(EmployeeManagerRepoImpl.class);
	
	// Transaction variable
	Transaction tx;

	@Override
	public List<EmployeeManager> getEmployeesByManager(Managers manager) throws BusinessException {
		
		// Initial list variable
		List<EmployeeManager> correspondingEmployees = new ArrayList<>();
		
		try (Session session = HibernateSessionFactory.getSession()) {
			
			// Begin a transaction
			tx = session.beginTransaction();
			
			// Query the DB and append the results to the list
			correspondingEmployees = session.createQuery("FROM EmployeeManager WHERE manager = :manager", EmployeeManager.class).setParameter("manager", manager).getResultList();
			
			// Commit the transaction
			tx.commit();
			
		}catch (HibernateException e) {

			// Log the error message
			log.trace(e.getMessage());
			
			// Rollback the transaction
			tx.rollback();
			
			// Throw new exception
			throw new BusinessException("Could not find employees for manager: " + manager.getManagerId());

		}
		
		return correspondingEmployees;
	}

	@Override
	public List<EmployeeManager> getRoster() throws BusinessException {
		
		// Initial List variable
		List<EmployeeManager> allEmployees = new ArrayList<>();
		
		try (Session session = HibernateSessionFactory.getSession()) {
			
			// Begin a transaction
			tx = session.beginTransaction();
			
			// Query the DB and append the results to the list
			allEmployees = session.createQuery("FROM EmployeeManager, EmployeeManager.class").getResultList();
			
			// Commit the transaction
			tx.commit();
			
		}catch (HibernateException e) {

			// Log the error message
			log.trace(e.getMessage());
			
			// Rollback the transaction
			tx.rollback();
			
			// Throw new exception
			throw new BusinessException("Could not retrieve data. Please check logs and connection to DB.");

		}
		
		return allEmployees;
	}

}
