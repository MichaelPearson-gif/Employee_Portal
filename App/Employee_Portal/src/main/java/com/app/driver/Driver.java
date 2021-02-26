package com.app.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.app.exceptions.BusinessException;
import com.app.model.Employees;
import com.app.model.Managers;
import com.app.service.EmployeesService;
import com.app.service.impl.EmployeesServiceImpl;

public class Driver {

	public static void main(String[] args) {
		
		EmployeesService employeesService = new EmployeesServiceImpl();
		
		try {
			Employees employee = employeesService.getEmployee("lbrown@gmail.com");
			Set<Managers> manager = employee.getManagers();
			List<Managers> managerList = new ArrayList<>();
			System.out.println(manager);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
