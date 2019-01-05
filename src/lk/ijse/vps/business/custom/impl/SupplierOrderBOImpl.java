/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.vps.business.custom.SupplierOrderBO;
import lk.ijse.vps.dao.DAOFactory;
import lk.ijse.vps.dao.custom.SupplierOrderDAO;
import lk.ijse.vps.entity.SupplierOrder;
import lk.ijse.vps.model.SupplierOrderDTO;

/**
 *
 * @author User
 */
public class SupplierOrderBOImpl implements SupplierOrderBO{
    private SupplierOrderDAO supplierOrderDAO;
    
    
    public SupplierOrderBOImpl(){
        this.supplierOrderDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER_ORDER);
    }

    @Override
    public boolean addSupplierOrder(SupplierOrderDTO supplierOrder) throws Exception {
        return supplierOrderDAO.save(new SupplierOrder(
                supplierOrder.getId(),
                supplierOrder.getSupplierId(),
                supplierOrder.getDate(), 
                supplierOrder.getQty()
        ));
    }

    @Override
    public boolean deleteSupplierOrder(String supplierOrderId) throws Exception {
        return supplierOrderDAO.delete(supplierOrderId);
    }

    @Override
    public boolean updateSupplierOrder(SupplierOrderDTO supplierOrder) throws Exception {
        return supplierOrderDAO.update(new SupplierOrder(
                supplierOrder.getId(), 
                supplierOrder.getSupplierId(),
                supplierOrder.getDate(), 
                supplierOrder.getQty()
        ));
    }

    @Override
    public SupplierOrderDTO searchSupplierOrder(String SupplierOrderId) throws Exception {
        SupplierOrder supplierOrder=supplierOrderDAO.search(SupplierOrderId);
        return new SupplierOrderDTO(
                supplierOrder.getId(), 
                supplierOrder.getSupplierId(), 
                supplierOrder.getDate(), 
                supplierOrder.getQty()
        );
    }

    @Override
    public ArrayList<SupplierOrderDTO> getAllSupplierOrders() throws Exception {
       ArrayList<SupplierOrder> customers=supplierOrderDAO.getAll();
        ArrayList<SupplierOrderDTO> dtoList=new ArrayList<>();
        for(SupplierOrder supplierOrder: customers){
            dtoList.add(new SupplierOrderDTO(
                supplierOrder.getId(), 
                supplierOrder.getSupplierId(), 
                supplierOrder.getDate(), 
                supplierOrder.getQty()
            ));
        }
        return dtoList;
    }

    @Override
    public ArrayList<String> getAllSupplierOrderIds() throws Exception {
        ArrayList<SupplierOrder> supplierOrderList=supplierOrderDAO.getAll();
        ArrayList<String> supplierOrderIdList=new ArrayList<>();
        for(SupplierOrder supplierOrder:supplierOrderList){
            supplierOrderIdList.add(supplierOrder.getId());
        }
        return supplierOrderIdList;
    }
    
}
