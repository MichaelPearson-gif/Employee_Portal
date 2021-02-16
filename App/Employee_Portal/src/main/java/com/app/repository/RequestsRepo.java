package com.app.repository;

import java.util.List;

import com.app.model.Requests;

public interface RequestsRepo {

	// Employees submit new reimbursement request
	int newRequest(Requests request);
	
	// Employee and manager views all there pending 
	List<Requests> pendingRequests(int employeeId);
	
	// Employee views all resolved requests
	List<Requests> resolvedRequests(int employeeId);
	
	// Managers can approve or deny requests
	int updateRequest(int employeeId, String status, int managerId);
	
	// Managers view all pending requests of their employees
	List<Requests> managersEmployeesPendingRequests(int managerId);
	
	// Managers can view all resolved requests
	List<Requests> allResolvedRequests();
	
	// Managers can view all reciept images
	List<String> recieptImages();
	
}
