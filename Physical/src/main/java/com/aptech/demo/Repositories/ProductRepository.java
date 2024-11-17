package com.aptech.demo.Repositories;

import com.aptech.demo.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    JdbcTemplate pDB;

    class ProductRowMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setID(rs.getLong("ID"));
            product.setName(rs.getString("Name"));
            product.setImage(rs.getString("Image"));
            product.setImportPrice(rs.getFloat("ImportPrice"));
            product.setSellPrice(rs.getFloat("SellPrice"));
            product.setDescription(rs.getString("Description"));
            product.setTypeID(rs.getLong("TypeID"));
            product.setStatus(rs.getString("Status"));
            return product;
        }
    }

    public List<Product> findAll(){
        try {
            return pDB.query("exec sp_show_product", new ProductRowMapper());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> findAllForUser(){
        try {
            return pDB.query("exec sp_show_product_for_user", new ProductRowMapper());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> findBySearch(String keyword){
        try {
            return pDB.query("exec sp_show_pro_by_name ?", new ProductRowMapper(), new Object[]{keyword});
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Product> findByFilter(Long id){
        try {
            return pDB.query("exec sp_show_product_by_type ?", new ProductRowMapper(), new Object[]{id});
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int insertProduct(Product pro){
        try{
            return pDB.update("insert into Products (Name, Image, ImportPrice, SellPrice, Description, TypeID) values (?, ?, ?, ?, ?, ?)",
                    new Object[]{pro.getName(), pro.getImage(), pro.getImportPrice(), pro.getSellPrice(), pro.getDescription(), pro.getTypeID()});
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteById(Long id){
        try {
            return pDB.update("delete Products where ID = ?", new Object[]{id});
        }catch (Exception e){
            e.printStackTrace();

        }
        return 0;
    }

    public Product findById(Long id){
        try {
            return pDB.queryForObject("select  * from Products where ID IN (?)", new ProductRowMapper(), new Object[]{id});
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int update(Product pro){
        try {
            return pDB.update("update Products set Name = ?, Image= ?, ImportPrice = ?, SellPrice = ?, Description = ?, TypeID = ? where ID = ?",
                    new Object[]{pro.getName(), pro.getImage(), pro.getImportPrice(), pro.getSellPrice(), pro.getDescription(), pro.getTypeID(), pro.getID()});
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public int updateProStatus(String update, Long id){
        try {
            return pDB.update("update Products set Status = ? where ID = ?", new Object[]{update, id});
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
