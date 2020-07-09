package com.rabo.api.jsonbody;

import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@ResponseBody
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AddressJsonBody 
{

	private int id;
	
	private String streetName;
	
	private String city;

	
}
