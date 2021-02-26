package com.app.service.impl;

import java.util.List;

import com.app.exceptions.BusinessException;
import com.app.model.Requests;
import com.app.service.RequestsService;

public class RequestsServiceImpl implements RequestsService {

	@Override
	public void newRequest(Requests request) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Requests> getPendingRequestsByEmail(String email) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
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
