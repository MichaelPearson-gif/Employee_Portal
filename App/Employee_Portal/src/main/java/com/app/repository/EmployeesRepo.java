package com.app.repository;

import java.util.List;

import com.app.exceptions.BusinessException;
import com.app.exceptions.EmptyListException;
import com.app.model.Employees;

public interface EmployeesRepo {
	
	// Employee updates their info
	void updateInfo(Employees employee) throws BusinessException;
	
	// List of all employees
	List<Employees> employeeList() throws EmptyListException;
	
	// Retrieve a single employee
	Employees getEmployee(String email) throws BusinessException;
	
}
