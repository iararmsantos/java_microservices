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

#### To run multiple applications:
- on Intellij: add vm options in the edit run configuration and add -Dserver.port=8002

#### To test Circuit Breaker run on PowerShell
- craeted class FooBarController with samples of Retry, CircuitBreaker, RateLimiter and Bulkhead
- uncomment any of the samples to test each one then run the bellow command to see in the logs how the calls to service are made:
```while (1) {curl http://localhost:8765/book-service/foo-bar; sleep 0.1}```
- Retry: it will call the request if error returns a fallback function
- CircuitBreaker: it will call requests, and we can see some call are made and some is not
- RateLimiter: give a limit of calls per second to the method
- Bulkhead: configure max concurrency calls

#### Swagger:
- to access json per service: {SERVICE_URL}:{PORT}/v3/api-docs
- to access UI per service: {SERVICE_URL}:{PORT}/swagger-ui.html

### Docker
- the Dockerfile with specifications of the container is in the root folder
- to build jar file: - go to root folder of the project in terminal and run: 
```mvn clean package -DskipTests```
- with the name of the .jar file create the docker image (no PowerShell): 
```docker build -t hello-docker:0.0.1-SNAPSHOT .```
- to list images run: 
```docker images```
- to send image to docker hub so other people can use it you need first add tag 
({nome original = image:tag} {nome final = image: tag}): 
```docker tag hello-docker:0.0.1-SNAPSHOT iararmsantos/hello-docker:0.0.1-SNAPSHOT```
- login in docker hub (you have to be authenticated in docker hub page, 
- if you are not then it will ask username and password): 
```docker login docker.io```
- to send container image to docker hub (repo name/image name:tag): 
```docker push iararmsantos/hello-docker:0.0.1-SNAPSHOT```
- to execute our container (-p to specify port // 80:80 -> host:container // -d to up the container detached
// repository name/image name/version(usually is latest that has to be created): 
```docker run -p 80:80 -d iararmsantos/hello-docker:0.0.1-SNAPSHOT```
- to show all containers running (use tag `-a` to run inclusive stopped containers): 
```docker container ls```
- to stop a container (ID = id/name of the container got with the above command): 
```docker container stop ${ID}```
- to see the container logs: 
``` docker logs ${ID}```
- to see the logs in real time: 
```docker logs -f ${ID}```
- to download images to your laptop: 
```docker pull IMAGE_NAME``` (example docker pull mysql -> download mysql image): 
- to search images (official or not):
```docker search mysql```
- to search for official image: 
```docker search mysql --filter is-official=true```
- to see history of the image (ID = image id or repository name): 
```docker image history {ID}```
- inspect docker image (image details): 
  ```docker image inspect {ID}```
- remove image (append -f to force): 
```docker image remove {ID} or docker rmi {ID}```
- to remove all images (Power Shell): 
```docker images -a -q | % { docker image rm $_ -f }```
- to pause container running: 
```docker container pause {ID}```
- to resume: 
```docker container unpause {ID}```
- to inspect container (show details, how it is mounted, port, environment variables): 
```docker container inspect {ID}```
- delete containers that is not running: 
```docker container prune```
- to delete all containers and volumes: 
```docker rm -vf $(docker ps -aq)```
- interrupt container: 
```docker container kill {ID}```
- to create a container that will always restart when stopped: 
```docker run -p 80:80 -d --restart=always IMAGE_NAME:TAG```
- to debug container: 
```docker events```
- see processes running: 
```docker top {ID}```
- see machine details (cpu, memory, net): 
```docker stats```
- to create a container with limited memory and cpu (100000 = 100% / 5000 = 5% (very slow)): 
  ```docker run -p 80:80 -m 512m --cpu-quota 5000 -d  IMAGE_NAME:TAG```
- info about system:
```docker system df```

#### Docker Compose
- to create image and run services: 
```docker-compose up -d```
- to see details of the container: 
```docker ps```
- to stop the container: 
```docker stop {Container Id}```
- to stop and remove all containers (in docker compose folder): 
```docker compose down```
- to access docker logs: 
```docker logs {container id}```
- show commands related to docker compose we ran (in docker compose folder): 
```docker compose ps```

### Distributed Log tracing with Zipkin, Sleuth, and RabbitMQ
- run docker command to start zipkin:
```docker run -d -p 9411:9411 openzipkin/zipkin```
- start all services and run query to get book. 
Then go to http://localhost:9411/zipkin and click on Run Query, there you can see the logs

### Dockerizando services
- to build service image, add plugin to maven (api-gateway sample),
go to service folder and run:
```mvn spring-boot:build-image -DskipTests```
- to run naming service without dockerfile:
```docker run -p 8761:8761 iararmsantos/naming-server:0.0.1-SNAPSHOT```
- in service with Dockerfile (cambio and book) run in the service root folder:
- To create the JAR file: 
```mvn clean package -DskipTests```
- Build the docker image:
```docker build -t iararmsantos/${service-name}:0.0.1-SNAPSHOT .```
- Run the docker container (in the docker-compose folder):
```docker-compose up -d```
