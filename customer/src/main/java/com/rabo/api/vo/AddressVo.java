package com.rabo.api.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddressVo 
{

	private int id;
	
	private String streetName;
	
	private String city;

	
}
