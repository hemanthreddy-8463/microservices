package com.springboot.microservices.currency_conversion_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.microservices.currency_conversion_service.model.CurrenyConversion;


//@FeignClient(name="Currency-Exchange",url="http://localhost:8000")
@FeignClient(name="Currency-Exchange")
public interface CurrencyExchangeProxy {
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	 public CurrenyConversion retriveValues(@PathVariable String from,
			 @PathVariable String to);
}
