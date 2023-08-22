# API FOR PAYMENTS

## Technologies:

> Java 17

> SpringBoot 2.7.5

> Spring Security 5

> Json Web Token

## Installation:

Maven installed is required (apt-get install maven)

1. `mvn install` for install dependencies
2. `mvn spring-boot:run` for run application
3. Acess the url: [http://localhost:8090](http://localhost:8090)

## Routes

> swagger: [http://localhost:8090/swagger-ui.html](http://localhost:8090/swagger-ui.html)

## Description

this api aims to simulate an ecommerce, registering products or services and being able to receive payment through the stripe tool.


## Configuration

Rename `application-example.yml` for `application.yml` and change the params  

~~~application-example.yml
server:
  port: 8090 #or any port available

app-config:
  stripe:
    secretKey: your_stripe_secret_key
  webhook:
    secretKey: your_webhook_key

spring:
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate.dialect: org.hibernate.dialect.MySQL5Dialect
  mvc: #config for swagger
    pathmatch:
      matching-strategy: ant-path-matcher

  datasource:
    url: your_database_url
    username: your_username
    password: your_password
~~~



 
