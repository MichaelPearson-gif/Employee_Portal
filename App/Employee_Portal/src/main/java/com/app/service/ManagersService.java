package com.app.service;

import java.util.List;

import com.app.exceptions.BusinessException;
import com.app.model.Managers;

public interface ManagersService {

	// Get a list of managers
	List<Managers> getManagers() throws BusinessException;
	
	// Get a manager by their email
	Managers getManager(String email) throws BusinessException;
}
