package com.app.repository;

import java.util.List;

import com.app.model.Employees;

public interface EmployeesRepo {

	// Employee can view their info
	Employees getInfo(String email);
	
	// Get Employee email & password to verify login credentials
	
	// Get the password associated with the employee email
	String getPassword(String email);
	
	// Employee updates their info
	Employees updateInfo(Employees employee);
	
	// List of all employees and their managers
	List<String> employeeList();
	
}
