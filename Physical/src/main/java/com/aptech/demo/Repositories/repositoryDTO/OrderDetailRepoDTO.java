package com.aptech.demo.Repositories.repositoryDTO;

import com.aptech.demo.DTOs.OrderDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderDetailRepoDTO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class OrderDetailDTORowMapper implements RowMapper<OrderDetailDTO>{

        @Override
        public OrderDetailDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            orderDetailDTO.setStt(rs.getInt("stt"));
            orderDetailDTO.setID(rs.getLong("ID"));
            orderDetailDTO.setName(rs.getString("Name"));
            orderDetailDTO.setImportPrice(rs.getFloat("ImportPrice"));
            orderDetailDTO.setSellPrice(rs.getFloat("SellPrice"));
            orderDetailDTO.setQuantity(rs.getInt("Quantity"));
            orderDetailDTO.setSubTotal(rs.getFloat("Total"));
            orderDetailDTO.setTotal(rs.getFloat("Total"));
            return orderDetailDTO;
        }
    }

    public List<OrderDetailDTO> showOrderDetail(Long id){
        try {
            return jdbcTemplate.query("exec sp_show_order_by_id ?", new OrderDetailDTORowMapper(), new Object[]{id});
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
