package com.app.repository;

import java.util.List;

public interface EmployeesToManagerRepo {

	// List of employees for a manager
	List<Integer> employeesList(int managerId);
	
}
