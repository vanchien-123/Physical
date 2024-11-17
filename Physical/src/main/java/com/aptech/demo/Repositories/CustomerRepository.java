package com.aptech.demo.Repositories;

import com.aptech.demo.Models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerRepository {
    @Autowired
    JdbcTemplate cDB;

    class CustomerRowMapper implements RowMapper<Customer>{

        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setStt(rs.getInt("Stt"));
            customer.setID(rs.getLong("ID"));
            customer.setName(rs.getString("Name"));
            customer.setBirthday(rs.getString("Birthday"));
            customer.setAddress(rs.getString("Address"));
            customer.setPhone(rs.getString("Phone"));
            customer.setEmail(rs.getString("Email"));
            customer.setSex(rs.getString("Sex"));
            customer.setUsername(rs.getString("Username"));
            customer.setPassword(rs.getString("Password"));
            customer.setCreateAt(rs.getString("CreateAt"));
            return customer;
        }
    }

    public List<Customer> findAll(){
        try{
            return cDB.query("exec sp_show_customer", new CustomerRowMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Customer> findBySearch(String keyword){
        try {
            return cDB.query("exec sp_show_cus_by_name ?", new CustomerRowMapper(), new Object[]{keyword});
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int insertCustomer(Customer customer){
        try {
            return cDB.update("Insert Into Customer (Name, Address, Phone, Email, Sex, Username, Password) values (?, ?, ?, ?, ?, ?, ?)",
                    new Object[]{customer.getName(), customer.getAddress(), customer.getPhone(), customer.getEmail(), customer.getSex(), customer.getUsername(), customer.getPassword()});
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public Customer findByLogin(String username, String password){
        try {
            return cDB.queryForObject("select row_number() over (order by ID) 'stt', * from Customer where Username = ? and Password = ?", new CustomerRowMapper(), new Object[]{username, password});
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Customer findByUsername(String username){
        try {
            return cDB.queryForObject("exec sp_find_cus_username ?", new CustomerRowMapper(), new Object[]{username});
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int updatePassword(String newPass, Long id){
        try {
            cDB.update("update customers set cus_password = ? where ID = ?", new Object[]{newPass, id});
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
