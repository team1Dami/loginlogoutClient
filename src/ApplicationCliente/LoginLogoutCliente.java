/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationCliente;

import ApplicationCliente.Controller.LoginController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author Ruben
 */
public class LoginLogoutCliente extends Application {

    private Logger logger = Logger.getLogger("ApplicationCliente.LoginLogoutCliente");

    /**
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        Parent root;
        try {
            LoginController controller = new LoginController();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Controller/Login.fxml"));

            try {
                root = (Parent) loader.load();
                controller = (loader.getController());
                controller.setStage(stage);
                controller.initStage(root);
            } catch (IOException ex) {
                Logger.getLogger(LoginLogoutCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            Logger.getLogger(LoginLogoutCliente.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

}
