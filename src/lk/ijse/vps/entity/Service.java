/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.entity;

/**
 *
 * @author User
 */
public class Service {
    private String id;
    private String ServiceCategoryName ;
   
    
    public Service(){
        
    }
    
    public Service(String id,String name){
        this.id=id;
        this.ServiceCategoryName =name;
        
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the ServiceCategoryName
     */
    public String getServiceCategoryName() {
        return ServiceCategoryName;
    }

    /**
     * @param ServiceCategoryName the ServiceCategoryName to set
     */
    public void setServiceCategoryName(String ServiceCategoryName) {
        this.ServiceCategoryName = ServiceCategoryName;
    }

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", ServiceCategoryName=" + ServiceCategoryName + '}';
    }

}