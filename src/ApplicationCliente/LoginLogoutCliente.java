/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationCliente;

import ApplicationCliente.controller.LoginController;
import ApplicationCliente.controller.LogoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ruben
 */
public class LoginLogoutCliente extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        LoginController controller = new LoginController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Login.fxml"));
        Parent root = (Parent) loader.load();
        
        controller = (loader.getController());
        controller.setStage(stage);
        controller.initStage(root);
        
        /*LogoutController controller = new LogoutController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/logout.fxml"));
        Parent root = (Parent) loader.load();
        
        controller = (loader.getController());
        controller.setStage(stage);
        controller.initStage(root);*/
        }
        
        
    /**
     * @param args the command line arguments
     */
    
    
     public static void main(String[] args) {
        launch(args);
    }
    
}
