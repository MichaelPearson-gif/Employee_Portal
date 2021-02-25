package com.app.driver;

import com.app.exceptions.EmptyListException;
import com.app.model.Employees;
import com.app.repository.EmployeesRepo;
import com.app.repository.impl.EmployeesRepoImpl;

public class Driver {

	public static void main(String[] args) {
		
		EmployeesRepo employeesRepo = new EmployeesRepoImpl();
		
		try {
			for(Employees employee : employeesRepo.employeeList()) {
				System.out.println(employee + "\n");
			}
		} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
