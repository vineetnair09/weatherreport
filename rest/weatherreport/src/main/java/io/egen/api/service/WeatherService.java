package io.egen.api.service;

import java.util.List;

import io.egen.api.entity.Weather;

public interface WeatherService {

	public List<Weather> findAll();

	public String[] findAllCities();
	
	public String findProperty(String city, String property);
	
	public Weather findByCity(String city);
	
	public Weather findHourlyAvg(String city);
	
	public Weather findDailyAvg(String city);
	
	public Weather create(Weather report);

	public Weather update(String id, Weather report);

	public void delete(String id);
}