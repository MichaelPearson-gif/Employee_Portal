package com.app.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.app.exceptions.BusinessException;
import com.app.exceptions.EmptyListException;
import com.app.model.Employees;
import com.app.repository.EmployeesRepo;
import com.app.repository.impl.EmployeesRepoImpl;
import com.app.service.EmployeesService;

public class EmployeesServiceImpl implements EmployeesService {
	
	// Instance of Repository layer implementation class
	private EmployeesRepo employeesRepo = new EmployeesRepoImpl();
	
	// Logger variable
	Logger log = Logger.getLogger(EmployeesServiceImpl.class);

	@Override
	public boolean validate(String email, String password) throws BusinessException {
		
		// Set default variable to false
		boolean b = false;
		
		// Retrieve the employee info
		Employees employee = employeesRepo.getEmployee(email);
		
		// Grab the password of the employee
		String savedPassword = employee.getPassword();
		
		// Check the user input password against the saved password
		if(password.equals(savedPassword)) {
			
			// Change b to true
			b = true;
			
		}else {
			// Log the error and throw a new exception
			Exception e = new BusinessException("The password " + password + " does not match the recorded password " + savedPassword);
			log.trace(e.getMessage());
		}
		
		return b;
	}

	@Override
	public Employees getEmployee(String email) throws BusinessException {
		
		// Create an employee object using the repository layer method for retrieving an employee
		Employees employee = employeesRepo.getEmployee(email);
		
		// Return the employee object
		return employee;
	}

	@Override
	public void updateInfo(Employees employee) throws BusinessException {
		
		// Call the repository layer method updateInfo
		employeesRepo.updateInfo(employee);

	}

	@Override
	public List<String> employeeRoster() throws BusinessException {
		
		// Initial list variable
		List<String> allEmployees = null;
		
		// Access the DB and manipulate the data
		try {
			
			// List of employee objects
			List<Employees> allEmployeesList = employeesRepo.employeeList();
			
			// Sort the list my manager (Ideal format: Manager, Employee, ..., Manager, Employee, ...)
			// Make a list variable of type String
			// Grab the the first and last name of each employee in the sorted allEmployeesList and concatnate them together
			// Append the concatnated name to the String list
			
			// Iterate through the list and append to the allEmployees list
			for(Employees employee : allEmployeesList) {
				
			}
			
		} catch (EmptyListException e) {
			log.trace(e.getMessage());
			throw new BusinessException("Check connection to the DB");
		}
		
		return allEmployees;
	}

}
