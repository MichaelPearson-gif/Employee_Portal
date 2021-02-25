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
	public List<Requests> pendingRequests(String email) throws EmptyListException {
		
		// Initial list
		List<Requests> myPendingRequests = new ArrayList<>();
		
		try (Session session = HibernateSessionFactory.getSession()) {
			
			// Begin a transaction
			tx = session.beginTransaction();
			
			// Query the DB for all pending requests for an employee and append it to myPendingRequests
			myPendingRequests = session.createQuery("FROM requests WHERE email = :email AND status = 'Pending'")
					.setParameter("email", email).getResultList();
			
			// Commit the transaction
			tx.commit();
			
		}catch (HibernateException e) {

			// Log the error message
			log.trace(e.getMessage());
			
			// Rollback the transaction
			tx.rollback();
			
			// Throw new exception
			throw new EmptyListException("Could not retrieve list of pending requests.");

		}
		
		return myPendingRequests;
	}

	@Override
	public List<Requests> resolvedRequests(String email) throws EmptyListException {
		
		// Initial list
		List<Requests> myResolvedRequests = new ArrayList<>();
		
		try (Session session = HibernateSessionFactory.getSession()) {
			
			// Begin a transaction
			tx = session.beginTransaction();
			
			// Query the DB for all pending requests for an employee and append it to myPendingRequests
			myResolvedRequests = session.createQuery("FROM requests WHERE email = :email AND status != 'Pending'")
					.setParameter("email", email).getResultList();
			
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
		
		return myResolvedRequests;
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
			throw new BusinessException(
					"Could not update the request.");

		}
		
	}

	@Override
	public List<Requests> managersEmployeesPendingRequests(int managerId) throws EmptyListException {
		
		// Initial list
		List<Requests> myEmployeesPendingRequests = new ArrayList<>();
		
		try (Session session = HibernateSessionFactory.getSession()) {
			
			// Begin a transaction
			tx = session.beginTransaction();
			
			// Query the DB for all pending requests for employees under a manager id
			// Append to the myEmployeesPendingRequests list
			myEmployeesPendingRequests = session.createQuery("FROM requests r INNER JOIN employee_manager em"
					+ " ON r.email = em.email WHERE em.manager_id IN ("
					+ "SELECT manager_id FROM managers WHERE manager_id = :manager_id)")
					.setParameter("manager_id", managerId).getResultList();
			
			// Commit the transaction
			tx.commit();
			
		}catch (HibernateException e) {

			// Log the error message
			log.trace(e.getMessage());
			
			// Rollback the transaction
			tx.rollback();
			
			// Throw new exception
			throw new EmptyListException("Could not retrieve list of employee pending requests.");

		}
		
		return myEmployeesPendingRequests;
	}

	@Override
	public List<Requests> allResolvedRequests() throws EmptyListException {
		
		// Initial list
		List<Requests> allResolvedRequestsList = new ArrayList<>();
		
		try (Session session = HibernateSessionFactory.getSession()) {
			
			// Begin a transaction
			tx = session.beginTransaction();
			
			// Query the DB for all resolved requests, ordered by manager_id, and append to the allResolvedRequestsList list
			allResolvedRequestsList = session.createQuery("FROM requests WHERE status != 'Pending' ORDER BY manager_id")
					.getResultList();
			
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
		
		return allResolvedRequestsList;
	}

	@Override
	public List<String> recieptImages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Requests> getRequestByEmail(String email) throws EmptyListException {
		
		// Initial list
		List<Requests> employeeRequests = new ArrayList<>();
		
		try (Session session = HibernateSessionFactory.getSession()) {
			
			// Begin a transaction
			tx = session.beginTransaction();
			
			// Query the DB and append to the employeeRequests list
			employeeRequests = session.createQuery("FROM requests WHERE email = :email").setParameter("email", email).getResultList();
			
			// Commit the transaction
			tx.commit();
			
		}catch (HibernateException e) {

			// Log the error message
			log.trace(e.getMessage());
			
			// Rollback the transaction
			tx.rollback();
			
			// Throw new exception
			throw new EmptyListException("Could not find a requests for " + email);

		}
		
		return employeeRequests;
	}

}
