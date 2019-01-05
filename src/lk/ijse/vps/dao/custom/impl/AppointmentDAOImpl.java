/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.vps.dao.CrudUtil;
import lk.ijse.vps.dao.custom.AppointmentDAO;
import lk.ijse.vps.entity.Appointment;

/**
 *
 * @author User
 */
public class AppointmentDAOImpl implements AppointmentDAO{

    @Override
    public boolean save(Appointment appointment) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO Appointments VALUES (?,?,?,?) ", appointment.getAID(), appointment.getADate(), appointment.getAdvance(), appointment.getCusID())>0;
    }

    @Override
    public boolean update(Appointment appointment) throws Exception {
        return CrudUtil.executeUpdate("UPDATE Appointments SET ADate=?, Advance=?, CusID=?, WHERE AID = ? " , appointment.getADate(), appointment.getAdvance(), appointment.getCusID(),appointment.getAID())>0;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE From Appointments where AID=? " , id)>0;
    }

    @Override
    public Appointment search(String id) throws Exception {
       ResultSet rst = CrudUtil.executeQuery("Select * From Appointments where aid=? " , id);
        if (rst.next()) {
            return new Appointment(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getString(4));
        } else {
            return null;
        } 
    }

    @Override
    public ArrayList<Appointment> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * From Appointments " );
        ArrayList<Appointment> appointmentList = new ArrayList<>();
        while (rst.next()) {
            Appointment appointment = new Appointment(rst.getString("AID"), rst.getString("ADate"), rst.getDouble("Advance"), rst.getString("CusID"));
            appointmentList.add(appointment);
        }
        return appointmentList;
    }
    
}
