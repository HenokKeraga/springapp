package com.example.persistanceone.repository;

import com.example.persistanceone.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Array;
import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerRepository {

    //datasource => where to execute the query
    protected final DataSource firstDatasource;

    public JdbcTemplate jdbcTemplate;

    public CustomerRepository(DataSource firstDatasource) {
        this.firstDatasource = firstDatasource;
    }

    public JdbcTemplate getJdbcTemplate() {
        if (null == jdbcTemplate) {
            jdbcTemplate = new JdbcTemplate(firstDatasource);
        }
        return jdbcTemplate;
    }


    public String addCustomer(Customer customer) {
        String sql = String.format("insert into customer (id, name) values (%d,'%s')", customer.getId(), customer.getName());
        System.out.printf("sql :::: " + sql);
        getJdbcTemplate().execute(sql);
        return "done";
    }


    public String addListOfCustomer(List<Customer> customers) {
        List<Object[]> cust = customers.stream().map(customer -> new Object[]{customer.getId(), customer.getName()}).collect(Collectors.toList());
        getJdbcTemplate().batchUpdate("insert into customer (id, name) values (?,?)", cust);
        return "done";
    }

    public List<Customer> getAllCustomer() {
        getJdbcTemplate().execute("");

        return null;
    }

    public Customer getOneCustomer() {

        return null;
    }
}
