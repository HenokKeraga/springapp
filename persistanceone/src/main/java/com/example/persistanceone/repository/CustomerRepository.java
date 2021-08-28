package com.example.persistanceone.repository;

import com.example.persistanceone.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.JDBCType;
import java.util.List;

@Component
public class CustomerRepository {

    //datasource => where to execute the query
    @Autowired
    protected DataSource dataSource;

    public JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        if (null == jdbcTemplate) {
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        return jdbcTemplate;
    }


    public String addCustomer(Customer customer) {
        String sql = String.format("insert into customer (id, name) values (%d,'%s')", customer.getId(), customer.getName());
        System.out.printf("sql :::: "+ sql);
        getJdbcTemplate().execute(sql);
        return "done";
    }
    public DataSource dataSource(){
        return  dataSource;
    }

    public String addListOfCustomer(List<Customer> customers) {

        return null;
    }

    public List<Customer> getAllCustomer() {

        return null;
    }

    public Customer getOneCustomer() {

        return null;
    }
}
