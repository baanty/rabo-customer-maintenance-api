package com.rabo.api.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rabo.api.exception.GenericCustomerApplicationRuntimeException;
import com.rabo.api.jsonbody.CustomerJsonBody;
import com.rabo.api.service.CustomerService;
import com.rabo.api.to.CustomerTransferObject;
import com.rabo.api.util.MappingUtil;

import static com.rabo.api.util.MappingUtil.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CustomerController {

	@Autowired
	CustomerService service;

	/**
	 * Use this method to find all the customers from the controller.
	 * @return
	 */
	@GetMapping("/findAllCustomers")
	List<CustomerJsonBody> findAllCustomers() {

		try {
			return Optional.ofNullable(service.findAllCustomers())
					.map(Collection::stream)
					.orElseGet(Stream::empty)
					.map(MappingUtil::buildCustomerJsonBodyFromCustomerTransferObject)
					.collect(Collectors.toList());
		} catch (Exception exception) {
			log.error("And error occured while finding all the customers.", exception);
			throw new GenericCustomerApplicationRuntimeException(exception);
		}
	}

	/**
	 * Use this method to save a new customer. However, if a new customer is already existing,
	 * it will update the old customer.
	 * @param customerJsonBody : The API input value object.
	 * @return : The created customer.
	 */
	@PostMapping("/addNewCustomer")
	CustomerJsonBody addNewCustomer(final @RequestBody CustomerJsonBody customerJsonBody) {
		try {
			return buildCustomerJsonBodyFromCustomerTransferObject(service.addNewCustomer(buildCustomerTransferObjectFromCustomerJsonBody(customerJsonBody)));
		} catch (Exception exception) {
			log.error("And error occured while adding the customer.", exception);
			throw new GenericCustomerApplicationRuntimeException(customerJsonBody.getId(), exception);
		}
	}

	/**
	 * Use this method to find a customer by his id.
	 * 
	 * @param id : The ID of the customer
	 * @return : The found customer. If not found, gives a null return.
	 */
	@GetMapping("/findCustomerById/{id}")
	CustomerJsonBody findCustomerById(final @PathVariable Integer id) {
		try {
			CustomerTransferObject customerTransferObject = service.findCustomerById(id);
			
			if ( customerTransferObject == null ) {
				throw new GenericCustomerApplicationRuntimeException(id,  new RuntimeException("Ca not find customer with id - " + id));				
			}
			return buildCustomerJsonBodyFromCustomerTransferObject(service.findCustomerById(id));
		} catch (Exception exception) {
			log.error("And error occured while finding customers by id.", exception);
			throw new GenericCustomerApplicationRuntimeException(id, exception);
		}
	}

	/**
	 * USe this method to get all the customers with matching first or last name.
	 * Please note , if the first name matches, then the last name will not be used.
	 * If there is no customer with the given first name, then only the last name will
	 * be consulted.
	 * 
	 * If the User does not like to give the first name and last name
	 * in the body of the json, he can use the over loaded <code>CustomerController.findCustomerByFirstNameOrLastName</code> method. 
	 * 
	 * @param customerJsonBody : The input Value object.
	 * @return : The found customers. If not found, then null.
	 */
	@GetMapping("/findCustomerByFirstNameOrLastName")
	List<CustomerJsonBody> findCustomerByFirstNameOrLastName(final @RequestBody CustomerJsonBody customerJsonBody) {

		try {
			return Optional
					.ofNullable(service.findCustomerByFirstNameOrLastName(
							buildCustomerTransferObjectFromCustomerJsonBody(customerJsonBody)))
							.map(Collection::stream)
							.orElseGet(Stream::empty)
							.map(MappingUtil::buildCustomerJsonBodyFromCustomerTransferObject)
							.collect(Collectors.toList());
		} catch (Exception exception) {
			log.error("And error occured while finding by first name or last name.", exception);
			throw new GenericCustomerApplicationRuntimeException(customerJsonBody.getId(), exception);
		}
	}		
		
	
	/**
	 * USe this method to get all the customers with matching first or last name.
	 * Please note , if the first name matches, then the last name will not be used.
	 * If there is no customer with the given first name, then only the last name will
	 * be consulted.
	 * 
	 * @param vo : The input Value object.
	 * @param firstName : First Name of the customer. Remember, that it is an exact Search. It is not a 'Like Search'. So, you have
	 * to give the exact search String.
	 * @param lastName : Last Name of the customer. Remember, that it is an exact Search. It is not a 'Like Search'. So, you have
	 * to give the exact search String.
	 * @return : The found customers. If not found, then null.
	 */
	@GetMapping("/findCustomerByFirstNameOrLastName/{firstName}/{lastName}")
	List<CustomerJsonBody> findCustomerByFirstNameOrLastName(final @PathVariable String firstName,
			final @PathVariable String lastName) {

		try {
			return Optional.ofNullable(service.findCustomerByFirstNameOrLastName(firstName, lastName))
					.map(Collection::stream)
					.orElseGet(Stream::empty)
					.map(MappingUtil::buildCustomerJsonBodyFromCustomerTransferObject)
					.collect(Collectors.toList());
		} catch (Exception exception) {
			log.error("And error occured while finding by first name or last name.", exception);
			throw new GenericCustomerApplicationRuntimeException(exception);
		}
	}


	/**
	 * Use this method to update the address of the customer. But if the customer does not already exist,
	 * you will get a null return.
	 * 
	 * @param customerJsonBody : The customer, whose address has to be saved.
	 * @return : The updated customer.
	 */
	@PutMapping("/updateAddress")
	CustomerJsonBody updateAddress(final @RequestBody CustomerJsonBody customerJsonBody) {

		try {
			return buildCustomerJsonBodyFromCustomerTransferObject(service.updateAddress(buildCustomerTransferObjectFromCustomerJsonBody(customerJsonBody)));
		} catch (Exception exception) {
			log.error("And error occured while updating customer address", exception);
			throw new GenericCustomerApplicationRuntimeException(customerJsonBody.getId(), exception);
		}
	}

}
