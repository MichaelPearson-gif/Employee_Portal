package com.app.model;

public class EmployeeToManager {

	private int jointId;
	private int managerId;
	private int employeeId;
	
	public EmployeeToManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeToManager(int jointId, int managerId, int employeeId) {
		super();
		this.jointId = jointId;
		this.managerId = managerId;
		this.employeeId = employeeId;
	}

	public int getJointId() {
		return jointId;
	}

	public void setJointId(int jointId) {
		this.jointId = jointId;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + employeeId;
		result = prime * result + jointId;
		result = prime * result + managerId;
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
		EmployeeToManager other = (EmployeeToManager) obj;
		if (employeeId != other.employeeId)
			return false;
		if (jointId != other.jointId)
			return false;
		if (managerId != other.managerId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeeToManager [jointId=" + jointId + ", managerId=" + managerId + ", employeeId=" + employeeId
				+ "]";
	}
	
}
