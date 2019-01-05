/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.vps.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

/**
 *
 * @author User
 */
public class HomeController implements Initializable{

     @FXML
    private AnchorPane pnlHome;

    @FXML
    private JFXButton btnAppointment;
    
    @FXML
    private JFXButton btnManageStock;

    @FXML
    private AnchorPane pnlLoad;
    
    @FXML
    private ImageView imgHome;
    
     @FXML
    private JFXButton btnPayments;
     
      @FXML
    private ImageView imgAppointment;

    @FXML
    private ImageView imgStock;

    @FXML
    private ImageView imgPayments;

    @FXML
    private ImageView imgReports;

     @FXML
    private ImageView imgSettings;

    @FXML
    private Label lblLabelDisplay;
    
     @FXML
    private JFXButton btnManageSupplier;
      @FXML
    private JFXButton btnReport;
    @FXML
    private JFXButton btnSettings;
    
     @FXML
    private Label lblTime;

    @FXML
    private Label lblDate;
    
    @FXML
    private ImageView imgSupplier;
    @FXML
    private JFXButton btnLogingOut;


    
    @FXML
    void loadAppointment(MouseEvent event) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/lk/ijse/vps/view/ManageAppointment.fxml"));
        pnlLoad.getChildren().setAll(pane);
    }
     
    @FXML
    void loadPayments(MouseEvent event) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/lk/ijse/vps/view/ManagePayments.fxml"));
        pnlLoad.getChildren().setAll(pane);
    }

    @FXML
    void loadReports(MouseEvent event) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/lk/ijse/vps/view/Reports.fxml"));
        pnlLoad.getChildren().setAll(pane);
    }
    
    @FXML
    void loadReportsPanel(ActionEvent event) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/lk/ijse/vps/view/Reports.fxml"));
        pnlLoad.getChildren().setAll(pane);
    }

    @FXML
    void loadSettings(MouseEvent event) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/lk/ijse/vps/view/Settings.fxml"));
        pnlLoad.getChildren().setAll(pane);
    }

    @FXML
    void loadStock(MouseEvent event) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/lk/ijse/vps/view/ManageStock.fxml"));
        pnlLoad.getChildren().setAll(pane);

    }


    
    @FXML
    void loadHome(MouseEvent event) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/lk/ijse/vps/view/home.fxml"));
        pnlHome.getChildren().setAll(pane);    
    }


    @FXML
    void loadMakeAppointment(ActionEvent event) {
         try {
             AnchorPane pane=FXMLLoader.load(getClass().getResource("/lk/ijse/vps/view/ManageAppointment.fxml"));
             pnlLoad.getChildren().setAll(pane);
         } catch (IOException ex) {
             Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    
    @FXML
    void loadManageStock(ActionEvent event) {
         try {
             AnchorPane pane=FXMLLoader.load(getClass().getResource("/lk/ijse/vps/view/ManageStock.fxml"));
             pnlLoad.getChildren().setAll(pane);
         } catch (IOException ex) {
             Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
         }

    }
    
    @FXML
    void loadMakePayments(ActionEvent event) {

         try {
             AnchorPane pane=FXMLLoader.load(getClass().getResource("/lk/ijse/vps/view/ManagePayments.fxml"));
             pnlLoad.getChildren().setAll(pane);
         } catch (IOException ex) {
             Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
        @FXML
    void mouseEntered(MouseEvent event) {
        try {
            if (event.getSource() instanceof ImageView) {
                ImageView image = (ImageView) event.getSource();
                String id = image.getId();
                FadeTransition fadeTransition = new FadeTransition(Duration.millis(200), lblLabelDisplay);
                switch (id) {
                    case "imgAppointment":
                        lblLabelDisplay.setText("Appointments");
                        fadeTransition.setFromValue(0.0);
                        fadeTransition.setToValue(1.0);
                        fadeTransition.play();
                        break;
                    case "imgStock":
                        lblLabelDisplay.setText("Stock");
                        fadeTransition.setFromValue(0.0);
                        fadeTransition.setToValue(1.0);
                        fadeTransition.play();
                        break;
                    case "imgPayments":
                        lblLabelDisplay.setText("Payments");
                        fadeTransition.setFromValue(0.0);
                        fadeTransition.setToValue(1.0);
                        fadeTransition.play();
                        break;
                    case "imgReports":
                        lblLabelDisplay.setText("Reports");
                        fadeTransition.setFromValue(0.0);
                        fadeTransition.setToValue(1.0);
                        fadeTransition.play();
                        break;
                    case "imgSettings":
                        lblLabelDisplay.setText("Settings");
                        fadeTransition.setFromValue(0.0);
                        fadeTransition.setToValue(1.0);
                        fadeTransition.play();
                        break;
                    case "imgHome":
                        lblLabelDisplay.setText("Home");
                        fadeTransition.setFromValue(0.0);
                        fadeTransition.setToValue(1.0);
                        fadeTransition.play();
                        break;
                    case "imgSupplier":
                        lblLabelDisplay.setText("Supplier");
                        fadeTransition.setFromValue(0.0);
                        fadeTransition.setToValue(1.0);
                        fadeTransition.play();
                        break;
                    
                    default:
                        break;

                }
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), image);
                scaleTransition.setToX(1.2);
                scaleTransition.setToY(1.2);
                scaleTransition.play();

                DropShadow dropShadow = new DropShadow();
                dropShadow.setColor(Color.CORNFLOWERBLUE);
                dropShadow.setHeight(20);
                dropShadow.setWidth(20);
                dropShadow.setRadius(20);
                image.setEffect(dropShadow);
                lblLabelDisplay.setEffect(dropShadow);

            }
        } catch (Exception e) {

        }
    }

    @FXML
    void mouseExited(MouseEvent event) {
         if (event.getSource() instanceof ImageView) {
            ImageView imageView = (ImageView) event.getSource();
            String id = imageView.getId();
            imageView.setEffect(null);

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), imageView);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);
            scaleTransition.play();
            lblLabelDisplay.setEffect(null);

        }
    }
    
    @FXML
    void loadSupplierPanel(ActionEvent event) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/lk/ijse/vps/view/ManageSupplier.fxml"));
        pnlLoad.getChildren().setAll(pane);
    }
    
     @FXML
    void loadSettingsPanel(ActionEvent event) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/lk/ijse/vps/view/Settings.fxml"));
        pnlLoad.getChildren().setAll(pane);
    }
    
    @FXML
    void loadSupplier(MouseEvent event) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/lk/ijse/vps/view/ManageSupplier.fxml"));
        pnlLoad.getChildren().setAll(pane);
    }


    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       setDateTime();
       
       FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), pnlHome);
       fadeIn.setFromValue(0.0);
       fadeIn.setToValue(1.0);
       fadeIn.play();
    }
    
     private void setDateTime() {
        Timeline newTimeLine=new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                lblDate.setText(new SimpleDateFormat("YYYY-MM-dd" ).format(new Date()));
                lblTime.setText(new SimpleDateFormat("hh:mm:ss a" ).format(new Date()));
            }
            
        }),new KeyFrame(Duration.seconds(1)));
        newTimeLine.setCycleCount(Animation.INDEFINITE);
        newTimeLine.play();
    }
     
 

    @FXML
    private void loggingOut(ActionEvent event) {
         Stage stage  = (Stage) pnlHome.getScene().getWindow();
         Alert a=new Alert(Alert.AlertType.INFORMATION, "Want LogOut?", ButtonType.OK);
         a.showAndWait();
         stage.close();
         
    }
    
    
    }
    

