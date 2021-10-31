package com.example.persistanceone.web;

import com.example.persistanceone.domain.Customer;
import com.example.persistanceone.repository.CustomerRepositoryNamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CustomerController {
  final CustomerRepositoryNamedParameterJdbcTemplate repository;

  public CustomerController(CustomerRepositoryNamedParameterJdbcTemplate repository) {
    this.repository = repository;
  }

  @GetMapping("/customer")
  public List<Customer> getAllCustomer() {
    return repository.getAllCustomer();
  }


  @GetMapping("/customer/{id}")
  public Customer getCustomer(@PathVariable("id") Long id) {
    return repository.getOneCustomerById(id);
  }

  @PostMapping("customer")
  public int addCustomer(@RequestBody Customer customer) {
    return repository.addCustomer(customer);
  }

  @PostMapping("customers")
  public int[] addAllCustomer(@RequestBody List<Customer> customers) {

    return repository.addListOfCustomer(customers);
  }

  @DeleteMapping("customer/{id}")
  public int deleteCustomer(@PathVariable("id") Long id) {
    return repository.deleteCustomerById(id);
  }
}
