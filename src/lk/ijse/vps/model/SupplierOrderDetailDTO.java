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
public class SupplierOrderDetailDTO {
    private String rawMaterialCode;
    private String supplierOrderId;
    
    
    
    public SupplierOrderDetailDTO(){
    }
    
    public SupplierOrderDetailDTO(String rawMaterialCode,String supplierOrderId){
        this.rawMaterialCode=rawMaterialCode;
        this.supplierOrderId=supplierOrderId;
        
    }

    /**
     * @return the rawMaterialCode
     */
    public String getRawMaterialCode() {
        return rawMaterialCode;
    }

    /**
     * @param rawMaterialCode the rawMaterialCode to set
     */
    public void setRawMaterialCode(String rawMaterialCode) {
        this.rawMaterialCode = rawMaterialCode;
    }

    /**
     * @return the supplierOrderId
     */
    public String getSupplierOrderId() {
        return supplierOrderId;
    }

    /**
     * @param supplierOrderId the supplierOrderId to set
     */
    public void setSupplierOrderId(String supplierOrderId) {
        this.supplierOrderId = supplierOrderId;
    }

    @Override
    public String toString() {
        return "SupplierOrderDetailDTO{" + "rawMaterialCode=" + rawMaterialCode + ", supplierOrderId=" + supplierOrderId + '}';
    }

}
