package io.egen.api.repository.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.api.entity.Weather;
import io.egen.api.repository.WeatherRepository;

@Repository
public class WeatherRepositoryImpl implements WeatherRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Weather> findAll() {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findAll", Weather.class);
		return query.getResultList();
	}

	@Override
	public Optional<Weather> findByCity(String city) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findByCity", Weather.class);
		query.setParameter("pcity", city);
		List<Weather> users = query.getResultList();
		if (!users.isEmpty()) {
			return Optional.of(users.get(0));
		} else {
			return Optional.empty();
		}
	}

	
	
	@Override
	public Weather findDailyAvg(String city) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findByCity", Weather.class);
		query.setParameter("pcity", city);
		List<Weather> reports = query.getResultList();
		Weather daily = new Weather();
		daily.setCity(city);
		daily.setDescription("Daily Average Weather");
		if (!reports.isEmpty()) {
			int i=0,sumTemp = 0,sumHum = 0,sumPress = 0;
			while(i<reports.size())
			{
				sumTemp += reports.get(i).getTemperature();
				sumHum += reports.get(i).getHumidity();
				sumPress += reports.get(i).getPressure();
				i++;
			}
			daily.setTemperature(sumTemp/(reports.size()));
			daily.setHumidity(sumHum/(reports.size()));
			daily.setPressure(sumPress/(reports.size()));
		} 
		return daily;
	}
	
	@Override
	public Weather findHourlyAvg(String city) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findByCity", Weather.class);
		query.setParameter("pcity", city);
		List<Weather> reports = query.getResultList();
		Weather daily = new Weather();
		daily.setCity(city);
		daily.setDescription("Hourly Average Weather");
		if (!reports.isEmpty()) {
			int i=0,sumTemp = 0,sumHum = 0,sumPress = 0;
			while(i<reports.size())
			{
				sumTemp += reports.get(i).getTemperature();
				sumHum += reports.get(i).getHumidity();
				sumPress += reports.get(i).getPressure();
				i++;
			}
			daily.setTemperature(sumTemp/(reports.size()));
			daily.setHumidity(sumHum/(reports.size()));
			daily.setPressure(sumPress/(reports.size()));
		} 
		return daily;
	}
	
	@Override
	public Weather create(Weather w) {
		em.persist(w);
		return w;
	}

	@Override
	public Weather update(Weather w) {
		return em.merge(w);
	}

	@Override
	public void delete(Weather w) {
		em.remove(w);
	}
}