/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.vps.dao.CrudUtil;
import lk.ijse.vps.dao.custom.EmployeeDAO;
import lk.ijse.vps.entity.Employee;

/**
 *
 * @author User
 */
public class EmployeeDAOImpl implements EmployeeDAO{

    @Override
    public boolean save(Employee employee) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO Employee VALUES (?,?,?,?,?)", employee.getId(),employee.getName(),employee.getPosition(),employee.getTelephoneNumber(),employee.getAddress())>0;
    }

    @Override
    public boolean update(Employee employee) throws Exception {
       return CrudUtil.executeUpdate("UPDATE Employee SET EName =?, EPos =?, ETel =?, EAddress =? WHERE EID  = ? ", employee.getName(), employee.getPosition(),employee.getTelephoneNumber(),employee.getAddress(), employee.getId())>0;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE From Employee where EID = ? ", id)>0;
    }

    @Override
    public Employee search(String id) throws Exception {
        ResultSet rst= CrudUtil.executeQuery("Select*From Employee where EID = ? ", id);
        if(rst.next()){
            return new Employee(rst.getString("EID"), rst.getString("EName"),rst.getString("EPos"),rst.getString("ETel"),rst.getString("EAddress"));
        }else{
            return null;
        }
    }

    @Override
    public ArrayList<Employee> getAll() throws Exception {
        ArrayList<Employee> allEmployees=new ArrayList<>();
        ResultSet rst=CrudUtil.executeQuery("SELECT*FROM Employee");
        while (rst.next()){
            allEmployees.add(new Employee(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5)));
        }
        return allEmployees;
    }
    
}
