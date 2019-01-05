/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.vps.business.custom.SupplierPaymentBO;
import lk.ijse.vps.dao.DAOFactory;
import lk.ijse.vps.dao.custom.SupplierPaymentDAO;
import lk.ijse.vps.entity.SupplierPayment;
import lk.ijse.vps.model.SupplierPaymentDTO;

/**
 *
 * @author User
 */
public class SupplierPaymentBOImpl implements SupplierPaymentBO{
    
    private SupplierPaymentDAO supplierPaymentDAO;
    
    public SupplierPaymentBOImpl(){
        this.supplierPaymentDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER_PAYMENT);
    }

    @Override
    public boolean addSupplierPayment(SupplierPaymentDTO supplierPaymentDTO) throws Exception {
        return supplierPaymentDAO.save(new SupplierPayment(supplierPaymentDTO.getId(), supplierPaymentDTO.getDate(), supplierPaymentDTO.getPayment(), supplierPaymentDTO.getSOID()));
               
       
    }

    @Override
    public boolean deleteSupplierPayment(String id) throws Exception {
        return supplierPaymentDAO.delete(id);
    }

    @Override
    public boolean updateSupplierPayment(SupplierPaymentDTO supplierPaymentDTO) throws Exception {
        return supplierPaymentDAO.update(new SupplierPayment(supplierPaymentDTO.getId(), supplierPaymentDTO.getDate(), supplierPaymentDTO.getPayment(), supplierPaymentDTO.getSOID()));
    }

    @Override
    public SupplierPaymentDTO searchSupplierPaymentt(String id) throws Exception {
        SupplierPayment supplierPayment=supplierPaymentDAO.search(id);
        return new SupplierPaymentDTO(supplierPayment.getId(), supplierPayment.getDate(), supplierPayment.getPayment(), supplierPayment.getSOID());
    }

   

    @Override
    public ArrayList<String> getAllSupplierPaymentIds() throws Exception {
          ArrayList<SupplierPayment> supplierPayments=supplierPaymentDAO.getAll();
        ArrayList<String> supplierPaymentIDList=new ArrayList<>();
        for(SupplierPayment supplierPayment:supplierPayments){
            supplierPaymentIDList.add(supplierPayment.getId());
        }
        return supplierPaymentIDList;
    }

 

    @Override
    public ArrayList<SupplierPaymentDTO> getALLSupplierPayments() throws Exception {
           ArrayList<SupplierPayment> supplierPayments=supplierPaymentDAO.getAll();
        ArrayList<SupplierPaymentDTO> dtoList=new ArrayList<>();
        for(SupplierPayment e: supplierPayments){
            dtoList.add(new SupplierPaymentDTO(e.getId(), e.getDate(), e.getPayment(), e.getSOID()));
        }
        return dtoList;
    }
    
}
