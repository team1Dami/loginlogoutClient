package TestFX;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import ApplicationCliente.Controller.LogoutController;
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
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 * The class that will check logout window behavior
 *
 * @author Eneko
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogoutTestFX extends ApplicationTest {

    /**
     * Starts the login window
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        LogoutController controller = new LogoutController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ApplicationCliente/Controller/Logout.fxml"));
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
     * Checks if the button to go back is enabled, clickable and works when the
     * window is created
     */
    @Test
    public void test1() {
        clickOn("#btnClose");
    }

    /**
     * Checks if the login window is created and visible when you sign out
     */
    @Test
    public void test2() {
        clickOn("#btnSignOut");
        verifyThat("#login", isVisible());
    }
}
