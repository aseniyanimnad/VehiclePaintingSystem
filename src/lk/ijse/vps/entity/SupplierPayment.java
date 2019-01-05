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
public class SupplierPayment {
    private String id;
    private String date;
    private double payment;
    private String SOID;
    
    public SupplierPayment(){
        
    }
    
    public SupplierPayment(String id, String date, double payment, String SOID){
        this.id=id;
        this.date=date;
        this.payment=payment;
        this.SOID=SOID;
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
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the payment
     */
    public double getPayment() {
        return payment;
    }

    /**
     * @param payment the payment to set
     */
    public void setPayment(double payment) {
        this.payment = payment;
    }

    /**
     * @return the SOID
     */
    public String getSOID() {
        return SOID;
    }

    /**
     * @param SOID the SOID to set
     */
    public void setSOID(String SOID) {
        this.SOID = SOID;
    }

    @Override
    public String toString() {
        return "SupplierPayment{" + "id=" + id + ", date=" + date + ", payment=" + payment + ", SOID=" + SOID + '}';
    }
    
}
