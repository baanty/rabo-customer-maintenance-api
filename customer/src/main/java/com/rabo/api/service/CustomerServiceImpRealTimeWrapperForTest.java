package com.rabo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.rabo.api.dao.CustomerRepository;
import com.rabo.api.to.CustomerTransferObject;

@Service
@Profile({"UNITTEST"})
public class CustomerServiceImpRealTimeWrapperForTest implements CustomerService {
	
	CustomerService service;
	
	@Autowired
	CustomerRepository customerRepository;
	
	

	/**
	 * The repository for testing.
	 * @param customerRepository : <code>CustomerRepository</code>
	 */

	public CustomerServiceImpRealTimeWrapperForTest(@Autowired CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
		this.service = new CustomerServiceImpRealTime(customerRepository);
	}

	
	/**
	 * Use this method to find all the customers from teh database.
	 * @return
	 */
	@Override
	public List<CustomerTransferObject> findAllCustomers() {
		return service.findAllCustomers();
	}

	
	/**
	 * Use this method to save a new customer. However, if a new customer is already existing,
	 * it will update the old customer.
	 * @param vo : The API input value object.
	 * @return : The created customer.
	 */
	@Override
	public CustomerTransferObject addNewCustomer(CustomerTransferObject vo) {
		return service.addNewCustomer(vo);
	}

	
	/**
	 * Use this method to find a customer by his id.
	 * 
	 * @param id : The ID of the customer
	 * @return : The found customer. If not found, gives a null return.
	 */
	@Override
	public CustomerTransferObject findCustomerById(int id) {
		return service.findCustomerById(id);
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
	@Override
	public List<CustomerTransferObject> findCustomerByFirstNameOrLastName(
			CustomerTransferObject customerTransferObject) {
		return service.findCustomerByFirstNameOrLastName(customerTransferObject);
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
	@Override
	public List<CustomerTransferObject> findCustomerByFirstNameOrLastName(String firstName, String lastName) {
		return service.findCustomerByFirstNameOrLastName(firstName, lastName);
	}

	
	/**
	 * Use this method to update the address of the customer. But if the customer does not already exist,
	 * you will get a null return.
	 * 
	 * @param valueObject : The customer, whose address has to be saved.
	 * @return : The updated customer.
	 */
	@Override
	public CustomerTransferObject updateAddress(CustomerTransferObject valueObject) {
		return service.updateAddress(valueObject);
	}

}
