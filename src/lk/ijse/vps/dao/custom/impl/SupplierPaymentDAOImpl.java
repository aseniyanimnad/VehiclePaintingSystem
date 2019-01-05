/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.vps.dao.CrudUtil;
import lk.ijse.vps.dao.custom.SupplierPaymentDAO;
import lk.ijse.vps.entity.SupplierPayment;

/**
 *
 * @author User
 */
public class SupplierPaymentDAOImpl implements SupplierPaymentDAO{

    @Override
    public boolean save(SupplierPayment supplierPayment) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO SupplierPayment VALUES (?,?,?,?)", supplierPayment.getId(),  supplierPayment.getDate(), supplierPayment.getPayment(),supplierPayment.getSOID())>0;
    }

    @Override
    public boolean update(SupplierPayment supplierPayment) throws Exception {
        return CrudUtil.executeUpdate("UPDATE SupplierPayment SET  SPDate =?, SPayment =?, SOID  =? WHERE SPID   = ? ", supplierPayment.getDate(), supplierPayment.getPayment(), supplierPayment.getSOID(), supplierPayment.getId())>0; 
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE From SupplierPayment where SPID = ? ", id)>0;
    }

    @Override
    public SupplierPayment search(String id) throws Exception {
         ResultSet rst = CrudUtil.executeQuery("Select * From CustomerPayment where CPID = ? ", id);
        if (rst.next()) {
            return new SupplierPayment(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getString(4));
        } else {
            return null;
        }  
    }

    @Override
    public ArrayList<SupplierPayment> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * From SupplierPayment");
        ArrayList<SupplierPayment> supplierPayments = new ArrayList<>();
        while (rst.next()) {
            SupplierPayment supplierPayment = new SupplierPayment(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getString(4));
            supplierPayments.add(supplierPayment);
        }
        return supplierPayments; 
    }
    
}
