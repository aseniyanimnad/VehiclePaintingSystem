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
public class SupplierOrderDetail {
    private String supplierOrderId;
    private String rawMaterialCode;
    
    
    public SupplierOrderDetail(){
        
    }
    
    public SupplierOrderDetail(String supplierOrderId, String rawMaterialCode){
        this.supplierOrderId=supplierOrderId;
        this.rawMaterialCode=rawMaterialCode;
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

    @Override
    public String toString() {
        return "SupplierOrderDetail{" + "supplierOrderId=" + supplierOrderId + ", rawMaterialCode=" + rawMaterialCode + '}';
    }
    
}