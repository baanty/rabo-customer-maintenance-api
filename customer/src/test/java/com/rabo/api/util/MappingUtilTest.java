package com.rabo.api.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import com.rabo.api.jsonbody.AddressJsonBody;
import com.rabo.api.jsonbody.CustomerJsonBody;
import com.rabo.api.service.entity.AddressEntity;
import com.rabo.api.service.entity.CustomerEntity;
import com.rabo.api.to.AddressTransferObject;
import com.rabo.api.to.CustomerTransferObject;

class MappingUtilTest {

	@Test
	void testBuildCustomerJsonBodyFromCustomerTransferObject() {
		AddressTransferObject newAddressTransferObject = AddressTransferObject.builder()
				.streetName("Lake Washington Boulevard  East").city("Seattle").build();
		CustomerTransferObject customerTransferObject = CustomerTransferObject.builder().id(12).firstName("Kurt")
				.lastName("Cobain").age(27).address(newAddressTransferObject).build();
		CustomerJsonBody customerJsonBody = MappingUtil
				.buildCustomerJsonBodyFromCustomerTransferObject(customerTransferObject);
		assertNotNull(customerJsonBody);
		assertNotNull(customerJsonBody.getAddress());
		assertEquals("Kurt", customerJsonBody.getFirstName());
		assertEquals("Cobain", customerJsonBody.getLastName());
		assertEquals("Lake Washington Boulevard  East", customerJsonBody.getAddress().getStreetName());
		assertEquals("Seattle", customerJsonBody.getAddress().getCity());
	}

	@Test
	void testBuildCustomerTransferObjectFromCustomerJsonBody() {
		AddressJsonBody addressJsonBody = AddressJsonBody.builder().streetName("Lake Washington Boulevard  East")
				.city("Seattle").build();
		CustomerJsonBody newCustomerJsonBody = CustomerJsonBody.builder().id(12).firstName("Kurt").lastName("Cobain")
				.age(27).address(addressJsonBody).build();
		CustomerTransferObject customerTransferObject = MappingUtil
				.buildCustomerTransferObjectFromCustomerJsonBody(newCustomerJsonBody);
		assertNotNull(customerTransferObject);
		assertNotNull(customerTransferObject.getAddress());
		assertEquals("Kurt", customerTransferObject.getFirstName());
		assertEquals("Cobain", customerTransferObject.getLastName());
		assertEquals("Lake Washington Boulevard  East", customerTransferObject.getAddress().getStreetName());
		assertEquals("Seattle", customerTransferObject.getAddress().getCity());
	}
	
	@Test
	void testTransferObjectToEntityMapping() {

		AddressTransferObject newAddressTransferObject = AddressTransferObject.builder()
				.streetName("Lake Washington Boulevard  East").city("Seattle").build();
		CustomerTransferObject customerTransferObject = CustomerTransferObject.builder().id(12).firstName("Kurt")
				.lastName("Cobain").age(27).address(newAddressTransferObject).build();
		CustomerEntity businessObject = MappingUtil
				.buildBusinessObjectFromTransferObject(customerTransferObject);
		assertNotNull(businessObject);
		assertNotNull(businessObject.getAddress());
		assertEquals("Kurt", businessObject.getFirstName());
		assertEquals("Cobain", businessObject.getLastName());
		assertEquals("Lake Washington Boulevard  East", businessObject.getAddress().getStreetName());
		assertEquals("Seattle", businessObject.getAddress().getCity());
	
	}
	
	@Test
	void testoEntityToTransferObjectMapping() {
		AddressEntity addressBusinessObject = AddressEntity.builder().streetName("Lake Washington Boulevard  East")
				.city("Seattle").build();
		CustomerEntity customerBusinessObject = CustomerEntity.builder().id(12).firstName("Kurt").lastName("Cobain")
				.age(27).address(addressBusinessObject).build();
		CustomerTransferObject customerTransferObject = MappingUtil
				.buildTransferObjectFromBusinessObject(customerBusinessObject);
		assertNotNull(customerTransferObject);
		assertNotNull(customerTransferObject.getAddress());
		assertEquals("Kurt", customerTransferObject.getFirstName());
		assertEquals("Cobain", customerTransferObject.getLastName());
		assertEquals("Lake Washington Boulevard  East", customerTransferObject.getAddress().getStreetName());
		assertEquals("Seattle", customerTransferObject.getAddress().getCity());
	}

}
