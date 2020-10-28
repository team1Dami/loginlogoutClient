/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementacion;

import classes.User;
import interfaces.ClientServer;

/**
 *
 * @author rubir
 */
public class Implementacion implements ClientServer{
    User userPrueba=new User();

    @Override
    public User signIn(User user) {
        userPrueba=prueba();
        if(user.getLogIn().equalsIgnoreCase(userPrueba.getLogIn())){
            if(user.getPasswd().equals(userPrueba.getPasswd())){
                user.setEmail("1");
            }
            else{
                user.setEmail("2");
            }
        }
        else{
            user.setEmail("2");
        }
        return user;
    }

    @Override
    public User signUp(User user) {
        
        return user;
    }
    
    public User prueba(){
        userPrueba.setEmail("hola@gmail.com");
        userPrueba.setFullname("1");
        userPrueba.setId(1);
        userPrueba.setLogIn("1");
        userPrueba.setPasswd("123456");
        return userPrueba;
    }
}
