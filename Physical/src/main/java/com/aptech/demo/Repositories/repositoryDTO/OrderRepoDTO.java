package com.aptech.demo.Repositories.repositoryDTO;

import com.aptech.demo.DTOs.OrderDTO;
import com.aptech.demo.Enums.OrderStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderRepoDTO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class OrderDTORowMapper implements RowMapper<OrderDTO>{

        @Override
        public OrderDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setStt(rs.getInt("stt"));
            orderDTO.setID(rs.getLong("ID"));
            orderDTO.setReceiver(rs.getString("Receiver"));
            orderDTO.setAddress(rs.getString("Address"));
            orderDTO.setPhone(rs.getString("Phone"));
            orderDTO.setOrderDate(rs.getDate("OrderDate"));
            orderDTO.setTotal(rs.getFloat("Total"));
            orderDTO.setStatus(OrderStatus.valueOf(rs.getString("Status")));
            orderDTO.setNote(rs.getString("Note"));
            orderDTO.setTransactionNo(rs.getLong("TransactionNo"));
            orderDTO.setTransactionDate(rs.getString("TransactionDate"));
            orderDTO.setPayment(rs.getString("Payment"));
            return orderDTO;
        }
    }

    public List<OrderDTO> showOrder(){
        try {
            return jdbcTemplate.query("exec sp_show_order", new OrderDTORowMapper());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int updateStatus(Long id, String  status){
        try {
            return jdbcTemplate.update("update orders set status = ? where ID = ?", new Object[]{status, id});
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    
    public int updateStatusHome(Long id, String  status){
        try {
            return jdbcTemplate.update("update orders set status = ? where ID = ?", new Object[]{status, id});
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public List<OrderDTO> showOrderByCusId(Long id){
        try {
            return jdbcTemplate.query("exec sp_show_order_by_cus_id ? ", new OrderDTORowMapper(), new Object[]{id});
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
