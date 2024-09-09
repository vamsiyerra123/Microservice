package com.microservices.address.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.address.ResponseObject.AddressResponse;
import com.microservices.address.entity.AddressEntity;
import com.microservices.address.service.AddressService;

@RestController
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/address/{employee_ID}")
	ResponseEntity<AddressResponse> getAddressByID(@PathVariable("employee_ID") int employee_ID){
		AddressResponse addressresponse = addressService.getAddressByID(employee_ID);
		return ResponseEntity.status(HttpStatus.OK).body(addressresponse);
		
	}
	@PostMapping("/saveAddress")
	ResponseEntity<AddressResponse> saveAddress(@RequestBody AddressResponse addressentity){
		AddressResponse addressresponse = addressService.saveAddress(addressentity);
		return ResponseEntity.status(HttpStatus.CREATED).body(addressresponse);
	}
	@GetMapping("/getAllAddresses")
	ResponseEntity<List<AddressResponse>> findAllAddresses(){
		List<AddressResponse> addressResponse =  addressService.findAllEmployees();
		return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
	}

}
