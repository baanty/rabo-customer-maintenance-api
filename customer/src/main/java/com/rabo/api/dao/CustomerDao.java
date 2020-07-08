package com.rabo.api.dao;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rabo.api.service.entity.CustomerEntity;


public interface CustomerDao extends CrudRepository<CustomerEntity, Integer> {
	
	List<CustomerEntity> findByFirstName(String firstName);
	
	List<CustomerEntity> findByLastName(String lastName);

}
