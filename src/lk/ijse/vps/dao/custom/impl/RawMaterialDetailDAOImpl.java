/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.dao.custom.impl;

import java.util.ArrayList;
import lk.ijse.vps.dao.CrudUtil;
import lk.ijse.vps.dao.custom.RawMaterialDetailDAO;
import lk.ijse.vps.entity.RawMaterialDetail;
import lk.ijse.vps.entity.RawMaterialDetail_PK;

/**
 *
 * @author User
 */
public class RawMaterialDetailDAOImpl implements RawMaterialDetailDAO{

    @Override
    public boolean save(RawMaterialDetail rawMaterialDetail) throws Exception {
        return CrudUtil.executeUpdate("Insert into RawMaterialDetail Values(?,?)", rawMaterialDetail.getRawMaterialDetail_PK().getRawMaterialCode(),rawMaterialDetail.getRawMaterialDetail_PK().getEmployeeId())>0;
    }

    @Override
    public boolean update(RawMaterialDetail entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(RawMaterialDetail_PK id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RawMaterialDetail search(RawMaterialDetail_PK id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<RawMaterialDetail> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
