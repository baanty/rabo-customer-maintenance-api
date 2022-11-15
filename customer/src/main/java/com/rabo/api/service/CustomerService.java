package com.rabo.api.service;

import java.util.List;

import com.rabo.api.to.CustomerTransferObject;

public interface CustomerService {

	/**
	 * Use this method to find all the customers from teh database.
	 * @return
	 */
	List<CustomerTransferObject> findAllCustomers();

	/**
	 * Use this method to save a new customer. However, if a new customer is already existing,
	 * it will update the old customer.
	 * @param vo : The API input value object.
	 * @return : The created customer.
	 */
	CustomerTransferObject addNewCustomer(CustomerTransferObject vo);

	/**
	 * Use this method to find a customer by his id.
	 * 
	 * @param id : The ID of the customer
	 * @return : The found customer. If not found, gives a null return.
	 */
	CustomerTransferObject findCustomerById(int id);

	/**
	 * USe this method to get all the customers with matching first or last name.
	 * Please note , if the first name matches, then the last name will not be used.
	 * If there is no customer with the given first name, then only the last name will
	 * be consulted.
	 * 
	 * @param customerTransferObject : The input Value object.
	 * @return : The found customers. If not found, then null.
	 */
	List<CustomerTransferObject> findCustomerByFirstNameOrLastName(CustomerTransferObject customerTransferObject);

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
	List<CustomerTransferObject> findCustomerByFirstNameOrLastName(String firstName, String lastName);

	/**
	 * Use this method to update the address of the customer. But if the customer does not already exist,
	 * you will get a null return.
	 * 
	 * @param valueObject : The customer, whose address has to be saved.
	 * @return : The updated customer.
	 */
	CustomerTransferObject updateAddress(CustomerTransferObject valueObject);

}