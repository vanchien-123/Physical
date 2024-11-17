package com.aptech.demo.Repositories.repositoryDTO;

import com.aptech.demo.DTOs.TopSellingProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TopSellingRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class TopSellingRepoRowMapper implements RowMapper<TopSellingProduct>{

        @Override
        public TopSellingProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
            TopSellingProduct topSellingProduct = new TopSellingProduct();
            topSellingProduct.setStt(rs.getInt("stt"));
            topSellingProduct.setPro_id(rs.getLong("ID"));
            topSellingProduct.setPro_name(rs.getString("Name"));
            topSellingProduct.setTotal_quantity_sold(rs.getInt("total_quantity_sold"));
            topSellingProduct.setTotal_revenue(rs.getInt("total_revenue"));
            return topSellingProduct;
        }
    }

    public List<TopSellingProduct> topSellingProducts(){
        try {
            return jdbcTemplate.query("exec sp_top_selling_product", new TopSellingRepoRowMapper());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
