package com.jetbrains.testcontainersdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomepageController {

    private static final Logger logger = LoggerFactory.getLogger(HomepageController.class);
   @Autowired
   CustomerDao customerDao;



    @GetMapping("/")
    public List<Customer> customers() {
        List<Customer> customers = customerDao.findAll();
        customers.forEach(c -> logger.info("Found a customer: {}", c));

        return customers;
    }
    @PostMapping("/")
    public String addCustomer(@RequestBody  Customer customer){
        customerDao.addCustomer(customer);
        return "done";
    }
}
