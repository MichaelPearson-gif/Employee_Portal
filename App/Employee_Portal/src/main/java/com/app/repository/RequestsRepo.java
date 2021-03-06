package com.app.repository;

import java.util.List;

import com.app.exceptions.BusinessException;
import com.app.exceptions.EmptyListException;
import com.app.model.Requests;

public interface RequestsRepo {

	// Employees submit new reimbursement request
	void newRequest(Requests request) throws BusinessException;
	
	// Managers can approve or deny requests
	void updateRequest(Requests request) throws BusinessException;
	
	// Get all request records
	List<Requests> getRequests() throws EmptyListException;
	
	// Managers can view all reciept images
	List<String> recieptImages();
	
	// Get a request by it's id
	Requests getRequestById(int requestId) throws BusinessException;
	
}
