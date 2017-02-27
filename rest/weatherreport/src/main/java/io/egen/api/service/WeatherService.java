package io.egen.api.service;

import java.util.List;

import io.egen.api.entity.Weather;

public interface WeatherService {

	public List<Weather> findAll();

	public Weather findByCity(String city);
	
	public String findByProperty(String city, String property);

	public Weather create(Weather user);

	public Weather update(String id, Weather user);

	public void delete(String id);
}