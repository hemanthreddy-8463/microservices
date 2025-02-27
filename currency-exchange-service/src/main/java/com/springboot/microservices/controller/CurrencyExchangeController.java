package com.springboot.microservices.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.microservices.model.CurrencyExchange;
import com.springboot.microservices.repository.CurrencyExchangeRepository;


@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	 public CurrencyExchange retriveValues(@PathVariable String from,
			 @PathVariable String to) {
		CurrencyExchange currencyExchange =repository.findByFromAndTo(from, to);
//				CurrencyExchange currencyExchange = new CurrencyExchange(100L,from,to,BigDecimal.valueOf(50));
		if(currencyExchange==null) {
			throw new RuntimeException("unable to find the data for"+from+"to"+to);
		}
				String property = environment.getProperty("local.server.port");
				currencyExchange.setEnvironment(property);
				
				
				return currencyExchange;
		 
	 }
}
