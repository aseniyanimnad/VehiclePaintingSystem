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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import lk.ijse.vps.common.PasswordUtil;
import lk.ijse.vps.dao.CrudUtil;
import lk.ijse.vps.db.DBConnection;

/**
 * FXML Controller class
 *
 * @author User
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane pnlLogin;
    @FXML
    private JFXButton btnLogin;
    
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private Label lblError;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(5000), pnlLogin);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }    

    @FXML
    private void loginForm(MouseEvent event) throws IOException, Exception {
//         ResultSet rst = CrudUtil.executeQuery("Select * from user");
//        if(rst.next()){
//            String userName=rst.getString(2);
//            String salt=rst.getString(3);
//            String secPw=rst.getString(4);
//            
//            boolean ifPasswordMatches=PasswordUtil.verifyUserPassword(txtPassword.getText(), secPw, salt);
//            if (/*ifPasswordMatches && txtUserName.getText().equals(userName)*/true) {
//                

   Parent parent=FXMLLoader.load(this.getClass().getResource("/lk/ijse/vps/view/home.fxml"));
                Scene scene=new Scene(parent);
                Stage window=(Stage)this.pnlLogin.getScene().getWindow();
                window.setScene(scene);
                window.centerOnScreen();
//            } else {
//                JOptionPane.showMessageDialog(null, "Error in username password combination!");
//            }
        }
    }

    
    

