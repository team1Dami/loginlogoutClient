/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClient;

import controller.SignUpController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author saray
 */
public class ApplicationSignUp extends Application {
    
  //  public static Stage signUpStage;
    @Override
    public void start(Stage signUpStage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/signUp.fxml"));

        Parent root = (Parent) loader.load();
        
        SignUpController controller = ((SignUpController) loader.getController());
       
        controller.setStage(signUpStage);
        controller.initStage(root);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}