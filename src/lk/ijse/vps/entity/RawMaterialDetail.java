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
public class RawMaterialDetail {
    private RawMaterialDetail_PK rawMaterialDetail_PK;
        
    public RawMaterialDetail(){
        
    }
    
    public RawMaterialDetail(RawMaterialDetail_PK rawMaterialDetail_PK){
        this.rawMaterialDetail_PK= rawMaterialDetail_PK;
    }
    
    public RawMaterialDetail(String rawMaterialCode,String serviceId){
        this.rawMaterialDetail_PK=new RawMaterialDetail_PK(rawMaterialCode,serviceId);
    }

    /**
     * @return the rawMaterialDetail_PK
     */
    public RawMaterialDetail_PK getRawMaterialDetail_PK() {
        return rawMaterialDetail_PK;
    }

    /**
     * @param rawMaterialDetail_PK the rawMaterialDetail_PK to set
     */
    public void setRawMaterialDetail_PK(RawMaterialDetail_PK rawMaterialDetail_PK) {
        this.rawMaterialDetail_PK = rawMaterialDetail_PK;
    }
}