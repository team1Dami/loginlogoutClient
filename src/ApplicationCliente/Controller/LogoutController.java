/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationCliente.Controller;

import ApplicationCliente.Controller.LoginController;
import Implementacion.Factoria;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author rubir
 */
public class LogoutController{
    
    @FXML
    private Stage stage;
    @FXML
    private Button btnSignOut;
    @FXML
    private Button btnClose;
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
        
        btnSignOut.setOnAction(this::handleButtonCerrarSesion);
        btnClose.setOnAction(this::handleButtonClose);
        
    }
    
    
    public void handleButtonClose(ActionEvent event) {
        stage.close();
    }
    
    public void handleButtonCerrarSesion(ActionEvent event) {
        
       stage.close();
        
        LoginController controller = new LoginController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root;
        try {
            root = (Parent) loader.load();
            controller = ((LoginController) loader.getController());
            controller.setStage(stage);
            controller.initStage(root);  
        } catch (IOException ex) {
            Logger.getLogger(LogoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
