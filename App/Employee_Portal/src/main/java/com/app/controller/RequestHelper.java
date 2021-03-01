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
import com.app.model.EmployeeManager;
import com.app.model.Employees;
import com.app.model.Managers;
import com.app.model.Requests;
import com.app.service.impl.EmployeeManagerServiceImpl;
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
			
		// Client can view all of their resolved requests
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
				
				// Loop through the tempList and append its elements to the employeeResolvedRequests list
				for(Requests resolvedRequests : tempList) {
					employeeResolvedRequests.add(resolvedRequests);
				}
				
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Return the list
			return employeeResolvedRequests;
			
		// Client (manager) views all pending requests of their employees
		case "/pending/requests":
			
			// Set the status code
			response.setStatus(200);
			
			// Inititial list of requests
			List<Requests> managerEmployeePendingRequests = new ArrayList<>();
			
			try {
				
				// Get the session attribute
				String attribute = (String) request.getSession(false).getAttribute("email");
				
				// Get the corresponding manager object
				Managers manager = new ManagersServiceImpl().getManager(attribute);
				
				// List variable that will call the service layer
				List<Requests> tempList = new RequestsServiceImpl().getPendingRequestsByManager(manager);
				
				// Loop through the tempList and append its elements to the managerEmployeePendingRequests list
				for(Requests managerPendingRequest : tempList) {
					managerEmployeePendingRequests.add(managerPendingRequest);
				}
				
				
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Return the list
			return managerEmployeePendingRequests;
			
		// Client (manager) can see all resolved requests and which manager resolved them
		case "/resolved/requests":
			
			// Set the status code
			response.setStatus(200);
			
			// Inititial list of requests
			List<Requests> allResolvedRequests = new ArrayList<>();
			
			try {
				
				// List variable that will call the service layer
				List<Requests> tempList = new RequestsServiceImpl().getResolvedRequests();
				
				// Loop through the tempList and append its elements to the managerEmployeePendingRequests list
				for(Requests resolvedRequest : tempList) {
					allResolvedRequests.add(resolvedRequest);
				}
				
				
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Return the list
			return allResolvedRequests;
			
		// Client (manager) can see all requests from one of their employees
		case "/employee/requests":
			
			// Set the status code
			response.setStatus(200);
			
			// Grab the input variable
			final String employeeEmail = request.getParameter("employeeEmail");
			
			// Inital list variable
			List<Requests> allEmployeeRequests = new ArrayList<>();
			
			try {
				
				// Get the session attribute
				String attribute = (String) request.getSession(false).getAttribute("email");
				
				// Get the client's manager info
				Managers manager = new ManagersServiceImpl().getManager(attribute);
				
				// Get list of employees for the manager
				List<EmployeeManager> employeeList = new EmployeeManagerServiceImpl().getEmployeesByManager(manager);
				
				// Iterate through the employeeList
				for(EmployeeManager employeeManager : employeeList) {
					
					// Check if the employee that the client requested for is one of their employees
					if(employeeManager.getEmployee().getEmail().equals(employeeEmail)) {
						
						// List variable that will call the service layer
						List<Requests> tempList = new RequestsServiceImpl().getEmployeeRequests(employeeEmail);
						
						// Iterate through the tempList and append to the allEmployeeRequests
						for(Requests employeeRequests : tempList) {
							allEmployeeRequests.add(employeeRequests);
						}
						
					}else {
						response.setStatus(404);
						return "Sorry the employee you specified is not one of your employees";
					}
					
				}
				
			}catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Return the list
			return allEmployeeRequests;
			
		// Client (manager) gets a list of employees and their managers
		case "employee/list":
			
			// Set the status code
			response.setStatus(200);
			
			// Initial list variable
			List<EmployeeManager> allEmployees = new ArrayList<>();
			
			try {
				
				// List variable that will call the service layer
				List<EmployeeManager> tempList = new EmployeeManagerServiceImpl().getRoster();
				
				// Loop through the tempList and append its elements to the allEmployees list
				for(EmployeeManager employeeList : tempList) {
					allEmployees.add(employeeList);
				}
				
				
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Return the list
			return allEmployees;

			
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
		
		// Client logs in and if it is successful they are granted a session and then sent to the appropriate home page
		case "/login":
			
			// Get the parameters
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
			
		// Client (employees) can update their personal info
		case "/update":
			
			// Get the employee object parameter
			final Employees employee = (Employees) request.getAttribute("employee");
			
			// Send the update to the service layer
			new EmployeesServiceImpl().updateInfo(employee);
			
			// End the case with a break
			break;
			
		default:
			response.setStatus(404);
		
		}
		
	}
	
}
