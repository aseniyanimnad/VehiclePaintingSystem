/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom.impl;

import java.sql.Connection;
import java.util.ArrayList;
import lk.ijse.vps.business.custom.AppointmentBO;
import lk.ijse.vps.dao.DAOFactory;
import lk.ijse.vps.dao.custom.AppointmentDAO;
import lk.ijse.vps.dao.custom.AppointmentDetailDAO;
import lk.ijse.vps.dao.custom.ServiceDAO;
import lk.ijse.vps.dao.custom.impl.AppointmentDAOImpl;
import lk.ijse.vps.dao.custom.impl.ServiceDAOImpl;
import lk.ijse.vps.db.DBConnection;
import lk.ijse.vps.entity.Appointment;
import lk.ijse.vps.entity.AppointmentDetail;
import lk.ijse.vps.entity.Service;
import lk.ijse.vps.model.AppointmentDTO;
import lk.ijse.vps.model.AppointmentDetailDTO;

/**
 *
 * @author User
 */
public class AppointmentBOImpl implements AppointmentBO{
    
    private AppointmentDAO appointmentDAO;
    
    private ServiceDAO serviceDAO;
    
    public AppointmentBOImpl(){
        this.appointmentDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.APPOINTMENT);
        this.serviceDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SERVICE);
        
    }
    

    @Override
    public boolean addAppointment(AppointmentDTO appointment) throws Exception {
        Connection connection=DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        
        try{
            boolean result=appointmentDAO.save(new Appointment(appointment.getAID(), appointment.getADate(), appointment.getAdvance(), appointment.getCusID()));
            if(!result){
                return false;
            }
            
//            result=serviceDAO.save(new Service(appointment.getService().getId(), appointment.getService().getServiceCategoryName()));
//            if(!result){
//                connection.rollback();
//                return false;
//            }
            connection.commit();
            return true;
        }finally{
            connection.setAutoCommit(true);
        }
    }
        
  
    @Override
    public boolean deleteAppointment(String appointmentId) throws Exception {
        return appointmentDAO.delete(appointmentId);
    }

    @Override
    public boolean updateAppointment(AppointmentDTO appointment) throws Exception {
        Connection connection=DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        
        try{
            boolean result=appointmentDAO.update(new Appointment(appointment.getAID(), appointment.getADate(), appointment.getAdvance(), appointment.getCusID()));
            if(!result){
                return false;
            }
//            result=serviceDAO.update(new Service(appointment.getService().getId(), appointment.getService().getServiceCategoryName()));
//            if(!result){
//                connection.rollback();
//                return false;
//            }
            connection.commit();
            return true;
        }finally{
            connection.setAutoCommit(true);
        }
    }
    
    @Override
    public AppointmentDTO searchAppointment(String appointmentId) throws Exception {
        Appointment appointment=appointmentDAO.search(appointmentId);
        return new AppointmentDTO(appointment.getAID(),appointment.getADate(),appointment.getAdvance(),appointment.getCusID());
        }
    

    @Override
    public ArrayList<AppointmentDTO> getAllAppointments() throws Exception {
        ArrayList<Appointment>appointments=appointmentDAO.getAll();
        ArrayList<AppointmentDTO>dtoList=new ArrayList<>();
        for(Appointment e:appointments){
            dtoList.add(new AppointmentDTO(e.getAID(), e.getADate(), e.getAdvance(), e.getCusID()));
        }
        return dtoList;
    }

    @Override
    public ArrayList<String> getAllAppointmentIds() throws Exception {
        ArrayList<Appointment>appointments=appointmentDAO.getAll();
        ArrayList<String>appointmentIdList=new ArrayList<>();
        for(Appointment appointment:appointments){
           appointmentIdList.add(appointment.getAID());
        }
        return appointmentIdList;
    }
        
}
