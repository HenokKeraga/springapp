package com.example.persistanceone;

import com.example.persistanceone.repository.CustomerRepositoryNamedParameterJdbcTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class PersistanceoneApplication {
  final CustomerRepositoryNamedParameterJdbcTemplate repository;

  public PersistanceoneApplication(CustomerRepositoryNamedParameterJdbcTemplate repository) {
    this.repository = repository;
  }

  public static void main(String[] args) {
    SpringApplication.run(PersistanceoneApplication.class, args);
  }
  @Bean
  RouterFunction<?> home() {
    return route(GET("/customer"), request -> ok().body(fromValue(repository.getAllCustomer())));
  }
}
