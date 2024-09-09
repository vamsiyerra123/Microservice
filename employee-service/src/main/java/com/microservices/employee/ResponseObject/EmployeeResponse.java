package com.microservices.employee.ResponseObject;

import javax.persistence.Column;

public class EmployeeResponse {
	private int employeeID;
	private String employeeName;
	private String employeeEmail;
	private String employeeBloodGroup;
	private AddressResponse addressresponse;
	
	public AddressResponse getAddressresponse() {
		return addressresponse;
	}
	public void setAddressresponse(AddressResponse addressresponse) {
		this.addressresponse = addressresponse;
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
	 
	

}
