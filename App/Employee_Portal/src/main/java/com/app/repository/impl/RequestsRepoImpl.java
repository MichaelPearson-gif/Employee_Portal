package com.app.repository.impl;

import java.util.List;

import com.app.model.Requests;
import com.app.repository.RequestsRepo;

public class RequestsRepoImpl implements RequestsRepo {

	@Override
	public int newRequest(Requests request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Requests> pendingRequests(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Requests> resolvedRequests(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateRequest(int employeeId, String status, int managerId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Requests> managersEmployeesPendingRequests(int managerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Requests> allResolvedRequests() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> recieptImages() {
		// TODO Auto-generated method stub
		return null;
	}

}
