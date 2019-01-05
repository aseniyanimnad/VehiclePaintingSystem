/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.vps.business.custom.ServiceBO;
import lk.ijse.vps.dao.DAOFactory;
import lk.ijse.vps.dao.custom.ServiceDAO;
import lk.ijse.vps.entity.Service;
import lk.ijse.vps.model.ServiceDTO;

/**
 *
 * @author User
 */
public class ServiceBOImpl implements ServiceBO{
    
    private ServiceDAO serviceDAO;
    
    public ServiceBOImpl(){
        this.serviceDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SERVICE);
    }

    @Override
    public boolean addService(ServiceDTO service) throws Exception {
        return serviceDAO.save(new Service(service.getId(), service.getServiceCategoryName()));
    }

    @Override
    public boolean deleteService(String serviceId) throws Exception {
        return serviceDAO.delete(serviceId);
    }

    @Override
    public boolean updateService(ServiceDTO service) throws Exception {
         return serviceDAO.update(new Service(service.getId(), service.getServiceCategoryName()));
    }

    @Override
    public ServiceDTO searchService(String ServiceId) throws Exception {
        Service service=serviceDAO.search(ServiceId);
        return new ServiceDTO(service.getId(), service.getServiceCategoryName());
    }

    @Override
    public ArrayList<ServiceDTO> getAllServices() throws Exception {
        ArrayList<Service> services=serviceDAO.getAll();
        ArrayList<ServiceDTO> dtoList=new ArrayList<>();
        for(Service e: services){
            dtoList.add(new ServiceDTO(e.getId(), e.getServiceCategoryName()));
        }
        return dtoList;
    }

    @Override
    public ArrayList<String> getAllServiceIds() throws Exception {
        ArrayList<Service> serviceList=serviceDAO.getAll();
        ArrayList<String> serviceIdList=new ArrayList<>();
        for(Service service:serviceList){
            serviceIdList.add(service.getId());
        }
        return serviceIdList;
    }

    @Override
    public ArrayList<String> getAllServiceNames() throws Exception {
        ArrayList<Service> serviceLists=serviceDAO.getAll();
        ArrayList<String> serviceNameList=new ArrayList<>();
        for(Service service:serviceLists){
            serviceNameList.add(service.getServiceCategoryName());
        }
        return serviceNameList;
       
    }

   

   

}