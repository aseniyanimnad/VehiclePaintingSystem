/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.vps.dao.CrudUtil;
import lk.ijse.vps.dao.custom.CustomerPaymentDAO;
import lk.ijse.vps.entity.CustomerPayment;

/**
 *
 * @author User
 */
public class CustomerPaymentDAOImpl implements CustomerPaymentDAO{

    @Override
    public boolean save(CustomerPayment customerPayment) throws Exception {
        System.out.println(customerPayment.getDate());
        return CrudUtil.executeUpdate("INSERT INTO CustomerPayment VALUES (?,?,?,?,?)", customerPayment.getId(),  customerPayment.getDate(), customerPayment.getAdvance(), customerPayment.getPaymentAmount(), customerPayment.getAppointmentId())>0;
    }

    @Override
    public boolean update(CustomerPayment customerPayment) throws Exception {
       return CrudUtil.executeUpdate("UPDATE CustomerPayment SET AID =?, PayDate=?, Amount=?, Payment=? WHERE CPID  = ? ", customerPayment.getDate(), customerPayment.getAdvance(), customerPayment.getPaymentAmount(), customerPayment.getAppointmentId(), customerPayment.getId())>0; 
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE From CustomerPayment where CPID = ? ", id)>0;
    }

    @Override
    public CustomerPayment search(String id) throws Exception {
       ResultSet rst = CrudUtil.executeQuery("Select * From CustomerPayment where CPID = ? ", id);
        if (rst.next()) {
            return new CustomerPayment(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getDouble(4), rst.getString(5));
        } else {
            return null;
        }  
    }

    @Override
    public ArrayList<CustomerPayment> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * From CustomerPayment ");
        ArrayList<CustomerPayment> customerPaymentList = new ArrayList<>();
        while (rst.next()) {
            CustomerPayment customerPayment = new CustomerPayment(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getDouble(4), rst.getString(5));
            customerPaymentList.add(customerPayment);
        }
        return customerPaymentList; 
    }
    
}
