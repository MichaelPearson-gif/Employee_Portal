package com.app.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.exceptions.BusinessException;
import com.app.exceptions.EmptyListException;
import com.app.model.Managers;
import com.app.repository.ManagersRepo;
import com.app.repository.util.HibernateSessionFactory;

public class ManagersRepoImpl implements ManagersRepo {
	
	// Logger variable
	Logger log = Logger.getLogger(ManagersRepoImpl.class);
	
	// Transaction variable
	Transaction tx;

	@Override
	public List<Managers> getManagers() throws EmptyListException {
		// Initial list
		List<Managers> allManagers = new ArrayList<>();

		try (Session session = HibernateSessionFactory.getSession()) {

			// Begin a transaction
			tx = session.beginTransaction();

			// Query the DB for a list of employees ordered by manager and append it to the
			// allEmployees list
			// An inner join will already sort the order by manager
			allManagers = session.createQuery("FROM Managers", Managers.class).getResultList();

			tx.commit();

		} catch (HibernateException e) {

			// Log the error message
			log.trace(e.getMessage());
			
			// Rollback the transaction
			tx.rollback();
			
			// Throw new exception
			throw new EmptyListException("Could not retrieve list of managers.");

		}

		return allManagers;

	}

	@Override
	public Managers getManager(String email) throws BusinessException {
		
		// Initial Managers object
		Managers manager = new Managers();
		
		try (Session session = HibernateSessionFactory.getSession()) {
			
			// Begin a transaction
			tx = session.beginTransaction();
			
			// Query the DB and set the result to the manager object
			manager = session.get(Managers.class, email);
			
			// Commit the transaction
			tx.commit();
			
		}catch (HibernateException e) {

			// Log the error message
			log.trace(e.getMessage());
			
			// Rollback the transaction
			tx.rollback();
			
			// Throw new exception
			throw new BusinessException("Could not find a manager with the email: " + email);
		}
		
		return manager;
	}

}
