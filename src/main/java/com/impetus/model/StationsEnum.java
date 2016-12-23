package com.impetus.model;

public enum StationsEnum {

	dilshadgarden("Dilshad Garden", 1), jhilmil("Jhilmil", 2), shahdara("Shahdara", 3), welcome("Welcome", 4);

	private String station;
	private int value;

	public int getValue() {
		return value;
	}

	public String getStation() {
		return station;
	}

	StationsEnum(String station, int value) {
		this.station = station;
		this.value = value;
	}

	public static StationsEnum getOrder(String station) {
		for (StationsEnum stationsEnum : StationsEnum.values()) {
			if (station.equals(stationsEnum.getStation())) {
				return stationsEnum;
			}
		}
		return null;
	}

}
