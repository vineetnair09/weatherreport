package io.egen.api.controller;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.egen.api.constants.URI;
import io.egen.api.entity.Weather;
import io.egen.api.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = URI.REPORTS)
@Api(tags = "reports")
public class WeatherController {

	private WeatherService service;

	public WeatherController(WeatherService service) {
		this.service = service;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = URI.ALLCITIES)
	@ApiOperation(value = "Find list of cities", notes = "Returns a list of all cities in the report")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public String[] findAllCities() {
		return service.findAllCities();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = URI.PROPERTY)
	@ApiOperation(value = "Find particular property of a latest city weather record", notes = "Returns a property of a city record")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public String findProperty(@RequestParam String city,@RequestParam String property) {
		return service.findProperty(city, property);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = URI.CITY)
	@ApiOperation(value = "Find records by city", notes = "Returns a latest weather record by city if it exists in the app")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public Weather findDaily(@PathVariable("city") String city) {
		return service.findByCity(city);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = URI.DAILY)
	@ApiOperation(value = "Find records by city", notes = "Returns a avg daily weather record by city if it exists in the app")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public Weather findHourly(@PathVariable("city") String city) {
		return service.findDailyAvg(city);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = URI.HOURLY)
	@ApiOperation(value = "Find records by city", notes = "Returns a avg hourly weather record by city if it exists in the app")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public Weather findOne(@PathVariable("city") String city) {
		return service.findHourlyAvg(city);
	}

	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create record for a city", notes = "Creates a weather record for a city")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public Weather create(@RequestBody Weather w) {
		return service.create(w);
	}

}