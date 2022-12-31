package com.example.RestApi.Filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class filteringController {
	
	
	@GetMapping("/filter") //field2
	public MappingJacksonValue FilterMethod() {
		Somebean somebean = new Somebean("value1" ,"value2","value3");
		MappingJacksonValue mappingJacksonvalue = new MappingJacksonValue(somebean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );
		mappingJacksonvalue.setFilters(filters );
		return mappingJacksonvalue;
	}
	
	@GetMapping("/filter-list") //field3
	public MappingJacksonValue FilterMethodList() {
		List<Somebean> list = Arrays.asList(new Somebean("value1" ,"value2","value3"),
				new Somebean("value4" ,"value5","value6"));
		MappingJacksonValue mappingJacksonvalue = new MappingJacksonValue(list);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );
		mappingJacksonvalue.setFilters(filters );
		return mappingJacksonvalue;
	}

}
