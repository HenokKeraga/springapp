package com.example.persistanceone.repository;

import com.example.persistanceone.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
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
       log.info("sql :::: {}" , sql);
        getJdbcTemplate().execute(sql);
        return "done";
    }


    public String addListOfCustomer(List<Customer> customers) {
        List<Object[]> cust = customers.stream().map(customer -> new Object[]{customer.getId(), customer.getName()}).collect(Collectors.toList());
        getJdbcTemplate().batchUpdate("insert into customer (id, name) values (?,?)", cust);
        return "done";
    }

    public List<Customer> getAllCustomer() {


        return getJdbcTemplate().query("select * from customer", new CustomerRowMapper());
    }

    public Customer getOneCustomer(Long id) {
        String sql= String.format("select * from customer where id = %d",id);

       log.info(" sql :::  {}",sql);

        return getJdbcTemplate().queryForObject(sql,(rs,rowNum)->new Customer(rs.getLong("id"),rs.getString("name")));
    }

     static class CustomerRowMapper implements RowMapper<Customer>{

         @Override
         public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
             return new Customer(rs.getLong("id"),rs.getString("name"));
         }
     }
}
