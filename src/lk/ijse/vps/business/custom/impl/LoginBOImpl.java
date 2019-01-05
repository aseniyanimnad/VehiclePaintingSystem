/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.vps.business.custom.LoginBO;
import lk.ijse.vps.dao.DAOFactory;
import lk.ijse.vps.dao.custom.LoginDAO;
import lk.ijse.vps.entity.Login;
import lk.ijse.vps.model.LoginDTO;

/**
 *
 * @author User
 */
public class LoginBOImpl implements LoginBO{
    
    private LoginDAO loginDAO;
    
    public LoginBOImpl(){
        this.loginDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.LOGIN);
    }

    @Override
    public boolean addLogin(LoginDTO login) throws Exception {
        return loginDAO.save(new Login(login.getUserName(), login.getPassword()));
    }

    @Override
    public boolean deleteLogin(String loginId) throws Exception {
        return loginDAO.delete(loginId);
    }

    @Override
    public boolean updateLogin(LoginDTO login) throws Exception {
        return loginDAO.update(new Login(login.getUserName(), login.getPassword()));
    }

    @Override
    public ArrayList<LoginDTO> getAllLogins() throws Exception {
        ArrayList<Login> logins=loginDAO.getAll();
        ArrayList<LoginDTO> dtoList=new ArrayList<>();
        for(Login e: logins){
            dtoList.add(new LoginDTO(e.getUserName(), e.getPassword()));
        }
        return dtoList;
    }

    @Override
    public ArrayList<String> getAllLoginUserNames() throws Exception {
        ArrayList<Login> loginList=loginDAO.getAll();
        ArrayList<String> loginUserNameList=new ArrayList<>();
        for(Login login:loginList){
            loginUserNameList.add(login.getUserName());
        }
        return loginUserNameList;
    }
    
}
