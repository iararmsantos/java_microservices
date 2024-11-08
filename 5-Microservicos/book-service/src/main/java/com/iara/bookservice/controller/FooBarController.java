package com.iara.bookservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/book-service")
public class FooBarController {

    private Logger logger = LoggerFactory.getLogger(FooBarController.class);

    @GetMapping("/foo-bar")
    //Retry, CircuitBreaker, RateLimiter, and Bulkhead are configured in application.yml
//    @Retry(name = "foo-bar", fallbackMethod = "fallbackMethod") //commented retry to use circuit breaker
//    @CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod") //commented to test rate limit and bulkhead
//    @RateLimiter(name = "default") //commented to test bulkhead (concurrency)
    @Bulkhead(name = "default")
    public String fooBar() {
        logger.info("Request to foo-bar is received!");
        //commented to test rate limit
//        var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
//        return response.getBody();
        return "Foo-Bar!!!";
    }

    public String fallbackMethod(Exception exception) {
        return "fallbackMethod foo-bar!!";
    }
}
