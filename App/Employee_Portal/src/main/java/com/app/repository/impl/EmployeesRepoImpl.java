package com.app.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.exceptions.BusinessException;
import com.app.exceptions.EmptyListException;
import com.app.model.Employees;
import com.app.repository.EmployeesRepo;
import com.app.repository.util.HibernateSessionFactory;

public class EmployeesRepoImpl implements EmployeesRepo {

	// Log variable
	Logger log = Logger.getLogger(EmployeesRepoImpl.class);
	
	// Transaction variable
	Transaction tx;

	@Override
	public void updateInfo(Employees employee) throws BusinessException {

		try (Session session = HibernateSessionFactory.getSession()) {

			// Begin a transaction
			tx = session.beginTransaction();

			// Update the record
			session.update(employee);

			// Commit transaction
			tx.commit();

		} catch (HibernateException e) {

			// Log the error message
			log.trace(e.getMessage());
			
			// Rollback the transaction
			tx.rollback();
			
			// Throw new exception
			throw new BusinessException(
					"An internal error has occured, please check the changed value for any errors.");

		}

	}

	@Override
	public List<Employees> employeeList() throws EmptyListException {

		// Initial list
		List<Employees> allEmployees = new ArrayList<>();

		try (Session session = HibernateSessionFactory.getSession()) {

			// Begin a transaction
			tx = session.beginTransaction();

			// Query the DB for a list of employees ordered by manager and append it to the
			// allEmployees list
			// An inner join will already sort the order by manager
			allEmployees = session.createQuery("FROM Employees", Employees.class).getResultList();

			tx.commit();

		} catch (HibernateException e) {

			// Log the error message
			log.trace(e.getMessage());
			
			// Rollback the transaction
//			tx.rollback();
			
			// Throw new exception
			throw new EmptyListException("Could not retrieve list of employees.");

		}

		return allEmployees;
	}

}
