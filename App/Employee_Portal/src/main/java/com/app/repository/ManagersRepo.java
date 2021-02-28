package com.app.repository;

import java.util.List;

import com.app.exceptions.BusinessException;
import com.app.exceptions.EmptyListException;
import com.app.model.Managers;

public interface ManagersRepo {

	// Get a list of all the managers
	public List<Managers> getManagers() throws EmptyListException;
	
	// Get a manager by their email
	Managers getManager(String email) throws BusinessException;
	
}
