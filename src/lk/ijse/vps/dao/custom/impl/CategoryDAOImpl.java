/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.vps.business.custom.CategoryBO;
import lk.ijse.vps.dao.CrudUtil;
import lk.ijse.vps.dao.custom.CategoryDAO;
import lk.ijse.vps.entity.Category;

/**
 *
 * @author User
 */
public class CategoryDAOImpl implements CategoryDAO{
    
    private CategoryBO categoryBO;

    @Override
    public boolean save(Category category) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO category VALUES (?,?) ", category.getCatID(), category.getCatName())>0;
    }

    @Override
    public boolean update(Category entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Category search(String id) throws Exception {
       ResultSet rst = CrudUtil.executeQuery("Select * From CATEGORY where catID =? " , id);
        if (rst.next()) {
            return new Category(rst.getString(1), rst.getString(2));
        } else {
            return null;
        }  
    }
    
    @Override
    public ArrayList<Category> getAll() throws Exception {
       // ArrayList<CategoryDTO> category=categoryBO.getAllCategories();
        ArrayList<Category> categoryList=new ArrayList<>();
        ResultSet rst=CrudUtil.executeQuery("Select * from CATEGORY");
        while (rst.next()){
          categoryList.add(new Category(rst.getString(1), rst.getString(2)));
        }
        return categoryList;
    }
}
