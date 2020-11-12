package TestFX;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import ApplicationCliente.Controller.LoginController;
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

/**
 *
 * @author eneko
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginTestFX extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        //new LoginLogoutCliente().start(stage);

        LoginController controller = new LoginController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ApplicationCliente/Controller/Login.fxml"));
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

    @Test
    public void test1() throws InterruptedException {
        clickOn("#tfLogin");
        write("Prueba1");
        clickOn("#tfPasswd");
        write("Asiesx");
        verifyThat("#btnLogin", isEnabled());
        
    }

    @Test
    public void test2() {
        clickOn("#tfLogin");
        write("Prueba1");
        verifyThat("#btnLogin", isDisabled());
        eraseText(7);
        clickOn("#tfPasswd");
        write("Asies");
        verifyThat("#btnLogin", isDisabled());
        
    }

    @Test
    public void test3() {
        clickOn("#tfLogin");
        write("HolaBienasTardesMeLLamoFedericoGarciaLorcaYVengoAcConquistarElMundo");
        verifyThat("#btnLogin", isDisabled());
        eraseText(20);
        clickOn("#tfPasswd");
        write("HolaBuenasTardes123456");
        verifyThat("#btnLogin", isDisabled());
        
    }
}
