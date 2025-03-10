package com.springboot.microservices;

import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import org.slf4j.Logger;

import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter{
	
	private Logger logger=LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// TODO Auto-generated method stub
		logger.info("requested api->{}",exchange.getRequest().getPath());
		return chain.filter(exchange);
	}

}
