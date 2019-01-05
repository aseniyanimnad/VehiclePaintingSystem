/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.vps.dao.CrudUtil;
import lk.ijse.vps.dao.custom.RawMaterialDAO;
import lk.ijse.vps.entity.RawMaterial;

/**
 *
 * @author User
 */
public class RawMaterialDAOImpl implements RawMaterialDAO{

    @Override
    public boolean save(RawMaterial rawMaterial) throws Exception {
       return CrudUtil.executeUpdate("INSERT INTO rawmaterial VALUES (?,?,?,?,?,?,?,? ) ", 
               rawMaterial.getCode(), rawMaterial.getBrand(), rawMaterial.getDate(), 
               rawMaterial.getDescription(),rawMaterial.getCategory(),rawMaterial.getPrice(),
               rawMaterial.getQty(),rawMaterial.getWarranty())>0; 
    }

    @Override
    public boolean update(RawMaterial rawMaterial) throws Exception {
        return CrudUtil.executeUpdate("UPDATE RawMaterial SET Brand =?, Date =?, Description =?, Category =?, Price =?, RQTY =? , Warantee =?  WHERE RCode = ? ", rawMaterial.getBrand(), rawMaterial.getDate(), rawMaterial.getDescription(), rawMaterial.getCategory(),rawMaterial.getPrice(), rawMaterial.getWarranty(),rawMaterial.getQty())>0;
    }

    @Override
    public boolean delete(String code) throws Exception {
        return CrudUtil.executeUpdate("DELETE From RawMaterial where RCode = ? " , code)>0;
    }

    @Override
    public RawMaterial search(String code) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * From RawMaterial where RCode = ? ", code);
        if (rst.next()) {
            return new RawMaterial(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getDouble(6),rst.getInt(7),rst.getString(8));
        } else {
            return null;
        } 
    }

    @Override
    public ArrayList<RawMaterial> getAll() throws Exception {
       ResultSet rst = CrudUtil.executeQuery("Select * From RawMaterial " );
        ArrayList<RawMaterial> materialList = new ArrayList<>();
        while (rst.next()) {
            RawMaterial rawMaterial = new RawMaterial(rst.getString(1),rst.getString(2),rst.getString(3), rst.getString(4), rst.getString(5),rst.getDouble(6), rst.getInt(7), rst.getString(8));
            materialList.add(rawMaterial);
        }
        return materialList; 
    }
    
}
