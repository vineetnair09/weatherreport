package io.egen.api.service;

import java.util.List;

import io.egen.api.entity.Weather;

public interface WeatherService {

	public List<Weather> findAll();

	public Weather findOne(String city);
	
	public String findProperty(String city, String property);

	public Weather findDailyAvg(String city);
	
	public Weather findHourlyAvg(String city);

	public Weather create(Weather w);
}