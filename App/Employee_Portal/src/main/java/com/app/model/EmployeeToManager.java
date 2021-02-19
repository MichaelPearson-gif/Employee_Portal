package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "employee_manager", schema = "employee_portal")
public class EmployeeToManager {

	/*
	 *  Mapping each field with their respective columns and column names with the @Column(name = "")
	 *  managerId is the primary key in the table, so it needs the @Id annotation
	 *  managerId is also a serial type in the DB, so I need the @GeneratedValue and @SequenceGenerator
	 *  @SequenceGenerator will correspond with the sequence constraints I created
	 */
	@Column(name = "emp_man_id")
	@Id
	@GeneratedValue(generator = "employee_manager_emp_man_id_seq")
	@SequenceGenerator(initialValue = 1000, allocationSize = 1, name = "employee_manager_emp_man_id_seq", sequenceName = "employee_manager_emp_man_id_seq")
	private int jointId;
	@Column(name = "manager_id")
	private int managerId;
	@Column(name = "email")
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
