package io.egen.api.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
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
		return repository.findAllGroupBycity();
	}

	@Override
	@Transactional(readOnly = true)
	public Weather findOne(String city) {
		return repository.findTopBycity(city);
	}

	@Override
	@Transactional
	public Weather create(Weather w) {
		// TODO Auto-generated method stub
		return repository.save(w);
	}

	@Override
	@Transactional(readOnly = true)
	public Weather findDailyAvg(String city) {
		Weather daily = new Weather();
		List<Weather> reports = repository.findAllBycity(city);
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
	@Transactional(readOnly = true)
	public Weather findHourlyAvg(String city) {
		Weather hourly = new Weather();
		List<Weather> reports = repository.findAllBycity(city);
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
	public String findProperty(String city, String property) {
		// TODO Auto-generated method stub
		if(property.equalsIgnoreCase("temperature"))
			return String.valueOf(repository.findTopBycity(city).getTemperature());
		else if(property.equalsIgnoreCase("humidity"))
			return String.valueOf(repository.findTopBycity(city).getHumidity());
		else 
			return String.valueOf(repository.findTopBycity(city).getPressure());
	}
}