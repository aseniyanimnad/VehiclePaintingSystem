/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom;

import java.util.ArrayList;
import lk.ijse.vps.business.SuperBO;
import lk.ijse.vps.model.SupplierDTO;
import lk.ijse.vps.model.SupplierOrderDTO;

/**
 *
 * @author User
 */
public interface SupplierBO extends SuperBO{
    public boolean addSupplier(SupplierDTO supplier) throws Exception;
    
    public boolean deleteSupplier(String supplierId) throws Exception;
    
    public boolean updateSupplier(SupplierDTO supplier) throws Exception;
    
    public SupplierDTO searchSupplier(String SupplierId) throws Exception;
    
    public ArrayList<SupplierDTO> getAllSuppliers() throws Exception;
    
    public ArrayList<String> getAllSupplierIds() throws Exception;

    
    
   
    
}
