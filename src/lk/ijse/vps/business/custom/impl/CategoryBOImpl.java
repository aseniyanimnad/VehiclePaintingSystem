/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.vps.business.custom.CategoryBO;
import lk.ijse.vps.dao.DAOFactory;
import lk.ijse.vps.dao.custom.CategoryDAO;
import lk.ijse.vps.entity.Category;
import lk.ijse.vps.model.CategoryDTO;

/**
 *
 * @author User
 */
public class CategoryBOImpl implements CategoryBO{
    
    private CategoryDAO categoryDAO;
    
    public CategoryBOImpl(){
        this.categoryDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CATEGORY);
    }

    @Override
    public boolean addCategory(CategoryDTO category) throws Exception {
        return categoryDAO.save(
                new Category(
                        category.getCatId(), 
                        category.getCatName()
                ));
    }

    @Override
    public ArrayList<CategoryDTO> getAllCategories() throws Exception {
        ArrayList<Category> categories=categoryDAO.getAll();
        ArrayList<CategoryDTO> dtoList=new ArrayList<>();
        for(Category e: categories){
            dtoList.add(new CategoryDTO(e.getCatID(), e.getCatName()));
        }
        return dtoList;
    }

//    @Override
//    public ArrayList<String> getAllCategoryIds() throws Exception {
//        ArrayList<Category> categoryList=categoryDAO.getAll();
//        ArrayList<String> categoryIdList=new ArrayList<>();
//        for(Category category:categoryList){
//            categoryIdList.add
//        }
//        return categoryIdList;
//    }

    @Override
    public ArrayList<String> getAllCategoryIds() throws Exception {
        ArrayList<Category>categoryList=categoryDAO.getAll();
        ArrayList<String>categoryIdList=new ArrayList<>();
        for(Category category: categoryList){
            categoryIdList.add(category.getCatID());
        }
        return categoryIdList;
    }

    @Override
    public CategoryDTO searchCategoryName(String CatID) throws Exception {
        Category category=categoryDAO.search(CatID);
        return new CategoryDTO(category.getCatID(),category.getCatName());
    }


}
