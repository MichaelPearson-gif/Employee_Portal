package com.app.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.exceptions.BusinessException;
import com.app.model.Employees;
import com.app.service.impl.EmployeesServiceImpl;
import com.app.service.impl.ManagersServiceImpl;

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
					response.sendRedirect("/Employee_Portal/Pages/manager.html");
					
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
