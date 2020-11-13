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
 *
 * @author eneko
 */
@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class LogoutTestFX extends ApplicationTest {
    
    @Override public void start (Stage stage) throws Exception{
        //new LoginLogoutCliente().start(stage);
        
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
    @Test
    public void test1VericacionBtnClose(){
        clickOn("#btnClose");       
    }
    
    @Test
    public void test2VerificacionBtnSignOut(){
        clickOn("#btnSignOut");
        verifyThat("#login", isVisible());
    }
}
