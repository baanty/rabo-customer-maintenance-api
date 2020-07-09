package com.rabo.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.rabo.api.dao.CustomerRepository;
import com.rabo.api.to.AddressTransferObject;
import com.rabo.api.to.CustomerTransferObject;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	CustomerRepository testRepository;
	
	@Autowired
	CustomerService service;
	
	@Test
	public void testFindAll() {
		List<CustomerTransferObject> allCustomers = service.findAllCustomers();
		assertNotNull(allCustomers);
		assertEquals(3, allCustomers.size());
		CustomerTransferObject trOne = allCustomers.get(0);
		CustomerTransferObject trTwo = allCustomers.get(1);
		assertNotNull(trOne);
		assertNotNull(trTwo);
		assertEquals(1, trOne.getId());
		assertEquals("Pijush K", trOne.getFirstName());
		assertEquals("Das", trOne.getLastName());

		assertEquals(2, trTwo.getId());
		assertEquals("Mijndert", trTwo.getFirstName());
		assertEquals("Rebel", trTwo.getLastName());

		
	}
	
	
	@Test
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	public void testAddNewCustomer() {
		AddressTransferObject newAddressTransferObject = 
				AddressTransferObject.builder()
									.streetName("Lake Washington Boulevard  East")
									.city("Seattle")
									.build();
		CustomerTransferObject newCustomerTransferObject =
				CustomerTransferObject.builder()
									.id(12)
									.firstName("Kurt")
									.lastName("Cobain")
									.age(27)
									.address(newAddressTransferObject)
									.build();
		service.addNewCustomer(newCustomerTransferObject);
		
		List<CustomerTransferObject> allCustomers = service.findAllCustomers();
		assertNotNull(allCustomers);
		assertEquals(4, allCustomers.size());
		CustomerTransferObject newCustomer = allCustomers.get(3);
		assertNotNull(newCustomer);
		assertEquals(8, newCustomer.getId());
		assertEquals("Kurt", newCustomer.getFirstName());
		assertEquals("Cobain", newCustomer.getLastName());
		
		AddressTransferObject newAddress = newCustomer.getAddress();
		assertNotNull(newAddress);
		assertEquals("Lake Washington Boulevard  East", newAddress.getStreetName());
		assertEquals("Seattle", newAddress.getCity());
		
		
	}
	
	
}
