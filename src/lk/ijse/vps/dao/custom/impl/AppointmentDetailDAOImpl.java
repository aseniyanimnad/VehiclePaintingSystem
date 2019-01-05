/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.vps.dao.CrudUtil;
import lk.ijse.vps.dao.custom.AppointmentDetailDAO;
import lk.ijse.vps.entity.AppointmentDetail;
/**
 *
 * @author User
 */
public class AppointmentDetailDAOImpl implements AppointmentDetailDAO{

    @Override
    public boolean save(AppointmentDetail appointmentDetail) throws Exception {
        //return false;
        return CrudUtil.executeUpdate("INSERT INTO AppointmentDetail VALUES(?,?,?)", appointmentDetail.getAppointmentId(),appointmentDetail.getServiceId(), appointmentDetail.getCustomerId())>0;
    }

    @Override
    public boolean update(AppointmentDetail entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<AppointmentDetail> getAll() throws Exception {
        ArrayList<AppointmentDetail> allAppointmentDetails=new ArrayList<>();
        ResultSet rst=CrudUtil.executeQuery("SELECT * FROM AppointmentDetail ");
        while (rst.next()){
            allAppointmentDetails.add(new AppointmentDetail(rst.getString(1), rst.getString(2), rst.getString(3)));
        }
        return allAppointmentDetails;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE From AppointmentDetail where AID = ? ", id)>0;
    }

    @Override
    public AppointmentDetail search(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * From Appointments where AID=? " , id);
        if (rst.next()) {
            return new AppointmentDetail(rst.getString(1), rst.getString(2), rst.getString(3));
        } else {
            return null;
        } 
    }
    
}
