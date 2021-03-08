package com.app.driver;

import java.util.List;

import com.app.exceptions.BusinessException;
import com.app.model.Requests;
import com.app.service.RequestsService;
import com.app.service.impl.RequestsServiceImpl;

public class Driver {

	public static void main(String[] args) {
		
		RequestsService requestsService = new RequestsServiceImpl();
		
		try {
			List<Requests> pendingRequests = requestsService.getPendingRequestsByEmail("lbrown@gmail.com");
			System.out.println(pendingRequests);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
