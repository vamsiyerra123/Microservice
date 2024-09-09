package com.microservices.employee.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.employee.entity.EntityClass;

public interface Repositories extends JpaRepository<EntityClass,Integer> {

}
