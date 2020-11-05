/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementacion;

import classes.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class of the client thread
 *
 * @author Rubén
 */
public class Hilo extends Thread {

    private ResourceBundle configFile;
    private String HOST;
    private int PORT;
    private Message message;

    /**
     * Set the message information to a local object Message
     *
     * @param message
     */
    public void setMessage(Message message) {
        this.message = message;
    }

    /**
     * Thread execution
     */
    @Override
    public void run() {
        configFile = ResourceBundle.getBundle("Properties.Client");
        this.HOST = configFile.getString("HOST");
        //CONTROLAR QUE EN EL ARCHIVO DE CONFIGURACIONES METAN INFORMACION CORRECTO
        this.PORT = Integer.parseInt(this.configFile.getString("PORT"));

        Socket socket = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            //socket = new Socket(HOST, PORT);
            socket = new Socket("192.168.20.105", 5000);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);

            objectInputStream = new ObjectInputStream(socket.getInputStream());
            message = (Message) objectInputStream.readObject();

        } catch (IOException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (null != objectOutputStream) {
                try {
                    objectOutputStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                if (null != socket) {
                    socket.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    /**
     * Returns the object message
     *
     * @return
     */
    public Message getMessage() {
        return message;
    }
}
