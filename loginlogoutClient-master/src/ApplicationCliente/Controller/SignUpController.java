package ApplicationCliente.Controller;

import Implementation.ClientServerImplementation;
import Implementation.ImpFactory;
import classes.User;
import interfaces.ClientServer;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

/**
 * Class to controll the elements of the signUp UI
 *
 * @author saray
 */
public class SignUpController {

    private static final Logger logger = Logger.getLogger("ApplicationClient.Controller.SignUpController");

    private static final int MAX_PASS_LENGHT = 12;
    private static final int MIN_PASS_LENGHT = 6;
    private  Alert alert;
    @FXML
    private Stage stage;
    @FXML
    private TextField tfFullName;
    @FXML
    private TextField tfUser;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPasswd;  //min 6 max 12
    @FXML
    private TextField tfPasswd2;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnAccept;

    /**
     * Method to set the stage
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    private void handleWindowShowing(WindowEvent we){
        tfFullName.textProperty().addListener(this::textChanged);
        tfFullName.setPromptText("Introduzca su nombre completo");
        tfUser.textProperty().addListener(this::textChanged);
        tfUser.setPromptText("Introduzca un nombre de usuario");
        tfEmail.textProperty().addListener(this::textChanged);
        tfEmail.setPromptText("Introduzca su Email");
        tfPasswd.textProperty().addListener(this::textChanged);
        tfPasswd.setPromptText("Introduzca una contraseña");
        tfPasswd2.textProperty().addListener(this::textChanged);
        tfPasswd2.setPromptText("Repita su contraseña");
        btnCancel.setOnAction(this::handleButtonCancelarAction);
        btnAccept.setDisable(true);
        btnAccept.setOnAction(this::handleButtonAceptarAction);
    }
    

    /**
     * Method to initialice the stage and add the events to the elements of the
     * UI
     *
     * @param root
     */
    public void initStage(Parent root) {
        logger.info("Initializing signUp stage");

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Formulario de registro");
        stage.setResizable(false);
        stage.setOnShowing(this::handleWindowShowing);
        stage.setOnCloseRequest(this::setOncloseRequest);
        
        /*
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
          public void handle(WindowEvent we) {
                     try {
            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent newroot = (Parent) loader.load();
            LoginController controller = ((LoginController) loader.getController());
            controller = (loader.getController());
            controller.setStage(stage);
            controller.initStage(newroot);
            we.consume(); //hacer como si nada hubiese pasado

        } catch (Exception ex) {
            logger.log(Level.SEVERE,
                    "UI LoginController: Error opening users managing window: {0}",
                    ex.getMessage());
           alert = new Alert(Alert.AlertType.WARNING, "No se ha podido cargar la ventana", ButtonType.OK);
        }
          }
      });      
        */
       
        stage.showAndWait();
    }
    
    //method that asks when you press the x if you want to go back or not, if you press OK you go back to login otherwhise you stay in the signup
     private void setOncloseRequest(WindowEvent we){
        
         try {
           alert = new Alert(Alert.AlertType.WARNING, "Desea Salir de esta ventana", ButtonType.OK,ButtonType.CANCEL);
           alert.showAndWait();
           if(alert.getResult().getButtonData().isCancelButton()){          
               alert = new Alert(Alert.AlertType.WARNING, "Se ha cancelado la accion",ButtonType.OK);
               alert.showAndWait();
               we.consume();
            
           }else{  
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
            logger.log(Level.SEVERE,
                    "UI LoginController: Error opening users managing window: {0}",
                    ex.getMessage());
           alert = new Alert(Alert.AlertType.WARNING, "No se ha podido cargar la ventana", ButtonType.OK);
           alert.showAndWait();
        }
    }
    

    /**
     * Method to initialize the view
     *
     * @param location
     * @param resources
     */

    /**
     * Method to control the changes on the text fields when the focus change If
     * an error ocurr an alert advice the user of the error
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void textChanged(ObservableValue observable, String oldValue,String newValue) {
        //logger.info("Checking changes in text fields");//cada vez que se cambia un campo
        
        //if this requisites arent't fullfield(fields not been empty) the buttonAccept will stay disabled
        if ( !tfFullName.getText().isEmpty()
                && !tfUser.getText().isEmpty()
                && !tfEmail.getText().isEmpty()
                && !tfPasswd.getText().isEmpty()
                && !tfPasswd2.getText().isEmpty()
                && tfPasswd.getText().length() >= MIN_PASS_LENGHT//minimun lenght for the password
                && tfPasswd.getText().length() <= MAX_PASS_LENGHT){//maximun lenght for the password
            btnAccept.setDisable(false);
        }else{
            btnAccept.setDisable(true);
        }
        
        
       
       
    }

    /**
     * Method to manage the action on the button Accept First of all: get the
     * values of the text fields and set to the User class After sends the
     * values to the ClientServerImplementation class If the register is
     * succesfull the login view is opened
     *
     * @param event
     */
    @FXML
    private void handleButtonAceptarAction(ActionEvent event) {
        User myUser;
        try {
            myUser = new User();
            myUser.setFullname(tfFullName.getText().toString());
            myUser.setLogIn(tfUser.getText().toString());
            myUser.setEmail(tfEmail.getText().toString());
            myUser.setPasswd(tfPasswd.getText().toString());
            
            if(!validateEmail(myUser.getEmail())){
                //the gmail is not correct
            alert = new Alert(Alert.AlertType.WARNING, "Error en el gmail", ButtonType.OK);
                          alert.showAndWait();
            }else if(!( tfPasswd.getText().equals(tfPasswd2.getText()))){
                //the passwd is not correct
            alert = new Alert(Alert.AlertType.WARNING, "Error en la contraseña", ButtonType.OK);
                          alert.showAndWait();
            }else{
                //Create the new user
              ClientServer imp = ImpFactory.getImplement();
              User serverUser = imp.signUp(myUser);

              if (null != serverUser) {
                  stage.close();
                  /*
                  
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                    Parent root = (Parent) loader.load();
                    LoginController controller = ((LoginController) loader.getController());
                    controller = (loader.getController());
                    controller.setStage(stage);
                    controller.initStage(root);
                  */
                }  
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "User can not set", e.getMessage());
            alert = new Alert(Alert.AlertType.WARNING, "No se ha podido conectar con el servidor. Inténtelo más tarde.");
            alert.showAndWait();
            
            try {
                //close the stage and go back to the login view
                stage.close();
                /*
            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = (Parent) loader.load();
            LoginController controller = ((LoginController) loader.getController());
            controller = (loader.getController());
            controller.setStage(stage);
            controller.initStage(root);
                */

        } catch (Exception ex) {
            logger.log(Level.SEVERE,
                    "UI LoginController: Error opening users managing window: {0}",
                    ex.getMessage());
        }
        }
    }

    /**
     * Method to manage the action on the button Cancel if is clicked the logIn
     * view is opened, if it can't be opened an alert message is showing
     *
     * @param event
     */
    @FXML
    private void handleButtonCancelarAction(ActionEvent event) {

        //try {
             //close the stage and go back to the login view
            stage.close();
            /*
            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = (Parent) loader.load();
            LoginController controller = ((LoginController) loader.getController());
            controller = (loader.getController());
            controller.setStage(stage);
            controller.initStage(root);

        } catch (Exception ex) {
            logger.log(Level.SEVERE,
                    "UI LoginController: Error opening users managing window: {0}",
                    ex.getMessage());
           alert = new Alert(Alert.AlertType.WARNING, "No se ha podido cargar la ventana", ButtonType.OK);
        }
            */
    }

    /**
     * Method to validate the email format
     *
     * @param email
     * @return true or false
     */
    private boolean validateEmail(String email) {
        // Patern to validate the email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        // Compare and see if the email introduced respects the patern establiced
        Matcher mather = pattern.matcher(email);
        if (!mather.find()) {
            return false;
        } else {
            return true;
        }
    }
}
