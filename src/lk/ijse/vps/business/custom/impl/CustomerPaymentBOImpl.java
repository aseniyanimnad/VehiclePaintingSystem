/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.vps.business.custom.CustomerPaymentBO;
import lk.ijse.vps.dao.DAOFactory;
import lk.ijse.vps.dao.custom.CustomerPaymentDAO;
import lk.ijse.vps.entity.CustomerPayment;
import lk.ijse.vps.model.CustomerPaymentDTO;

/**
 *
 * @author User
 */
public class CustomerPaymentBOImpl implements CustomerPaymentBO {

    private CustomerPaymentDAO customerPaymentDAO;

    public CustomerPaymentBOImpl() {
        this.customerPaymentDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER_PAYMENT);
    }

    @Override
    public boolean addCustomerPayment(CustomerPaymentDTO customerPayment) throws Exception {
        
        return customerPaymentDAO.save(new CustomerPayment(
                customerPayment.getId(),
                customerPayment.getDate(),
                customerPayment.getAdvance(),
                customerPayment.getPaymentAmount(),
                customerPayment.getAppointmentId()
        ));
    }

    @Override
    public boolean deleteCustomerPayment(String id) throws Exception {
        return customerPaymentDAO.delete(id);
    }

    @Override
    public boolean updateCustomerPayment(CustomerPaymentDTO customerPayment) throws Exception {
        return customerPaymentDAO.update(new CustomerPayment(customerPayment.getId(), customerPayment.getDate(), customerPayment.getAdvance(), customerPayment.getPaymentAmount(), customerPayment.getAppointmentId()));
    }

    @Override
    public CustomerPaymentDTO searchCustomerPayment(String id) throws Exception {
        CustomerPayment customerPayment = customerPaymentDAO.search(id);
        return new CustomerPaymentDTO(customerPayment.getId(), customerPayment.getDate(), customerPayment.getAdvance(), customerPayment.getPaymentAmount(), customerPayment.getAppointmentId());
    }

    @Override
    public ArrayList<String> getAllCustomerPaymentIds() throws Exception {
        ArrayList<CustomerPayment> customerPaymentList = customerPaymentDAO.getAll();
        ArrayList<String> customerPaymentIdList = new ArrayList<>();
        for (CustomerPayment customer : customerPaymentList) {
            customerPaymentIdList.add(customer.getId());
        }
        return customerPaymentIdList;
    }

    @Override
    public ArrayList<CustomerPaymentDTO> getAllCustomerPayments() throws Exception {
        ArrayList<CustomerPayment> customerPaymentList=customerPaymentDAO.getAll();
        ArrayList<CustomerPaymentDTO> dtoList = new ArrayList<>();
        for(CustomerPayment e : customerPaymentList){
            dtoList.add(new CustomerPaymentDTO(e.getId(), e.getDate(), e.getAdvance(), e.getPaymentAmount(), e.getAppointmentId()));
        }
        return dtoList;
    }
}


