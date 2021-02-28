package com.app.service;

import java.util.List;

import com.app.exceptions.BusinessException;
import com.app.model.Managers;
import com.app.model.Requests;

public interface RequestsService {

	// Employees submit a new reimbursment request
	void newRequest(Requests request) throws BusinessException;
	
	// Employees can view all their pending requests
	List<Requests> getPendingRequestsByEmail(String email) throws BusinessException;
	
	// Employees can view all their resolved requests
	List<Requests> getResolvedRequestsByEmail(String email) throws BusinessException;
	
	// Managers can view all the resolved requests
	List<Requests> getResolvedRequests() throws BusinessException;
	
	// Managers can view all requests from one of their employees
	List<Requests> getEmployeeRequests(String email) throws BusinessException;
	
	// Managers can view all pending requests from their employees
	List<Requests> getPendingRequestsByManager(Managers manager) throws BusinessException;
	
	// Managers can approve or deny pending requests
	void updateRequests(Requests request) throws BusinessException;
	
}
