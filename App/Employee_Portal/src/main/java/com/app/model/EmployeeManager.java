package com.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee_manager", schema = "employee_portal")
public class EmployeeManager {

	// Mapping each field with their respective columns and column names with the @Column(name = "")
	// Email is the primary key in the table, so it needs the @Id annotation
	// Both fields are foreign keys to different tables with their own relationships, so I will need to map those correctly
	@Id
	@OneToOne
	@JoinColumn(name = "email")
	private Employees employee;
	
	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Managers manager;

	public EmployeeManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeManager(Employees employee, Managers manager) {
		super();
		this.employee = employee;
		this.manager = manager;
	}

	public Employees getEmployee() {
		return employee;
	}

	public void setEmployee(Employees employee) {
		this.employee = employee;
	}

	public Managers getManager() {
		return manager;
	}

	public void setManager(Managers manager) {
		this.manager = manager;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
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
		EmployeeManager other = (EmployeeManager) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeeManager [employee=" + employee + ", manager=" + manager + "]";
	}
	
}
