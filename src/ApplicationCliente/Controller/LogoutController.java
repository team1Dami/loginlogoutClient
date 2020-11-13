/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationCliente.Controller;

import Implementation.ClientServerImplementation;
import Implementation.ImpFactory;
import classes.Message;
import classes.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import static sun.util.logging.LoggingSupport.log;

/**
 * Controller of the signup view
 *
 * @author Rubén
 */
public class LogoutController {

    public static void setMensaje(User aMensaje) {
        mensaje = aMensaje;
    }

    @FXML
    private Stage stage;
    @FXML
    private Button btnSignOut;
    @FXML
    private Button btnClose;
    @FXML
    private Label txtWelcome;

    private static User mensaje;

    /**
     * Set the stage of the view
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Initialize the view and set actions
     *
     * @param root
     */
    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Logout");
        txtWelcome.setText("Bienvenido, " + mensaje.getLogIn());
        stage.show();

        btnSignOut.setOnAction(this::handleButtonCerrarSesion);
        btnClose.setOnAction(this::handleButtonClose);
        stage.setOnCloseRequest(this::setOncloseRequest);

    }

    //method that asks when you press the x if you want to go back or not, if you press OK you go back to login otherwhise you stay in the signup
    private void setOncloseRequest(WindowEvent we) {

        try {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Desea Salir de esta ventana", ButtonType.OK, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult().getButtonData().isCancelButton()) {
                alert = new Alert(Alert.AlertType.WARNING, "Se ha cancelado la accion", ButtonType.OK);
                alert.showAndWait();
                we.consume();

            } else {
                /*   
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent newroot = (Parent) loader.load();
            LoginController controller = ((LoginController) loader.getController());
            controller = (loader.getController());
            controller.setStage(stage);
            controller.initStage(newroot);
                 */
            }

            //we.consume(); //hacer como si nada hubiese pasado
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.WARNING, "No se ha podido cargar la ventana", ButtonType.OK);
            alert.showAndWait();
        }
    }

    /**
     * Action event handler. It close the stage.
     *
     * @param event
     */
    public void handleButtonClose(ActionEvent event) {
        stage.close();
    }

    /**
     * Action event handler. It open the login view.
     *
     * @param event
     */
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
