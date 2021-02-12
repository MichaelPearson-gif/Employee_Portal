package com.app.model;

public class Managers {

	private int managerNum;
	private int employeeNum;
	
	public Managers() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Managers(int managerNum, int employeeNum) {
		super();
		this.managerNum = managerNum;
		this.employeeNum = employeeNum;
	}

	public int getManagerNum() {
		return managerNum;
	}

	public void setManagerNum(int managerNum) {
		this.managerNum = managerNum;
	}

	public int getEmployeeNum() {
		return employeeNum;
	}

	public void setEmployeeNum(int employeeNum) {
		this.employeeNum = employeeNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + employeeNum;
		result = prime * result + managerNum;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Managers other = (Managers) obj;
		if (employeeNum != other.employeeNum)
			return false;
		if (managerNum != other.managerNum)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Managers [managerNum=" + managerNum + ", employeeNum=" + employeeNum + "]";
	}
	
}
