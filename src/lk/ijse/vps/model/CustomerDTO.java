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
public class CustomerDTO {
    private String CusID;
    private String CusNIC;
    private String CusName;
    private String CusTel;

    public CustomerDTO() {
    }

    public CustomerDTO(String id, String nic, String name, String telephoneNumber) {
        this.CusID = id;
        this.CusNIC = nic;
        this.CusName = name;
        this.CusTel = telephoneNumber;
    }

    /**
     * @return the CusID
     */
    public String getCusID() {
        return CusID;
    }

    /**
     * @param CusID the CusID to set
     */
    public void setCusID(String CusID) {
        this.CusID = CusID;
    }

    /**
     * @return the CusNIC
     */
    public String getCusNIC() {
        return CusNIC;
    }

    /**
     * @param CusNIC the CusNIC to set
     */
    public void setCusNIC(String CusNIC) {
        this.CusNIC = CusNIC;
    }

    /**
     * @return the CusName
     */
    public String getCusName() {
        return CusName;
    }

    /**
     * @param CusName the CusName to set
     */
    public void setCusName(String CusName) {
        this.CusName = CusName;
    }

    /**
     * @return the CusTel
     */
    public String getCusTel() {
        return CusTel;
    }

    /**
     * @param CusTel the CusTel to set
     */
    public void setCusTel(String CusTel) {
        this.CusTel = CusTel;
    }
}

   