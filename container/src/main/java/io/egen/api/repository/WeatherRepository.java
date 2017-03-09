package io.egen.api.repository;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Max;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import io.egen.api.entity.Weather;

public interface WeatherRepository extends Repository<Weather, String> {

	@Query("SELECT w FROM Weather w GROUP BY w.city order by w.timestamp desc")
	public List<Weather> findAllGroupBycity();

	public Weather findTopBycity(String city);
	
	public List<Weather> findAllBycity(String city);
	
	//public Optional<Weather> findByEmail(String email);

	public Weather save(Weather w); //update and insert

	public void delete(Weather w);
}