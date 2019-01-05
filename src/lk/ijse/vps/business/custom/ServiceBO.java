/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom;

import java.util.ArrayList;
import lk.ijse.vps.business.SuperBO;
import lk.ijse.vps.model.ServiceDTO;

/**
 *
 * @author User
 */
public interface ServiceBO extends SuperBO{
    public boolean addService(ServiceDTO service) throws Exception;
    
    public boolean deleteService(String serviceId) throws Exception;
    
    public boolean updateService(ServiceDTO service) throws Exception;
    
    public ServiceDTO searchService(String ServiceId) throws Exception;
    
    public ArrayList<ServiceDTO> getAllServices() throws Exception;
    
    public ArrayList<String> getAllServiceIds() throws Exception;
    
    public ArrayList<String> getAllServiceNames() throws Exception;

   
  
}
