Microservices are essential today for agile and scalable development, enabling developers worldwide to deliver software with greater speed and efficiency. This course empowers developers to build microservices in Java using Spring Boot and Spring Cloud, covering both theoretical fundamentals (10%) and intensive practice (90%).

In this course, I learned how to create microservices with Spring Boot 2.5 and Java 16, beginning with an introduction to the basic concepts, implementing a simple microservice, and progressing to a complex, integrated stack. With each module, I expanded my knowledge of essential tools and technologies in the microservices ecosystem, including:

- Spring Cloud Config for centralized configuration management.
- Spring Boot Actuator for service monitoring.
- Feign to streamline consuming RESTful APIs.
- Service Discovery and Registry with Eureka for locating and registering microservices.
- Load Balancing with Eureka, Feign, and Spring Cloud LoadBalancer.
- API Gateway with Spring Cloud Gateway and RouteLocator.
- Circuit Breaker with Resilience4j to enhance microservices resilience.
- I also configured Swagger OpenAPI to document APIs, implemented distributed tracing with Docker, Zipkin, Eureka, and Sleuth, and learned how to dockerize microservices and set up a continuous delivery pipeline with GitHub Actions.

The course also covered the evolution of Spring Cloud, which has adopted technologies like Resilience4j, Spring Cloud Load Balancer, and Spring Cloud Gateway to replace Netflix's original tools (like Hystrix, Ribbon, and Zuul), ensuring that the acquired knowledge aligns with the latest best practices.

By the end of the course, I am prepared to build robust, scalable, and well-documented microservices with a modern and secure stack. With extra minicourses on Docker, Docker Compose, and integration with the ChatGPT API, I feel more equipped than ever to tackle real-world challenges in modern, microservices-driven corporate environments.

Udemy Url: https://www.udemy.com/course/microservices-do-0-a-gcp-com-spring-boot-kubernetes-e-docker

### To run:
1. Clone this project
2. Open it in any java IDE
3. Create maven project with the POM
4. Run the Project

### 4 - GreetingMicroservice
This project is configured to use spring cloud server with github. The project with the configuration is [config-server-sample](https://github.com/iararmsantos/config-server-sample).
Follow the instructions in that repository to run using cloud config server.
- To update configuration without close this project
- clone [config-server-sample](https://github.com/iararmsantos/config-server-sample)
- make the changes you need and commit it
- run in Postman: [GREETING_URL:PORT]/actuator/refresh
- run [GREETING_URL:PORT]/greeting to verify the changes

### 5 - Microservices
- microservices using Feign to communicate between services

* To run multiple applications:
- on Intellij: add vm options in the edit run configuration and add -Dserver.port=8002

* To test Circuit Breaker run on PowerShell
- craeted class FooBarController with samples of Retry, CircuitBreaker, RateLimiter and Bulkhead
- uncomment any of the samples to test each one then run the bellow command to see in the logs how the calls to service are made:
```while (1) {curl http://localhost:8765/book-service/foo-bar; sleep 0.1}```
- Retry: it will call the request if error returns a fallback function
- CircuitBreaker: it will call requests, and we can see some call are made and some is not
- RateLimiter: give a limit of calls per second to the method
- Bulkhead: configure max concurrency calls


