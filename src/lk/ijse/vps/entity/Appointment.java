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
public class Appointment {
    private String AID;
    private String ADate;
    private double Advance;
    private String CusID;
        
    public Appointment(String id,String date,double advance,String customerId){
        this.AID=id;
        this.ADate=date;
        this.Advance=advance;
        this.CusID=customerId;
        
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

    @Override
    public String toString() {
        return "Appointment{" + "AID=" + AID + ", ADate=" + ADate + ", Advance=" + Advance + ", CusID=" + CusID + '}';
    }


}