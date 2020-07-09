package com.rabo.api.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import com.rabo.api.jsonbody.AddressJsonBody;
import com.rabo.api.jsonbody.CustomerJsonBody;
import com.rabo.api.to.AddressTransferObject;
import com.rabo.api.to.CustomerTransferObject;

class MappingUtilTest {

	@Test
	void testBuildCustomerJsonBodyFromCustomerTransferObject() {
		AddressTransferObject newAddressTransferObject = AddressTransferObject.builder()
				.streetName("Lake Washington Boulevard  East").city("Seattle").build();
		CustomerTransferObject newCustomerJsonBody = CustomerTransferObject.builder().id(12).firstName("Kurt")
				.lastName("Cobain").age(27).address(newAddressTransferObject).build();
		CustomerJsonBody customerJsonBody = MappingUtil
				.buildCustomerJsonBodyFromCustomerTransferObject(newCustomerJsonBody);
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

}
