package com.app.repository.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.app.exceptions.BusinessException;
import com.app.exceptions.InvalidLoginException;
import com.app.model.Employees;
import com.app.repository.EmployeesRepo;
import com.app.repository.util.HibernateSessionFactory;

public class EmployeesRepoImpl implements EmployeesRepo {
	
	// Log variable
	Logger log = Logger.getLogger(EmployeesRepoImpl.class);

	@Override
	public Employees getInfo(String email) throws BusinessException {
		
		// Initial employee object
		Employees employee = new Employees();
		
		try(Session session = HibernateSessionFactory.getSession()){
			
			// Begin a transaction
			Transaction tx = session.beginTransaction();
			
			// Query the DB and set the result to the employee object
			employee = session.get(Employees.class, email);
			
			// Commit the transaction
			tx.commit();
			
		}catch(HibernateException e) {
			
			// Log the error message
			log.trace(e.getMessage());
			throw new BusinessException("Could not find and employee with the email: " + email);
			
		}
		
		return employee;
	}

	@Override
	public String getPassword(String email) throws InvalidLoginException {
		
		// Initial password variable
		String password = null;
		
		try(Session session = HibernateSessionFactory.getSession()){
			
			// Begin a transaction
			Transaction tx = session.beginTransaction();
			
			// Created a HQL query to get the password from the DB associated with the email input
			// Had to caste to String since .createQuery returns a query type
			password = (String) session.createQuery("SELECT password FROM employees e WHERE e.email = :email").setParameter("email", email).uniqueResult();
			
			// Commit transaction
			tx.commit();
			
		}catch(HibernateException e) {
			
			// Log the error message
			log.trace(e.getMessage());
			throw new InvalidLoginException("Could not find a password for " + email);
			
		}
		
		return password;
	}

	@Override
	public void updateInfo(Employees employee) throws BusinessException {
		
		try(Session session = HibernateSessionFactory.getSession()){
			
			// Begin a transaction
			Transaction tx = session.beginTransaction();
			
			// Update the record
			session.update(employee);
			
			// Commit transaction
			tx.commit();
			
		}catch(HibernateException e) {
			
			// Log the error message
			log.trace(e.getMessage());
			throw new BusinessException("An internal error has occured, please check the changed value for any errors.");
			
		}
		
	}

	@Override
	public List<String> employeeList() {
		// TODO Auto-generated method stub
		return null;
	}

}
