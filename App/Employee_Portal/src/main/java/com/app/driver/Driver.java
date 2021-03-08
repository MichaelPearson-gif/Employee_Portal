package com.app.driver;

import com.app.exceptions.BusinessException;
import com.app.model.Managers;
import com.app.service.ManagersService;
import com.app.service.RequestsService;
import com.app.service.impl.ManagersServiceImpl;
import com.app.service.impl.RequestsServiceImpl;

public class Driver {

	public static void main(String[] args) {
		
		RequestsService requestsService = new RequestsServiceImpl();
		ManagersService managersService = new ManagersServiceImpl();
		
//		try {
//			List<Requests> pendingRequests = requestsService.getPendingRequestsByEmail("lbrown@gmail.com");
//			System.out.println(pendingRequests);
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			Managers manager = managersService.getManager("jsmith@gmail.com");
			System.out.println(manager);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
