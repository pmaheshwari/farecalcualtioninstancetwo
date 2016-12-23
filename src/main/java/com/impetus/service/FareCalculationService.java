package com.impetus.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.impetus.model.MetroDetail;
import com.impetus.model.StationsEnum;

@Service
public class FareCalculationService {
	
	public void calculate(MetroDetail metroDetail, Map<String, Double> cacheMap)
	{
		String source = metroDetail.getSource();
		String destination = metroDetail.getDestination();
		double basePrice = 8.0;
		if (source.equals(destination)) {
			metroDetail.setFare(basePrice);
		} else {
			int diff = Math.abs(StationsEnum.getOrder(source).getValue()
					- StationsEnum.getOrder(destination).getValue());
			double newPrice = basePrice + (diff * 2);
			metroDetail.setFare(newPrice);
		}
		cacheMap.put(source + "_" + destination, metroDetail.getFare());
		metroDetail.setInCache("false");
	}

}
