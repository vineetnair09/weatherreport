package io.egen.api.repository.impl;

import java.sql.Timestamp;
import java.util.Calendar;
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
	public String[] findAllCities() {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findAll", Weather.class);
		List<Weather> all = query.getResultList();
		String[] cities = new String[all.size()];
		int i = 0;
		for(Weather w: all)
		{
			cities[i] = w.getCity();
			i++;
		}
		return cities;
	}

	@Override
	public Optional<Weather> findByCity(String city) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findByCity", Weather.class);
		query.setParameter("pcity", city);
		List<Weather> reports = query.getResultList();
		if (!reports.isEmpty()) {
			return Optional.of(reports.get(0));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public String findProperty(String city, String property) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findByCity", Weather.class);
		query.setParameter("pcity", city);
		List<Weather> reports = query.getResultList();
		if (!reports.isEmpty()) {
			if(property.equalsIgnoreCase("temperature"))
				return String.valueOf(reports.get(0).getTemperature());
			if(property.equalsIgnoreCase("humidity"))
				return String.valueOf(reports.get(0).getHumidity());
			if(property.equalsIgnoreCase("pressure"))
				return String.valueOf(reports.get(0).getPressure());
		}
		return null;
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
		Weather hourly = new Weather();
		Timestamp t = new Timestamp(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		Timestamp tOld = new Timestamp(cal.getTimeInMillis());
		hourly.setCity(city);
		hourly.setDescription("Hourly Average Weather");
		if (!reports.isEmpty()) {
			int i=0,count =0,sumTemp = 0,sumHum = 0,sumPress = 0;
			while(i<reports.size())
			{
				if (reports.get(i).getTimestamp().before(t) && (reports.get(i).getTimestamp().after(tOld))) 
				{
					sumTemp += reports.get(i).getTemperature();
				    sumHum += reports.get(i).getHumidity();
				    sumPress += reports.get(i).getPressure();
				    count++;
				} 
				i++;
			}
			hourly.setTemperature(sumTemp/(count));
			hourly.setHumidity(sumHum/(count));
			hourly.setPressure(sumPress/(count));
		} 
		return hourly;
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