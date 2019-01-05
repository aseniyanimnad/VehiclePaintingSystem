/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.vps.business.custom.EmployeeBO;
import lk.ijse.vps.dao.DAOFactory;
import lk.ijse.vps.dao.custom.EmployeeDAO;
import lk.ijse.vps.entity.Employee;
import lk.ijse.vps.model.EmployeeDTO;

/**
 *
 * @author User
 */
public class EmployeeBOImpl implements EmployeeBO{
    
    private EmployeeDAO employeeDAO;
    
    public EmployeeBOImpl(){
        this.employeeDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    }

    @Override
    public boolean addEmployee(EmployeeDTO employee) throws Exception {
        return employeeDAO.save(new Employee(employee.getId(), employee.getName(), employee.getPosition(), employee.getTelephoneNumber(), employee.getAddress()));
    }

    @Override
    public boolean deleteEmployee(String employeeId) throws Exception {
        return employeeDAO.delete(employeeId);
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employee) throws Exception {
        return employeeDAO.update(new Employee(employee.getId(), employee.getName(), employee.getPosition(), employee.getTelephoneNumber(), employee.getAddress()));
    }

    @Override
    public EmployeeDTO searchEmployee(String EmployeeId) throws Exception {
        Employee employee= employeeDAO.search(EmployeeId);
        return new EmployeeDTO(employee.getId(), employee.getName(), employee.getPosition(), employee.getTelephoneNumber(), employee.getAddress());
    }

    @Override
    public ArrayList<EmployeeDTO> getAllEmployees() throws Exception {
        ArrayList<Employee> employees=employeeDAO.getAll();
        ArrayList<EmployeeDTO> dtoList=new ArrayList<>();
        for(Employee e: employees){
            dtoList.add(new EmployeeDTO(e.getId(), e.getName(), e.getPosition(), e.getTelephoneNumber(), e.getAddress()));
        }
        return dtoList;
    }

  
    @Override
    public ArrayList<String> getAllEmployeeIds() throws Exception {
        ArrayList<Employee> employeeList=employeeDAO.getAll();
        ArrayList<String> employeeIdList=new ArrayList<>();
        for(Employee employee:employeeList){
            employeeIdList.add(employee.getId());
        }
        return employeeIdList;
    }
      
    
}
