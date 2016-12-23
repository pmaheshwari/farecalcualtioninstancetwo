package com.impetus.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.impetus.model.MetroDetail;
import com.impetus.service.FareCalculationService;

@RestController
public class FareCalculationController {

	@Autowired
	private FareCalculationService fareCalcService;

	private Map<String, Double> cacheMap;
	
	private static final Logger log = LoggerFactory.getLogger(FareCalculationController.class);
	

	public FareCalculationController() {
		HazelcastInstance hazelInstance = Hazelcast.newHazelcastInstance();
		cacheMap = hazelInstance.getMap("fareMap");
	}

	@RequestMapping(value = "/calcFare/{source}/{destination}", produces = "application/json")
	public @ResponseBody MetroDetail calculateFare(MetroDetail metroDetail,
			@PathVariable(value = "source") String from,
			@PathVariable(value = "destination") String to) {
		String source = from;
		String destination = to;
		if (source != null && destination != null) {
			String hazelKey = source + "_" + destination;
			Double cachedFare = cacheMap.get(hazelKey);
			if (cachedFare != null) {
				log.info("--Result already exists in cache--");
				metroDetail.setFare(cachedFare);
				metroDetail.setInCache("true");
			}
			else {
				log.info("--Calculating metro fare--");
				fareCalcService.calculate(metroDetail, cacheMap);
			}
			metroDetail.setInstance("Instance 2");
		}
		return metroDetail;
	}
	
}