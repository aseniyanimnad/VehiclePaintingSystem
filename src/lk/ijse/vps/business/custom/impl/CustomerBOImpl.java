/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.vps.business.custom.CustomerBO;
import lk.ijse.vps.dao.DAOFactory;
import lk.ijse.vps.dao.custom.CustomerDAO;
import lk.ijse.vps.entity.Customer;
import lk.ijse.vps.model.CustomerDTO;

/**
 *
 * @author User
 */
public class CustomerBOImpl implements CustomerBO{
    
    private CustomerDAO customerDAO;
    
    public CustomerBOImpl(){
        this.customerDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    }

    @Override
    public boolean addCustomer(CustomerDTO customer) throws Exception {
        return customerDAO.save(new Customer(customer.getCusID(), customer.getCusNIC(), customer.getCusName(), customer.getCusTel()));
    }

    @Override
    public boolean deleteCustomer(String CusID) throws Exception {
        return customerDAO.delete(CusID);
    }
    @Override
    public boolean updateCustomer(CustomerDTO customer) throws Exception {
        return customerDAO.update(new Customer(customer.getCusID(), customer.getCusNIC(), customer.getCusName(), customer.getCusTel()));
    }

    @Override
    public CustomerDTO searchCustomer(String customerId) throws Exception {
        Customer customer=customerDAO.search(customerId);
        return new CustomerDTO(customer.getCusID(), customer.getCusNIC(), customer.getCusName(), customer.getCusTel());
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws Exception {
        ArrayList<Customer> customers=customerDAO.getAll();
        ArrayList<CustomerDTO> dtoList=new ArrayList<>();
        for(Customer e: customers){
            dtoList.add(new CustomerDTO(e.getCusID(), e.getCusNIC(), e.getCusName(), e.getCusTel()));
        }
        return dtoList;
    }
    @Override
    public ArrayList<String> getAllCustomerIds() throws Exception {
        ArrayList<Customer> customerList=customerDAO.getAll();
        ArrayList<String> customerIdList=new ArrayList<>();
        for(Customer customer:customerList){
            customerIdList.add(customer.getCusID());
        }
        return customerIdList;
    }
    
}
