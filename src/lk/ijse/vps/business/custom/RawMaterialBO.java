/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.business.custom;

import java.util.ArrayList;
import lk.ijse.vps.business.SuperBO;
import lk.ijse.vps.model.RawMaterialDTO;


/**
 *
 * @author User
 */
public interface RawMaterialBO extends SuperBO{
    public boolean addRawMaterial(RawMaterialDTO rawMaterial) throws Exception;
    
    public boolean deleteRawMaterial(String rawMaterialId) throws Exception;
    
    public boolean updateRawMaterial(RawMaterialDTO rawMaterial) throws Exception;
    
    public RawMaterialDTO searchRawMaterial(String RawMaterialId) throws Exception;
    
    public ArrayList<RawMaterialDTO> getALLRawMaterials() throws Exception;
    
    public ArrayList<String> getAllRawMaterialIds() throws Exception;
    
}
