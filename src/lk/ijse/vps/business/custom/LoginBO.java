/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom;

import java.util.ArrayList;
import lk.ijse.vps.business.SuperBO;
import lk.ijse.vps.model.LoginDTO;

/**
 *
 * @author User
 */
public interface LoginBO extends SuperBO{
    
    public boolean addLogin(LoginDTO login) throws Exception;
    
    public boolean deleteLogin(String loginId) throws Exception;
    
    public boolean updateLogin(LoginDTO login) throws Exception;
    
    public ArrayList<LoginDTO> getAllLogins() throws Exception;
    
    public ArrayList<String> getAllLoginUserNames() throws Exception;
}
