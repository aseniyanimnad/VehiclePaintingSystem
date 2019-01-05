/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.controller;

import com.jfoenix.controls.JFXButton;
import static com.lowagie.text.xml.simpleparser.EntitiesToSymbol.map;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import lk.ijse.vps.db.DBConnection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ReportsController implements Initializable {

    @FXML
    private AnchorPane panelReports;
    @FXML
    private JFXButton btnCustomerReport;
    @FXML
    private JFXButton btnAppointmentReport;
    @FXML
    private JFXButton btnServiceReport;
    @FXML
    private JFXButton btnStockReport;
    @FXML
    private JFXButton btnSupplierReport;
    @FXML
    private JFXButton btnSupplierOrders;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadCustomerReport(ActionEvent event) throws IOException {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/lk/ijse/vps/reports/Customer_List.jasper");
            HashMap hMap= new HashMap();
            
            JasperPrint jasperPrint= JasperFillManager.fillReport(inputStream, map, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadAppointmentReport(ActionEvent event) throws IOException {
         try {
            InputStream inputStream = getClass().getResourceAsStream("/lk/ijse/vps/reports/Appointments.jasper");
            HashMap hMap= new HashMap();
            
            JasperPrint jasperPrint= JasperFillManager.fillReport(inputStream, map, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadServiceReport(ActionEvent event) throws IOException {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/lk/ijse/vps/reports/Services.jasper");
            HashMap hMap= new HashMap();
            
            JasperPrint jasperPrint= JasperFillManager.fillReport(inputStream, map, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadCustomerPaymentReport(ActionEvent event) throws IOException {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/lk/ijse/vps/reports/Customer_Payments.jasper");
            HashMap hMap= new HashMap();
            
            JasperPrint jasperPrint= JasperFillManager.fillReport(inputStream, map, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadSupplierPaymentReport(ActionEvent event) throws IOException {
         try {
            InputStream inputStream = getClass().getResourceAsStream("/lk/ijse/vps/reports/Supplier_Payments.jasper");
            HashMap hMap= new HashMap();
            
            JasperPrint jasperPrint= JasperFillManager.fillReport(inputStream, map, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadStockReports(ActionEvent event) throws IOException {
         try {
            InputStream inputStream = getClass().getResourceAsStream("/lk/ijse/vps/reports/Stock_Reports.jasper");
            HashMap hMap= new HashMap();
            
            JasperPrint jasperPrint= JasperFillManager.fillReport(inputStream, map, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadSupplierReport(ActionEvent event) throws IOException {
         try {
            InputStream inputStream = getClass().getResourceAsStream("/lk/ijse/vps/reports/Supplier_Report.jasper");
            HashMap hMap= new HashMap();
            
            JasperPrint jasperPrint= JasperFillManager.fillReport(inputStream, map, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadSupplierOrders(ActionEvent event) throws IOException {
         try {
            InputStream inputStream = getClass().getResourceAsStream("/lk/ijse/vps/reports/Supplier_Orders.jasper");
            HashMap hMap= new HashMap();
            
            JasperPrint jasperPrint= JasperFillManager.fillReport(inputStream, map, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
