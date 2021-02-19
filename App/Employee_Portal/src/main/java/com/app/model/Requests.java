package com.app.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "requests", schema = "employee_portal")
public class Requests {

	/*
	 *  Mapping each field with their respective columns and column names with the @Column(name = "")
	 *  requestId is the primary key in the table, so it needs the @Id annotation
	 *  requestId is also a serial type in the DB, so I need the @GeneratedValue and @SequenceGenerator
	 *  @SequenceGenerator will correspond with the sequence constraints I created
	 */
	@Column(name = "request_id")
	@Id
	@GeneratedValue(generator = "requests_request_id_seq")
	@SequenceGenerator(allocationSize = 1, name = "requests_request_id_seq", sequenceName = "requests_request_id_seq")
	private int requestId;
	@Column(name = "email")
	private String email;
	@Column(name = "manager_id")
	private int managerId;
	@Column(name = "amount")
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

	public Requests(int requestId, String email, int managerId, double amount, String reciept, String status,
			Date date) {
		super();
		this.requestId = requestId;
		this.email = email;
		this.managerId = managerId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
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
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + managerId;
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (managerId != other.managerId)
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
		return "Requests [requestId=" + requestId + ", email=" + email + ", managerId=" + managerId + ", amount="
				+ amount + ", reciept=" + reciept + ", status=" + status + ", date=" + date + "]";
	}	
}
