package com.rabo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rabo.api.service.CustomerService;
import com.rabo.api.vo.CustomerVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CustomerController {

	@Autowired
	CustomerService service;

	/**
	 * Use this method to find all the customers from the service.
	 * @return
	 */
	@GetMapping("/findAllCustomers")
	List<CustomerVo> findAllCustomers() {

		try {
			return service.findAllCustomers();
		} catch (Exception exception) {
			log.error("And error occured while finding all the customers.", exception);
			throw new RuntimeException(exception);
		}
	}

	/**
	 * Use this method to save a new customer. However, if a new customer is already existing,
	 * it will update the old customer.
	 * @param vo : The API input value object.
	 * @return : The created customer.
	 */
	@PostMapping("/addNewCustomer")
	CustomerVo addNewCustomer(final @RequestBody CustomerVo vo) {
		try {
			return service.addNewCustomer(vo);
		} catch (Exception exception) {
			log.error("And error occured while adding the customer.", exception);
			throw new RuntimeException(exception);
		}
	}

	/**
	 * Use this method to find a customer by his id.
	 * 
	 * @param id : The ID of the customer
	 * @return : The found customer. If not found, gives a null return.
	 */
	@GetMapping("/findCustomerById/{id}")
	CustomerVo findCustomerById(final @PathVariable Integer id) {
		try {
			return service.findCustomerById(id);
		} catch (Exception exception) {
			log.error("And error occured while finding customers by id.", exception);
			throw new RuntimeException(exception);
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
	 * @param customerVo : The input Value object.
	 * @return : The found customers. If not found, then null.
	 */
	@GetMapping("/findCustomerByFirstNameOrLastName")
	List<CustomerVo> findCustomerByFirstNameOrLastName(final @RequestBody CustomerVo customerVo) {

		try {
			return service.findCustomerByFirstNameOrLastName(customerVo);
		} catch (Exception exception) {
			log.error("And error occured while finding by first name or last name.", exception);
			throw new RuntimeException(exception);
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
	List<CustomerVo> findCustomerByFirstNameOrLastName(final @PathVariable String firstName, final @PathVariable String lastName) {

		try {
			return service.findCustomerByFirstNameOrLastName(firstName, lastName);
		} catch (Exception exception) {
			log.error("And error occured while finding by first name or last name.", exception);
			throw new RuntimeException(exception);
		}
	}


	/**
	 * Use this method to update the address of the customer. But if the customer does not already exist,
	 * you will get a null return.
	 * 
	 * @param vo : The customer, whose address has to be saved.
	 * @return : The updated customer.
	 */
	@PutMapping("/updateAddress")
	CustomerVo updateAddress(final @RequestBody CustomerVo vo) {

		try {
			return service.updateAddress(vo);
		} catch (Exception exception) {
			log.error("And error occured while updating customer address", exception);
			throw new RuntimeException(exception);
		}
	}

}
