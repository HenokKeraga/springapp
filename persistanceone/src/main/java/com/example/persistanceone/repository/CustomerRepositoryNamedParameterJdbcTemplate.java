package com.example.persistanceone.repository;

import com.example.persistanceone.domain.Customer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerRepositoryNamedParameterJdbcTemplate {
  protected final DataSource firstDatasource;
  public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public CustomerRepositoryNamedParameterJdbcTemplate(DataSource firstDatasource) {
    this.firstDatasource = firstDatasource;
  }

  public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
    if (null == namedParameterJdbcTemplate) {
      namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(firstDatasource);
    }
    return namedParameterJdbcTemplate;
  }

  public List<Customer> getAllCustomer() {

    return getNamedParameterJdbcTemplate().query("select * from customer", new CustomerRowMapper());
  }

  public Customer getOneCustomerById(Long id) {
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);

    return getNamedParameterJdbcTemplate()
        .queryForObject(
            "select * from customer where id=:id", sqlParameterSource, new CustomerRowMapper());
  }

  public Customer getOneCustomerByCustomerBean(Customer customer) {
    SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(customer);

    return getNamedParameterJdbcTemplate()
        .queryForObject(
            "select * from customer where id=:id and name=:name",
            sqlParameterSource,
            new CustomerRowMapper());
  }

  public int addCustomer(Customer customer) {

    var sqlParameterSource = new MapSqlParameterSource();
    sqlParameterSource.addValue("id", customer.getId()).addValue("name", customer.getName());

    return getNamedParameterJdbcTemplate()
        .update("insert into customer (id, name) values (:id,:name)", sqlParameterSource);
  }

  public int[] addListOfCustomer(List<Customer> customers) {

    List<MapSqlParameterSource> sqlParameterSourceList =
        customers.stream()
            .map(
                customer ->
                    new MapSqlParameterSource("id", customer.getId())
                        .addValue("name", customer.getName()))
            .collect(Collectors.toList());
    SqlParameterSource[] sqlParameterSource =
        sqlParameterSourceList.toArray(MapSqlParameterSource[]::new);

    return getNamedParameterJdbcTemplate()
        .batchUpdate("insert into customer (id, name) values (:id,:name)", sqlParameterSource);
  }

  public int deleteCustomerById(Long id) {
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id",id);

    return getNamedParameterJdbcTemplate().update("delete from customer where id=:id",sqlParameterSource);
  }


  static class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Customer(rs.getLong("id"), rs.getString("name"));
    }
  }
}
