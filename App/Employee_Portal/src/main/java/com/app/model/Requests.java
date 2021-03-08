package com.app.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "requests", schema = "employee_portal")
public class Requests {

	/*
	 *  Mapping each field with their respective columns and column names with the @Column(name = "")
	 *  requestId is the primary key in the table, so it needs the @Id annotation
	 *  requestId is also a serial type in the DB, so I need the @GeneratedValue and @SequenceGenerator
	 *  @SequenceGenerator will correspond with the sequence constraints I created.
	 *  
	 *  Both employee and manager are many-to-one relationships with the requests table.
	 */
	@Column(name = "request_id")
	@Id
	@GeneratedValue(generator = "employee_portal.requests_request_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize = 1, name = "employee_portal.requests_request_id_seq", initialValue = 1, sequenceName = "employee_portal.requests_request_id_seq")
	private int requestId;
	@JoinColumn(name = "email")
	@ManyToOne
	private Employees employee;
	@JoinColumn(name = "manager_id")
	@ManyToOne
	private Managers manager;
	@Column(name = "amount", columnDefinition = "NUMERIC")
	private double amount;
	@Column(name = "reciept")
	private String reciept;
	@Column(name = "status")
	private String status;
	@Column(name = "dates")
	private Date date;
	
	public Requests() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Requests(int requestId, Employees employee, Managers manager, double amount, String reciept, String status,
			Date date) {
		super();
		this.requestId = requestId;
		this.employee = employee;
		this.manager = manager;
		this.amount = amount;
		this.reciept = reciept;
		this.status = status;
		this.date = date;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getReciept() {
		return reciept;
	}

	public void setReciept(String reciept) {
		this.reciept = reciept;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
		result = prime * result + ((reciept == null) ? 0 : reciept.hashCode());
		result = prime * result + requestId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Requests other = (Requests) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
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
		if (reciept == null) {
			if (other.reciept != null)
				return false;
		} else if (!reciept.equals(other.reciept))
			return false;
		if (requestId != other.requestId)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Requests [requestId=" + requestId + ", employee=" + employee + ", manager=" + manager + ", amount="
				+ amount + ", reciept=" + reciept + ", status=" + status + ", date=" + date + "]";
	}

}
