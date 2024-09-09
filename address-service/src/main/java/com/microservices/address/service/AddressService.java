package com.microservices.address.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.address.ResponseObject.AddressResponse;
import com.microservices.address.entity.AddressEntity;
import com.microservices.address.repository.AddressRepo;

@Service
public class AddressService {
	@Autowired
	private AddressRepo addressrepo;
	@Autowired
	private ModelMapper modelmapper;
	
	public AddressResponse getAddressByID(int employee_ID) {
		AddressEntity address = addressrepo.getAddressByEmployeeId(employee_ID);
		AddressResponse addressResponse = modelmapper.map(address, AddressResponse.class);
		System.out.println("you got it ....");
		return addressResponse;
	}
	public AddressResponse saveAddress(AddressResponse addressresponse) {
		
		AddressEntity addressentity = new AddressEntity();
		addressentity.setEmployee_id(addressresponse.getEmployee_ID());
		addressentity.setID(addressresponse.getID());
		addressentity.setLane1(addressresponse.getLane1());
		addressentity.setLane2(addressresponse.getLane2());
		addressentity.setState(addressresponse.getState());
		addressentity.setZip(addressresponse.getZip());
		addressrepo.save(addressentity);
//		AddressResponse addressResponse = modelmapper.map(address, AddressResponse.class);
		return addressresponse;
	}
	public List<AddressResponse> findAllEmployees() {
		List<AddressEntity> addresses = addressrepo.findAll();
		List<AddressResponse> addressReponses = new ArrayList<>();
		addresses.forEach(address->{
			AddressResponse addressResponse = modelmapper.map(address, AddressResponse.class);
			addressReponses.add(addressResponse);
		});
		System.out.println("sent all the address buddy ! please check.......");
		return addressReponses;
	}
}
