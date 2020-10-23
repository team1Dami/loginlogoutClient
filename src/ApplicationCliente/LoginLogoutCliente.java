/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationCliente;

import ApplicationCliente.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author eneko
 */
public class LoginLogoutCliente extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
       /*Parent root = FXMLLoader.load(getClass().getResource("fxml/LOGIN.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene); 
        stage.setResizable(false);
        stage.show();*/
        
        LoginController controller = new LoginController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/LOGIN.fxml"));
        Parent root = (Parent) loader.load();
        
        controller = (loader.getController());
        controller.setStage(stage);
        controller.initStage(root);
        
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("Spinner.fxml"));
        Parent root = (Parent) loader.load();
        
        LoginController controller = (LoginController) loader.getController();
        
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/
    }
        
        
    /**
     * @param args the command line arguments
     */
    
    
     public static void main(String[] args) {
        launch(args);
    }
    
}
