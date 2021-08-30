package com.example.persistanceone.repository;

import com.example.persistanceone.domain.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
class CustomerRepositoryNamedParameterJdbcTemplateTest {

  //  @Mock NamedParameterJdbcTemplate mockNamedParameterJdbcTemplate;

  //  DataSource dataSource;
  @Container
  private static MySQLContainer container =
      new MySQLContainer(DockerImageName.parse("mysql:8.0.26"));

  @Autowired
  CustomerRepositoryNamedParameterJdbcTemplate customerRepositoryNamedParameterJdbcTemplate;

  @DynamicPropertySource
  public static void overrideProps(DynamicPropertyRegistry registry) {
    System.out.println(":::::" + container.getJdbcUrl());
    System.out.println(":::::" + container.getUsername());
    System.out.println(":::::" + container.getPassword());
    registry.add("first.datasource.jdbc-url", container::getJdbcUrl);
    registry.add("first.datasource.username", container::getUsername);
    registry.add("first.datasource.password", container::getPassword);
    registry.add("spring.sql.init.mode", ()->"always");
    registry.add("spring.sql.init.schema-locations", ()->"schema.sql");
  }

  @BeforeEach
  void setUp() throws InterruptedException {
    //    MockitoAnnotations.openMocks(this);
    //        dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
    //                .addScript("classpath:testdata/schema.sql")
    //                .addScript("classpath:testdata/test-data.sql")
    //                .generateUniqueName(true)
    //                .build();

    //    customerRepositoryNamedParameterJdbcTemplate =
    //        new CustomerRepositoryNamedParameterJdbcTemplate(dataSource);

    System.out.println("::::::" + container.getJdbcUrl());


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
