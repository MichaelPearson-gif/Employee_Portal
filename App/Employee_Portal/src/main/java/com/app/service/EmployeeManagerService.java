package com.app.service;

import java.util.List;

import com.app.exceptions.BusinessException;
import com.app.model.EmployeeManager;
import com.app.model.Managers;

public interface EmployeeManagerService {

	// Get a list of employees by their manager
	List<EmployeeManager> getEmployeesByManager(Managers manager) throws BusinessException;
	
}
