/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom;

import java.util.ArrayList;
import lk.ijse.vps.business.SuperBO;
import lk.ijse.vps.model.CategoryDTO;

/**
 *
 * @author User
 */
public interface CategoryBO  extends SuperBO{
    
    public boolean addCategory(CategoryDTO category) throws Exception;
    
    public ArrayList<CategoryDTO>getAllCategories() throws Exception;
    
    public ArrayList<String>getAllCategoryIds()throws Exception;

    public CategoryDTO searchCategoryName(String CatID)throws Exception;
}
