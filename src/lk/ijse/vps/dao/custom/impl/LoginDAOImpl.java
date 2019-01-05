/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.vps.dao.CrudUtil;
import lk.ijse.vps.dao.custom.LoginDAO;
import lk.ijse.vps.entity.Login;

/**
 *
 * @author User
 */
public class LoginDAOImpl implements LoginDAO{

    @Override
    public boolean save(Login login) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO LOGIN VALUES (?,?)", login.getUserName(),login.getPassword())>0;
    }

    @Override
    public boolean update(Login login) throws Exception {
               return CrudUtil.executeUpdate("UPDATE LOGIN SET Password  =? WHERE  UserName  = ? ", login.getPassword(), login.getUserName())>0;

    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE From LOGIN where UserName = ? ", id)>0;
    }

    @Override
    public Login search(String id) throws Exception {
        ResultSet rst= CrudUtil.executeQuery("Select*From LOGIN where UserName = ? ", id);
        if(rst.next()){
            return new Login(rst.getString("UserName"), rst.getString("Password "));
        }else{
            return null;
        }
    }

    @Override
    public ArrayList<Login> getAll() throws Exception {
        ArrayList<Login> logins=new ArrayList<>();
        ResultSet rst=CrudUtil.executeQuery("SELECT*FROM LOGIN");
        while (rst.next()){
            logins.add(new Login(rst.getString(1), rst.getString(2)));
        }
        return logins;
    }
    
}
