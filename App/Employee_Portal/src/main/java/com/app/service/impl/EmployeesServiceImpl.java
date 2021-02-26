package com.app.service.impl;

import java.util.List;

import com.app.exceptions.BusinessException;
import com.app.model.Employees;
import com.app.service.EmployeesService;

public class EmployeesServiceImpl implements EmployeesService {

	@Override
	public boolean validate(String email, String password) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Employees getEmployee(String email) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateInfo(Employees employee) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Employees> employeeRoster() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
