package com.app.repository;

import java.util.List;

import com.app.exceptions.BusinessException;
import com.app.exceptions.EmptyListException;
import com.app.exceptions.InvalidLoginException;
import com.app.model.Employees;

public interface EmployeesRepo {

	// Employee can view their info
	Employees getInfo(String email) throws BusinessException;
	
	// Get the password associated with the employee email
	String getPassword(String email) throws InvalidLoginException;
	
	// Employee updates their info
	void updateInfo(Employees employee) throws BusinessException;
	
	// List of all employees
	List<String> employeeList() throws EmptyListException;
	
	// List of all the managers
	List<String> managerList() throws EmptyListException;
	
}
