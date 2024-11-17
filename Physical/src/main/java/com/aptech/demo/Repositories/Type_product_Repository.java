package com.aptech.demo.Repositories;

import com.aptech.demo.Models.TypeProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Type_product_Repository {
    @Autowired
    JdbcTemplate typeDB;


    class Type_productRowMapper implements RowMapper<TypeProduct> {

        @Override
        public  TypeProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
        	TypeProduct type = new TypeProduct();
            type.setStt(rs.getInt("Stt"));
            type.setID(rs.getLong("ID"));
            type.setName(rs.getString("Name"));
            return type;
        }

    }
    public List<TypeProduct> findAll() {
        try{
            return typeDB.query("exec sp_show_type", new Type_productRowMapper());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insertType(TypeProduct type){
        try {
            return typeDB.update("insert into TypeProduct values (?)", new Object[]{type.getName()});
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public TypeProduct findById(Long id){
        try {
            return typeDB.queryForObject("select row_number() over (order by ID) 'stt', * from TypeProduct where ID IN (?)", new Type_productRowMapper(), new Object[]{id});
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int updateType(TypeProduct typeProduct){
        try {
            typeDB.update("update TypeProduct set Name = ? where ID = ?", new Object[]{typeProduct.getName(), typeProduct.getID()});
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
