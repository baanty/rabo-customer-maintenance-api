package com.rabo.api.dao;


import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rabo.api.service.entity.CustomerEntity;

@Repository
@Profile({"TEST", "ACCP", "PROD"})
public interface CustomerDao extends  CrudRepository<CustomerEntity, Integer>, CustomerRepository {

}
