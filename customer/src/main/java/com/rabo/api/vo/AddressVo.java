package com.rabo.api.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AddressVo 
{

	private int id;
	
	private String streetName;
	
	private String city;

	
}
