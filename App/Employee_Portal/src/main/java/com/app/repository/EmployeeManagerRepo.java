package com.app.repository;

import java.util.List;

import com.app.exceptions.BusinessException;
import com.app.model.EmployeeManager;
import com.app.model.Managers;

public interface EmployeeManagerRepo {

	// Get a list of employees by their manager
	List<EmployeeManager> getEmployeesByManager(Managers manager) throws BusinessException;
	
	// Get a list of all employees and managers
	List<EmployeeManager> getRoster() throws BusinessException;
	
}
