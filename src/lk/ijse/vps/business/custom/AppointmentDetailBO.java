/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom;

import java.util.ArrayList;
import lk.ijse.vps.business.SuperBO;
import lk.ijse.vps.model.AppointmentDTO;
import lk.ijse.vps.model.AppointmentDetailDTO;
import lk.ijse.vps.model.CustomerDTO;
import lk.ijse.vps.model.ServiceDTO;

/**
 *
 * @author User
 */
public interface AppointmentDetailBO extends SuperBO{
    public boolean add(CustomerDTO customer, AppointmentDTO appointment, AppointmentDetailDTO appointmentdetail, ServiceDTO service) throws Exception;
    
    public boolean delete(CustomerDTO customer, AppointmentDTO appointment, AppointmentDetailDTO appointmentdetail, ServiceDTO service)throws Exception;
    
    public ArrayList<AppointmentDetailDTO> getAllAppointmentDetails() throws Exception;
    
}
