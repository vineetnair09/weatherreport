package io.egen.api.repository;

import java.util.List;
import java.util.Optional;

import io.egen.api.entity.Weather;

public interface WeatherRepository {

	public List<Weather> findAll();

	public Optional<Weather> findByCity(String city);

	public Weather findDailyAvg(String city);
	
	public Weather findHourlyAvg(String city);
	
	public Weather create(Weather user);

	public Weather update(Weather user);

	public void delete(Weather user);
}