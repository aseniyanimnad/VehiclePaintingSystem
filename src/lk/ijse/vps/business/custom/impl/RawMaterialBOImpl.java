/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.vps.business.custom.RawMaterialBO;
import lk.ijse.vps.dao.DAOFactory;
import lk.ijse.vps.dao.custom.RawMaterialDAO;
import lk.ijse.vps.entity.RawMaterial;
import lk.ijse.vps.model.RawMaterialDTO;

/**
 *
 * @author User
 */
public class RawMaterialBOImpl implements RawMaterialBO{
    
    private RawMaterialDAO rawMaterialDAO;
    
    public RawMaterialBOImpl(){
        this.rawMaterialDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RAW_MATERIAL);
    }

    @Override
    public boolean addRawMaterial(RawMaterialDTO rawMaterial) throws Exception {
        return rawMaterialDAO.save(new RawMaterial(
                rawMaterial.getCode(), rawMaterial.getBrand(),rawMaterial.getDate(), 
                rawMaterial.getDescription(), rawMaterial.getCategory(),rawMaterial.getPrice(),rawMaterial.getQty(), rawMaterial.getWarranty() 
                
        ));
    }
    
    @Override
    public boolean deleteRawMaterial(String rawMaterialId) throws Exception {
        return rawMaterialDAO.delete(rawMaterialId);
    }

    @Override
    public boolean updateRawMaterial(RawMaterialDTO rawMaterial) throws Exception {
        return rawMaterialDAO.update(new RawMaterial(rawMaterial.getCode(), rawMaterial.getBrand(),rawMaterial.getDate(), rawMaterial.getDescription(), rawMaterial.getCategory(),rawMaterial.getPrice(),rawMaterial.getQty(), rawMaterial.getWarranty()));
    }

    @Override
    public RawMaterialDTO searchRawMaterial(String RawMaterialId) throws Exception {
        RawMaterial rawMaterial=rawMaterialDAO.search(RawMaterialId);
        return new RawMaterialDTO(rawMaterial.getCode(), rawMaterial.getBrand(),rawMaterial.getDate(), rawMaterial.getDescription(), rawMaterial.getCategory(),rawMaterial.getPrice(), rawMaterial.getQty(), rawMaterial.getWarranty());
    }

    @Override
    public ArrayList<RawMaterialDTO> getALLRawMaterials() throws Exception {
        ArrayList<RawMaterial> rawMaterials=rawMaterialDAO.getAll();
        ArrayList<RawMaterialDTO> dtoList=new ArrayList<>();
        for(RawMaterial e: rawMaterials){
            dtoList.add(new RawMaterialDTO(e.getCode(), e.getBrand(), e.getDate(), e.getDescription(), e.getCategory(), e.getPrice(), e.getQty(), e.getWarranty()));
        }
        return dtoList;
    }

    @Override
    public ArrayList<String> getAllRawMaterialIds() throws Exception {
         ArrayList<RawMaterial> rawMaterials=rawMaterialDAO.getAll();
        ArrayList<String> rawMaterialIdList=new ArrayList<>();
        for(RawMaterial rawMaterial:rawMaterials){
            rawMaterialIdList.add(rawMaterial.getCode());
        }
        return rawMaterialIdList;
    }
    
}
