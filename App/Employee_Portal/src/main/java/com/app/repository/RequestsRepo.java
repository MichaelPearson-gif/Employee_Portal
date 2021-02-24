package com.app.repository;

import java.util.List;

import com.app.exceptions.BusinessException;
import com.app.exceptions.EmptyListException;
import com.app.model.Requests;

public interface RequestsRepo {

	// Employees submit new reimbursement request
	void newRequest(Requests request) throws BusinessException;
	
	// Employee and manager views all there pending 
	List<Requests> pendingRequests(String email) throws EmptyListException;
	
	// Employee views all resolved requests
	List<Requests> resolvedRequests(String email);
	
	// Managers can approve or deny requests
	void updateRequest(String email, String status, int managerId);
	
	// Managers view all pending requests of their employees
	List<Requests> managersEmployeesPendingRequests(int managerId);
	
	// Managers can view all resolved requests
	List<Requests> allResolvedRequests();
	
	// Managers can view all reciept images
	List<String> recieptImages();
	
}
