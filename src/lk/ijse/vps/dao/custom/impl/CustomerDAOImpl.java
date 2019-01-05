/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.vps.dao.CrudUtil;
import lk.ijse.vps.dao.custom.CustomerDAO;
import lk.ijse.vps.entity.Customer;

/**
 *
 * @author User
 */
public class CustomerDAOImpl implements CustomerDAO{

    @Override
    public boolean save(Customer customer) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO Customer VALUES (?,?,?,?) ", customer.getCusID(),customer.getCusNIC(),customer.getCusName(),customer.getCusTel())>0;
    }

    @Override
    public boolean update(Customer customer) throws Exception {
        return CrudUtil.executeUpdate("UPDATE Customer SET CusNic=?, CusName=?, CusTel=? WHERE CusID=? ", customer.getCusNIC(), customer.getCusName(),customer.getCusTel(), customer.getCusID())>0;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE From Customer where cusid=? ", id)>0;
    }

    @Override
    public Customer search(String id) throws Exception {
        ResultSet rst= CrudUtil.executeQuery("Select*From Customer where Cusid=? ", id);
        if(rst.next()){
            return new Customer(rst.getString("CusID"), rst.getString("CusNIC"),rst.getString("CusName"),rst.getString("CusTel"));
        }else{
            return null;
        }
    }

    @Override
    public ArrayList<Customer> getAll() throws Exception {
        ArrayList<Customer> allCustomers=new ArrayList<>();
        ResultSet rst=CrudUtil.executeQuery("SELECT*FROM Customer ");
        while (rst.next()){
            allCustomers.add(new Customer(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return allCustomers;
    }
    
}
