/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business;

import lk.ijse.vps.business.custom.impl.AppointmentBOImpl;
import lk.ijse.vps.business.custom.impl.AppointmentDetailBOImpl;
import lk.ijse.vps.business.custom.impl.CategoryBOImpl;
import lk.ijse.vps.business.custom.impl.CustomerBOImpl;
import lk.ijse.vps.business.custom.impl.EmployeeBOImpl;
import lk.ijse.vps.business.custom.impl.RawMaterialBOImpl;
import lk.ijse.vps.business.custom.impl.ServiceBOImpl;
import lk.ijse.vps.business.custom.impl.SupplierBOImpl;
import lk.ijse.vps.business.custom.impl.SupplierOrderBOImpl;
import lk.ijse.vps.business.custom.impl.SupplierOrderDetailBOImpl;
import lk.ijse.vps.business.custom.impl.SupplierPaymentBOImpl;
import lk.ijse.vps.entity.Service;

/**
 *
 * @author User
 */
public class BOFactory {
    
    public enum BOTypes{
        CATEGORY,APPOINTMENT, CUSTOMER, CUSTOMER_PAYMENT, EMPLOYEE, RAW_MATERIAL, SERVICE, SERVICE_CATEGORY, SUPPLIER, SUPPLIER_ORDER, SUPPLIER_ORDER_DETAIL, SUPPLIER_PAYMENT, APPOINTMENT_DETAIL ;
    }
    
    private static BOFactory bOFactory;
    
    private BOFactory(){
        
    }
    
    public static BOFactory getInstance(){
        if(bOFactory==null){
            bOFactory=new BOFactory();
        }
        return bOFactory;
    }
    
    public <T extends SuperBO> T getBO(BOTypes boTypes){
        switch(boTypes){
            case APPOINTMENT:
                return (T) new AppointmentBOImpl();
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case EMPLOYEE:
                return (T) new  EmployeeBOImpl();
            case RAW_MATERIAL:
                return (T) new RawMaterialBOImpl();
            case SERVICE:
                return (T) new ServiceBOImpl();
            case SUPPLIER:
                return (T) new SupplierBOImpl();
            case SUPPLIER_ORDER:
                return (T) new SupplierOrderBOImpl();
            case SUPPLIER_ORDER_DETAIL:
                return (T) new SupplierOrderDetailBOImpl();
            case SUPPLIER_PAYMENT:
                return (T) new SupplierPaymentBOImpl();
            case CATEGORY:
                return (T) new CategoryBOImpl();
            case APPOINTMENT_DETAIL:
                return (T) new AppointmentDetailBOImpl();    
            default:
                return null;
        }
    }
}
