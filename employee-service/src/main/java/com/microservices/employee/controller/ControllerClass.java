package com.microservices.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.employee.ResponseObject.EmployeeResponse;
import com.microservices.employee.ServiceLLayer.ServiceLayer;
import com.microservices.employee.entity.EntityClass;

@RestController
public class ControllerClass {
	@Autowired
	ServiceLayer servicelayer;
	@GetMapping("/employee/{employeeID}")
	ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("employeeID") int employeeID){
		EmployeeResponse employeeResponse = servicelayer.getEmployeeDetails(employeeID);
		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
	}
	@PostMapping("/saveEmployee")
	ResponseEntity<EmployeeResponse> saveEmployee(@RequestBody EmployeeResponse entityclass){
		EmployeeResponse employeeResponse = servicelayer.saveEmployee(entityclass);
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeResponse);
	}
	@GetMapping("/findAllEmployees")
	ResponseEntity<List<EmployeeResponse>> findAllEmployee(){
		List<EmployeeResponse> employeeResponse = servicelayer.findAllEmployee();
		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
	}
	
	

}
