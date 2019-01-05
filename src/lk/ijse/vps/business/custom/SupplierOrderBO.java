/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom;

import java.util.ArrayList;
import lk.ijse.vps.business.SuperBO;
import lk.ijse.vps.model.SupplierOrderDTO;

/**
 *
 * @author User
 */
public interface SupplierOrderBO extends SuperBO{
  public boolean addSupplierOrder(SupplierOrderDTO supplierOrder) throws Exception;
    
    public boolean deleteSupplierOrder(String supplierOrderId) throws Exception;
    
    public boolean updateSupplierOrder(SupplierOrderDTO supplierOrder) throws Exception;
    
    public SupplierOrderDTO searchSupplierOrder(String SupplierOrderId) throws Exception;
    
    public ArrayList<SupplierOrderDTO> getAllSupplierOrders() throws Exception;
    
    public ArrayList<String> getAllSupplierOrderIds() throws Exception;  
}
