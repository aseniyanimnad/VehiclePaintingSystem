/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.vps.dao.CrudUtil;
import lk.ijse.vps.dao.custom.SupplierOrderDAO;
import lk.ijse.vps.entity.SupplierOrder;

/**
 *
 * @author User
 */
public class SupplierOrderDAOImpl implements SupplierOrderDAO{

    @Override
    public boolean save(SupplierOrder supplierOrder) throws Exception {
        return CrudUtil.executeUpdate("Insert into SupplierOrders Values(?,?,?,?)", supplierOrder.getId(),supplierOrder.getSupplierId(), supplierOrder.getDate(),supplierOrder.getQty()) > 0;
    }

    @Override
    public boolean update(SupplierOrder supplierOrder) throws Exception {
        return CrudUtil.executeUpdate("UPDATE SupplierOrders SET SupID =?, SupOrderDate =?, SupOrderQTY=?  WHERE SOID = ? ", supplierOrder.getSupplierId(), supplierOrder.getDate(), supplierOrder.getQty(),supplierOrder.getId())>0;
    }

    @Override
    public boolean delete(String id) throws Exception {
       return CrudUtil.executeUpdate("DELETE From SupplierOrders where SOID = ? ", id)>0; 
    }

    @Override
    public SupplierOrder search(String id) throws Exception {
      ResultSet rst = CrudUtil.executeQuery("Select * From SupplierOrders where SOID = ? ", id);
        if (rst.next()) {
            return new SupplierOrder(rst.getString(1),rst.getString(2), rst.getString(3),rst.getInt(4));
        } else {
            return null;
        }  
    }

    @Override
    public ArrayList<SupplierOrder> getAll() throws Exception {
          ResultSet rst = CrudUtil.executeQuery("Select * From SupplierOrders");
        ArrayList<SupplierOrder> orderList = new ArrayList<>();
        while (rst.next()) {
            SupplierOrder supplierOrder = new SupplierOrder(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4));
            orderList.add(supplierOrder);
        }
        return orderList;
    }
    
}
