/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom;

import java.util.ArrayList;
import lk.ijse.vps.business.SuperBO;
import lk.ijse.vps.model.CustomerPaymentDTO;

/**
 *
 * @author User
 */
public interface CustomerPaymentBO extends SuperBO{
    
    public boolean addCustomerPayment(CustomerPaymentDTO customerPayment)throws Exception;
    
    public boolean deleteCustomerPayment(String id)throws Exception;
    
    public boolean updateCustomerPayment(CustomerPaymentDTO customerPayment)throws Exception;
    
    public CustomerPaymentDTO searchCustomerPayment(String id)throws Exception;
    
    public ArrayList<String> getAllCustomerPaymentIds() throws Exception;

    public ArrayList<CustomerPaymentDTO> getAllCustomerPayments() throws Exception;
    
}
