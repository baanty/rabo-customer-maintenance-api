package com.rabo.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.rabo.api.to.AddressTransferObject;
import com.rabo.api.to.CustomerTransferObject;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

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
	
	
	
	@Test
	public void testCustomerById_When_Found() {
		CustomerTransferObject customer = service.findCustomerById(1);
		assertNotNull(customer);
		assertEquals(1, customer.getId());
		assertEquals("Pijush K", customer.getFirstName());
		assertEquals("Das", customer.getLastName());
	}
	
	
	@Test
	public void testCustomerById_When_Not_Found() {
		CustomerTransferObject customer = service.findCustomerById(4);
		assertNull(customer);
	}
	
	
	@Test
	public void testFindCustomerByFirstName_WhenFound_by_To() {
		CustomerTransferObject toToSearch = CustomerTransferObject
												.builder()
												.firstName("Pijush K")
												.build();
		List<CustomerTransferObject> allCustomers = service.findCustomerByFirstNameOrLastName(toToSearch);
		assertNotNull(allCustomers);
		assertEquals(1, allCustomers.size());
		CustomerTransferObject trOne = allCustomers.get(0);
		assertNotNull(trOne);
		assertEquals(1, trOne.getId());
		assertEquals("Pijush K", trOne.getFirstName());
		assertEquals("Das", trOne.getLastName());
	}
	
	
	@Test
	public void testFindCustomerByLastName_WhenFound_by_To() {
		CustomerTransferObject toToSearch = CustomerTransferObject
												.builder()
												.lastName("Das")
												.build();
		List<CustomerTransferObject> allCustomers = service.findCustomerByFirstNameOrLastName(toToSearch);
		assertNotNull(allCustomers);
		assertEquals(1, allCustomers.size());
		CustomerTransferObject trOne = allCustomers.get(0);
		assertNotNull(trOne);
		assertEquals(1, trOne.getId());
		assertEquals("Pijush K", trOne.getFirstName());
		assertEquals("Das", trOne.getLastName());
	}
	
	
	@Test
	public void testFindCustomerByFIrstNameOrLastName_When_Not_Found_by_To() {
		CustomerTransferObject toToSearch = CustomerTransferObject
												.builder()
												.firstName("Curt")
												.lastName("Kobain")
												.build();
		List<CustomerTransferObject> allCustomers = service.findCustomerByFirstNameOrLastName(toToSearch);
		assertNull(allCustomers);
	}
	
	@Test
	public void testFindCustomerByFirstName_WhenFound_by_property() {
		List<CustomerTransferObject> allCustomers = service.findCustomerByFirstNameOrLastName("Pijush K", null);
		assertNotNull(allCustomers);
		assertEquals(1, allCustomers.size());
		CustomerTransferObject trOne = allCustomers.get(0);
		assertNotNull(trOne);
		assertEquals(1, trOne.getId());
		assertEquals("Pijush K", trOne.getFirstName());
		assertEquals("Das", trOne.getLastName());
	}
	
	
	@Test
	public void testFindCustomerByLastName_WhenFound_by_property() {
		List<CustomerTransferObject> allCustomers = service.findCustomerByFirstNameOrLastName(null, "Das");
		assertNotNull(allCustomers);
		assertEquals(1, allCustomers.size());
		CustomerTransferObject trOne = allCustomers.get(0);
		assertNotNull(trOne);
		assertEquals(1, trOne.getId());
		assertEquals("Pijush K", trOne.getFirstName());
		assertEquals("Das", trOne.getLastName());
	}
	
	
	@Test
	public void testFindCustomerByFIrstNameOrLastName_When_Not_Found_by_property() {
		List<CustomerTransferObject> allCustomers = service.findCustomerByFirstNameOrLastName("Curt", "Kobain");
		assertNull(allCustomers);
	}
	
	@Test
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	public void testUpdateAddress() {
		
		AddressTransferObject newAddressTransferObject = 
				AddressTransferObject.builder()
									.streetName("Meteorstraat")
									.city("Hilversum")
									.build();
		CustomerTransferObject newCustomerTransferObject =
				CustomerTransferObject.builder()
									.id(1)
									.firstName("Pijush")
									.lastName("Cobain")
									.age(27)
									.address(newAddressTransferObject)
									.build();
		service.updateAddress(newCustomerTransferObject);
		
		CustomerTransferObject customer = service.findCustomerById(1);
		assertNotNull(customer);
		assertEquals(1, customer.getId());
		assertNotNull(customer.getAddress());
		assertEquals("Meteorstraat", customer.getAddress().getStreetName());
	}
	
	
}
