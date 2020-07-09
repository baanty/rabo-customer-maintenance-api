package com.rabo.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.rabo.api.dao.CustomerDao;
import com.rabo.api.service.entity.AddressEntity;
import com.rabo.api.service.entity.CustomerEntity;
import com.rabo.api.to.AddressTransferObject;
import com.rabo.api.to.CustomerTransferObject;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author : Pijush Kanti Das.
 * This service will find the Customers, save the customers and update the customers.
 * 
 *
 */
@Slf4j
@Service
@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
public class CustomerService {

	@Autowired
	CustomerDao dao;

	/**
	 * Use this method to find all the customers from teh database.
	 * @return
	 */
	public List<CustomerTransferObject> findAllCustomers() {

		try {
			Iterable<CustomerEntity> customers = dao.findAll();

			if (customers != null) {
				return buildEntitiesFromVosAndReturn(customers);
			}
		} catch (Exception exception) {
			log.error("And error occured while finding all the customers.", exception);
			throw new RuntimeException(exception);
		}
		return null;
	}

	/**
	 * Use this method to save a new customer. However, if a new customer is already existing,
	 * it will update the old customer.
	 * @param vo : The API input value object.
	 * @return : The created customer.
	 */
	public CustomerTransferObject addNewCustomer(CustomerTransferObject vo) {
		saveCustomer(vo, false);
		return vo;
	}

	/**
	 * Use this method to find a customer by his id.
	 * 
	 * @param id : The ID of the customer
	 * @return : The found customer. If not found, gives a null return.
	 */
	public CustomerTransferObject findCustomerById(int id) {
		try {
			Optional<CustomerEntity> optionalEntity = dao.findById(id);

			if (optionalEntity.isPresent()) {

				CustomerEntity entity = optionalEntity.get();

				AddressEntity address = entity.getAddress() != null ? entity.getAddress()
						: new AddressEntity(0, null, null, entity);
				AddressTransferObject addressTransferObject = AddressTransferObject.builder().id(address.getId()).city(address.getCity()).streetName(address.getStreetName())
						.build();
				return CustomerTransferObject.builder().id(entity.getId()).firstName(entity.getFirstName()).lastName(entity.getLastName())
						.age(entity.getAge()).address(addressTransferObject).build();
			}
		} catch (Exception exception) {
			log.error("And error occured while finding all the customers.", exception);
			throw new RuntimeException(exception);
		}
		return null;
	}

	/**
	 * USe this method to get all the customers with matching first or last name.
	 * Please note , if the first name matches, then the last name will not be used.
	 * If there is no customer with the given first name, then only the last name will
	 * be consulted.
	 * 
	 * @param customerTransferObject : The input Value object.
	 * @return : The found customers. If not found, then null.
	 */
	public List<CustomerTransferObject> findCustomerByFirstNameOrLastName(CustomerTransferObject customerTransferObject) {

		try {
			if (customerTransferObject != null && (StringUtils.hasText(customerTransferObject.getFirstName()) || StringUtils.hasText(customerTransferObject.getLastName()))) {

				return findCustomerByFirstNameOrLastName(customerTransferObject.getFirstName(), customerTransferObject.getLastName());
			}
		} catch (Exception exception) {
			log.error("And error occured while finding by first name or last name.", exception);
			throw new RuntimeException(exception);
		}
		return null;
	}
	
	/**
	 * USe this method to get all the customers with matching first or last name.
	 * Please note , if the first name matches, then the last name will not be used.
	 * If there is no customer with the given first name, then only the last name will
	 * be consulted.
	 * 
	 * Also, Please note that it is an exact match search. It is not a like search.
	 * So, you have to give the exact match of the First Name or the Last Name.
	 * 
	 * @param firstName : First Name of the customer.
	 * @param lastName : Last Name of the customer.
	 * @return : The found customers. If not found, then null.
	 */
	public List<CustomerTransferObject> findCustomerByFirstNameOrLastName(String firstName, String lastName) {

		try {
			if ( StringUtils.hasText(firstName) || StringUtils.hasText(firstName)) {

				if (StringUtils.hasText(firstName)) {
					List<CustomerEntity> entities = dao.findByFirstName(firstName);

					if (!CollectionUtils.isEmpty(entities)) {
						return buildEntitiesFromVosAndReturn(entities);
					}
				}

				if (StringUtils.hasText(lastName)) {

					List<CustomerEntity> entities = dao.findByLastName(lastName);

					if (!CollectionUtils.isEmpty(entities)) {
						return buildEntitiesFromVosAndReturn(entities);
					}
				}
			}
		} catch (Exception exception) {
			log.error("And error occured while finding by first name or last name.", exception);
			throw new RuntimeException(exception);
		}
		return null;
	}

	/**
	 * Use this method to update the address of the customer. But if the customer does not already exist,
	 * you will get a null return.
	 * 
	 * @param vo : The customer, whose address has to be saved.
	 * @return : The updated customer.
	 */
	public CustomerTransferObject updateAddress(CustomerTransferObject vo) {

		if (vo != null && vo.getAddress() != null  ) {
			saveCustomer(vo, true);
		}
		return vo;
	}

	private void saveCustomer(CustomerTransferObject vo, boolean isUpdate) {
		try {

			AddressEntity addressEntity = (vo != null && vo.getAddress() != null)
					? AddressEntity.builder().city(vo.getAddress().getCity())
							.streetName(vo.getAddress().getStreetName()).build()
					: new AddressEntity(0, null, null, null);
			CustomerEntity entity = CustomerEntity.builder().firstName(vo.getFirstName()).lastName(vo.getLastName())
					.age(vo.getAge()).address(addressEntity).build();
			
			if ( isUpdate ) {
				Optional<CustomerEntity> optionalEntity = dao.findById(vo.getId());

				if ( optionalEntity.isPresent() ) {
					entity = optionalEntity.get();
					entity.getAddress().setCity(addressEntity.getCity());
					entity.getAddress().setStreetName(addressEntity.getStreetName());
				}
				
			}
			dao.save(entity);
		} catch (Exception exception) {
			log.error("And error occured while saving the customer.", exception);
			throw new RuntimeException(exception);
		}
	}

	private List<CustomerTransferObject> buildEntitiesFromVosAndReturn(Iterable<CustomerEntity> entities) {

		try {
			List<CustomerTransferObject> vos = StreamSupport.stream(entities.spliterator(), false).filter(entity -> entity != null)
					.map(entity -> {

						AddressEntity address = entity.getAddress() != null ? entity.getAddress()
								: new AddressEntity(0, null, null, entity);
						AddressTransferObject addressTransferObject = AddressTransferObject.builder().id(address.getId()).city(address.getCity())
								.streetName(address.getStreetName()).build();
						CustomerTransferObject vo = CustomerTransferObject.builder().id(entity.getId()).firstName(entity.getFirstName())
								.lastName(entity.getLastName()).age(entity.getAge()).address(addressTransferObject).build();
						return vo;
					}).collect(Collectors.toList());
			return vos;
		} catch (Exception exception) {
			log.error("And error occured while converting from entity to vo.", exception);
			throw new RuntimeException(exception);
		}
	}

}
