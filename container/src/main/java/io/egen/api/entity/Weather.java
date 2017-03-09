package io.egen.api.entity;

import java.util.UUID;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
//@NamedQueries({
//	@NamedQuery(name="Weather.findAll", query="SELECT w FROM Weather w GROUP BY w.city order by w.timestamp desc"),
//	@NamedQuery(name="Weather.findByCity", query="SELECT w FROM Weather w where w.city=:pcity order by w.timestamp desc"),
//})
public class Weather {

	@Id
	private String id;
	private int humidity;
	private int temperature;

	private Timestamp timestamp;
	private String city;
	private int pressure;
	private String description;
	
	public Weather() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getPressure() {
		return pressure;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}
