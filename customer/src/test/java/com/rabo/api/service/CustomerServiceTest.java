package com.rabo.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rabo.api.to.CustomerTransferObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

	CustomerService service;
	
	@Test
	public void testFindAll() {
		List<CustomerTransferObject> allCustomers = service.findAllCustomers();
		assertNotNull(allCustomers);
		assertEquals(2, allCustomers.size());
	}
}
