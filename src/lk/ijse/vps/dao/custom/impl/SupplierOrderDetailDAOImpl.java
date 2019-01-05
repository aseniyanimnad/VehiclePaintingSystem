/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.vps.dao.CrudUtil;
import lk.ijse.vps.dao.custom.SupplierOrderDetailDAO;
import lk.ijse.vps.entity.SupplierOrderDetail;


/**
 *
 * @author User
 */
public class SupplierOrderDetailDAOImpl implements SupplierOrderDetailDAO{

    @Override
    public boolean save(SupplierOrderDetail supplierOrderDetail) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO SupplierOrderDetail VALUES(?,?)", supplierOrderDetail.getRawMaterialCode(),supplierOrderDetail.getSupplierOrderId())>0;
    }

    @Override
    public boolean update(SupplierOrderDetail entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SupplierOrderDetail search(String id) throws Exception {
         ResultSet rst = CrudUtil.executeQuery("Select * From SupplierOrderDetail where AID=? " , id);
        if (rst.next()) {
            return new SupplierOrderDetail(rst.getString(1), rst.getString(2));
        } else {
            return null;
        } 
    }

    @Override
    public ArrayList<SupplierOrderDetail> getAll() throws Exception {
         ArrayList<SupplierOrderDetail> orderDetails=new ArrayList<>();
        ResultSet rst=CrudUtil.executeQuery("SELECT*FROM SupplierOrderDetail");
        while (rst.next()){
            orderDetails.add(new SupplierOrderDetail(rst.getString(1),rst.getString(2)));
        }
        return orderDetails;
    }
}


  