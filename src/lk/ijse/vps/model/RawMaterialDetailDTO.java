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
public class RawMaterialDetailDTO {
    private String rawMaterialCode;
    private String employeeId;
    
    public RawMaterialDetailDTO(){
    }
    
    public RawMaterialDetailDTO(String rawMaterialCode,String employeeId){
        this.rawMaterialCode=rawMaterialCode;
        this.employeeId=employeeId;
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
     * @return the employeeId
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "RawMaterialDetailDTO{" + "rawMaterialCode=" + rawMaterialCode + ", employeeId=" + employeeId + '}';
    }
    
}
