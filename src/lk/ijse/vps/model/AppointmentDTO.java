/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.model;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class AppointmentDTO {
    private String AID;
    private String ADate;
    private double Advance;
    private String CusID;
   

    public AppointmentDTO() {
    }

   
    public AppointmentDTO(String AID, String ADate, double Advance, String CusID) {
        this.AID = AID;
        this.ADate = ADate;
        this.Advance = Advance;
        this.CusID = CusID;
       
        
    }

   
    /**
     * @return the AID
     */
    public String getAID() {
        return AID;
    }

    /**
     * @param AID the AID to set
     */
    public void setAID(String AID) {
        this.AID = AID;
    }

    /**
     * @return the ADate
     */
    public String getADate() {
        return ADate;
    }

    /**
     * @param ADate the ADate to set
     */
    public void setADate(String ADate) {
        this.ADate = ADate;
    }

    /**
     * @return the Advance
     */
    public double getAdvance() {
        return Advance;
    }

    /**
     * @param Advance the Advance to set
     */
    public void setAdvance(double Advance) {
        this.Advance = Advance;
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

    
}

   

    
   