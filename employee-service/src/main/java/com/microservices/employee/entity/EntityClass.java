package com.microservices.employee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "EmployeeDetails")
public class EntityClass {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeID;
	@Column
	private String employeeName;
	@Column
	private String employeeEmail;
	@Column
	private String employeeBloodGroup;
	
	public EntityClass(int employeeID, String employeeName, String employeeEmail, String employeeBloodGroup) {
		super();
		this.employeeID = employeeID;
		this.employeeName = employeeName;
		this.employeeEmail = employeeEmail;
		this.employeeBloodGroup = employeeBloodGroup;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	public String getEmployeeBloodGroup() {
		return employeeBloodGroup;
	}
	public void setEmployeeBloodGroup(String employeeBloodGroup) {
		this.employeeBloodGroup = employeeBloodGroup;
	}
	public EntityClass() {
		super();
	}
	
	
	
	
}
