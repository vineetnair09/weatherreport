package io.egen.api.repository;

import java.util.List;
import java.util.Optional;

import io.egen.api.entity.Weather;

public interface WeatherRepository {

	public List<Weather> findAll();

	public String[] findAllCities();
	
	public String findProperty(String city, String weather);
	
	public Optional<Weather> findByCity(String city);

	public Weather findDailyAvg(String city);
	
	public Weather findHourlyAvg(String city);
	
	public Weather create(Weather report);

	public Weather update(Weather report);

	public void delete(Weather report);
}