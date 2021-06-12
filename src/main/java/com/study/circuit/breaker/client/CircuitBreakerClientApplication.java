package com.study.circuit.breaker.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class CircuitBreakerClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CircuitBreakerClientApplication.class, args);
	}

}
