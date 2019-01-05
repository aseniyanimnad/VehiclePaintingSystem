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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.ijse.vps.business.BOFactory;
import lk.ijse.vps.business.custom.AppointmentBO;
import lk.ijse.vps.business.custom.AppointmentDetailBO;
import lk.ijse.vps.business.custom.CustomerBO;
import lk.ijse.vps.business.custom.ServiceBO;

import lk.ijse.vps.business.custom.impl.AppointmentBOImpl;
import lk.ijse.vps.business.custom.impl.AppointmentDetailBOImpl;
import lk.ijse.vps.business.custom.impl.CustomerBOImpl;
import lk.ijse.vps.business.custom.impl.ServiceBOImpl;
import lk.ijse.vps.common.IDGenarator;
import lk.ijse.vps.dao.custom.AppointmentDAO;
import lk.ijse.vps.dao.custom.AppointmentDetailDAO;
import lk.ijse.vps.dao.custom.impl.AppointmentDAOImpl;
import lk.ijse.vps.dao.custom.impl.AppointmentDetailDAOImpl;
import lk.ijse.vps.entity.AppointmentDetail;

import lk.ijse.vps.model.AppointmentDTO;
import lk.ijse.vps.model.AppointmentDetailDTO;
import lk.ijse.vps.model.CustomerDTO;
import lk.ijse.vps.model.ServiceDTO;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ManageAppointmentController implements Initializable {

    @FXML
    private AnchorPane pnlManageAppointment;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtNIC;
    @FXML
    private JFXTextField txtTel;
    @FXML
    private TextField txtCustId;
    @FXML
    private TableView<CustomerDTO> tableCustomer;

    @FXML
    private TextField txtSearchCustomer;

    @FXML
    private TableView<AppointmentDTO> tableAppointment;

    @FXML
    private TextField txtSearchAppointment;

    @FXML
    private TextField txtAppointmentId;

    @FXML
    private JFXTextField txtAdvance;

    @FXML
    private TextField txtADate;

    @FXML
    private JFXButton btnAddAppo;


    @FXML
    private JFXButton butDelAppo;

    @FXML
    private JFXButton btnManageSerCato;

    @FXML
    private JFXComboBox<String> cmbServiceID;

    @FXML
    private TextField txtServiceName;
    
     @FXML
    private TableView<AppointmentDetailDTO> tableAppointmentDetail;
    
    

    /**
     * Initializes the controller class.
     */
    private static CustomerBO customerBO;
    AppointmentBO appointmentBo;
    ServiceBO serviceBO;
    AppointmentDetailBO appointmentDetailBO;
    @FXML
    private TextField txtServicePayment;

    public ManageAppointmentController() {
        customerBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
        serviceBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SERVICE);
        appointmentBo = BOFactory.getInstance().getBO(BOFactory.BOTypes.APPOINTMENT);
        appointmentDetailBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.APPOINTMENT_DETAIL);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       FadeTransition fadeIn = new FadeTransition(Duration.millis(800), pnlManageAppointment);
       fadeIn.setFromValue(0.0);
       fadeIn.setToValue(1.0);
       fadeIn.play();
        
       try {
            loadServiceID();
        } catch (Exception ex) {
            Logger.getLogger(ManageAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            getAllCustomers();
            getAllAppointments();
            getAllAppointmentDeatails();

        } catch (Exception ex) {
            Logger.getLogger(ManageAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setDateTime();
        
        try {
            CustomerID();
        } catch (SQLException ex) {
            Logger.getLogger(ManageAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManageAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManageAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            AppointmentID();
        } catch (SQLException ex) {
            Logger.getLogger(ManageAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManageAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManageAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onMouseClick(MouseEvent event) {
        CustomerDTO selectedItem = tableCustomer.getSelectionModel().getSelectedItem();
        txtCustId.setText(selectedItem.getCusID());
        txtName.setText(selectedItem.getCusName());
        txtNIC.setText(selectedItem.getCusNIC());
        txtTel.setText(selectedItem.getCusTel());
    }


    @FXML
    void searchCustomer(ActionEvent event) throws Exception {
        String custId = txtSearchCustomer.getText();
        CustomerDTO customer = customerBO.searchCustomer(custId);
        if (customer != null) {
            txtCustId.setText(customer.getCusID());
            txtNIC.setText(customer.getCusNIC());
            txtName.setText(customer.getCusName());
            txtTel.setText(customer.getCusTel());
        }
    }



    private void getAllCustomers() throws Exception {
        try {
            customerBO = new CustomerBOImpl();
            tableCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("CusID"));
            tableCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("CusNIC"));
            tableCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("CusName"));
            tableCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("CusTel"));
            ArrayList<CustomerDTO> customerDTO = customerBO.getAllCustomers();
            tableCustomer.setItems(FXCollections.observableArrayList(customerDTO));
        } catch (Exception ex) {
            Logger.getLogger(ManageAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void addAppointment(ActionEvent event) throws Exception {
        double advance = Double.parseDouble(txtAdvance.getText());
        CustomerDTO customerDTO = new CustomerDTO(txtCustId.getText(), txtNIC.getText(), txtName.getText(), txtTel.getText());
        AppointmentDTO appointment = new AppointmentDTO(txtAppointmentId.getText(), txtADate.getText(), advance, txtCustId.getText());
        AppointmentDetailDTO appointmentDetailDTO = new AppointmentDetailDTO(txtAppointmentId.getText(), (String)cmbServiceID.getItems().get(0), txtCustId.getText());
        String s =cmbServiceID.getSelectionModel().getSelectedItem();
        ServiceDTO serviceDTO = new ServiceDTO(s, txtServiceName.getText());
        
        boolean validation = appointmentDetailBO.add(customerDTO, appointment, appointmentDetailDTO, serviceDTO);
        
        if (validation) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Added Sucessful", ButtonType.OK);
            a.show();
            getAllAppointments();
            getAllCustomers();
            getAllAppointmentDeatails();
            txtName.clear();
            txtNIC.clear();
            txtTel.clear();
            txtAdvance.clear();
            txtServiceName.clear();
            
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK);
            a.show();
        }
    }

    @FXML
    void deleteAppointment(ActionEvent event) throws Exception {
        
        double advance = Double.parseDouble(txtAdvance.getText());
        CustomerDTO customerDTO = new CustomerDTO(txtCustId.getText(), txtNIC.getText(), txtName.getText(), txtTel.getText());
        AppointmentDTO appointment = new AppointmentDTO(txtAppointmentId.getText(), txtADate.getText(), advance, txtCustId.getText());
        AppointmentDetailDTO appointmentDetailDTO = new AppointmentDetailDTO(txtAppointmentId.getText(), (String)cmbServiceID.getItems().get(0), txtCustId.getText());
        String s =cmbServiceID.getSelectionModel().getSelectedItem();
        ServiceDTO serviceDTO = new ServiceDTO(s, txtServiceName.getText());
        
        boolean isAdded = appointmentDetailBO.delete(customerDTO, appointment, appointmentDetailDTO, serviceDTO);
        if (isAdded) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "IS Ok", ButtonType.OK);
            a.show();
            getAllAppointmentDeatails();
            getAllAppointments();
            getAllCustomers();
            txtName.clear();
            txtNIC.clear();
            txtTel.clear();
            txtAdvance.clear();
            txtServiceName.clear();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK);
            a.show();
        }
    }

    @FXML
    void onAppointmentMouseClick(MouseEvent event) {
        AppointmentDTO selectedItem = tableAppointment.getSelectionModel().getSelectedItem();
        txtAppointmentId.setText(selectedItem.getAID());
        txtADate.setText(selectedItem.getADate());
        txtAdvance.setText("" + selectedItem.getAdvance());
        txtCustId.setText(selectedItem.getCusID());
    }


    @FXML
    void searchAppointment(ActionEvent event) throws Exception {
        String appointmentId = txtSearchAppointment.getText();
        AppointmentDTO appointmentDTO = appointmentBo.searchAppointment(appointmentId);
        if (appointmentDTO != null) {
            txtAppointmentId.setText(appointmentDTO.getAID());
            txtADate.setText(appointmentDTO.getADate());
            txtAdvance.setText("" + appointmentDTO.getAdvance());
            txtCustId.setText(appointmentDTO.getCusID());
        }
    }

    private void getAllAppointments() {
        try {
            appointmentBo = new AppointmentBOImpl();
            AppointmentDetailDAO appoinemtntDetailsDAO = new AppointmentDetailDAOImpl();
            tableAppointment.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("AID"));
            tableAppointment.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("ADate"));
            tableAppointment.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Advance"));
            tableAppointment.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("CusID"));
            ArrayList<AppointmentDTO> appointmentDTOs = appointmentBo.getAllAppointments();
            tableAppointment.setItems(FXCollections.observableArrayList(appointmentDTOs));
        } catch (Exception ex) {
            Logger.getLogger(ManageAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
    private void getAllAppointmentDeatails() {
        try {
            appointmentDetailBO = new AppointmentDetailBOImpl();
            tableAppointmentDetail.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            tableAppointmentDetail.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("serviceId"));
            tableAppointmentDetail.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerId"));
            ArrayList<AppointmentDetailDTO> appointmentDetailDTOs = appointmentDetailBO.getAllAppointmentDetails();
            tableAppointmentDetail.setItems(FXCollections.observableArrayList(appointmentDetailDTOs));
        } catch (Exception ex) {
            Logger.getLogger(ManageAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


     @FXML
    void onAppointmentDetailClicked(MouseEvent event) {
        AppointmentDetailDTO selectedItem = tableAppointmentDetail.getSelectionModel().getSelectedItem();
        txtAppointmentId.setText(selectedItem.getAppointmentId());
        cmbServiceID.getSelectionModel().select(selectedItem.getServiceId());
        txtCustId.setText(selectedItem.getCustomerId());
        
    }
    
    @FXML
    void loadSerCatoPanel(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/vps/view/ServiceCategory.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.showAndWait();
    }

    private void loadServiceID() throws Exception {
        ArrayList<String> serviceId = serviceBO.getAllServiceIds();
        cmbServiceID.getItems().clear();
        for (String id : serviceId) {
            cmbServiceID.getItems().add(id);
        }
    }

    @FXML
    void searchServiceName(ActionEvent event) throws Exception {
        String serviceId = cmbServiceID.getSelectionModel().getSelectedItem();
        ServiceDTO serviceDTO = serviceBO.searchService(serviceId);
        if (serviceDTO != null) {
            txtServiceName.setText(serviceDTO.getServiceCategoryName());
                
        }
    } 
    
     private void setDateTime() {
        Timeline newTimeLine=new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                txtADate.setText(new SimpleDateFormat("YYYY-MM-dd" ).format(new Date()));
                
            }
            
        }),new KeyFrame(Duration.seconds(1)));
        newTimeLine.setCycleCount(Animation.INDEFINITE);
        newTimeLine.play();
    }
     
     
    @FXML
    void AAdvanceAction(ActionEvent event) {
        String Advance=txtAdvance.getText();
        if(!Advance.matches("[0-9 .]+")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Appointment Advance", ButtonType.OK);
            alert.show();
            txtAdvance.selectAll();
            txtAdvance.requestFocus();
        }else{
            txtAdvance.requestFocus();
        }

    }

  

   
    @FXML
    void CusNICAction(ActionEvent event) {
        String CusId=txtNIC.getText();
        if(!CusId.matches("[0-9]{9}[vVxX]")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Customer NIC", ButtonType.OK);
            alert.show();
            txtNIC.selectAll();
            txtNIC.requestFocus();
            txtNIC.clear();
        }else{
              txtName.requestFocus();
        }

    }

    @FXML
    void CusNameAction(ActionEvent event) {
        String cusName=txtName.getText();
        if(!cusName.matches("[A-Za-z .]+")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Customer Name", ButtonType.OK);
            alert.show();
            txtName.selectAll();
            
            txtName.requestFocus();
            txtName.clear();
        }else{
            
            txtTel.requestFocus();
        }

    }

    @FXML
    void CusTelAction(ActionEvent event) {
        try{
        String CusTel=txtTel.getText();
        if(!CusTel.matches("[0-9]{10}")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Customer Telephone Number", ButtonType.OK);
            alert.show();
            txtTel.selectAll();
            
            txtTel.requestFocus();
            txtTel.clear();
        }else{
           
            txtAdvance.requestFocus();
        }
        }catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR,ex.toString(), ButtonType.OK);
            alert.show();
            txtTel.selectAll();
        }

    }
    
 

    private void CustomerID() throws SQLException, ClassNotFoundException, IOException {
        String customerId;
        customerId = IDGenarator.getNewID("Customer", "CusID ", "C");
        txtCustId.setText(customerId);
    }

    private void AppointmentID() throws SQLException, ClassNotFoundException, IOException {
        String appointmentId;
        appointmentId = IDGenarator.getNewID("Appointments", "AID  ", "A");
        txtAppointmentId.setText(appointmentId);
    }


    public boolean validation(){
        if(txtNIC.getText().trim().isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR, "Customer NIC is Empty", ButtonType.OK);
            a.show();
            return false;
        }else if(txtName.getText().trim().isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR, "Customer Name is Empty", ButtonType.OK);
            a.show();
            return false;
        }else if(txtTel.getText().trim().isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR, "Customer Contact is Empty", ButtonType.OK);
            a.show();
            return false;
        }else if(txtAdvance.getText().trim().isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR, "A Contact is Empty", ButtonType.OK);
            a.show();
            return false;
        }else if(txtServiceName.getText().trim().isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR, "A Contact is Empty", ButtonType.OK);
            a.show();
            return false;
        }
        return true;
    }
}
    
