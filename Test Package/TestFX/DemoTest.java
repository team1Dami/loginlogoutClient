package TestFX;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import ApplicationCliente.LoginLogoutCliente;
import javafx.stage.Stage;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.framework.junit.ApplicationTest;

/**
 *
 * @author eneko
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DemoTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new LoginLogoutCliente().start(stage);
    }

    @Test
    public void test1() throws InterruptedException {
        clickOn("#tfLogin");
        write("eneko1225");

        clickOn("#tfPasswd");
        write("serv1doR");

        clickOn("#btnLogin");

        clickOn("#btnSignOut");

        clickOn("#btnRegister");

        clickOn("#tfFullName");
        write("team1Dami");

        clickOn("#tfUser");
        write("team1");
        
        clickOn("#tfEmail");
        write("team1@gmail.com");

        clickOn("#tfPasswd");
        write("abcd*1234");

        clickOn("#tfPasswd2");
        write("abcd*1234");
        clickOn("#btnAccept");

    }

}
