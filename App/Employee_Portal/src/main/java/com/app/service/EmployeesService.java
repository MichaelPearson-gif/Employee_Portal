package com.app.service;

import java.util.List;

import com.app.exceptions.BusinessException;
import com.app.model.Employees;

public interface EmployeesService {

	// Validate the login credentials
	boolean validate(String email, String password) throws BusinessException;
	
	// Retrieves an employee record
	Employees getEmployee(String email) throws BusinessException;
	
	// Employees can update their personal info
	void updateInfo(Employees employee) throws BusinessException;
	
	// Get a list of all employees (Just need names)
	List<Employees> employeeRoster() throws BusinessException;
	
}
