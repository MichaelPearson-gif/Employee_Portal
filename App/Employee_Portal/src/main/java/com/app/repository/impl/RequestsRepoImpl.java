package com.app.repository.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.exceptions.BusinessException;
import com.app.exceptions.EmptyListException;
import com.app.model.Requests;
import com.app.repository.RequestsRepo;
import com.app.repository.util.HibernateSessionFactory;

public class RequestsRepoImpl implements RequestsRepo {
	
	// Log variable
	Logger log = Logger.getLogger(RequestsRepoImpl.class);
	
	// Transaction variable
	Transaction tx;

	@Override
	public void newRequest(Requests request) throws BusinessException {
		
		try (Session session = HibernateSessionFactory.getSession()) {
			
			// Begin a transaction
			tx = session.beginTransaction();
			
			// Getting the current date
			LocalDate ld = LocalDate.now();
			
			// Converting to a sql date object
			Date today = Date.valueOf(ld);
			
			// Set the date field in the request object to the current date
			request.setDate(today);
			
			// Set the status to Pending
			request.setStatus("Pending");
			
			// Persist the request
			session.save(request);
			
			// Commit the transaction
			tx.commit();
			
		}catch (HibernateException e) {

			// Log the error message
			log.trace(e.getMessage());
			
			// Rollback the transaction
			tx.rollback();
			
			// Throw new exception
			throw new BusinessException("Could not submit request to DB");

		}
		
	}

	@Override
	public void updateRequest(Requests request) throws BusinessException {
		
		try (Session session = HibernateSessionFactory.getSession()) {
			
			// Begin a transaction
			tx = session.beginTransaction();
			
			// Update the request
			session.update(request);
			
			// Commit the transaction
			tx.commit();
			
		}catch (HibernateException e) {

			// Log the error message
			log.trace(e.getMessage());
			
			// Rollback the transaction
			tx.rollback();
			
			// Throw new exception
			throw new BusinessException("Could not update the request.");

		}
		
	}

	@Override
	public List<Requests> getRequests() throws EmptyListException {
		
		// Initial list
		List<Requests> allRequests = new ArrayList<>();
		
		try (Session session = HibernateSessionFactory.getSession()) {
			
			// Begin a transaction
			tx = session.beginTransaction();
			
			// Query the DB for all resolved requests, ordered by manager_id, and append to the allResolvedRequestsList list
			allRequests = session.createQuery("FROM Requests", Requests.class).getResultList();
			
			// Commit the transaction
			tx.commit();
			
		}catch (HibernateException e) {

			// Log the error message
			log.trace(e.getMessage());
			
			// Rollback the transaction
			tx.rollback();
			
			// Throw new exception
			throw new EmptyListException("Could not retrieve list of resolved requests.");

		}
		
		return allRequests;
	}

	@Override
	public List<String> recieptImages() {
		// TODO Auto-generated method stub
		return null;
	}
}
