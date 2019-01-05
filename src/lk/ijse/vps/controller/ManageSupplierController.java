/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import lk.ijse.vps.business.custom.RawMaterialBO;
import lk.ijse.vps.business.custom.SupplierBO;
import lk.ijse.vps.business.custom.SupplierOrderBO;
import lk.ijse.vps.business.custom.SupplierOrderDetailBO;
import lk.ijse.vps.business.custom.impl.RawMaterialBOImpl;
import lk.ijse.vps.business.custom.impl.SupplierBOImpl;
import lk.ijse.vps.business.custom.impl.SupplierOrderBOImpl;
import lk.ijse.vps.business.custom.impl.SupplierOrderDetailBOImpl;
import lk.ijse.vps.common.IDGenarator;
import lk.ijse.vps.model.RawMaterialDTO;
import lk.ijse.vps.model.SupplierDTO;
import lk.ijse.vps.model.SupplierOrderDTO;
import lk.ijse.vps.model.SupplierOrderDetailDTO;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ManageSupplierController implements Initializable {
    
    @FXML
    private AnchorPane panelManageSupplier;


    @FXML
    private TextField txtSupplierId;

    @FXML
    private JFXTextField txtSupplierName;

    @FXML
    private JFXTextField txtSupEmail;

    @FXML
    private JFXTextField txtSupplierTel;

    @FXML
    private JFXTextField txtSupplierAddress;

    @FXML
    private JFXButton btnAddSupplier;

    @FXML
    private JFXButton btnUpdateSupplier;

    @FXML
    private JFXButton btnDeleteSupplier;

    @FXML
    private TextField txtSearchSupplier;

    @FXML
    private TableView<SupplierDTO> tableSupplier;

    @FXML
    private TextField txtSupplierOrderId;

    @FXML
    private TextField txtSearchOrder;

    @FXML
    private TextField txtOrderDAte;

    @FXML
    private JFXButton btnAddSupOrder;

    @FXML
    private JFXButton btnUpdateSupOrder;

    @FXML
    private JFXButton btnDeleteSupOrder;

    @FXML
    private TableView<SupplierOrderDTO> tableSupplierOrder;

    @FXML
    private JFXComboBox<String> cmbSupplier;

    @FXML
    private JFXTextField txtOrderQTY;
    @FXML
    private JFXComboBox<String> cmbRMID;

    /**
     * Initializes the controller class.
     */
    private static SupplierBO supplierBO;
    SupplierOrderBO supplierOrderBO;
    SupplierOrderDetailBO supplierOrderDetailBO;
    private RawMaterialBO rawMaterialBO;

    public ManageSupplierController() {
        supplierOrderBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER_ORDER);
        supplierBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER);
        supplierOrderDetailBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER_ORDER_DETAIL);
        rawMaterialBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.RAW_MATERIAL);
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        FadeTransition fadeIn = new FadeTransition(Duration.millis(800), panelManageSupplier);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
        
        getAllSuppliers();
        setDateTime();
        SupplierID();
        loadSupplierID();
        loadSupplierOrderID();
        try {
            getAllSupplierOrders();
            loadRawMaterialID();
        } catch (Exception ex) {
            Logger.getLogger(ManageSupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void addSupplier(ActionEvent event) throws Exception {
        SupplierDTO supplierDTO = new SupplierDTO(txtSupplierId.getText(), txtSupplierName.getText(), txtSupplierTel.getText(), txtSupEmail.getText(), txtSupplierAddress.getText());
        boolean isAdded = supplierBO.addSupplier(supplierDTO);
        if (isAdded) {
            getAllSuppliers();
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Added Successful", ButtonType.OK);
            a.show();
            getAllSuppliers();
            txtSupplierName.clear();
            txtSupplierTel.clear();
            txtSupEmail.clear();
            txtSupplierAddress.clear();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK);
            a.show();

        }
    }

    @FXML
    private void updateSupplier(ActionEvent event) throws Exception {
        boolean updateSupplier = supplierBO.updateSupplier(new SupplierDTO(txtSupplierId.getText(), txtSupplierName.getText(), txtSupplierTel.getText(), txtSupEmail.getText(), txtSupplierAddress.getText()));
        if (updateSupplier) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Updated !", ButtonType.OK);
            a.show();
            getAllSuppliers();
            txtSupplierName.clear();
            txtSupplierTel.clear();
            txtSupEmail.clear();
            txtSupplierAddress.clear();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Something went wrong !", ButtonType.OK);
            a.show();
        }
    }

    @FXML
    private void deleteSupplier(ActionEvent event) throws Exception {
        boolean isAdded = supplierBO.deleteSupplier(txtSupplierId.getText());
        if (isAdded) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "IS Ok", ButtonType.OK);
            a.show();
            getAllSuppliers();
            txtSupplierName.clear();
            txtSupplierTel.clear();
            txtSupEmail.clear();
            txtSupplierAddress.clear();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK);
            a.show();
        }
    }

    @FXML
    void searchSupplier(ActionEvent event) throws Exception {
        String supplierId = txtSearchSupplier.getText();
        SupplierDTO supplier = supplierBO.searchSupplier(supplierId);
        if (supplier != null) {
            txtSupplierId.setText(supplier.getId());
            txtSupplierName.setText(supplier.getName());
            txtSupplierTel.setText(supplier.getTelephoneNumber());
            txtSupEmail.setText(supplier.getEMail());
            txtSupplierAddress.setText(supplier.getAddress());

        }
    }

    @FXML
    private void addSupplierOrder(ActionEvent event) throws Exception {
       int qty = Integer.parseInt(txtOrderQTY.getText());
       SupplierOrderDTO supplierOrderDTO = new SupplierOrderDTO(txtSupplierOrderId.getText(), (String)cmbSupplier.getSelectionModel().getSelectedItem(), txtOrderDAte.getText(), qty);
       //RawMaterialDTO rawMaterialDTO = new RawMaterialDTO(txt, brand, date, description, category, Double.NaN, qty, warranty)
        SupplierOrderDetailDTO supplierOrderDetailDTO = new SupplierOrderDetailDTO((String)cmbRMID.getSelectionModel().getSelectedItem(), txtSupplierOrderId.getText());
        
        boolean isAdded = supplierOrderDetailBO.add(supplierOrderDTO,supplierOrderDetailDTO);
        if (isAdded) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Added Sucessful", ButtonType.OK);
            a.show();
            getAllSupplierOrders();
            txtOrderQTY.clear();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK);
            a.show();
        }
    }

    @FXML
    private void updateSupplierOrder(ActionEvent event) {

    }

    @FXML
    private void deleteSupplierOrder(ActionEvent event) throws Exception {
        boolean isAdded = supplierOrderBO.deleteSupplierOrder(txtSupplierOrderId.getText());
        if (isAdded) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "IS Ok", ButtonType.OK);
            a.show();
            getAllSupplierOrders();
            txtOrderQTY.clear();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK);
            a.show();
        }
    }

    @FXML
    void searchSupplierOrder(ActionEvent event) throws Exception {
        String SOID = txtSearchOrder.getText();
        SupplierOrderDTO supplierOrder = supplierOrderBO.searchSupplierOrder(SOID);
        if (supplierOrder != null) {
            txtSupplierOrderId.setText(supplierOrder.getId());
            //cmbSupplier.getSelectionModel().select(supplierOrder.getId());
            txtOrderDAte.setText(supplierOrder.getDate());
            txtOrderQTY.setText("" + supplierOrder.getQty());

        }
    }

    private void getAllSuppliers() {

        try {
            tableSupplier.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
            tableSupplier.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
            tableSupplier.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));
            tableSupplier.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("EMail"));
            tableSupplier.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("address"));

            ArrayList<SupplierDTO> supplier = supplierBO.getAllSuppliers();
            tableSupplier.setItems(FXCollections.observableArrayList(supplier));
        } catch (Exception ex) {
            Logger.getLogger(ManageSupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void onSupplierMouseClick(MouseEvent event) {
        SupplierDTO selectedItem = tableSupplier.getSelectionModel().getSelectedItem();
        txtSupplierId.setText(selectedItem.getId());
        txtSupplierName.setText(selectedItem.getName());
        txtSupplierTel.setText(selectedItem.getTelephoneNumber());
        txtSupEmail.setText(selectedItem.getEMail());
        txtSupplierAddress.setText(selectedItem.getAddress());

    }

    @FXML
    void onSupplierOrderMouseClicked(MouseEvent event) {

    }

    private void setDateTime() {
        Timeline newTimeLine = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtOrderDAte.setText(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));

            }

        }), new KeyFrame(Duration.seconds(1)));
        newTimeLine.setCycleCount(Animation.INDEFINITE);
        newTimeLine.play();
    }

    @FXML
    private void supplierNameAction(ActionEvent event) {
          String SName=txtSupplierName.getText();
        if(!SName.matches("[A-z .]+")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Supplier Name ", ButtonType.OK);
            alert.show();
            txtSupplierName.selectAll();
            
            txtSupplierName.requestFocus();
        }else{
            
            txtSupplierTel.requestFocus();
        }
    }

    @FXML
    private void supplierTelAction(ActionEvent event) {
          String STel=txtSupplierTel.getText();
        if(!STel.matches("[0-9]{10}")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Supplier Telephone Number ", ButtonType.OK);
            alert.show();
            txtSupplierTel.selectAll();
            
            txtSupplierTel.requestFocus();
        }else{
            
            txtSupEmail.requestFocus();
        }
    }
    
    @FXML
    void SupEmailAction(ActionEvent event) {
          String SEmail=txtSupEmail.getText();
        if(!SEmail.matches("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Supplier E-mail", ButtonType.OK);
            alert.show();
            txtSupEmail.selectAll();
            
            txtSupEmail.requestFocus();
        }else{
            
            txtSupplierAddress.requestFocus();
        }
    }


    @FXML
    private void supplierAddressAction(ActionEvent event) {
          String SAddress=txtSupplierAddress.getText();
        if(!SAddress.matches("[A-z][0-9]")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Supplier Address ", ButtonType.OK);
            alert.show();
            txtSupplierAddress.selectAll();
            
            txtSupplierAddress.requestFocus();
        }else{
            
            txtSupplierAddress.requestFocus();
        }
    }

    private void SupplierID() {
        try {
            String supId;
            supId = IDGenarator.getNewID("Supplier", "SupID", "S");
            txtSupplierId.setText(supId);
        } catch (SQLException ex) {
            Logger.getLogger(ManageSupplierController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManageSupplierController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManageSupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supOrderQTYAction(ActionEvent event) {
         String SOQTY=txtOrderQTY.getText();
        if(!SOQTY.matches("[A-z .]+")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Supplier Order QTY" , ButtonType.OK);
            alert.show();
            txtOrderQTY.selectAll();
            
            txtOrderQTY.requestFocus();
        }else{
            
            txtOrderQTY.requestFocus();
        }
    }

    private void loadSupplierID() {
        try {
            ArrayList<String> suplierId = supplierBO.getAllSupplierIds();
            cmbSupplier.getItems().clear();
            for (String id : suplierId) {
                cmbSupplier.getItems().add(id);
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageSupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getAllSupplierOrders() throws Exception {
        tableSupplierOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tableSupplierOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        tableSupplierOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("date"));
        tableSupplierOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        //tableSupplierOrder.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("RCode"));
       
        ArrayList<SupplierOrderDTO> supplierOrder = supplierOrderBO.getAllSupplierOrders();
        tableSupplierOrder.setItems(FXCollections.observableArrayList(supplierOrder));
        
        //ArrayList<SupplierOrderDTO>orderDTOs=
        
        
    }

    private void loadRawMaterialID() throws Exception {
        ArrayList<RawMaterialDTO> rawMaterialDTOs = rawMaterialBO.getALLRawMaterials();

        cmbRMID.getItems().clear();

        for (RawMaterialDTO rawMaterialDTO : rawMaterialDTOs) {
            cmbRMID.getItems().add(rawMaterialDTO.getCode());
        }
    }

    private void loadSupplierOrderID() {
        try {
            String supOrderId;
            supOrderId = IDGenarator.getNewID("SupplierOrders", "SOID", "SO");
            txtSupplierOrderId.setText(supOrderId);
        } catch (SQLException ex) {
            Logger.getLogger(ManageSupplierController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManageSupplierController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManageSupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean validation(){
        if(txtSupplierName.getText().trim().isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR, "Supplier Name is Empty", ButtonType.OK);
            a.show();
            return false;
        }else if(txtSupplierTel.getText().trim().isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR, "Supplier Name is Empty", ButtonType.OK);
            a.show();
            return false;
        }else if(txtSupEmail.getText().trim().isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR, "Supplier Contact is Empty", ButtonType.OK);
            a.show();
            return false;
        }else if(txtSupplierAddress.getText().trim().isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR, "Supplier is Empty", ButtonType.OK);
            a.show();
            return false;
        }else if(txtOrderQTY.getText().trim().isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR, "Supplier Order QTY is Empty", ButtonType.OK);
            a.show();
            return false;
        }
        return true;
    }
}
