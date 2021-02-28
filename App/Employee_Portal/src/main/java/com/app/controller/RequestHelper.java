package com.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.exceptions.BusinessException;
import com.app.model.Employees;
import com.app.service.impl.EmployeesServiceImpl;

public class RequestHelper {

	// Method to process GET requests
	public static Object processGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/EmployeePortal", "");
		
		switch(RESOURCE) {
		
		case "/employee/info":
			response.setStatus(200);
			Employees employee = new Employees();
			try {
				 employee = new EmployeesServiceImpl().getEmployee(null);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return employee;
			
		default:
			response.setStatus(404);
			return "Sorry. The resource you have requested does not exist.";
		
		}
		
	}
	
}
