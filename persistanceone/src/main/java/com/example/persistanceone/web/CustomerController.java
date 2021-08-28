package com.example.persistanceone.web;

import com.example.persistanceone.domain.Customer;
import com.example.persistanceone.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    final
    CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/customer")
    public List<Customer>  getAllCustomer(){
        return repository.getAllCustomer();
    }

    @GetMapping("/customer/id")
    public Customer getCustomer(){
        return repository.getOneCustomer();
    }

    @PostMapping("customer")
    public String addCustomer(@RequestBody Customer customer){
        return repository.addCustomer(customer);
    }
    @PostMapping("customers")
    public String addAllCustomer(@RequestBody List<Customer> customers){

        return repository.addListOfCustomer(customers);
    }



}
