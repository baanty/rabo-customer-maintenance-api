package com.rabo.api.util;

import com.rabo.api.jsonbody.AddressJsonBody;
import com.rabo.api.jsonbody.CustomerJsonBody;
import com.rabo.api.service.entity.AddressEntity;
import com.rabo.api.service.entity.CustomerEntity;
import com.rabo.api.to.AddressTransferObject;
import com.rabo.api.to.CustomerTransferObject;

/**
 * This utility class will do the object to object data 
 * mapping. This class has been designed as a singleton.
 * So, only utility methods. No instance variables.
 * 
 * @author Pijush Kanti Das.
 *
 */
public final class MappingUtil {
	
	/**
	 * The private constructor.
	 */
	private MappingUtil () {
		
	}
	
	/**
	 * Use this method to convert from <code>CustomerTransferObject</code> to
	 * <code>CustomerJsonBody</code>.
	 * 
	 * @param customerTransferObject : The data container <code>CustomerTransferObject</code> Object.
	 * @return : The <code>CustomerJsonBody</code> built object.
	 */
	public static final CustomerJsonBody buildCustomerJsonBodyFromCustomerTransferObject(CustomerTransferObject customerTransferObject) {
		
		AddressTransferObject addressTransferObject = customerTransferObject.getAddress();
		AddressJsonBody addressJsonBody = null;
		
		if ( addressTransferObject != null ) {
			addressJsonBody = AddressJsonBody.builder()
													.id(addressTransferObject.getId())
													.city(addressTransferObject.getCity())
													.streetName(addressTransferObject.getStreetName())
													.build();
		}
		
		CustomerJsonBody customerJsonBody = CustomerJsonBody.builder()
												.id(customerTransferObject.getId())
												.firstName(customerTransferObject.getFirstName())
												.lastName(customerTransferObject.getLastName())
												.age(customerTransferObject.getAge())
												.address(addressJsonBody)
												.build();
		return customerJsonBody;
	}
	
	
	
	
	/**
	 * Use this method to convert from <code>CustomerJsonBody</code> to build the
	 * <code>CustomerTransferObject</code>.
	 * 
	 * @param customerJsonBody : The data container <code>CustomerJsonBody</code> Object.
	 * @return : The <code>CustomerTransferObject</code> built object.
	 */
	public static final CustomerTransferObject buildCustomerTransferObjectFromCustomerJsonBody(CustomerJsonBody customerJsonBody) {
		
		AddressJsonBody addressJsonBody = customerJsonBody.getAddress();
		AddressTransferObject addressTransferObject = null;
		
		if ( addressJsonBody != null ) {
			addressTransferObject = AddressTransferObject.builder()
													.id(addressJsonBody.getId())
													.city(addressJsonBody.getCity())
													.streetName(addressJsonBody.getStreetName())
													.build();
		}
		
		CustomerTransferObject customerTransferObject = CustomerTransferObject.builder()
												.id(customerJsonBody.getId())
												.firstName(customerJsonBody.getFirstName())
												.lastName(customerJsonBody.getLastName())
												.age(customerJsonBody.getAge())
												.address(addressTransferObject)
												.build();
		return customerTransferObject;
	}

	/**
	 * Use this utility method to transform <code>CustomerEntity</code> into <code>CustomerTransferObject</code>
	 * 
	 * @param customerEntity : The input <code>CustomerEntity</code> object.
	 * @return : THe fully built <code>CustomerTransferObject</code> object.
	 */
	public static final CustomerTransferObject buildTransferObjectFromBusinessObject(CustomerEntity customerEntity) {
		
		AddressEntity address = customerEntity.getAddress() != null ? 
					customerEntity.getAddress()
					: new AddressEntity(0, null, null);
		AddressTransferObject addressTransferObject = AddressTransferObject
							.builder()
							.id(address.getId())
							.city(address.getCity())
							.streetName(address.getStreetName())
							.build();
		CustomerTransferObject customerTransferObject = CustomerTransferObject
							.builder()
							.id(customerEntity.getId())
							.firstName(customerEntity.getFirstName())
							.lastName(customerEntity.getLastName())
							.age(customerEntity.getAge())
							.address(addressTransferObject)
							.build();
		return customerTransferObject;
	}
	
	/**
	 * Use this utility method to transform <code>CustomerTransferObject</code> into <code>CustomerEntity</code>
	 * 
	 * @param customerEntity : The input <code>CustomerTransferObject</code> object.
	 * @return : THe fully built <code>CustomerEntity</code> object.
	 */
	public static final CustomerEntity buildBusinessObjectFromTransferObject(CustomerTransferObject customerTransferObject) {
		AddressEntity addressEntity = (customerTransferObject != null && customerTransferObject.getAddress() != null)
										? AddressEntity
												.builder()
												.city(customerTransferObject.getAddress().getCity())
												.streetName(customerTransferObject.getAddress().getStreetName()).build()
												: new AddressEntity(0, null, null);
		CustomerEntity customerEntity = CustomerEntity
											.builder()
											.firstName(customerTransferObject.getFirstName())
											.lastName(customerTransferObject.getLastName())
											.age(customerTransferObject.getAge())
											.address(addressEntity)
											.build();
		return customerEntity;
	}
}
