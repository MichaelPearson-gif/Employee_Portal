package com.app.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.exceptions.BusinessException;
import com.app.exceptions.EmptyListException;
import com.app.exceptions.InvalidLoginException;
import com.app.model.Employees;
import com.app.repository.EmployeesRepo;
import com.app.repository.util.HibernateSessionFactory;

public class EmployeesRepoImpl implements EmployeesRepo {

	// Log variable
	Logger log = Logger.getLogger(EmployeesRepoImpl.class);
	
	// Transaction variable
	Transaction tx;

	@Override
	public Employees getInfo(String email) throws BusinessException {

		// Initial employee object
		Employees employee = new Employees();

		try (Session session = HibernateSessionFactory.getSession()) {

			// Begin a transaction
			tx = session.beginTransaction();

			// Query the DB and set the result to the employee object
			employee = session.get(Employees.class, email);

			// Commit the transaction
			tx.commit();

		} catch (HibernateException e) {

			// Log the error message
			log.trace(e.getMessage());
			
			// Rollback the transaction
			tx.rollback();
			
			// Throw new exception
			throw new BusinessException("Could not find and employee with the email: " + email);

		}

		return employee;
	}

	@Override
	public String getPassword(String email) throws InvalidLoginException {

		// Initial password variable
		String password = null;

		try (Session session = HibernateSessionFactory.getSession()) {

			// Begin a transaction
			tx = session.beginTransaction();

			// Created a HQL query to get the password from the DB associated with the email
			// input
			// Had to caste to String since .createQuery returns a query type
			password = (String) session.createQuery("SELECT password FROM employees e WHERE e.email = :email")
					.setParameter("email", email).uniqueResult();

			// Commit transaction
			tx.commit();

		} catch (HibernateException e) {

			// Log the error message
			log.trace(e.getMessage());
			
			// Rollback the transaction
			tx.rollback();
			
			// Throw new exception
			throw new InvalidLoginException("Could not find a password for " + email);

		}

		return password;
	}

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
	public List<String> employeeList() throws EmptyListException {

		// Initial list
		List<String> allEmployees = new ArrayList<>();

		try (Session session = HibernateSessionFactory.getSession()) {

			// Begin a transaction
			tx = session.beginTransaction();

			// Query the DB for a list of employees ordered by manager and append it to the
			// allEmployees list
			// An inner join will already sort the order by manager
//			allEmployees = session.createQuery(
//					"SELECT CONCAT(e.first_name, e.last_name) FROM employees e INNER JOIN employee_manager em ON e.email = em.email")
//					.getResultList();
			
			allEmployees = session.createQuery(
					"SELECT CONCAT(first_name, last_name) FROM employees")
					.getResultList();

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

	@Override
	public List<String> managerList() throws EmptyListException {

		// Initial list
		List<String> allManagers = new ArrayList<>();

		try (Session session = HibernateSessionFactory.getSession()) {

			// Begin a transaction
			tx = session.beginTransaction();

			// Query the DB for a list of employees ordered by manager and append it to the
			// allEmployees list
			// An inner join will already sort the order by manager
			allManagers = session.createQuery(
					"SELECT CONCAT(e.first_name, e.last_name) FROM employees e INNER JOIN managers m ON e.email = m.email")
					.getResultList();

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

}
