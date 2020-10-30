/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementacion;

import classes.Message;
import classes.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rubén Rabadán
 */

public class Hilo extends Thread {
    private ResourceBundle configFile;
    private String HOST;
    private int PORT;
    private Message message;

    public void setMessage(Message message) {
        this.message = message;
    }
    
    public void run(){
        User user=new User();
        user.setLogIn("Ruben");
        user.setPasswd("abcd*1234");
        
        Message mensaje=new Message();
        mensaje.setUser(user);
        
        
        configFile=ResourceBundle.getBundle("Properties.Client");
        this.HOST=configFile.getString("HOST");
        //CONTROLAR QUE EN EL ARCHIVO DE CONFIGURACIONES METAN INFORMACION CORRECTO
        this.PORT=Integer.parseInt(this.configFile.getString("PORT"));
        
        Socket socket=null;
        ObjectOutputStream objectOutputStream=null;
        ObjectInputStream objectInputStream=null;
        try {
            socket = new Socket(HOST, PORT);
            
            objectOutputStream = new ObjectOutputStream (socket.getOutputStream()); 
            objectOutputStream.writeObject(mensaje);
            
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            Message vuelta= (Message) objectInputStream.readObject();
            System.out.println(vuelta.getUser().getLogIn()+" "+vuelta.getUser().getEmail());
            
        } catch (IOException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (null != objectOutputStream)
                try {
                    objectOutputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (null != socket)
                    socket.close();
            } catch (IOException ex) {
                System.out.println (ex.getMessage());
            }
        }
            
            
    }
}
