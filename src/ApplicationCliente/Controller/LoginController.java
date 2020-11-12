package ApplicationCliente.Controller;

import ApplicationCliente.LoginLogoutCliente;
import Implementation.ClientServerImplementation;
import Implementation.ImpFactory;
import classes.User;
import exceptions.LoginNoExistException;
import exceptions.NoServerConnectionException;
import exceptions.PasswordErrorException;
import interfaces.ClientServer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller of the login view
 *
 * @author Rubén
 */
public class LoginController {

    private static final Logger logger = Logger.getLogger("ApplicationClient.Controller.LoginController");
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

    /**
     * Set the stage of the view
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Initialize the view and set actions on different widgets
     *
     * @param root
     */
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

    /**
     * Text changed event handler. If both fields are empty the button is
     * disable
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void textChange(ObservableValue observable, String oldValue, String newValue) {
        //disable the Login button

        //If password field is higher than 12
        if (tfPasswd.getText().length() > 12) {
            btnLogin.setDisable(true);
        } //If text fields are empty 
        else if (tfLogin.getText().trim().isEmpty()
                || tfPasswd.getText().trim().isEmpty()) {
            btnLogin.setDisable(true);
        } //Else, enable accept button
        else {
            btnLogin.setDisable(false);
        }

    }

    /**
     * Action event handler. It validate the login and password fields. If both
     * fields are ok open logout view.
     *
     * @param event
     */
    private void handleButtonLogin(ActionEvent event) {
        if (tfLogin.getText().isEmpty() || tfPasswd.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error", ButtonType.APPLY);
            alert.showAndWait();
        }else if(validateNoBlankSpace(tfLogin.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR, "No deje espacios en blanco en el Usuario", ButtonType.APPLY);
            alert.showAndWait();
        }else if(validateNoBlankSpace(tfPasswd.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR, "No deje espacios en blanco en la contraseña", ButtonType.APPLY);
            
        }
        else {
            User myUser = new User();
            myUser.setLogIn(tfLogin.getText().toString());
            myUser.setPasswd(tfPasswd.getText().toString());
            ClientServer imp = ImpFactory.getImplement();
            User serverUser = null;
            try {
                serverUser = imp.signIn(myUser);
            } catch (LoginNoExistException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                alert.showAndWait();
            } catch (PasswordErrorException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
            } catch (NoServerConnectionException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
            }

            if (serverUser != null) {

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
                    Alert alert = new Alert(Alert.AlertType.ERROR,"Ha ocurrido un error inesperado", ButtonType.OK);
                    alert.showAndWait();
                }
            } else {
                logger.info("User null");
            }
        }

    }
    
    private boolean validateNoBlankSpace(String email) {
        // Patron para validar el email
        Pattern pattern = Pattern.compile("\\s");

        Matcher mather = pattern.matcher(email);
        if (!mather.find()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Action event handler. It open register view.
     *
     * @param event
     */
    private void handleButtonRegister(ActionEvent event) {

        Parent root;
        try {
            SignUpController controller = new SignUpController();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
            root = (Parent) loader.load();
            controller = (loader.getController());
            controller.setStage(stage);
            controller.initStage(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginLogoutCliente.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.WARNING, "No se ha podido cargar la ventana", ButtonType.OK);
        }
    }
}
