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

	@Override
	public List<CustomerTransferObject> findAllCustomers() {
		return service.findAllCustomers();
	}

	@Override
	public CustomerTransferObject addNewCustomer(CustomerTransferObject vo) {
		return service.addNewCustomer(vo);
	}

	@Override
	public CustomerTransferObject findCustomerById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerTransferObject> findCustomerByFirstNameOrLastName(
			CustomerTransferObject customerTransferObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerTransferObject> findCustomerByFirstNameOrLastName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerTransferObject updateAddress(CustomerTransferObject vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
