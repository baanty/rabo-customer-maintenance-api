package com.rabo.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.airticket.dao.AirportRepository;
import com.cts.airticket.entity.AirportEntity;
import com.cts.airticket.util.ApiUtil;
import com.cts.airticket.vo.AirportVo;

@RestController
public class CustomerController {

	@Autowired
	AirportRepository service;
	
	@RequestMapping("/findAll")
	List<AirportVo> findAllAirports() {
		List<AirportEntity> airportEntities = new ArrayList<AirportEntity>();
		service.findAll().forEach(airportEntities::add);
		return airportEntities.stream().map(ApiUtil::converFromEntityToVo)
				.collect(Collectors.toList());
	}
	

	@RequestMapping("/findByCode/{airportCode}")
	List<AirportVo> findByAirportCodeContaining(@PathVariable String airportCode) {
		List<AirportEntity> airportEntities = service.findByAirportCodeContaining(airportCode);

		if (!CollectionUtils.isEmpty(airportEntities)) {
			List<AirportVo> vos =  airportEntities
					.stream()
					.map(ApiUtil::converFromEntityToVo)
					.collect(Collectors.toList());
			return vos;
					
		}

		return null;
	}

	@RequestMapping("/findByDescription/{airportDescription}")
	List<AirportVo> findByAirportDescriptionContaining(@PathVariable String airportDescription) {
		List<AirportEntity> airportEntities = service.findByAirportDescriptionContaining(airportDescription);

		if (!CollectionUtils.isEmpty(airportEntities)) {

			List<AirportVo> vos = airportEntities
					.stream()
					.map(ApiUtil::converFromEntityToVo)
					.collect(Collectors.toList());
					
			return vos;
		}

		return null;
	}
}
