package com.rabo.api.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.rabo.api.exception.GenericCustomerApplicationRuntimeException;
import com.rabo.api.jsonbody.AddressJsonBody;
import com.rabo.api.jsonbody.CustomerJsonBody;


@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerControllerTest {

	@Autowired
	CustomerController controller;
	
	@Test
	public void testFindAll() {
		List<CustomerJsonBody> allCustomers = controller.findAllCustomers();
		assertNotNull(allCustomers);
		assertEquals(3, allCustomers.size());
		CustomerJsonBody trOne = allCustomers.get(0);
		CustomerJsonBody trTwo = allCustomers.get(1);
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
		AddressJsonBody newAddressTransferObject = 
				AddressJsonBody.builder()
									.streetName("Lake Washington Boulevard  East")
									.city("Seattle")
									.build();
		CustomerJsonBody newCustomerJsonBody =
				CustomerJsonBody.builder()
									.id(12)
									.firstName("Kurt")
									.lastName("Cobain")
									.age(27)
									.address(newAddressTransferObject)
									.build();
		controller.addNewCustomer(newCustomerJsonBody);
		
		List<CustomerJsonBody> allCustomers = controller.findAllCustomers();
		assertNotNull(allCustomers);
		assertEquals(4, allCustomers.size());
		CustomerJsonBody newCustomer = allCustomers.get(3);
		assertNotNull(newCustomer);
		assertEquals(8, newCustomer.getId());
		assertEquals("Kurt", newCustomer.getFirstName());
		assertEquals("Cobain", newCustomer.getLastName());
		
		AddressJsonBody newAddress = newCustomer.getAddress();
		assertNotNull(newAddress);
		assertEquals("Lake Washington Boulevard  East", newAddress.getStreetName());
		assertEquals("Seattle", newAddress.getCity());
		
		
	}
	
	
	
	@Test
	public void testCustomerById_When_Found() {
		CustomerJsonBody customer = controller.findCustomerById(1);
		assertNotNull(customer);
		assertEquals(1, customer.getId());
		assertEquals("Pijush K", customer.getFirstName());
		assertEquals("Das", customer.getLastName());
	}
	
	
	@Test
	public void testCustomerById_When_Not_Found() {
		Assertions.assertThrows( 
				GenericCustomerApplicationRuntimeException.class, () 
					-> controller.findCustomerById(4));
	}
	
	
	@Test
	public void testFindCustomerByFirstName_WhenFound_by_To() {
		CustomerJsonBody toToSearch = CustomerJsonBody
												.builder()
												.firstName("Pijush K")
												.build();
		List<CustomerJsonBody> allCustomers = controller.findCustomerByFirstNameOrLastName(toToSearch);
		assertNotNull(allCustomers);
		assertEquals(1, allCustomers.size());
		CustomerJsonBody trOne = allCustomers.get(0);
		assertNotNull(trOne);
		assertEquals(1, trOne.getId());
		assertEquals("Pijush K", trOne.getFirstName());
		assertEquals("Das", trOne.getLastName());
	}
	
	
	@Test
	public void testFindCustomerByLastName_WhenFound_by_To() {
		CustomerJsonBody toToSearch = CustomerJsonBody
												.builder()
												.lastName("Das")
												.build();
		List<CustomerJsonBody> allCustomers = controller.findCustomerByFirstNameOrLastName(toToSearch);
		assertNotNull(allCustomers);
		assertEquals(1, allCustomers.size());
		CustomerJsonBody trOne = allCustomers.get(0);
		assertNotNull(trOne);
		assertEquals(1, trOne.getId());
		assertEquals("Pijush K", trOne.getFirstName());
		assertEquals("Das", trOne.getLastName());
	}
	
	
	@Test
	public void testFindCustomerByFIrstNameOrLastName_When_Not_Found_by_To() {
		CustomerJsonBody toToSearch = CustomerJsonBody
												.builder()
												.firstName("Curt")
												.lastName("Kobain")
												.build();
		List<CustomerJsonBody> allCustomers = controller.findCustomerByFirstNameOrLastName(toToSearch);
		assertNotNull(allCustomers);
		assertEquals(0, allCustomers.size());
	}
	
	@Test
	public void testFindCustomerByFirstName_WhenFound_by_property() {
		List<CustomerJsonBody> allCustomers = controller.findCustomerByFirstNameOrLastName("Pijush K", null);
		assertNotNull(allCustomers);
		assertEquals(1, allCustomers.size());
		CustomerJsonBody trOne = allCustomers.get(0);
		assertNotNull(trOne);
		assertEquals(1, trOne.getId());
		assertEquals("Pijush K", trOne.getFirstName());
		assertEquals("Das", trOne.getLastName());
	}
	
	
	@Test
	public void testFindCustomerByLastName_WhenFound_by_property() {
		List<CustomerJsonBody> allCustomers = controller.findCustomerByFirstNameOrLastName(null, "Das");
		assertNotNull(allCustomers);
		assertEquals(1, allCustomers.size());
		CustomerJsonBody trOne = allCustomers.get(0);
		assertNotNull(trOne);
		assertEquals(1, trOne.getId());
		assertEquals("Pijush K", trOne.getFirstName());
		assertEquals("Das", trOne.getLastName());
	}
	
	
	@Test
	public void testFindCustomerByFIrstNameOrLastName_When_Not_Found_by_property() {
		List<CustomerJsonBody> allCustomers = controller.findCustomerByFirstNameOrLastName("Curt", "Kobain");
		assertNotNull(allCustomers);
		assertEquals(0, allCustomers.size());
	}
	
	@Test
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	public void testUpdateAddress() {
		
		AddressJsonBody newAddressJsonBody = 
				AddressJsonBody.builder()
									.streetName("Meteorstraat")
									.city("Hilversum")
									.build();
		CustomerJsonBody newCustomerJsonBody =
				CustomerJsonBody.builder()
									.id(1)
									.firstName("Pijush")
									.lastName("Cobain")
									.age(27)
									.address(newAddressJsonBody)
									.build();
		controller.updateAddress(newCustomerJsonBody);
		
		CustomerJsonBody customer = controller.findCustomerById(1);
		assertNotNull(customer);
		assertEquals(1, customer.getId());
		assertNotNull(customer.getAddress());
		assertEquals("Meteorstraat", customer.getAddress().getStreetName());
	}
	
	
}
