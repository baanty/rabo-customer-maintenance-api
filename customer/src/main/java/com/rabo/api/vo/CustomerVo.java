package com.rabo.api.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerVo 
{

	private int id;

	private String firstName;
	
	private String lastName;
	
	private int age;
	
	private AddressVo address;

}
