package com.rabo.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.rabo.api.dao.CustomerDao;
import com.rabo.api.service.entity.AddressEntity;
import com.rabo.api.service.entity.CustomerEntity;
import com.rabo.api.vo.AddressVo;
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
											.filter(entity -> entity != null)
					.map(entity -> {

						AddressEntity address = entity.getAddress() != null ? entity.getAddress()
								: new AddressEntity(0, null, null, entity);
						AddressVo addressVo = AddressVo.builder().city(address.getCity())
								.streetName(address.getStreetName()).build();
						CustomerVo vo = CustomerVo.builder().firstName(entity.getFirstName())
								.lastName(entity.getLastName()).age(entity.getAge()).address(addressVo).build();
						return vo;
					})
											.collect(Collectors.toList());
			
			return vos;
		}
		return null;
	}
	
	
	CustomerVo addNewCustomer(CustomerVo vo) {
		AddressEntity addressEntity = 
				( vo != null && vo.getAddress() != null ) ?
				AddressEntity.builder()
							.city(vo.getAddress().getCity())
							.streetName(vo.getAddress().getStreetName())
							.build() 
				: new AddressEntity(0, null, null, null);
		CustomerEntity entity = CustomerEntity.builder()
												.firstName(vo.getFirstName())
												.lastName(vo.getLastName())
												.age(vo.getAge())
												.address(addressEntity).build();
		dao.save(entity);
		return vo;
	}
	
	
	CustomerVo findCustomerById(int id) {
		Optional<CustomerEntity> optionalEntity = dao.findById(id);
		
		if ( optionalEntity.isPresent() ) {
			
			
			CustomerEntity entity = optionalEntity.get();
			
			AddressEntity address = entity.getAddress() != null ? entity.getAddress()
					: new AddressEntity(0, null, null, entity);
			AddressVo addressVo = AddressVo.builder().city(address.getCity())
					.streetName(address.getStreetName()).build();
			return CustomerVo.builder()
					.firstName(entity.getFirstName())
					.lastName(entity.getLastName())
					.age(entity.getAge())
					.address(addressVo)
					.build();
		}
		return null;
	}
	
	CustomerVo findCustomerByFirstNameOrLastName(CustomerVo vo) {
		
		if (vo != null && ( StringUtils.hasText(vo.getFirstName()) || StringUtils.hasText(vo.getLastName()) )) {
			
			CustomerEntity entity = dao.findByFirstName(vo.getFirstName()).isEmpty() ? 
			
			if ( StringUtils.hasText(vo.getFirstName()) ) {
				
			}

			Optional<CustomerEntity> optionalEntity = dao.findById(id);
			
			if ( optionalEntity.isPresent() ) {
				
				
				CustomerEntity entity = optionalEntity.get();
				
				AddressEntity address = entity.getAddress() != null ? entity.getAddress()
						: new AddressEntity(0, null, null, entity);
				AddressVo addressVo = AddressVo.builder().city(address.getCity())
						.streetName(address.getStreetName()).build();
				return CustomerVo.builder()
						.firstName(entity.getFirstName())
						.lastName(entity.getLastName())
						.age(entity.getAge())
						.address(addressVo)
						.build();
			}
			
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
