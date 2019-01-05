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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.ijse.vps.business.BOFactory;
import lk.ijse.vps.business.custom.CategoryBO;
import lk.ijse.vps.business.custom.RawMaterialBO;
import lk.ijse.vps.business.custom.impl.CategoryBOImpl;
import lk.ijse.vps.business.custom.impl.RawMaterialBOImpl;
import lk.ijse.vps.common.IDGenarator;
import lk.ijse.vps.model.CategoryDTO;
import lk.ijse.vps.model.RawMaterialDTO;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ManageStockController implements Initializable {

    @FXML
    private AnchorPane panelManageStock;
    @FXML
    private JFXTextField txtMeterialCode;
    @FXML
    private JFXTextField txtBrand;
    @FXML
    private JFXTextField txtQTY;
    @FXML
    private JFXTextField txtwarrantee;
    @FXML
    private TableView<RawMaterialDTO> tableStock;
    @FXML
    private TextField txtDate;
    
    @FXML
    private TextField txtSearchMaterial;

    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXComboBox<String> cmbMaterialCategory;
    @FXML
    private JFXTextField txtPrice;
    
    @FXML
    private JFXButton btnRMCategory;
    
     @FXML
    private TextField txtServiceCategoryName;
     
    private static RawMaterialBO rawMaterialBO=new RawMaterialBOImpl();
    public static CategoryBO categoryBO=new CategoryBOImpl();
    
    CategoryDTO categoryDTO=new CategoryDTO(); 
    
    
    /**
     * Initializes the controller class.
     */
   
    @FXML
    private JFXButton btnAddMaterial;
    
    public ManageStockController(){
        rawMaterialBO=BOFactory.getInstance().getBO(BOFactory.BOTypes.RAW_MATERIAL);
        categoryBO=BOFactory.getInstance().getBO(BOFactory.BOTypes.CATEGORY);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       FadeTransition fadeIn = new FadeTransition(Duration.millis(800), panelManageStock);
       fadeIn.setFromValue(0.0);
       fadeIn.setToValue(1.0);
       fadeIn.play();
        
        try {
            getAllRawMaterials();
        } catch (Exception ex) {
            Logger.getLogger(ManageStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            loadRawMaterialCategoryName();
        } catch (Exception ex) {
            Logger.getLogger(ManageStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setDateTime();
        
        try {
            RawMatrialID();
        } catch (SQLException ex) {
            Logger.getLogger(ManageStockController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManageStockController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManageStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void onMouseClicked(MouseEvent event) {
        RawMaterialDTO selectedItem=(RawMaterialDTO) tableStock.getSelectionModel().getSelectedItem();
        txtMeterialCode.setText(selectedItem.getCode());
        txtBrand.setText(selectedItem.getBrand());
        txtDate.setText(selectedItem.getDate());
        txtDescription.setText(selectedItem.getDescription());
        cmbMaterialCategory.getSelectionModel().select(selectedItem.getCategory());
        txtPrice.setText(""+selectedItem.getPrice());
        txtQTY.setText(""+selectedItem.getQty());
        txtwarrantee.setText(selectedItem.getWarranty());
        
    }

   

    @FXML
    void addMaterial(ActionEvent event) {

        try {
            RawMaterialDTO rawMaterial=new RawMaterialDTO(
                    txtMeterialCode.getText(),
                    txtBrand.getText(),
                    txtDate.getText(),
                    txtDescription.getText(), (String) cmbMaterialCategory.getSelectionModel().getSelectedItem(),
                    Double.parseDouble(txtPrice.getText()),
                    Integer.parseInt(txtQTY.getText()),txtwarrantee.getText());
            boolean isAdded=rawMaterialBO.addRawMaterial(rawMaterial);
                if (isAdded) {
                    Alert a=new Alert(Alert.AlertType.INFORMATION, "IS Ok", ButtonType.OK);
                    a.show();
                    getAllRawMaterials();
                }else{
                    Alert a=new Alert(Alert.AlertType.ERROR, "IS Ok", ButtonType.OK);
                    a.show();
                }
        } catch (Exception ex) {
            Logger.getLogger(ManageStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private void updateRawMaterial(ActionEvent event) throws Exception {
//          boolean updateRawMaterial = rawMaterialBO.updateRawMaterial(new RawMaterialDTO(
//                    txtMeterialCode.getText(),
//                    txtBrand.getText(),
//                    txtDate.getText(),
//                    txtDescription.getText(), (String) cmbMaterialCategory.getSelectionModel().getSelectedItem(),
//                    Double.parseDouble(txtPrice.getText()),
//                    Integer.parseInt(txtQTY.getText()),txtwarrantee.getText()));
//        if(updateRawMaterial){
//            Alert a=new Alert(Alert.AlertType.INFORMATION,"Updated !",ButtonType.OK);
//            a.show();
//        }else{
//            Alert a=new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK);
//            a.show();    
//        }
//    }

    @FXML
    void searchRawMaterial(ActionEvent event) throws Exception {
        String RCode=txtSearchMaterial.getText();
        RawMaterialDTO rawMaterial=rawMaterialBO.searchRawMaterial(RCode);
        if(rawMaterial!=null){
          txtMeterialCode.setText(rawMaterial.getCode());
          txtBrand.setText(rawMaterial.getBrand());
          txtDate.setText(rawMaterial.getDate());
          txtDescription.setText (rawMaterial.getDescription());
          cmbMaterialCategory.getSelectionModel().select(rawMaterial.getCategory());
          txtPrice.setText (""+rawMaterial.getPrice());
          txtQTY.setText(""+rawMaterial.getQty());
          txtwarrantee.setText (rawMaterial.getWarranty());
        }
    }
    

    @FXML
    private void deleteRawMaterial(ActionEvent event) throws Exception {
        boolean isAdded=rawMaterialBO.deleteRawMaterial(txtMeterialCode.getText()); 
        if (isAdded) {
            Alert a=new Alert(Alert.AlertType.INFORMATION, "IS Ok", ButtonType.OK);
            a.show();
            getAllRawMaterials();
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK);
            a.show();
        }  
    }
    
   

    private void getAllRawMaterials() throws Exception {
        rawMaterialBO = new RawMaterialBOImpl();
        tableStock.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tableStock.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tableStock.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("date"));
        tableStock.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("description"));
        tableStock.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("category"));
        tableStock.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("price"));
        tableStock.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tableStock.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("warranty"));
       
        ArrayList<RawMaterialDTO>rawMaterialDTO=rawMaterialBO.getALLRawMaterials();
        tableStock.setItems(FXCollections.observableArrayList(rawMaterialDTO));
        

    }

    private void txtFiledSearchCategory(KeyEvent event) {
        try {
            String RCode=txtSearchMaterial.getText();
            RawMaterialDTO rawMaterial=rawMaterialBO.searchRawMaterial(txtSearchMaterial.getText());
            if(rawMaterial!=null){
                txtMeterialCode.setText(rawMaterial.getCode());
                txtBrand.setText(rawMaterial.getBrand());
                txtDate.setText(rawMaterial.getDate());
                txtDescription.setText (rawMaterial.getDescription());
                
                cmbMaterialCategory.getSelectionModel().select(rawMaterial.getCategory());
                txtPrice.setText (""+rawMaterial.getPrice());
                txtQTY.setText(""+rawMaterial.getQty());
                txtwarrantee.setText (rawMaterial.getWarranty());
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    
     @FXML
    void loadRMCategoryPanel(ActionEvent event) throws IOException {
        Parent parent=FXMLLoader.load(this.getClass().getResource("/lk/ijse/vps/view/RawMaterialCatogery.fxml"));
        Scene scene=new Scene(parent);
        Stage stage=new Stage(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.showAndWait();
    }

    private void loadRawMaterialCategoryName() throws Exception {
        ArrayList<String> categoryId = categoryBO.getAllCategoryIds();
        cmbMaterialCategory.getItems().clear();
        for (String catId : categoryId) {
            cmbMaterialCategory.getItems().add(catId);
        }
    }
    
     private void setDateTime() {
        Timeline newTimeLine=new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                txtDate.setText(new SimpleDateFormat("YYYY-MM-dd" ).format(new Date()));
                
            }
            
        }),new KeyFrame(Duration.seconds(1)));
        newTimeLine.setCycleCount(Animation.INDEFINITE);
        newTimeLine.play();
    }
     
     @FXML
    void searchCategoryName(ActionEvent event) throws Exception {
        String catId=cmbMaterialCategory.getSelectionModel().getSelectedItem();
        CategoryDTO category=categoryBO.searchCategoryName(catId);
        if(category!=null){
            txtServiceCategoryName.setText(category.getCatName());
        }
       
    }
     
    @FXML
     void brandAction(ActionEvent event) {
          String RMBrand=txtBrand.getText();
        if(!RMBrand.matches("[A-z .]+")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Raw Material Brand ", ButtonType.OK);
            alert.show();
            txtBrand.selectAll();
            txtBrand.requestFocus();
        }else{
            txtDescription.requestFocus();
        }
    }

    

    @FXML
    private void qtyAction(ActionEvent event) {
          String RMQTY=txtQTY.getText();
        if(RMQTY.matches("[A-z .]+")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Raw Material QTY ", ButtonType.OK);
            alert.show();
            txtQTY.selectAll();
            txtQTY.requestFocus();
        }else{
            txtBrand.requestFocus();
        }
    }

    @FXML
    private void waranteeAction(ActionEvent event) {
          String RMWarantee=txtwarrantee.getText();
        if(!RMWarantee.matches("[0-9 .]+")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Raw Material Warantee ", ButtonType.OK);
            alert.show();
            txtwarrantee.selectAll();
            txtwarrantee.requestFocus();
        }else{
            txtPrice.requestFocus();
        }
    }

    @FXML
    private void descriptionAction(ActionEvent event) {
          String RMDescription=txtDescription.getText();
        if(!RMDescription.matches("[A-z .]+")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Raw Material Warantee ", ButtonType.OK);
            alert.show();
            txtDescription.selectAll();
            txtDescription.requestFocus();
        }else{
            txtwarrantee.requestFocus();
        }
    }

    @FXML
    private void priceAction(ActionEvent event) {
        String RMPrice=txtPrice.getText();
        if(!RMPrice.matches("[0-9 .]+")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Raw Material Price ", ButtonType.OK);
            alert.show();
            txtPrice.selectAll();
            txtPrice.requestFocus();
        }else{
            txtPrice.requestFocus();
        }
    }

    private void RawMatrialID() throws SQLException, ClassNotFoundException, IOException {
         String rawMaterialId;
        rawMaterialId = IDGenarator.getNewID("RawMaterial ", "RCode", "R");
        txtMeterialCode.setText(rawMaterialId);
    }
    
    public boolean validation(){
        if(txtQTY.getText().trim().isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR, "Material QTY is Empty", ButtonType.OK);
            a.show();
            return false;
        }else if(txtBrand.getText().trim().isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR, "Material Brand is Empty", ButtonType.OK);
            a.show();
            return false;
        }else if(txtDescription.getText().trim().isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR, "Material Description is Empty", ButtonType.OK);
            a.show();
            return false;
        }else if(txtwarrantee.getText().trim().isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR, "Material warrantee is Empty", ButtonType.OK);
            a.show();
            return false;
        }else if(txtPrice.getText().trim().isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR, "Material Price is Empty", ButtonType.OK);
            a.show();
            return false;
        }
        return true;
    }

}
