/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom.impl;

import java.sql.Connection;
import java.util.ArrayList;
import lk.ijse.vps.business.custom.SupplierOrderBO;
import lk.ijse.vps.business.custom.SupplierOrderDetailBO;
import lk.ijse.vps.dao.DAOFactory;
import lk.ijse.vps.dao.custom.RawMaterialDAO;
import lk.ijse.vps.dao.custom.SupplierOrderDAO;
import lk.ijse.vps.dao.custom.SupplierOrderDetailDAO;
import lk.ijse.vps.db.DBConnection;
import lk.ijse.vps.entity.RawMaterial;
import lk.ijse.vps.entity.SupplierOrder;
import lk.ijse.vps.entity.SupplierOrderDetail;
import lk.ijse.vps.model.RawMaterialDTO;
import lk.ijse.vps.model.SupplierOrderDTO;
import lk.ijse.vps.model.SupplierOrderDetailDTO;

/**
 *
 * @author User
 */
public class SupplierOrderDetailBOImpl implements SupplierOrderDetailBO{
    
    private SupplierOrderDetailDAO supplierOrderDetailDAO;
    private SupplierOrderDAO supplierOrderDAO;
    private RawMaterialDAO  rawMaterialDAO;
    
    public SupplierOrderDetailBOImpl(){
        this.supplierOrderDetailDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER_ORDER_DETAIL);
        this.supplierOrderDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER_ORDER);
    }
    
    @Override
    public ArrayList<String> getAllSupplierOrderDetailIds() throws Exception {
          ArrayList<SupplierOrderDetail> supplierOrderDetails=supplierOrderDetailDAO.getAll();
        ArrayList<String> SupOrderDetailList=new ArrayList<>();
        for(SupplierOrderDetail supplierOrderDetail:supplierOrderDetails){
            SupOrderDetailList.add(supplierOrderDetail.getRawMaterialCode());
        }
        return SupOrderDetailList;
    }

    @Override
    public ArrayList<SupplierOrderDetailDTO> getAllSupplierOrderDetails() throws Exception {
         ArrayList<SupplierOrderDetail> supplierOrderDetails=supplierOrderDetailDAO.getAll();
        ArrayList<SupplierOrderDetailDTO> dtoList=new ArrayList<>();
        for(SupplierOrderDetail e: supplierOrderDetails){
            dtoList.add(new SupplierOrderDetailDTO(e.getRawMaterialCode(), e.getSupplierOrderId()));
        }
        return dtoList;
    }

    @Override
    public boolean add(SupplierOrderDTO supplierOrderDTO,SupplierOrderDetailDTO supplierDetailOrderDTO ) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        
        try{
            boolean result = supplierOrderDAO.save(new SupplierOrder(
                    supplierOrderDTO.getId(),
                    supplierOrderDTO.getDate(),
                    supplierOrderDTO.getSupplierId(),
                    supplierOrderDTO.getQty()
            ));
            if(!result){
                return false;
            }
            result = supplierOrderDetailDAO.save(new SupplierOrderDetail(
                    supplierDetailOrderDTO.getRawMaterialCode(),
                    supplierDetailOrderDTO.getSupplierOrderId()
            ));
            if(!result){
                connection.rollback();
                return false;
            }
            connection.commit();
            return true;
        }finally{
            connection.setAutoCommit(true);
        }
    }

    
}

   
    
    

