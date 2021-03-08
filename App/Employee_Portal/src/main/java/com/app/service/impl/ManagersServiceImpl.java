package com.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.exceptions.BusinessException;
import com.app.exceptions.EmptyListException;
import com.app.model.Managers;
import com.app.repository.ManagersRepo;
import com.app.repository.impl.ManagersRepoImpl;
import com.app.service.ManagersService;

public class ManagersServiceImpl implements ManagersService {
	
	// Instance of Repository layer implementation class
	private ManagersRepo managersRepo = new ManagersRepoImpl();
	
	// Logger variable
	Logger log = Logger.getLogger(ManagersServiceImpl.class);

	@Override
	public List<Managers> getManagers() throws BusinessException {
		
		// Initial list variable
		List<Managers> allManagers = new ArrayList<>();
		
		// Append the list with the data from the DB
		try {
			for(Managers manager : managersRepo.getManagers()) {
				allManagers.add(manager);
			}
			
			
		} catch (EmptyListException e) {
			
			// Log the error and throw new exception
			log.trace(e.getMessage());
			throw new BusinessException("Check connection to the DB");
		}
		return allManagers;
	}

	@Override
	public Managers getManager(String email) throws BusinessException {
		
		// Create a manager object 
		Managers manager = new Managers();
		
		try {
			
			// Get a list of managers
			List<Managers> managers = managersRepo.getManagers();
			
			// Filter the list to get the manager with the matching email
			managers.removeIf((m) -> !m.getEmployee().getEmail().equals(email));
			
			// Set the manager object to the only manager left in the list
			for(Managers winnerManager : managers) {
				manager = winnerManager;
			}
			
		} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Return the manager object
		return manager;
	}

}
