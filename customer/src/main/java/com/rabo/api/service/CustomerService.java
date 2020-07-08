package com.rabo.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabo.api.dao.CustomerDao;
import com.rabo.api.service.entity.CustomerEntity;
import com.rabo.api.vo.CustomerVo;

@Service
public class CustomerService {

	@Autowired
	CustomerDao dao;
	
	List<CustomerVo> findAllCustomers() {
		Iterable<CustomerEntity> customers = dao.findAll();
		
		if ( customers != null ) {
			List<CustomerVo> vos = StreamSupport
											.stream(customers.spliterator(), false)
											.map(entity -> CustomerVo.builder()
												.firstName(entity.getFirstName())
												.lastName(entity.getLastName())
												.age(entity.getAge())
												.address(entity.getAddress())
												.build())
											.collect(Collectors.toList());
			
			return vos;
		}
		return null;
	}
	
	
	CustomerVo addNewCustomer(CustomerVo vo) {
		CustomerEntity entity = CustomerEntity.builder()
												.firstName(vo.getFirstName())
												.lastName(vo.getLastName())
												.age(vo.getAge())
												.address(vo.getAddress()).build();
		dao.save(entity);
		return vo;
	}
	
	
	CustomerVo findCustomerById(int id) {
		Optional<CustomerEntity> optionalEntity = dao.findById(id);
		
		if ( optionalEntity.isPresent() ) {
			CustomerEntity entity = optionalEntity.get();
			return CustomerVo.builder()
					.firstName(entity.getFirstName())
					.lastName(entity.getLastName())
					.age(entity.getAge())
					.address(entity.getAddress())
					.build();
		}
		return null;
	}
	
	
	
/*
	@RequestMapping("/findByCode/{airportCode}")
	List<AirportVo> findByAirportCodeContaining(@PathVariable String airportCode) {
		List<AirportEntity> airportEntities = service.findByAirportCodeContaining(airportCode);

		if (!CollectionUtils.isEmpty(airportEntities)) {
			List<AirportVo> vos =  airportEntities
					.stream()
					.map(ApiUtil::converFromEntityToVo)
					.collect(Collectors.toList());
			return vos;
					
		}

		return null;
	}

	@RequestMapping("/findByDescription/{airportDescription}")
	List<AirportVo> findByAirportDescriptionContaining(@PathVariable String airportDescription) {
		List<AirportEntity> airportEntities = service.findByAirportDescriptionContaining(airportDescription);

		if (!CollectionUtils.isEmpty(airportEntities)) {

			List<AirportVo> vos = airportEntities
					.stream()
					.map(ApiUtil::converFromEntityToVo)
					.collect(Collectors.toList());
					
			return vos;
		}

		return null;
	}
*/}
