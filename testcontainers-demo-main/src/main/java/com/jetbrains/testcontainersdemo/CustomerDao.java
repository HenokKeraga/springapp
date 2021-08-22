package com.jetbrains.testcontainersdemo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerDao {


    final JdbcTemplate jdbcTemplate;

    public CustomerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Customer> findAll() {
        return jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM customers",
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        );
    }
    public void addCustomer(Customer customer){
        String addSql=
                String.format("Insert into customers ( first_name, last_name) values('%s','%s')",
                        customer.getFirstName(),customer.getLastName());

        String test ="select id, first_name, last_name from customer2";
        jdbcTemplate.execute(addSql);
    }

}
