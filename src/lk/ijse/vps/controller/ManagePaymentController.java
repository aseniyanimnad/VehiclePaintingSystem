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
import lk.ijse.vps.business.custom.AppointmentBO;
import lk.ijse.vps.business.custom.AppointmentDetailBO;
import lk.ijse.vps.business.custom.CustomerPaymentBO;
import lk.ijse.vps.business.custom.SupplierOrderBO;
import lk.ijse.vps.business.custom.SupplierPaymentBO;
import lk.ijse.vps.business.custom.impl.CustomerPaymentBOImpl;
import lk.ijse.vps.business.custom.impl.SupplierPaymentBOImpl;
import lk.ijse.vps.common.IDGenarator;
import lk.ijse.vps.model.AppointmentDTO;
import lk.ijse.vps.model.CustomerPaymentDTO;
import lk.ijse.vps.model.SupplierPaymentDTO;

/**
 *
 * @author User
 */
public class ManagePaymentController implements Initializable {

    @FXML
    private AnchorPane pnlManagePayment;
    
    @FXML
    private TextField txtCPID;

    @FXML
    private TextField txtSPDate;
     @FXML
    private JFXTextField txtAdvance;

    private JFXTextField txtTotalAmount;

    @FXML
    private JFXButton btnCPAdd;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<CustomerPaymentDTO> tableCustomerPayment;
    @FXML
    private JFXButton btnSPAdd;
    @FXML
    private JFXButton btnSPDelete;
    @FXML
    private TextField txtCPSearch;
    @FXML
    private TextField txtSPSearch;

    @FXML
    private JFXComboBox<String> cmbAppointmentId;

    @FXML
    private TextField txtDateTxt;
    @FXML
    private TextField txtSupplierPaymentID;
     @FXML
    private JFXTextField txtSupplierPayment;
    @FXML
    private TableView<SupplierPaymentDTO> tableSupplierPayment;
    @FXML
    private JFXComboBox<String> cmbSupOrderID;
    
    private JFXComboBox<?> cmbServiceID;
    
    @FXML
    private TextField txtPayment;
    @FXML
    private TextField txtBalance;
    @FXML
    private JFXTextField txtPaymentAmount;
    @FXML
    private TextField txtFullAmount;
    


    private static CustomerPaymentBO customerPaymentBO;
    AppointmentBO appointmentBO;
    SupplierPaymentBO supplierPaymentBO;
    SupplierOrderBO supplierOrderBO;
    AppointmentDetailBO appointmentDetailBO;
    
    public ManagePaymentController() {
        customerPaymentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER_PAYMENT);
        appointmentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.APPOINTMENT);
        supplierPaymentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER_PAYMENT);
        supplierOrderBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER_ORDER);
        appointmentDetailBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.APPOINTMENT_DETAIL);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
       FadeTransition fadeIn = new FadeTransition(Duration.millis(800), pnlManagePayment);
       fadeIn.setFromValue(0.0);
       fadeIn.setToValue(1.0);
       fadeIn.play();
        
       getAllCustomerPayments();

        try {
            loadAppointmentId();
        } catch (Exception ex) {
            Logger.getLogger(ManagePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setDateTime();

        try {
            CustomerPaymentID();
        } catch (SQLException ex) {
            Logger.getLogger(ManagePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManagePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManagePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            SupplierPaymentID();
        } catch (SQLException ex) {
            Logger.getLogger(ManagePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManagePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManagePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            getALLSupplierPayments();
        } catch (Exception ex) {
            Logger.getLogger(ManagePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            loadSupplierPaymentID();
        } catch (Exception ex) {
            Logger.getLogger(ManagePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void addCustomerPayment(ActionEvent event) throws Exception {
        double amount = Double.parseDouble(txtAdvance.getText());
        double payment = Double.parseDouble(txtPaymentAmount.getText());

        CustomerPaymentDTO customerPayment = new CustomerPaymentDTO(txtCPID.getText(), txtDateTxt.getText(), amount, payment, (String) cmbAppointmentId.getSelectionModel().getSelectedItem());

        boolean isAdded = customerPaymentBO.addCustomerPayment(customerPayment);
        if (isAdded) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Added Sucessful", ButtonType.OK);
            a.show();
           getAllCustomerPayments();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK);
            a.show();
        }

    }


    @FXML
    private void deleteCustomerPayment(ActionEvent event) throws Exception {
        boolean isAdded = customerPaymentBO.deleteCustomerPayment(txtCPID.getText());
        if (isAdded) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "IS Ok", ButtonType.OK);
            a.show();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK);
            a.show();
        }
    }

    @FXML
    private void addSupplierPayment(ActionEvent event) throws Exception {

        double payment = Double.parseDouble(txtSupplierPayment.getText());

        SupplierPaymentDTO supplierPayment = new SupplierPaymentDTO(txtSupplierPaymentID.getText(), txtSPDate.getText(), payment, (String) cmbSupOrderID.getSelectionModel().getSelectedItem());

        boolean isAdded = supplierPaymentBO.addSupplierPayment(supplierPayment);
        if (isAdded) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Added Sucessful", ButtonType.OK);
            a.show();
            getALLSupplierPayments();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK);
            a.show();
        }
    }


    @FXML
    private void deleteSupplierPayment(ActionEvent event) throws Exception {
        boolean isAdded = supplierPaymentBO.deleteSupplierPayment(txtSupplierPaymentID.getText());
        if (isAdded) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "IS Ok", ButtonType.OK);
            a.show();
            getALLSupplierPayments();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK);
            a.show();
        }
    }

   

    private void loadAppointmentId() throws Exception {
        ArrayList<String> appointmentId = appointmentBO.getAllAppointmentIds();
        cmbAppointmentId.getItems().clear();
        for (String id : appointmentId) {
            cmbAppointmentId.getItems().add(id);
        }
    }

    @FXML
    void onCusPaymentMouseClicked(MouseEvent event) {
        CustomerPaymentDTO selectedItem = tableCustomerPayment.getSelectionModel().getSelectedItem();
        txtCPID.setText(selectedItem.getId());
        txtDateTxt.setText(selectedItem.getDate());
        txtAdvance.setText("" + selectedItem.getAdvance());
        txtPaymentAmount.setText("" + selectedItem.getPaymentAmount());
        cmbAppointmentId.getSelectionModel().select(selectedItem.getAppointmentId());
    }

    private void setDateTime() {
        Timeline newTimeLine = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtSPDate.setText(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
                txtDateTxt.setText(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
            }

        }), new KeyFrame(Duration.seconds(1)));
        newTimeLine.setCycleCount(Animation.INDEFINITE);
        newTimeLine.play();
    }

     @FXML
    void CusPaymentAdvanceAction(ActionEvent event) {
        String cpAdvance=txtAdvance.getText();
        if(!cpAdvance.matches("[0-9 .]+")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Customer Payment Advance", ButtonType.OK);
            alert.show();
            txtAdvance.selectAll();
            txtAdvance.requestFocus();
        }else{
            txtPaymentAmount.requestFocus();
        }
    }

     @FXML
    void CusPaymentAmountAction(ActionEvent event) {
        
        double amount=Double.parseDouble(txtPaymentAmount.getText());
        double advance=Double.parseDouble(txtAdvance.getText());
        txtFullAmount.setText((amount-advance)+"");

    }

   

     @FXML
    void SupplierPaymentAction(ActionEvent event) {
        String SupAmount=txtSupplierPayment.getText();
        if(!SupAmount.matches("[A-Z,0-9]{6}")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Supplier  Payment ", ButtonType.OK);
            alert.show();
            txtSupplierPayment.selectAll();
            txtSupplierPayment.requestFocus();
        }else{
            txtSupplierPayment.requestFocus();
        }
    }

    private void CustomerPaymentID() throws SQLException, ClassNotFoundException, IOException {
        String cuPaymentId;
        cuPaymentId = IDGenarator.getNewID("CustomerPayment", "CPID   ", "CP");
        txtCPID.setText(cuPaymentId);
    }



    @FXML
    void onSupplierPaymentMouseClick(MouseEvent event) {
        SupplierPaymentDTO selectedItem = tableSupplierPayment.getSelectionModel().getSelectedItem();
        txtSupplierPaymentID.setText(selectedItem.getId());
        txtSPDate.setText(selectedItem.getDate());
        txtSupplierPayment.setText("" + selectedItem.getPayment());
        cmbSupOrderID.getSelectionModel().select(selectedItem.getSOID());
    }

    private void getALLSupplierPayments() throws Exception {
        supplierPaymentBO = new SupplierPaymentBOImpl();
        tableSupplierPayment.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tableSupplierPayment.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tableSupplierPayment.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("payment"));
        tableSupplierPayment.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("SOID"));
        ArrayList<SupplierPaymentDTO> supplierPaymentDTO = supplierPaymentBO.getALLSupplierPayments();
        tableSupplierPayment.setItems(FXCollections.observableArrayList(supplierPaymentDTO));
    }

    private void loadSupplierPaymentID() throws Exception {
        ArrayList<String> supplierPaymentID = supplierOrderBO.getAllSupplierOrderIds();
        cmbSupOrderID.getItems().clear();
        for (String id : supplierPaymentID) {
            cmbSupOrderID.getItems().add(id);
        }
    }

    private void getAllCustomerPayments() {
        try {
            customerPaymentBO = new CustomerPaymentBOImpl();
            tableCustomerPayment.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
            tableCustomerPayment.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
            tableCustomerPayment.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("advance"));
            tableCustomerPayment.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("paymentAmount"));
            tableCustomerPayment.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            ArrayList<CustomerPaymentDTO> customerPaymentDTO = customerPaymentBO.getAllCustomerPayments();
            tableCustomerPayment.setItems(FXCollections.observableArrayList(customerPaymentDTO));
        } catch (Exception ex) {
            Logger.getLogger(ManagePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void SupplierPaymentID() throws SQLException, ClassNotFoundException, IOException {
        String supPaymentId;
        supPaymentId = IDGenarator.getNewID("SupplierPayment", "SPID", "SP");
        txtSupplierPaymentID.setText(supPaymentId);
    }
    
     @FXML
    void SearchAdvance(ActionEvent event) throws Exception {
        String appointmentId = cmbAppointmentId.getSelectionModel().getSelectedItem();
        AppointmentDTO appointmentDTO = appointmentBO.searchAppointment(appointmentId);
        if (appointmentDTO != null) {
            txtAdvance.setText(""+appointmentDTO.getAdvance());
                    
        }
    }

    @FXML
    private void onActionPayment(ActionEvent event) {
        double fullAmount=Double.parseDouble(txtFullAmount.getText());
        double payment=Double.parseDouble(txtPayment.getText());
        txtBalance.setText((payment-fullAmount)+"");
    }

    @FXML
    private void searchCustomerPayment(ActionEvent event) throws Exception {
         String CPID = txtCPSearch.getText();
        CustomerPaymentDTO customerPayment = customerPaymentBO.searchCustomerPayment(CPID);
        if (customerPayment != null) {
            txtCPID.setText(customerPayment.getId());
            txtDateTxt.setText(customerPayment.getDate());
            txtAdvance.setText(""+customerPayment.getAdvance());
            txtPaymentAmount.setText(""+customerPayment.getPaymentAmount());
            cmbAppointmentId.getSelectionModel().select(customerPayment.getAppointmentId());
        }
    }

    

    @FXML
    private void searchSupplierPayment(ActionEvent event) throws Exception {
     String SPID = txtSPSearch.getText();
        SupplierPaymentDTO supplierPayment = supplierPaymentBO.searchSupplierPaymentt(SPID);
        if (supplierPayment != null) {
            txtSupplierPaymentID.setText(supplierPayment.getId());
            txtSPDate.setText(supplierPayment.getDate());
            txtSupplierPayment.setText(""+supplierPayment.getPayment());
            cmbSupOrderID.getSelectionModel().select(supplierPayment.getSOID());
        }
    }


     public boolean validation(){
        if(txtAdvance.getText().trim().isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR, "Customer Advance is Empty", ButtonType.OK);
            a.show();
            return false;
        }else if(txtPaymentAmount.getText().trim().isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR, "Customer PaymentAmount is Empty", ButtonType.OK);
            a.show();
            return false;
        }else if(txtPayment.getText().trim().isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR, "Customer Payment is Empty", ButtonType.OK);
            a.show();
            return false;
        }else if(txtSupplierPayment.getText().trim().isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR, "SupplierPayment is Empty", ButtonType.OK);
            a.show();
            return false;
        }
        return true;
    }
}
