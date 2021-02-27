package com.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.exceptions.BusinessException;
import com.app.exceptions.EmptyListException;
import com.app.model.Requests;
import com.app.repository.RequestsRepo;
import com.app.repository.impl.RequestsRepoImpl;
import com.app.service.RequestsService;

public class RequestsServiceImpl implements RequestsService {

	// Instance of the Repository layer implementation class
	private RequestsRepo requestsRepo = new RequestsRepoImpl();
	
	// Logger variabel
	Logger log = Logger.getLogger(RequestsServiceImpl.class);
	
	@Override
	public void newRequest(Requests request) throws BusinessException {
		
		// Call the newRequest method from the repository layer
		requestsRepo.newRequest(request);

	}

	@Override
	public List<Requests> getPendingRequestsByEmail(String email) throws BusinessException {
		
		// Initial list object that will be returned
		List<Requests> employeePendingRequests = new ArrayList<>();
		
		try {
			
			// Filtered list variable that will store all the requests
			List<Requests> filteredRequests = requestsRepo.getRequests();
			
			// Filter the list by employee email
			filteredRequests.removeIf((f) -> f.getEmployee().getEmail().equals(email));
			// Filter by status of request to only pending requests
			filteredRequests.removeIf((f) -> f.getStatus().equals("Pending"));
			
			// Append the employeePendingRequests with elements of the filtered list
			for(Requests request : filteredRequests) {
				employeePendingRequests.add(request);
			}
			
		} catch (EmptyListException e) {
			
			log.trace(e.getMessage());
			throw new BusinessException("Could not get list of requests. Check connection to the DB");
		}
		
		return employeePendingRequests;
	}

	@Override
	public List<Requests> getResolvedRequestsByEmail(String email) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Requests> getResolvedRequests() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Requests> getEmployeeRequests(String email) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Requests> getPendingRequestsByManager(int managerId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRequests(Requests request) throws BusinessException {
		// TODO Auto-generated method stub

	}

}
