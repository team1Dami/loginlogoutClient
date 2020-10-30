/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementacion;

import classes.Message;
import classes.User;
import interfaces.ClientServer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rubir
 */
public class ClientServerImplementation implements ClientServer{
    User userPrueba=new User();
    Message message=new Message();
    

    @Override
    public User signIn(User user) {
        
        message.setUser(user);
        message.setType(1);
        Hilo hilo=new Hilo();
        hilo.setMessage(message);
        hilo.start();
        try {
            hilo.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        message=hilo.getMessage();
        user=message.getUser();
        System.out.println(user.getLogIn());
        if(message.getException()!=null){
            user.setEmail("1");
        }
        else{
            user.setEmail("2");
        }
            
        return user;
    }

    @Override
    public User signUp(User user) {
       
        message.setUser(user);
        message.setType(2);
        Hilo hilo=new Hilo();
        hilo.setMessage(message);
        hilo.start();
        try {
            hilo.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        message=hilo.getMessage();
        if(message.getException()!=null){
              
        }
        return user;
    }
    
    public String exceptions(){
        String error=null;
        switch(message.getException().toString()){
            case "UserExistException":
                error="Usuario ya registrado";
                break;
            case "EmailExistException":
                break;
            case "EmailFormatException":
                break;
            case "LoginNoExistException":
                break;
            case "NoConnectionDBException":
                break;
            case "PasswordErrorException":
                break;
            
        }
        return error;
    }
}