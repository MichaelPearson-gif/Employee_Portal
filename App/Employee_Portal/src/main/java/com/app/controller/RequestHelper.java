package com.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.exceptions.BusinessException;
import com.app.model.Employees;
import com.app.model.Requests;
import com.app.service.impl.EmployeesServiceImpl;
import com.app.service.impl.ManagersServiceImpl;
import com.app.service.impl.RequestsServiceImpl;

public class RequestHelper {

	// Method to process GET requests
	public static Object processGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/EmployeePortal/api", "");
		
		switch(RESOURCE) {
		
		// Client views their info
		case "/info":
			// Set the status code
			response.setStatus(200);
			
			// Initial Employee object
			Employees employee = new Employees();
		
			try {
				
				// Get the session attribute
				String attribute = (String) request.getSession(false).getAttribute("email");
				
				// Use the service layer method .getEmployee to get the employee info from the email session attribute
				employee = new EmployeesServiceImpl().getEmployee(attribute);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Return the employee object
			return employee;
			
		// Client logs out
		case "/logout":
			
			// Close the session
			HttpSession session = request.getSession(false);
			if(session != null) {
				session.invalidate();
			}
			return "Your session has been invalidated.";
			
		// Client can view their pending requests
		case "employee/pending/requests":
			
			// Set the status code 
			response.setStatus(200);
			
			// Initial list of requests
			List<Requests> employeePendingRequests = new ArrayList<>();
			
			try {
				
				// Get the session attribute
				String attribute = (String) request.getSession(false).getAttribute("email");
				
				// List variable that will call the service layer
				List<Requests> tempList = new RequestsServiceImpl().getPendingRequestsByEmail(attribute);
				
				// Loop through the tempList and append its elements to the employeePendingRequests list
				for(Requests pendingRequest : tempList) {
					employeePendingRequests.add(pendingRequest);
				}
				
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Return the list
			return employeePendingRequests;
			
		case "employee/resolved/requests":
			
			// Set the status code
			response.setStatus(200);
			
			// Initial list of requests
			List<Requests> employeeResolvedRequests = new ArrayList<>();
			
			try {
				
				// Get the session attribute
				String attribute = (String) request.getSession(false).getAttribute("email");
				
				// List variable that will call the service layer
				List<Requests> tempList = new RequestsServiceImpl().getResolvedRequestsByEmail(attribute);
				
				// Loop through the tempList and append its elements to the employeePendingRequests list
				for(Requests pendingRequest : tempList) {
					employeeResolvedRequests.add(pendingRequest);
				}
				
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Return the list
			return employeeResolvedRequests;
			
		default:
			response.setStatus(404);
			return "Sorry. The resource you have requested does not exist.";
		
		}
		
	}
	
	// Method to process POST requests
	public static void processPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, BusinessException{
		
		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/EmployeePortal", "");
		
		switch(RESOURCE) {
		
		case "/login":
			
			final String email = request.getParameter("email");
			final String password = request.getParameter("password");
			
			if(new EmployeesServiceImpl().validate(email, password)) {
				
				// Grant the client a session
				HttpSession session = request.getSession();
				
				// Set the client email as a session attribute
				session.setAttribute("email", email);
				
				// Redirect to the correct page
				// Check to see if the client is a manager
				if(new ManagersServiceImpl().getManager(email) != null) {
					
					// Send to the managers home page
					response.sendRedirect("/Employee_Portal/api/Pages/manager.html");
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/manager.html");
					dispatcher.forward(request, response);
					
				}else {
					
					// Send to the employees home page
					response.sendRedirect("/Employee_Portal/Pages/employee.html");
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/employee.html");
					dispatcher.forward(request, response);
					
				}
				
			}
			break;
			
		default:
			response.setStatus(404);
		
		}
		
	}
	
}
