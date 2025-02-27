package com.springboot.microservices.currency_conversion_service.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springboot.microservices.currency_conversion_service.CurrencyExchangeProxy;
import com.springboot.microservices.currency_conversion_service.model.CurrenyConversion;

@RestController
public class CurrencyConversionController {
	@Autowired
	private CurrencyExchangeProxy proxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrenyConversion calculate(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			) {
		HashMap<String,String> urlVaraibales=new HashMap<>();
		urlVaraibales.put("from",from);
		urlVaraibales.put("to", to);
		
		ResponseEntity<CurrenyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",CurrenyConversion.class, urlVaraibales);
		CurrenyConversion  currencyConversion= responseEntity.getBody();
		return new CurrenyConversion(currencyConversion.getId(),currencyConversion.getFrom(),currencyConversion.getTo()
				,quantity,currencyConversion.getConversionMultiple(),
				quantity.multiply(currencyConversion.getConversionMultiple()),
				currencyConversion.getEnvironment());
	}
		
		@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
		public CurrenyConversion calculateCurrencyConversionFeign(
				@PathVariable String from,
				@PathVariable String to,
				@PathVariable BigDecimal quantity
				) {
			
		CurrenyConversion currencyConversion = proxy.retriveValues(from, to);
			return new CurrenyConversion(currencyConversion.getId(),currencyConversion.getFrom(),currencyConversion.getTo()
					,quantity,currencyConversion.getConversionMultiple(),
					quantity.multiply(currencyConversion.getConversionMultiple()),
					currencyConversion.getEnvironment()+" "+"feign");
		
	}

}
