/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationCliente;

import Controller.LoginController;
import Implementacion.Factoria;
import Implementacion.Hilo;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private Logger logger=Logger.getLogger("ApplicationCliente.LoginLogoutCliente");
    
    @Override
    public void start(Stage stage)  {
        Factoria implement=new Factoria();
        
        LoginController controller = new LoginController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Controller/Login.fxml"));
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
        
        
    /**
     * @param args the command line arguments
     */
    
    
     public static void main(String[] args) {
         /*Hilo hilo=new Hilo();
         hilo.start();*/
        launch(args);
    }
    
}
