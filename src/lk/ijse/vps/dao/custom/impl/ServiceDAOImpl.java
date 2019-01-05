/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.vps.dao.CrudUtil;
import lk.ijse.vps.dao.custom.ServiceDAO;
import lk.ijse.vps.entity.Service;

/**
 *
 * @author User
 */
public class ServiceDAOImpl implements ServiceDAO{

    @Override
    public boolean save(Service service) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO Service VALUES (?,?) ", service.getId(),service.getServiceCategoryName())>0;
    }

    @Override
    public boolean update(Service service) throws Exception {
        return CrudUtil.executeUpdate("UPDATE Service SET ServiceCategoryName = ?  WHERE SerID = ? ",service.getServiceCategoryName(),service.getId())>0;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE From Service where SerID = ? ", id)>0;
    }

    @Override
    public Service search(String id) throws Exception {
        ResultSet rst=CrudUtil.executeQuery("Select * From Service where serId= ? ", id);
        if(rst.next()){
            return new Service(rst.getString(1),rst.getString(2));
        }else{
            return null;
        }
    }

    @Override
    public ArrayList<Service> getAll() throws Exception {
        ArrayList<Service> serviceList=new ArrayList<>();
         ResultSet rst=CrudUtil.executeQuery("Select*From Service ");
        while(rst.next()){
            Service service=new Service(rst.getString(1),rst.getString(2));
            serviceList.add(service);
        }
        return serviceList;
    }
    
}
