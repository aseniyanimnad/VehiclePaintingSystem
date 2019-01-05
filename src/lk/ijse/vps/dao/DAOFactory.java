/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.dao;

import lk.ijse.vps.dao.custom.impl.AppointmentDAOImpl;
import lk.ijse.vps.dao.custom.impl.AppointmentDetailDAOImpl;
import lk.ijse.vps.dao.custom.impl.CategoryDAOImpl;
import lk.ijse.vps.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.vps.dao.custom.impl.CustomerPaymentDAOImpl;
import lk.ijse.vps.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.vps.dao.custom.impl.LoginDAOImpl;
import lk.ijse.vps.dao.custom.impl.RawMaterialDAOImpl;
import lk.ijse.vps.dao.custom.impl.RawMaterialDetailDAOImpl;
import lk.ijse.vps.dao.custom.impl.ServiceDAOImpl;
import lk.ijse.vps.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.vps.dao.custom.impl.SupplierOrderDAOImpl;
import lk.ijse.vps.dao.custom.impl.SupplierOrderDetailDAOImpl;
import lk.ijse.vps.dao.custom.impl.SupplierPaymentDAOImpl;

/**
 *
 * @author User
 */
public class DAOFactory {
    
    public enum DAOTypes{
        CATEGORY,CUSTOMER, APPOINTMENT, APPOINTMENT_DETAIL, CUSTOMER_PAYMENT, SERVICE, SERVICE_CATEGORY, EMPLOYEE, RAW_MATERIAL, RAW_MATERIAL_DETAILS, SUPPLIER, SUPPLIER_ORDER, SUPPLIER_PAYMENT, SUPPLIER_ORDER_DETAIL, LOGIN;
    }
    private static DAOFactory DAOFactory;
    
    private DAOFactory(){
        
    }
    
    public static DAOFactory getInstance(){
        if(DAOFactory==null){
            DAOFactory=new DAOFactory();
        }
        return DAOFactory;
        
    }
    
    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes){
        switch(daoTypes){
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case APPOINTMENT:
                return (T) new AppointmentDAOImpl();
            case APPOINTMENT_DETAIL:
                return (T) new AppointmentDetailDAOImpl();
            case CUSTOMER_PAYMENT:    
                return (T) new CustomerPaymentDAOImpl();
            case SERVICE:
                return (T) new ServiceDAOImpl();
            case EMPLOYEE:
                return (T) new EmployeeDAOImpl();
            case RAW_MATERIAL:
                return (T) new RawMaterialDAOImpl();
            case RAW_MATERIAL_DETAILS:
                return (T) new RawMaterialDetailDAOImpl();
            case SUPPLIER:
                return (T) new SupplierDAOImpl();
            case SUPPLIER_ORDER:
               return (T) new SupplierOrderDAOImpl();
            case SUPPLIER_PAYMENT:
                return (T) new SupplierPaymentDAOImpl();
            case SUPPLIER_ORDER_DETAIL:
               return (T) new SupplierOrderDetailDAOImpl();
            case CATEGORY:
               return (T) new CategoryDAOImpl();
            case LOGIN:
               return (T) new LoginDAOImpl();
            default:
                return null;
        }
    }
}
