/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.model;

/**
 *
 * @author User
 */
public class CategoryDTO {
   private String CatId;
   private String CatName;
   
   public CategoryDTO(){
       
   }
   
   public CategoryDTO(String id, String name){
       this.CatId=id;
       this.CatName=name;
   }

    /**
     * @return the CatId
     */
    public String getCatId() {
        return CatId;
    }

    /**
     * @param CatId the CatId to set
     */
    public void setCatId(String CatId) {
        this.CatId = CatId;
    }

    /**
     * @return the CatName
     */
    public String getCatName() {
        return CatName;
    }

    /**
     * @param CatName the CatName to set
     */
    public void setCatName(String CatName) {
        this.CatName = CatName;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" + "CatId=" + CatId + ", CatName=" + CatName + '}';
    }
}