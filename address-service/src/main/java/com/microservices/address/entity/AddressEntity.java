package com.microservices.address.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address_details")
public class AddressEntity {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	@Column
	private String lane1;
	@Column
	private String lane2;
	@Column
	private String state;
	@Column
	private String zip;
	@Column
	private int employee_id;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getLane1() {
		return lane1;
	}
	public void setLane1(String lane1) {
		this.lane1 = lane1;
	}
	public String getLane2() {
		return lane2;
	}
	public void setLane2(String lane2) {
		this.lane2 = lane2;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	
	public AddressEntity(int iD, String lane1, String lane2, String state, String zip, int employee_id) {
		super();
		ID = iD;
		this.lane1 = lane1;
		this.lane2 = lane2;
		this.state = state;
		this.zip = zip;
		this.employee_id = employee_id;
	}
	public AddressEntity() {
		super();
	}
	
	
}
