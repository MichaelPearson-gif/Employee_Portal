package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "managers", schema = "employee_portal")
public class Managers {

	/*
	 *  Mapping each field with their respective columns and column names with the @Column(name = "")
	 *  managerId is the primary key in the table, so it needs the @Id annotation
	 *  managerId is also a serial type in the DB, so I need the @GeneratedValue and @SequenceGenerator
	 *  @SequenceGenerator will correspond with the sequence constraints I created
	 */
	@Column(name = "manager_id")
	@Id
	@GeneratedValue(generator = "managers_manager_id_seq")
	@SequenceGenerator(initialValue = 100, allocationSize = 1, name = "managers_manager_id_seq", sequenceName = "managers_manager_id_seq")
	private int managerId;
	@Column(name = "email")
	private String email;
	
	public Managers() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Managers(int managerId, String email) {
		super();
		this.managerId = managerId;
		this.email = email;
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
		Managers other = (Managers) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (managerId != other.managerId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Managers [managerId=" + managerId + ", email=" + email + "]";
	}	
}
