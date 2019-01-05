/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.vps.dao.CrudUtil;
import lk.ijse.vps.dao.custom.SupplierDAO;
import lk.ijse.vps.entity.Supplier;

/**
 *
 * @author User
 */
public class SupplierDAOImpl implements SupplierDAO{

    @Override
    public boolean save(Supplier supplier) throws Exception {
       return CrudUtil.executeUpdate("INSERT INTO Supplier VALUES (?,?,?,?,?)", supplier.getId(),supplier.getName(),supplier.getTelephoneNumber(),supplier.getEMail(),supplier.getAddress())>0; 
    }
    @Override
    public boolean update(Supplier supplier) throws Exception {
       return CrudUtil.executeUpdate("UPDATE Supplier SET SupName=?, SupTel=?, SupEmail=?, SupAddress=? WHERE SupID = ? ", supplier.getName(),supplier.getEMail(),supplier.getAddress(), supplier.getTelephoneNumber(), supplier.getId())>0;  
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE From Supplier where SupID = ? ", id)>0;
    }

    @Override
    public Supplier search(String id) throws Exception {
        ResultSet rst= CrudUtil.executeQuery("Select*From Supplier where SupID = ?  ", id);
        if(rst.next()){
            return new Supplier(rst.getString("SupID"), rst.getString("SupName"),rst.getString("SupTel"),rst.getString("SupEmail"),rst.getString("SupAddress"));
        }else{
            return null;
        }
    }

    @Override
    public ArrayList<Supplier> getAll() throws Exception {
        ArrayList<Supplier> allSuppliers=new ArrayList<>();
        ResultSet rst=CrudUtil.executeQuery("SELECT*FROM Supplier");
        while (rst.next()){
        allSuppliers.add(new Supplier(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5)));
        }
        return allSuppliers;
    }
    
}
