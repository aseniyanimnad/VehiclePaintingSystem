/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.vps.business.custom.SupplierBO;
import lk.ijse.vps.dao.DAOFactory;
import lk.ijse.vps.dao.custom.SupplierDAO;
import lk.ijse.vps.entity.Supplier;
import lk.ijse.vps.model.SupplierDTO;

/**
 *
 * @author User
 */
public class SupplierBOImpl implements SupplierBO{
    
    private SupplierDAO supplierDAO;
    
    public SupplierBOImpl(){
        this.supplierDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    }

    @Override
    public boolean addSupplier(SupplierDTO supplier) throws Exception {
        return supplierDAO.save(new Supplier(supplier.getId(), supplier.getName(), supplier.getTelephoneNumber(), supplier.getEMail(), supplier.getAddress()));
    }

    @Override
    public boolean deleteSupplier(String supplierId) throws Exception {
        return supplierDAO.delete(supplierId);
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplier) throws Exception {
        return supplierDAO.update(new Supplier(supplier.getId(), supplier.getName(), supplier.getTelephoneNumber(), supplier.getEMail(), supplier.getAddress()));

    }

    @Override
    public SupplierDTO searchSupplier(String SupplierId) throws Exception {
        Supplier supplier = supplierDAO.search(SupplierId);
        return new SupplierDTO(
                supplier.getId(), 
                supplier.getName(),
                supplier.getTelephoneNumber(),
                supplier.getEMail(), 
                supplier.getAddress() 
                );
    }

    @Override
    public ArrayList<SupplierDTO> getAllSuppliers() throws Exception {
        ArrayList<Supplier> employees=supplierDAO.getAll();
        ArrayList<SupplierDTO> dtoList=new ArrayList<>();
        for(Supplier sup: employees){
            dtoList.add(new SupplierDTO(
                    sup.getId(), 
                    sup.getName(),
                    sup.getTelephoneNumber(),
                    sup.getEMail(),
                    sup.getAddress() 
                    
            ));
        }
        return dtoList;
    }

    @Override
    public ArrayList<String> getAllSupplierIds() throws Exception {
         ArrayList<Supplier>suppliers=supplierDAO.getAll();
        ArrayList<String>supplierIDList=new ArrayList<>();
        for(Supplier appointment:suppliers){
           supplierIDList.add(appointment.getId());
        }
        return supplierIDList;
    }

    
    }
    


   