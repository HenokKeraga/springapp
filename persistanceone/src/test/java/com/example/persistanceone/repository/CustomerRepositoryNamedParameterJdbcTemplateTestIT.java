package com.example.persistanceone.repository;

import com.example.persistanceone.domain.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;
@JdbcTest
class CustomerRepositoryNamedParameterJdbcTemplateTestIT {
  @Autowired
 NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  CustomerRepositoryNamedParameterJdbcTemplate customerRepositoryNamedParameterJdbcTemplate;

  @BeforeEach
  void setUp() {
    customerRepositoryNamedParameterJdbcTemplate= new CustomerRepositoryNamedParameterJdbcTemplate();
    customerRepositoryNamedParameterJdbcTemplate.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
  }

  @Test
  void getAllCustomer() {
    var customerListExpected =
        List.of(
            new Customer(1L, "James"),
            new Customer(2L, "Donald"),
            new Customer(3L, "Linus"),
            new Customer(4L, "Dennis"));
    //    Mockito.when(
    //            mockNamedParameterJdbcTemplate.query(
    //                "select * from customer", Mockito.eq(Mockito.any(RowMapper.class))))
    //        .thenReturn(customerListExpected);

    Assertions.assertEquals(
        customerListExpected, customerRepositoryNamedParameterJdbcTemplate.getAllCustomer());
  }

  @Test
  void getOneCustomerById() {}

  @Test
  void getOneCustomerByCustomerBean() {}

  @Test
  void addCustomer() {}

  @Test
  void addListOfCustomer() {}

  @Test
  void deleteCustomerById() {}
}
