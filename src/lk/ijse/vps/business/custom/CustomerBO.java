/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom;

import java.util.ArrayList;
import lk.ijse.vps.business.SuperBO;
import lk.ijse.vps.model.CustomerDTO;

/**
 *
 * @author User
 */
public interface CustomerBO extends SuperBO{
    public boolean addCustomer(CustomerDTO customer) throws Exception;
    
    public boolean deleteCustomer(String CusID) throws Exception;
    
    public boolean updateCustomer(CustomerDTO customer) throws Exception;
    
    public CustomerDTO searchCustomer(String CusID) throws Exception;
    
    public ArrayList<CustomerDTO> getAllCustomers() throws Exception;
    
    public ArrayList<String> getAllCustomerIds() throws Exception;
}
