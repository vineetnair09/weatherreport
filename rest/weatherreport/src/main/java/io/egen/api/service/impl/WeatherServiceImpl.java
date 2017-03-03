package io.egen.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.api.entity.Weather;
import io.egen.api.exception.BadRequestException;
import io.egen.api.exception.NotFoundException;
import io.egen.api.repository.WeatherRepository;
import io.egen.api.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {

	private WeatherRepository repository;

	public WeatherServiceImpl(WeatherRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Weather> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Weather findByCity(String city) {
		return repository.findByCity(city)
				.orElseThrow(() -> new NotFoundException("Record with " + city + " does not exist"));
	}

	@Override
	@Transactional
	public Weather create(Weather w) {
		return repository.create(w);
	}

	@Override
	@Transactional
	public Weather update(String city, Weather w) {
		repository.findByCity(city).orElseThrow(() -> new NotFoundException("Record of " + city + " does not exist"));
		return repository.update(w);
	}

	@Override
	@Transactional
	public void delete(String city) {
		Weather existing = repository.findByCity(city)
				.orElseThrow(() -> new NotFoundException("Record with " + city + " does not exist"));
		repository.delete(existing);
	}

	@Override
	@Transactional
	public Weather findHourlyAvg(String city) {
		// TODO Auto-generated method stub
		return repository.findHourlyAvg(city);
	}
	
	@Override
	@Transactional
	public Weather findDailyAvg(String city) {
		// TODO Auto-generated method stub
		return repository.findDailyAvg(city);
	}

	@Override
	@Transactional
	public String[] findAllCities() {
		// TODO Auto-generated method stub
		return repository.findAllCities();
	}
	
	@Override
	@Transactional
	public String findProperty(String city, String property) {
		// TODO Auto-generated method stub
		return repository.findProperty(city, property);
	}

}