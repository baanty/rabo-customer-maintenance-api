package com.rabo.api.dao;

import java.util.List;
import java.util.Optional;

import com.rabo.api.service.entity.CustomerEntity;

public interface CustomerRepository {

	List<CustomerEntity> findByFirstName(String firstName);

	List<CustomerEntity> findByLastName(String lastName);
	
	Optional<CustomerEntity> findById(Integer id);
	
	Iterable<CustomerEntity> findAll();
	
	CustomerEntity save(CustomerEntity entity);

}