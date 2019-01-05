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
import lk.ijse.vps.business.custom.ServiceBO;
import lk.ijse.vps.business.custom.impl.ServiceBOImpl;
import lk.ijse.vps.common.IDGenarator;
import lk.ijse.vps.model.ServiceDTO;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ServiceCategoryController implements Initializable {

    @FXML
    private AnchorPane serviceCatePanel;
    @FXML
    private JFXButton btnAddSerCate;
    
    @FXML
    private JFXTextField txtServiceCategory;
    
    @FXML
    private JFXTextField txtServiceCategoID;
    
    @FXML
    private ImageView imgClose;


    /**
     * Initializes the controller class.
     */
    
    private static ServiceBO serviceBO=new ServiceBOImpl();
    @FXML
    private JFXTextField txtServicePrice;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        FadeTransition fadeIn = new FadeTransition(Duration.millis(800), serviceCatePanel);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
        
        ServiceCategoryID();       
    }    

    @FXML
    private void addService(ActionEvent event) throws Exception {
       
        ServiceDTO serviceDTO=new ServiceDTO(txtServiceCategoID.getText(),txtServiceCategory.getText());
        boolean isAdded=serviceBO.addService(serviceDTO);
        if(isAdded){
            Alert a=new Alert(Alert.AlertType.INFORMATION, "Added Successful ! ", ButtonType.OK);
            a.showAndWait();
            Window window=serviceCatePanel.getScene().getWindow();
            ((Stage)window).close();
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR, "Something went wrong ! ", ButtonType.OK);
            a.show();
        }
    }

    @FXML
    private void servicecatoNamrAction(ActionEvent event) {
//           String SerCatoID=txtServiceCategoID.getText();
//        if(!SerCatoID.matches("[A-Z,0-9]{6}")){
//            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Service category ID ", ButtonType.OK);
//            alert.show();
//            txtServiceCategoID.selectAll();
//            txtServiceCategoID.setStyle("-fx-background-color: red;");
//            txtServiceCategoID.requestFocus();
//        }else{
//            txtServiceCategoID.setStyle("-fx-background-color: white;");
//            txtServiceCategoID.requestFocus();
//        }
    }


    private void ServiceCategoryID() {
        try {
            String SCatoId;
            SCatoId = IDGenarator.getNewID("Service", "SerID", "SC");
            txtServiceCategoID.setText(SCatoId);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServiceCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void closePanel(MouseEvent event) {
         Window window=serviceCatePanel.getScene().getWindow();
        ((Stage)window).close();
    }

    @FXML
    private void servicePriceAction(ActionEvent event) {
    }
}
