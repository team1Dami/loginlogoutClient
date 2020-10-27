/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationCliente.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.ConditionalFeature.FXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author rubir
 */
public class LogoutController implements Initializable{
    
    @FXML
    private Stage stage;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnCancel;
    @FXML
    private Label lblUser;
    
    
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    
    public void initStage(Parent root) {        
        Scene scene = new Scene(root);
        stage.setScene(scene); 
        stage.setResizable(false);
        stage.setTitle("Logout");
        stage.show();
        
        
        
        
        
        
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
    }
    
    public void handleButtonClose() {
        stage.close();
    }
    
    public void handleButtonCerrarSesion(){
         LoginController controller = new LoginController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./fxml/Login.fxml"));
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
}
