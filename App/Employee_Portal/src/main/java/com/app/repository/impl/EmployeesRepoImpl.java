package com.app.repository.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.exceptions.BusinessException;
import com.app.model.Employees;
import com.app.repository.EmployeesRepo;
import com.app.repository.util.HibernateSessionFactory;

public class EmployeesRepoImpl implements EmployeesRepo {
	
	// Log variable
	Logger log = Logger.getLogger(EmployeesRepoImpl.class);

	@Override
	public Employees getInfo(String email) throws BusinessException {
		
		Employees employee = new Employees();
		
		try(Session session = HibernateSessionFactory.getSession()){
			
			Transaction tx = session.beginTransaction();
			
			employee = session.get(Employees.class, email);
			
			tx.commit();
			
		}catch(HibernateException e) {
			
			// Log the error message
			log.trace(e.getMessage());
			throw new BusinessException("Could not find and employee with the email: " + email);
			
		}
		
		return employee;
	}

	@Override
	public String getPassword(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employees updateInfo(Employees employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> employeeList() {
		// TODO Auto-generated method stub
		return null;
	}

}
