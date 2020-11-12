package TestFX;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ApplicationCliente.Controller.SignUpController;
import ApplicationCliente.LoginLogoutCliente;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

/**
 *
 * @author USUARIO
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        //new LoginLogoutCliente().start(stage);
        
        SignUpController controller = new SignUpController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ApplicationCliente/Controller/SignUp.fxml"));
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

    public SignUpControllerTest() {
    }

    //verify everything is empty first
    @Test
    public void test1_InitialState() {
        //clickOn("#btnRegister");
        
        verifyThat("#tfFullName",hasText(""));
        verifyThat("#tfUser",hasText(""));
        verifyThat("#tfEmail",hasText(""));
        verifyThat("#tfPasswd",hasText(""));
        verifyThat("#tfPasswd2",hasText(""));
        verifyThat("#btnAccept",isDisabled());
        verifyThat("#btnCancel",isEnabled());   
    }
    //check that button accept is enable with everything filled and disable with something empty
    @Test
    public void test2_ButtonAcceptControl() {
       // clickOn("#btnRegister");
        
        clickOn("#tfFullName");
        write("team1Dami");

        clickOn("#tfUser");
        write("team1");

        clickOn("#tfEmail");
        write("team1@gmail.com");

        clickOn("#tfPasswd");
        write("passwd");

        clickOn("#tfPasswd2");
        write("passwd");

        verifyThat("#btnAccept", isEnabled());
        eraseText(6);
        verifyThat("#btnAccept", isDisabled());
        write("passwd");

        clickOn("#tfPasswd");
        verifyThat("#btnAccept", isEnabled());
        eraseText(6);
        verifyThat("#btnAccept", isDisabled());
        write("passwd");

        clickOn("#tfEmail");
        verifyThat("#btnAccept", isEnabled());
        eraseText(15);
        verifyThat("#btnAccept", isDisabled());
        //check that without @ the button accept is disabled
        write("team1gmail.com");
        verifyThat("#btnAccept", isDisabled());
         eraseText(14);
         write("team1@gmail.com");
         verifyThat("#btnAccept", isEnabled());
        

        clickOn("#tfFullName");
        verifyThat("#btnAccept", isEnabled());
        eraseText(9);
        verifyThat("#btnAccept", isDisabled());
        write("team1Dami");

        clickOn("#tfUser");
        verifyThat("#btnAccept", isEnabled());
        eraseText(5);
        verifyThat("#btnAccept", isDisabled());
        write("team1");

    }
    //check tehe different ways of introducing a passwd
    @Test
    public void test3_CheckPasswdCorrect() {
        //clickOn("#btnRegister");
                
        clickOn("#tfFullName");
        write("team1Dami");
        
        clickOn("#tfUser");
        write("team1");

        clickOn("#tfEmail");
        write("team1@gmail.com");

        clickOn("#tfPasswd");
        write("aa");
        
        clickOn("#tfPasswd2");
        write("aa");
        verifyThat("#btnAccept", isDisabled());
        eraseText(2);

        clickOn("#tfPasswd");
        eraseText(2);
        write("123456789123456789");
        
        clickOn("#tfPasswd2");
        write("123456789123456789");
        verifyThat("#btnAccept", isDisabled());
        eraseText(18);

        clickOn("#tfPasswd");
        eraseText(18);
        write("abcd*1234");
        
        clickOn("#tfPasswd2");
        write("abcd*1234");
        verifyThat("#btnAccept", isEnabled());

        clickOn("#tfPasswd");
        eraseText(9);
        
        clickOn("#tfPasswd2");
        eraseText(9);
        verifyThat("#btnAccept", isDisabled());

    }

    //check that when button cancel is clicked you move to login window 
    @Test
    public void test4_ButtonCancelControl() {
        //clickOn("#btnRegister");
        
        clickOn("#btnCancel");
        verifyThat("#login", isVisible());
    }

    //check that when button accept is clicked and the data is correct you move to login window 
    @Test
    public void test4_ButtonAcceptControl() {
       // clickOn("#btnRegister");
        
        clickOn("#btnAccept");
        verifyThat("#btnAccept", isDisabled());
    }

    

    //check that the passwd2 is different from passwd
    @Test
    public void test5_PasswdDifferent() {
        //clickOn("#btnRegister");
        
        clickOn("#tfUser");
        write("team1");

        clickOn("#tfFullName");
        write("team1Dami");

        clickOn("#tfEmail");
        write("team1@gmail.com");

        clickOn("#tfPasswd");
        write("abcd*1234");

        clickOn("#tfPasswd2");
        write("abcd*123456789");
        verifyThat("#btnAccept", isDisabled());

    }

}
