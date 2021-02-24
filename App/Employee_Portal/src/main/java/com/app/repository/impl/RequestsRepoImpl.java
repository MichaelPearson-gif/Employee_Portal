package com.app.repository.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.exceptions.BusinessException;
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
	public List<Requests> pendingRequests(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Requests> resolvedRequests(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRequest(String email, String status, int managerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Requests> managersEmployeesPendingRequests(int managerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Requests> allResolvedRequests() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> recieptImages() {
		// TODO Auto-generated method stub
		return null;
	}

}
