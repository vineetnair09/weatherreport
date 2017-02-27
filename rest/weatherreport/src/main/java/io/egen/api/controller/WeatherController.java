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

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Find All records", notes = "Returns a list of all records in the report")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public List<Weather> findAll() {
		return service.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = URI.PROPERTY)
	@ApiOperation(value = "Find records by city, property", notes = "Returns a record by city if it exists in the app")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public String findByProperty(@PathVariable("city") String city,@PathVariable("property") String property){
		//return city+property;
		return service.findByProperty(city, property);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = URI.CITY)
	@ApiOperation(value = "Find records by city", notes = "Returns a record by city if it exists in the app")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public Weather findOne(@PathVariable("city") String city) {
		return service.findByCity(city);
	}

	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create User", notes = "Creates a user in the app with unique email")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public Weather create(@RequestBody Weather w) {
		return service.create(w);
	}

	@RequestMapping(method = RequestMethod.PUT, value = URI.ID)
	@ApiOperation(value = "Update User", notes = "Updates an existing user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public Weather update(@PathVariable("id") String id, @RequestBody Weather w) {
		return service.update(id, w);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = URI.ID)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	@ApiOperation(value = "Delete User", notes = "Deletes an existing user")
	public void delete(@PathVariable("id") String id) {
		service.delete(id);
	}
}