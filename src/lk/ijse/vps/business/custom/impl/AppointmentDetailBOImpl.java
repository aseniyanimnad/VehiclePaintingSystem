/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom.impl;

import java.sql.Connection;
import java.util.ArrayList;
import lk.ijse.vps.business.custom.AppointmentDetailBO;
import lk.ijse.vps.dao.DAOFactory;
import lk.ijse.vps.dao.custom.AppointmentDAO;
import lk.ijse.vps.dao.custom.AppointmentDetailDAO;
import lk.ijse.vps.dao.custom.CustomerDAO;
import lk.ijse.vps.dao.custom.ServiceDAO;
import lk.ijse.vps.db.DBConnection;
import lk.ijse.vps.entity.Appointment;
import lk.ijse.vps.entity.AppointmentDetail;
import lk.ijse.vps.entity.Customer;
import lk.ijse.vps.entity.Service;
import lk.ijse.vps.model.AppointmentDTO;
import lk.ijse.vps.model.AppointmentDetailDTO;
import lk.ijse.vps.model.CustomerDTO;
import lk.ijse.vps.model.ServiceDTO;

/**
 *
 * @author User
 */
public class AppointmentDetailBOImpl implements AppointmentDetailBO {

    private AppointmentDetailDAO appointmentDetailDAO;
    private CustomerDAO customerDAO;
    private AppointmentDAO appointmentDAO;
    private ServiceDAO serviceDAO;

    public AppointmentDetailBOImpl() {
        this.appointmentDetailDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.APPOINTMENT_DETAIL);
        this.customerDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
        this.appointmentDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.APPOINTMENT);
        this.serviceDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SERVICE);
    }

    @Override
    public ArrayList<AppointmentDetailDTO> getAllAppointmentDetails() throws Exception {
        ArrayList<AppointmentDetail> appointmentDetails=appointmentDetailDAO.getAll();
        ArrayList<AppointmentDetailDTO> dtoList=new ArrayList<>();
        for(AppointmentDetail e: appointmentDetails){
            dtoList.add(new AppointmentDetailDTO(e.getAppointmentId(), e.getServiceId(), e.getCustomerId()));
        }
        return dtoList;
    }

    @Override
    public boolean add(CustomerDTO customer, AppointmentDTO appointment, AppointmentDetailDTO appointmentdetail, ServiceDTO service) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try{
            boolean result=customerDAO.save(new Customer(customer.getCusID(),customer.getCusNIC(), customer.getCusName(), customer.getCusTel()));
            if(!result){
                return false;
            }
            result=appointmentDAO.save(new Appointment(appointment.getAID(), appointment.getADate(), appointment.getAdvance(), appointment.getCusID()));
            if(!result){
                connection.rollback();
                return false;
            }
            result=appointmentDetailDAO.save(new AppointmentDetail(appointmentdetail.getAppointmentId(), appointmentdetail.getServiceId(), appointmentdetail.getCustomerId()));
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

    @Override
    public boolean delete(CustomerDTO customer, AppointmentDTO appointment, AppointmentDetailDTO appointmentdetail, ServiceDTO service) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        
        try{
            boolean result =appointmentDetailDAO.delete(appointmentdetail.getAppointmentId());
            if(!result){
                return false;
                
            }
            result= appointmentDAO.delete(appointment.getAID());
            if(!result){
                connection.rollback();
                return false;
            }
            result=customerDAO.delete(customer.getCusID());
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


