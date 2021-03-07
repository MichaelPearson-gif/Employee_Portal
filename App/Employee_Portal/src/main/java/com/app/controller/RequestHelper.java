package com.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.exceptions.BusinessException;
import com.app.model.EmployeeManager;
import com.app.model.Employees;
import com.app.model.Managers;
import com.app.model.Requests;
import com.app.service.EmployeesService;
import com.app.service.impl.EmployeeManagerServiceImpl;
import com.app.service.impl.EmployeesServiceImpl;
import com.app.service.impl.ManagersServiceImpl;
import com.app.service.impl.RequestsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestHelper {
	
	// Instance of service layers
	private static EmployeesService employeesService = new EmployeesServiceImpl();

	// Method to process GET requests
	public static Object processGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		final String URI = request.getRequestURI();
		final String RESOURCE = URI.replace("/Employee_Portal/api", "");
		
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
			
			// Redirect to the login page
//			response.sendRedirect("Employee_Portal/webapp/index.html");
			
			return null;
			
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
		final String RESOURCE = URI.replace("/Employee_Portal/api", "");
		
		switch(RESOURCE) {
		
		// Client logs in and if it is successful they are granted a session and then sent to the appropriate home page
		case "/login":
			
			// Get the parameters
			final String email = request.getParameter("userEmail");
			final String password = request.getParameter("userPassword");
						
			if(employeesService.validate(email, password) == true) {
				
				// Get the employee's title
				String title = employeesService.getEmployee(email).getTitle();
				
				// Grant the client a session
				HttpSession session = request.getSession();
				
				// Set the client email and title as session attributes
				session.setAttribute("email", email);
				session.setAttribute("title", title);
				
				// Redirect to the correct page
				// Check to see if the client is a manager
				if(title.equals("Manager") | title.equals("General Manager")) {
					
					// Send to the managers home page
					
					response.sendRedirect("/Employee_Portal/Pages/manager.html");
					
				}else {
					
					// Send to the employees home page
					
					response.sendRedirect("/Employee_Portal/Pages/employee.html");
										
				}
				
			}else {
				// If .validate is false redirect back to the login page
				response.sendRedirect("/Employee_Portal/Pages/index.html");
			}
			break;
			
		// Client (employees) can update their personal info
		case "/update":
			
			// Get the email and title session attributes
			String emailAttribute = (String) request.getSession(false).getAttribute("email");
			String titleAttribute = (String) request.getSession(false).getAttribute("title");
			
			// Get the employee based on the session attribute
			Employees employee1 = employeesService.getEmployee(emailAttribute);
			 
			//------------------------------------------------------------------------------------------
			// Deserializing the JSON object using Jasckon ObjectMapper
			
			// Instance of the object mapper
			ObjectMapper mapper = new ObjectMapper();
			
			// Deserialize the JSON
			final Employees employeeUpdate = mapper.readValue(request.getInputStream(), Employees.class);
			
			//-------------------------------------------------------------------------------------------
			
			// set the updated info to the employee object
			employee1.setFirstName(employeeUpdate.getFirstName());
			employee1.setLastName(employeeUpdate.getLastName());
			employee1.setGender(employeeUpdate.getGender());
			
			// Send the update to the service layer
			employeesService.updateInfo(employee1);
			
			// Return back to the same page the client was on based on the title session attribute
			if(titleAttribute.equals("Manager") | titleAttribute.equals("General Manager")) {
				
				// Send to the managers home page
				
				response.sendRedirect("/Employee_Portal/Pages/manager.html");
				
			}else {
				
				// Send to the employees home page
				
				response.sendRedirect("/Employee_Portal/Pages/employee.html");
									
			}
			
			// End the case with a break
			break;
			
		// Client can submit a reimbursement request
		case "/request":
			
			// Get the request object parameter
			final Requests newRequest = (Requests) request.getAttribute("newRequest");
			
			// Send the reimbursment request to the service layer
			new RequestsServiceImpl().newRequest(newRequest);
			
			// End the case with a break
			break;
			
		// Client (managers) can approve or deny requests
		case "/resolving":
			
			// Get the request object parameter
			final Requests updateRequest = (Requests) request.getAttribute("updateRequest");
			
			// Send the update to the service layer
			new RequestsServiceImpl().updateRequests(updateRequest);
			
			// End the case with a break
			break;
			
		default:
			response.setStatus(404);
		
		}
		
	}
	
}
