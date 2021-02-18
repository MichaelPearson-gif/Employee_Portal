package com.app.model;

public class EmployeeToManager {

	private int jointId;
	private int managerId;
	private String email;
	
	public EmployeeToManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeToManager(int jointId, int managerId, String email) {
		super();
		this.jointId = jointId;
		this.managerId = managerId;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (jointId != other.jointId)
			return false;
		if (managerId != other.managerId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeeToManager [jointId=" + jointId + ", managerId=" + managerId + ", email=" + email + "]";
	}	
}
