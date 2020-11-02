/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClient.Controller.ApplicationClient.Controller;

    
import ApplicationClient.Controller.SignUpController;
import com.sun.deploy.security.ValidationState;
import org.junit.Test;
import org.junit.FixMethodOrder;
import static org.junit.Assert.*;
import javafx.stage.Stage;
import org.testfx.api.FxToolkit;
import org.junit.runners.MethodSorters;
import org.testfx.api.FxAssert;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

/**
 *
 * @author USUARIO
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpControllerTest extends SignUpController{
    
    public SignUpControllerTest() {
    }
    
    //verify everything is empty first
    @Test
    public void test1_InitialState() {
        verifyThat("#tfFullName",hasText(""));
        verifyThat("#tfUser",hasText(""));
        verifyThat("#tfEmail",hasText(""));
        verifyThat("#tfPasswd",hasText(""));
        verifyThat("#tfPasswd2",hasText(""));
        verifyThat("#btnAccept",isDisabled());
        verifyThat("#btnCancel",isEnabled());   
    }
    //check that button accept is enable with everything filled and disable with something empty
    public void test2_ButtonAcceptControl(){
       
        clickOn("#tfUser");
        write("team1");
        
        clickOn("#tfFullName");
        write("team1Dami");
        
        clickOn("#tfEmail");
        write("team1@gmail.com");
        
        clickOn("#tfPasswd");
        write("passwd");
        
        clickOn("#tfPasswd2");
        write("passwd");
        
        verifyThat("#btnAccept",isEnabled());
        eraseText(6);
        verifyThat("#btnAccept",isDisabled()); 
        write("passwd");
        
        clickOn("#tfPasswd");      
        verifyThat("#btnAccept",isEnabled());
        eraseText(6);
        verifyThat("#btnAccept",isDisabled()); 
         write("passwd");
         
        clickOn("#tfEmail");
        verifyThat("#btnAccept",isEnabled());
        eraseText(15);
        verifyThat("#btnAccept",isDisabled());
        write("team1@gmail.com");
        
        clickOn("#tfFullName");
        verifyThat("#btnAccept",isEnabled());
        eraseText(9);
        verifyThat("#btnAccept",isDisabled());
        write("team1Dami");
        
        clickOn("#tfUser");    
        verifyThat("#btnAccept",isEnabled());
        eraseText(5);
        verifyThat("#btnAccept",isDisabled());
        write("team1");
        
        
        
        
       
    }
    
    
    //check that when button cancel is clicked you move to login window 
    public void test4_ButtonCancelControl(){
        clickOn("#btnCancel");
        verifyThat("#Login",isVisible());
    }
    //check that when button accept is clicked and the data is correct you move to login window 
    public void test4_ButtonAcceptControl(){
        clickOn("#btnAccept");
        verifyThat("#Login",isVisible());
    }
    //check tehe different ways of introducing a passwd
    public void test3_CheckPasswdCorrect(){
        
        clickOn("#ftPasswd");
        write("aa");
        verifyThat("#btnAccept", isEnabled());
        
        clickOn("#ftPasswd");
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        FxAssert.verifyThat("#btnAccept", isEnabled());
        
        clickOn("#ftPasswd");
        write("aa");
       verifyThat("#btnAccept", isEnabled());
        
        clickOn("#ftPasswd");
        write("abcd*1234");
        verifyThat("#btnAccept", isEnabled());
        
        clickOn("#ftPasswd");
        write("");
        verifyThat("#btnAccept", isDisabled());
        
    }
    
    //check that the passwd2 is different from passwd
    public void test5_PasswdDifferent(){
        
        clickOn("#ftPasswd");
        write("abcd*1234");
        
        clickOn("#ftPasswd2");
        write("abcd*123456789");
        verifyThat("#btnAccept", isEnabled());
        
    }
    private void clickOn(String ftPasswd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void write(String aa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void eraseText(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }  
}
