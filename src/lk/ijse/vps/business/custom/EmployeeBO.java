/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom;

import java.util.ArrayList;
import lk.ijse.vps.business.SuperBO;
import lk.ijse.vps.model.EmployeeDTO;

/**
 *
 * @author User
 */
public interface EmployeeBO extends SuperBO{
    public boolean addEmployee(EmployeeDTO employee) throws Exception;
    
    public boolean deleteEmployee(String employeeId) throws Exception;
    
    public boolean updateEmployee(EmployeeDTO employee) throws Exception;
    
    public EmployeeDTO searchEmployee(String EmployeeId) throws Exception;
    
    public ArrayList<EmployeeDTO> getAllEmployees() throws Exception;
    
    public ArrayList<String> getAllEmployeeIds() throws Exception;
    
}
