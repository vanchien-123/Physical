package com.aptech.demo.Repositories;

import com.aptech.demo.Models.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderDetailRepository {
    @Autowired
    JdbcTemplate OrderDetailRepo;

    class OrderDetailRowMapper implements RowMapper<OrderDetail>{

        @Override
        public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setID(rs.getLong("ID"));
            orderDetail.setOrderID(rs.getLong("OrderID"));
            orderDetail.setProID(rs.getLong("ProID"));
            orderDetail.setImportPrice(rs.getFloat("ImportPrice"));
            orderDetail.setSellPrice(rs.getFloat("SellPrice"));
            orderDetail.setQuantity(rs.getInt("Quantity"));
            orderDetail.setTotal(rs.getFloat("Total"));
            return orderDetail;
        }
    }

    public List<OrderDetail> findAll(){
        try{
            return OrderDetailRepo.query("select * from OrderDetail", new OrderDetailRowMapper());
        }catch (Exception e){
            System.out.println("Something wrong in order_detail");
        }
        return null;
    }
}
