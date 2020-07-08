package com.rabo.api.service.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@Table(name = "CUSTOMER")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "AGE")
	private int age;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID", referencedColumnName = "id")
	private AddressEntity address;

}
