package com.aptech.demo.Repositories;

import com.aptech.demo.Enums.OrderStatus;
import com.aptech.demo.Models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class OrderRepository {
    @Autowired
    JdbcTemplate orderDB;

    class OrderRowMapper implements RowMapper<Order>{

        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order orders = new Order();
            orders.setID(rs.getLong("ID"));
            orders.setOrderDate(rs.getString("OrderDate"));
            orders.setCusID(rs.getInt("CusID"));
            orders.setTotal(rs.getFloat("Total"));
            orders.setStatus(OrderStatus .valueOf(rs.getString("Status")));
            orders.setReceiver(rs.getString("Receiver"));
            orders.setAddress(rs.getString("Address"));
            orders.setPhone(rs.getString("Phone"));
            orders.setNote(rs.getString("Note"));
            orders.setPayment(rs.getString("Payment"));
            return orders;
        }
    }

    public List<Order> findAll(){
        try{
            return orderDB.query("select * from Orders", new OrderRowMapper());
        }catch (Exception e){
            System.out.println("Something wrong in Orders");
        }
        return null;
    }
    
    public Order findById(Long id){
        try{
            return orderDB.queryForObject("select * from orders where ID = ?", new OrderRowMapper(), new Object[]{id});
        }catch (Exception e){
            System.out.println("Something wrong in Orders");
        }
        return null;
    }
}
