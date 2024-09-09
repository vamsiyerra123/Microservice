package com.microservices.employee.openFiegnClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.microservices.employee.ResponseObject.AddressResponse;

@FeignClient(name = "address-service",path= "/service-api/")
public interface OpenFeign {
	@GetMapping("/address/{employeeID}")
	ResponseEntity<AddressResponse> getEmployeeDetails(@PathVariable("employeeID") int employeeID);
	
	@PostMapping("/saveAddress")
	ResponseEntity<AddressResponse> saveEmployeeAddress(AddressResponse addressResponse);
	
	@GetMapping("/getAllAddresses")
	ResponseEntity<List<AddressResponse>> findAllAddresses();
}
