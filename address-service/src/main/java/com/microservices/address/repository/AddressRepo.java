package com.microservices.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microservices.address.entity.AddressEntity;

public interface AddressRepo extends JpaRepository<AddressEntity, Integer> {
	@Query(nativeQuery = true, value = "SELECT * FROM address_details WHERE employee_id = :employeeId")
	AddressEntity getAddressByEmployeeId(@Param("employeeId") int employeeId);

}
