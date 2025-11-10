# spring-session-redis-poc

Proof of concept: Spring Session with Redis, Spring Security, and Actuator on Spring Boot.

## Features
- Spring Security (form login + HTTP Basic)
- Role-based authN/Z for REST endpoints
- Spring Session backed by Redis
- Actuator endpoints (health/info by default)
- In-memory users for demo

## Project Structure
- src/main/java/com/deepak/spring_sec1/config/ProjectConfig.java
    - SecurityFilterChain
    - InMemoryUserDetailsManager
    - @EnableRedisHttpSession
- src/main/resources/application.properties
    - Redis host/port/password
    - Logging and Actuator exposure

## Endpoints
- Public: /welcome, /contact, /notices
- Authenticated: /myAccount, /myBalance, /myLoans, /myCards
- Actuator: /actuator/** (ADMIN only by default)

## Users
- user / password — authorities: read
- admin / [bcrypt hash] — ensure it has ROLE_ADMIN
    - Note: If you set both roles(...) and authorities(...), authorities override roles. Include ROLE_ADMIN in authorities or remove authorities to keep the role.

## Configuration
- application.properties:
    - server.port=8080
    - spring.session.redis.save-mode=always
    - spring.data.redis.* (host/port/password)
    - management.endpoints.web.exposure.include=*
        - To expose all Actuator endpoints
    - Optional: management.endpoint.shutdown.enabled=true

## Run
- Ensure Redis is reachable (env matches application.properties)
- mvn spring-boot:run

## Notes
- Access to /actuator/** requires ADMIN:
    - In ProjectConfig: .requestMatchers("/actuator/**").hasRole("ADMIN")
    - Ensure admin has ROLE_ADMIN:
        - Either remove .authorities(...) or add "ROLE_ADMIN" to authorities.
