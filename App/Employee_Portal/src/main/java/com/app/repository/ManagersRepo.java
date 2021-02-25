package com.app.repository;

import java.util.List;

import com.app.exceptions.EmptyListException;
import com.app.model.Managers;

public interface ManagersRepo {

	// Get a list of all the managers
	public List<Managers> getManagers() throws EmptyListException;
	
}
