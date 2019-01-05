/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import lk.ijse.vps.business.BOFactory;
import lk.ijse.vps.business.custom.CategoryBO;
import lk.ijse.vps.business.custom.impl.CategoryBOImpl;
import lk.ijse.vps.common.IDGenarator;
import lk.ijse.vps.model.CategoryDTO;
import lk.ijse.vps.model.RawMaterialDTO;

/**
 * FXML Controller class
 *
 * @author User
 */
public class RawMaterialCatogeryController implements Initializable {

    
    @FXML
    private AnchorPane AddPane;
    
    @FXML
    private JFXTextField txtAddRawMaterialId;
    
     @FXML
    private JFXTextField txtAddRawMaterialName;

    @FXML
    private JFXButton btnAddRMCategory;
    
    @FXML
    private ImageView imgClose;

    /**
     * Initializes the controller class.
     */
    
    private static CategoryBO categoryBO=new CategoryBOImpl();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        FadeTransition fadeIn = new FadeTransition(Duration.millis(800), AddPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
        
        RawMaterialCategoryID();
        
    }  
    
   
    
    
    @FXML
    void AddCategory(ActionEvent event) throws Exception {
        CategoryDTO categoryDTO=new CategoryDTO(txtAddRawMaterialId.getText(), txtAddRawMaterialName.getText());
        boolean isAdded=categoryBO.addCategory(categoryDTO);
        if(isAdded){
            Alert a=new Alert(Alert.AlertType.INFORMATION, "Added Successful ! ", ButtonType.OK);
            a.showAndWait();
            Window window=AddPane.getScene().getWindow();
            ((Stage)window).close();
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR, "Something went wrong ! ", ButtonType.OK);
            a.show();
        }
    }

    @FXML
    private void RMCategoryName(ActionEvent event) {
//          String RMCatoID=txtAddRawMaterialId.getText();
//        if(!RMCatoID.matches("[A-Z,0-9]{6}")){
//            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Raw Material category ID ", ButtonType.OK);
//            alert.show();
//            txtAddRawMaterialId.selectAll();
//            txtAddRawMaterialId.setStyle("-fx-background-color: red;");
//            txtAddRawMaterialId.requestFocus();
//        }else{
//            txtAddRawMaterialId.setStyle("-fx-background-color: white;");
//            txtAddRawMaterialId.requestFocus();
//        }
    }


    private void RawMaterialCategoryID() {
        try {
            String RMCatoId;
            RMCatoId = IDGenarator.getNewID("CATEGORY", "catID", "RC");
            txtAddRawMaterialId.setText(RMCatoId);
        } catch (SQLException ex) {
            Logger.getLogger(RawMaterialCatogeryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RawMaterialCatogeryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RawMaterialCatogeryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void closeCatoPanel(MouseEvent event) {
        Window window=AddPane.getScene().getWindow();
        ((Stage)window).close();
    }
}

    


