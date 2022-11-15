package com.rabo.api.to;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CustomerTransferObject 
{

	private int id;

	private String firstName;
	
	private String lastName;
	
	private int age;
	
	private AddressTransferObject address;

}
