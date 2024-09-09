   package com.microservices.employee.ServiceLLayer;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.employee.Repositories.Repositories;
import com.microservices.employee.ResponseObject.AddressResponse;
import com.microservices.employee.ResponseObject.EmployeeResponse;
import com.microservices.employee.entity.EntityClass;
import com.microservices.employee.openFiegnClient.OpenFeign;

@Service
public class ServiceLayer {
	@Autowired
	private Repositories reposiories;
	@Autowired
	private ModelMapper modelMapper;
//	@Autowired
//	private RestTemplate restTemplate;
//	@Autowired
//	private DiscoveryClient discoveryClient;
//	@Value("${address-service.base.url}")
//	private String addressbaseurl;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	@Autowired
	private OpenFeign openfeign;
	
	public List<EmployeeResponse> findAllEmployee() {
	    List<EntityClass> employees = reposiories.findAll();

	    ResponseEntity<List<AddressResponse>> addressResponseEntity = openfeign.findAllAddresses();
	    List<AddressResponse> addressResponses = addressResponseEntity.getBody();
	    List<EmployeeResponse> employeeResponses = new ArrayList<>();

	    if (addressResponses != null) {
	        employees.forEach(employee -> {
	            EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
	            AddressResponse mappedResponses = addressResponses.stream()
	                .filter(address -> address.getEmployee_ID() == employee.getEmployeeID())
	                .findFirst()
	                .orElse(null);

	            if (mappedResponses == null) {
	                System.out.println("No address found for employee ID: " + employee.getEmployeeID());
	            } else {
	                System.out.println("Found address for employee ID: " + employee.getEmployeeID());
	            }

	            employeeResponse.setAddressresponse(mappedResponses);
	            employeeResponses.add(employeeResponse);
	        });
	    } else {
	        System.out.println("No addresses were returned by the Feign client.");
	    }

	    return employeeResponses;
	
//	public List<EmployeeResponse> findAllEmployee() {
//		List<EntityClass> employees = reposiories.findAll();
//		
//		ResponseEntity<List<AddressResponse>> addressResponseEntity = openfeign.findAllAddresses();
//		List<AddressResponse> addressResponses = addressResponseEntity.getBody();
//		List<EmployeeResponse> employeeResponses = new ArrayList<>();
//		employees.forEach(employee -> {
//			EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
//			AddressResponse mappedResponses = addressResponses.stream()
//					.filter(address -> address.getEmployee_ID() == employee.getEmployeeID())
//					.findFirst()
//					.orElse(null);
//			employeeResponse.setAddressresponse(mappedResponses);
//			employeeResponses.add(employeeResponse);
//		});
//        return employeeResponses;
        
//		return employees.stream().map(employee -> {
//      EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
//
//      // Assuming that address details are stored separately and need to be fetched
//      ResponseEntity<AddressResponse> addressResponseEntity = 
//          openfeign.getEmployeeDetails(employee.getEmployeeID());
//      AddressResponse addressResponse = addressResponseEntity.getBody();
//      
//      employeeResponse.setAddressresponse(addressResponse);
//
//      return employeeResponse;
//  }).collect(Collectors.toList());
    }
		
	
	public EmployeeResponse getEmployeeDetails(int employeeID) {
		EntityClass employee = reposiories.findById(employeeID).get();
		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);	
//		List<ServiceInstance> instances = discoveryClient.getInstances("ADDRESS-SERVICE");
//		ServiceInstance serviceInstance = instances.get(0);
		
		ServiceInstance serviceInstance = loadBalancerClient.choose("ADDRESS-SERVICE");
		String uri = serviceInstance.getUri().toString();
		String contextRoot =serviceInstance.getMetadata().get("configPath");
//		AddressResponse addressResponse =restTemplate.getForObject(uri+contextRoot+"/address/{employeeID}", AddressResponse.class, employeeID);
		ResponseEntity<AddressResponse> addressResponseEntity = openfeign.getEmployeeDetails(employeeID);
		AddressResponse addressResponse = addressResponseEntity.getBody();
		employeeResponse.setAddressresponse(addressResponse);	
		System.out.println(uri+contextRoot);
		return employeeResponse;
		
	}
	public EmployeeResponse saveEmployee(EmployeeResponse employeeresponseDto) {
		
		EntityClass entityclass = new EntityClass();
		entityclass.setEmployeeID(employeeresponseDto.getEmployeeID());
		entityclass.setEmployeeName(employeeresponseDto.getEmployeeName());
		entityclass.setEmployeeEmail(employeeresponseDto.getEmployeeEmail());
		entityclass.setEmployeeBloodGroup(employeeresponseDto.getEmployeeBloodGroup());
		EntityClass employee = reposiories.save(entityclass);
		
		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
//		ServiceInstance instance = loadBalancerClient.choose("ADDRESS-SERVICE");
//		String uri = instance.getUri().toString();
//		String contextPath = instance.getMetadata().get("configPath");
		AddressResponse addressResponse = employeeresponseDto.getAddressresponse();
		addressResponse.setEmployee_ID(employee.getEmployeeID());
		
		ResponseEntity<AddressResponse> addressResponseEntity = openfeign.saveEmployeeAddress(addressResponse);
		employeeResponse.setAddressresponse(addressResponse);	
		System.out.println(addressResponse.toString());
		return employeeResponse;
	}
}
