package Implementation;

import classes.Message;
import classes.User;
import exceptions.NoServerConnectionException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 * The class of the client thread
 *
 * @author Rubén
 */
public class ClientWorker extends Thread {

    private static final Logger logger = Logger.getLogger("Implementacion.Hilo");
    private static final ResourceBundle clientFile = ResourceBundle.getBundle("ApplicationClient.Properties/Client");
    private static String HOST;
    private static int PORT;
    private static Message message;

    /**
     * Set the message information to a local object Message
     *
     * @param message object of class Message
     */
    public void setMessage(Message message) {
        this.message = message;
    }

    /**
     * Thread execution
     */
    public void run() {
        logger.info("Hilo del cliente recogiendo el mensaje");

        this.HOST = clientFile.getString("HOST");
        this.PORT = Integer.parseInt(this.clientFile.getString("PORT"));

        Socket socket = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            socket = new Socket(HOST, PORT);
            socket.setSoTimeout(10000);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
            logger.log(Level.INFO, "Mensaje{0}", message.getUser());

            objectInputStream = new ObjectInputStream(socket.getInputStream());
            message = (Message) objectInputStream.readObject();

        } catch (ConnectException ex) {
            Logger.getLogger(ClientWorker.class.getName()).log(Level.SEVERE, null, ex);
            message.setUser(null);
            message.setException(new NoServerConnectionException(null));
        } catch (SocketException ex) {
            Logger.getLogger(ClientWorker.class.getName()).log(Level.SEVERE, null, ex);
            message.setUser(null);
            message.setException(new NoServerConnectionException(null));
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ClientWorker.class.getName()).log(Level.SEVERE, null, ex);
            message.setUser(null);
            message.setException(ex);
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                    objectInputStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(ClientWorker.class.getName()).log(Level.SEVERE, null, ex);
                    message.setException(ex);
                }
            }
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientWorker.class.getName()).log(Level.SEVERE, null, ex);
                message.setException(ex);
            }
        }
    }

    /**
     * Returns the object message
     *
     * @return a class Message
     */
    public static Message getMessage() {
        return message;
    }
}
