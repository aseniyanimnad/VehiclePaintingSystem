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
public class SupplierOrderDTO {
    private String id;
    private String date;
    private String supplierId;
    private int qty;
    
    public SupplierOrderDTO(){
    }
    
    public SupplierOrderDTO(String id,String supplierId,String date, int qty){
        this.id=id;
        this.date=date;
        this.supplierId=supplierId;
        this.qty=qty;
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
     * @return the supplierId
     */
    public String getSupplierId() {
        return supplierId;
    }

    /**
     * @param supplierId the supplierId to set
     */
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * @return the qty
     */
    public int getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "SupplierOrderDTO{" + "id=" + id + ", date=" + date + ", supplierId=" + supplierId + ", qty=" + qty + '}';
    }

  }
