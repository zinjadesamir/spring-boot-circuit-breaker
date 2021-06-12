package com.study.circuit.breaker.client.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/rest")
public class ClientController {

    @HystrixCommand(fallbackMethod = "fallBackMethod",
    commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value = "1"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
            @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value = "1000")
    }
    )
    @GetMapping("/client/getStatus")
    public String getStatus() {
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:8081/rest/server/getStatus";
        return restTemplate.getForObject(URL, String.class);
    }

    public String fallBackMethod() {
        return "Fallback method called ! Circuit breaker is open";
    }
}
