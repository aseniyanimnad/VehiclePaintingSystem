/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.vps.business.BOFactory;
import lk.ijse.vps.business.custom.EmployeeBO;
import lk.ijse.vps.common.IDGenarator;
import lk.ijse.vps.common.PasswordUtil;
import lk.ijse.vps.dao.CrudUtil;
import lk.ijse.vps.model.EmployeeDTO;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SettingsController implements Initializable {
    
    @FXML
    private AnchorPane pnlSettings;

    @FXML
    private TextField txtEmployeeId;
    @FXML
    private JFXTextField txtEmpName;
    @FXML
    private JFXTextField txtEMPPos;
    @FXML
    private JFXTextField txtEmpTel;
    @FXML
    private JFXTextField txtEMPAddress;
    @FXML
    private JFXButton btnAddEmployee;
    @FXML
    private JFXButton btnUpdateEmployee;
    @FXML
    private JFXButton btnDeleteEmployee;
    @FXML
    private TableView<EmployeeDTO> tableEmployee;
    @FXML
    private JFXButton btnChangePW;

    /**
     * Initializes the controller class.
     * 
     */
    
    private static EmployeeBO employeeBO;
    @FXML
    private JFXTextField txtNewUser;
    @FXML
    private JFXTextField txtNewPassword;
    @FXML
    private JFXTextField txtConformationPassword;
    
    public SettingsController(){
        employeeBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.EMPLOYEE);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        FadeTransition fadeIn = new FadeTransition(Duration.millis(800), pnlSettings);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
        
        getAllEmployees();
        EmployeeID();
    }    

    @FXML
    private void addEmployee(ActionEvent event) throws Exception {
        EmployeeDTO employeeDTO=new EmployeeDTO(txtEmployeeId.getText(), txtEmpName.getText(), txtEMPPos.getText(), txtEmpTel.getText(), txtEMPAddress.getText());
        boolean isAdded = employeeBO.addEmployee(employeeDTO);
        if(isAdded){
            
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Added Successful !", ButtonType.OK);
            a.show();
            getAllEmployees();
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR, "Sommething went wrong !", ButtonType.OK);;
            a.show();
        }
    }

    @FXML
    private void updateEmployee(ActionEvent event) throws Exception {
      boolean updateEmployee = employeeBO.updateEmployee(new EmployeeDTO(txtEmployeeId.getText(), txtEmpName.getText(), txtEMPPos.getText(), txtEmpTel.getText(), txtEMPAddress.getText()));
        if(updateEmployee){
            Alert a=new Alert(Alert.AlertType.INFORMATION, "Updated !", ButtonType.OK);
            a.show();
            getAllEmployees();
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR, "Something went wrong !", ButtonType.OK);
            a.show();
        }  
    }

    @FXML
    private void deleteEmployee(ActionEvent event) throws Exception {
       boolean isAdded = employeeBO.deleteEmployee(txtEmployeeId.getText());
        if (isAdded) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "IS Ok", ButtonType.OK);
            a.show();
            getAllEmployees();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK);
            a.show();
        } 
    }

    @FXML
    private void onEmployeeMouseClicked(MouseEvent event) {
        EmployeeDTO selectedItem =tableEmployee.getSelectionModel().getSelectedItem();
        txtEmployeeId.setText(selectedItem.getId());
        txtEmpName.setText(selectedItem.getName());
        txtEMPPos.setText(selectedItem.getPosition());
        txtEmpTel.setText(selectedItem.getTelephoneNumber());
        txtEMPAddress.setText(selectedItem.getAddress());
    }

    private void getAllEmployees() {
        try {
              tableEmployee.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
              tableEmployee.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
              tableEmployee.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("position"));
              tableEmployee.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));
              tableEmployee.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("address"));
              
              
              ArrayList<EmployeeDTO> employee=employeeBO.getAllEmployees();
              tableEmployee.setItems(FXCollections.observableArrayList(employee));
          } catch (Exception ex) {
              Logger.getLogger(ManageSupplierController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }


    @FXML
    private void empNameAction(ActionEvent event) {
         String EmName=txtEmpName.getText();
        if(!EmName.matches("[A-z .]+")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Employee Name" , ButtonType.OK);
            alert.show();
            txtEmpName.selectAll();
            
            txtEmpName.requestFocus();
        }else{
            
            txtEMPPos.requestFocus();
        }
    }

    @FXML
    private void empPosAction(ActionEvent event) {
         String EmPos=txtEMPPos.getText();
        if(!EmPos.matches("[A-z .]+")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Employee Posistion" , ButtonType.OK);
            alert.show();
            txtEMPPos.selectAll();
            
            txtEMPPos.requestFocus();
        }else{
            
            txtEmpTel.requestFocus();
        }
    }

    @FXML
    private void empTelAction(ActionEvent event) {
         String EmTel=txtEmpTel.getText();
        if(!EmTel.matches("[0-9]{10}")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Employee Conatct Number" , ButtonType.OK);
            alert.show();
            txtEmpTel.selectAll();
            
            txtEmpTel.requestFocus();
        }else{
            
            txtEMPAddress.requestFocus();
        }
    }

    @FXML
    private void empAddressAction(ActionEvent event) {
         String EmAdd=txtEMPAddress.getText();
        if(!EmAdd.matches("[A-z][0-9]")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Employee Address " , ButtonType.OK);
            alert.show();
            txtEMPAddress.selectAll();
            
            txtEMPAddress.requestFocus();
        }else{
            
            txtEMPAddress.requestFocus();
        }
    }

    private void EmployeeID() {
        try {
            String EID;
            EID = IDGenarator.getNewID("Employee", "EID", "E");
            txtEmployeeId.setText(EID);
        } catch (SQLException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addNewUser(ActionEvent event) throws Exception {
        try {
            String userName=txtNewUser.getText();
            String password=txtNewPassword.getText();
            String salt=PasswordUtil.getSalt(30);
            String secPw=PasswordUtil.generateSecurePassword(password, salt);
            CrudUtil.executeUpdate("Insert into user values(?,?,?,?)", "001",userName,salt,secPw);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
