/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom;

import java.util.ArrayList;
import lk.ijse.vps.business.SuperBO;
import lk.ijse.vps.model.RawMaterialDTO;
import lk.ijse.vps.model.SupplierOrderDTO;
import lk.ijse.vps.model.SupplierOrderDetailDTO;

/**
 *
 * @author User
 */
public interface SupplierOrderDetailBO extends SuperBO{
    
    public ArrayList<String> getAllSupplierOrderDetailIds() throws Exception; 
    
    public ArrayList<SupplierOrderDetailDTO> getAllSupplierOrderDetails() throws Exception;
    
    public boolean add(SupplierOrderDTO supplierOrderDTO, SupplierOrderDetailDTO supplierOrderDetailDTO) throws Exception;
    
}
