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
	public Optional<String> findByProperty(String city, String property) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findByCity", Weather.class);
		query.setParameter("pcity", city);
		List<Weather> users = query.getResultList();
		if (!users.isEmpty()) {
			if (property=="Temp")
				return Optional.of(String.valueOf(users.get(0).getTemperature()));
		} else {
			return Optional.empty();
		}
		return Optional.empty();
	}
	
	@Override
	public double findDailyAvg(String city) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findByCity", Weather.class);
		query.setParameter("pcity", city);
		List<Weather> reports = query.getResultList();
		if (!reports.isEmpty()) {
			int i=0,sum = 0;
			while(i<reports.size())
			{
				sum += reports.get(i).getTemperature();
			}
			return sum/(reports.size()*24);
		} else {
			return 0;
		}
	}
	
	@Override
	public double findHourlyAvg(String city) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findByCity", Weather.class);
		query.setParameter("pcity", city);
		List<Weather> reports = query.getResultList();
		if (!reports.isEmpty()) {
			int i=0,sum = 0;
			while(i<reports.size())
			{
				sum += reports.get(i).getTemperature();
			}
			return sum/(reports.size());
		} else {
			return 0;
		}
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