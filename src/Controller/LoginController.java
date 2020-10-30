/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ApplicationCliente.LoginLogoutCliente;
import Implementacion.Factoria;
import Implementacion.Implementacion;
import classes.User;
import interfaces.ClientServer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.ConditionalFeature.FXML;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Rubén Rabadán
 */
public class LoginController {
    
    //Declaration of attributes
    @FXML
        private Stage stage;
    
    @FXML
        private Button btnLogin;
    @FXML
        private Button btnRegister;
    @FXML
        private TextField tfLogin;
    @FXML
        private TextField tfPasswd;
    
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    
    public void initStage(Parent root) {        
        Scene scene = new Scene(root);
        stage.setScene(scene); 
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.show();
        
        tfLogin.textProperty().addListener(this::textChange);
        tfPasswd.textProperty().addListener(this::textChange);
        btnLogin.setOnAction(this::handleButtonLogin);
        btnRegister.setOnAction(this::handleButtonRegister);
    }
    
    
    private void textChange(ObservableValue observable,String oldValue, String newValue){
        //disable the Login button

        //If password field is higher than 12
        if(tfPasswd.getText().trim().length() > 20){
            btnLogin.setDisable(true);
        }
        //If text fields are empty 
        else if(tfLogin.getText().trim().isEmpty()||
                tfPasswd.getText().trim().isEmpty())
            btnLogin.setDisable(true);
        //Else, enable accept button
        else btnLogin.setDisable(false);
        
    }
    
    private void handleButtonLogin(ActionEvent event){
        if(tfLogin.getText().isEmpty()||tfPasswd.getText().isEmpty()){
             Alert alert=new Alert(Alert.AlertType.ERROR,"Error",ButtonType.APPLY);
        }
        else{
            User user= new User();
            user.setLogIn(tfLogin.getText().toString());
            user.setPasswd(tfPasswd.getText().toString());
            Implementacion imp=new Implementacion();
            user=imp.signIn(user);
            if(user.getEmail().equals("1")){
                LogoutController controller = new LogoutController();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Logout.fxml"));
            Parent root;

            try {
                root = (Parent) loader.load();
                controller = (loader.getController());
                controller.setStage(stage);
                controller.initStage(root);  
            } catch (IOException ex) {
                Logger.getLogger(LogoutController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            else{
                Alert alert=new Alert(Alert.AlertType.ERROR,"Error",ButtonType.OK);
                alert.showAndWait();
            }
        }
        
    }
    
    private void handleButtonRegister(ActionEvent event){
        LogoutController controller = new LogoutController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Logout.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
            controller = (loader.getController());
            controller.setStage(stage);
            controller.initStage(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginLogoutCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}