package com.app.driver;

import java.sql.Date;
import java.util.List;

import com.app.exceptions.BusinessException;
import com.app.model.EmployeeManager;
import com.app.model.Employees;
import com.app.model.Managers;
import com.app.service.EmployeeManagerService;
import com.app.service.impl.EmployeeManagerServiceImpl;

public class Driver {

	public static void main(String[] args) {
		
		EmployeeManagerService employeeManagerService = new EmployeeManagerServiceImpl();
		
		Employees employee = new Employees("sgoldberg@gmail.com", "Sarah", "Goldberg", 4432100987L, "F", new Date(195876), 505982341, 78500, "Manager", "50Shadesofmoney");
		
		Managers manager = new Managers(100, employee);
		
		try {
			
			List<EmployeeManager> mappedEmployees = employeeManagerService.getEmployeesByManager(manager);
			
			for(EmployeeManager employee1 : mappedEmployees) {
				System.out.println(employee1 + "\n");
			}
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
