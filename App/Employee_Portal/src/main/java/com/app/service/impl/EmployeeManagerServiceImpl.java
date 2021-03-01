package com.app.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.app.exceptions.BusinessException;
import com.app.model.EmployeeManager;
import com.app.model.Managers;
import com.app.repository.EmployeeManagerRepo;
import com.app.repository.impl.EmployeeManagerRepoImpl;
import com.app.service.EmployeeManagerService;

public class EmployeeManagerServiceImpl implements EmployeeManagerService {
	
	// Instance of Repository layer implementation class
	private EmployeeManagerRepo employeeManagerRepo = new EmployeeManagerRepoImpl();
	
	// Logger variable
	Logger log = Logger.getLogger(EmployeeManagerServiceImpl.class);

	@Override
	public List<EmployeeManager> getEmployeesByManager(Managers manager) throws BusinessException {
		
		// Get the list from the repository layer
		List<EmployeeManager> correspondingEmployees = employeeManagerRepo.getEmployeesByManager(manager);
		
		return correspondingEmployees;
	}

	@Override
	public List<EmployeeManager> getRoster() throws BusinessException {
		
		// Get list from the repository layer
		List<EmployeeManager> allEmployees = employeeManagerRepo.getRoster();
		
		// Return the list
		return allEmployees;
	}

}
