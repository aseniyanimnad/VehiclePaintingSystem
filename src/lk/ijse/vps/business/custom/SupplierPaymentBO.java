/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom;

import java.util.ArrayList;
import lk.ijse.vps.business.SuperBO;
import lk.ijse.vps.model.SupplierPaymentDTO;

/**
 *
 * @author User
 */
public interface SupplierPaymentBO extends SuperBO{
    
    public boolean addSupplierPayment(SupplierPaymentDTO supplierPaymentDTO)throws Exception;
    
    public boolean deleteSupplierPayment(String id)throws Exception;
    
    public boolean updateSupplierPayment(SupplierPaymentDTO supplierPaymentDTO)throws Exception;
    
    public SupplierPaymentDTO searchSupplierPaymentt(String id)throws Exception;
    
    public ArrayList<String> getAllSupplierPaymentIds() throws Exception; 

    public ArrayList<SupplierPaymentDTO> getALLSupplierPayments() throws Exception;

    
    
}
