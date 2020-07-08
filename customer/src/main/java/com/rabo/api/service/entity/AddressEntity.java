package com.rabo.api.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity
@Table(name = "ADDRESS" )
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AddressEntity 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "STREET_NAME")
	private String streetName;
	
	@Column(name = "CITY")
	private String city;
	
    @OneToOne(mappedBy = "address")
    private CustomerEntity customerEntity;

}
