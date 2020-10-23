/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationCliente.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import static javafx.application.ConditionalFeature.FXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author rubir
 */
public class LoginController implements Initializable{
    
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
        stage.show();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
    }
}
