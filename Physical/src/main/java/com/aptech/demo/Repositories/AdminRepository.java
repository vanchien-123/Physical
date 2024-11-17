package com.aptech.demo.Repositories;

import com.aptech.demo.Models.Admin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {
    @Autowired 
    JdbcTemplate aDB;
    
    class AdminRowMapper implements RowMapper<Admin> {
    	 
    	@Override
    	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
            Admin admin = new Admin();
            admin.setID(rs.getInt("ID"));
            admin.setUsername(rs.getString("Username"));
            admin.setPassword(rs.getString("Password"));
            return admin;
        }
    }
    public List<Admin> findAll() {
    	try {
    		return aDB.query("select * from Admin", new AdminRowMapper());
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }

    public Admin findByFilter(String username, String password){
        try {
            return aDB.queryForObject("select * from Admin where Username = ? and Password = ?", new AdminRowMapper(), new Object[]{username, password});
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    
}
