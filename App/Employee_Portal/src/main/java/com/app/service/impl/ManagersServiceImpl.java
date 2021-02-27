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
			log.trace(e.getMessage());
			throw new BusinessException("Check connection to the DB");
		}
		return allManagers;
	}

}
