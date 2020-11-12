package Implementation;

import classes.Message;
import classes.User;
import exceptions.LoginNoExistException;
import exceptions.NoConnectionDBException;
import exceptions.NoServerConnectionException;
import exceptions.PasswordErrorException;
import interfaces.ClientServer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * This class manage the client implementation in order to access to server
 *
 * @author Ruben
 */
public class ClientServerImplementation implements ClientServer {

    private User userPrueba = new User();
    private Message message = new Message();
    private String login;

    /**
     * It will generate a thread to connect the server, it'll recieve a User
     * object witch contains the information introduced by the user. It'll do a
     * login
     *
     * @param user
     * @return Object class User
     * @throws exceptions.PasswordErrorException
     * @throws exceptions.LoginNoExistException
     * @throws exceptions.NoServerConnectionException
     */
    @Override
    public User signIn(User user) throws PasswordErrorException, LoginNoExistException, NoServerConnectionException, NoConnectionDBException {
        userPrueba = user;
        message.setUser(user);

        message.setType(1);
        ClientWorker hilo = new ClientWorker();
        hilo.setMessage(message);
        hilo.start();
        try {
            hilo.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        message = hilo.getMessage();

        user = message.getUser();
        this.login = user.getLogIn();
        if (message.getException() != null) {
            user = null;
            System.out.println(message.getException());
            if(message.getException().equals("LoginNoExistException")){
                throw new LoginNoExistException(null);
            }
            else if(message.getException().equals("PasswordErrorException")){
                throw new PasswordErrorException(null);
            }
            else if (message.getException().equals("NoConnectionDBException")){
                throw new NoConnectionDBException(null);
            }
            else{
                throw new NoServerConnectionException(null);
            }
            
            /*user = null;
            String error = exceptions();
            Alert alert = new Alert(Alert.AlertType.ERROR, error, ButtonType.OK);
            alert.showAndWait();
            message.setException(null);*/
        }
        return user;
    }

    /**
     * It will generate a thread to connect the server, it'll recieve a User
     * object witch contains the information introduced by the user. It'll do a
     * signup
     *
     * @param user
     * @return Object class User
     */
    @Override
    public User signUp(User user) {

        message.setUser(user);
        message.setType(2);
        ClientWorker hilo = new ClientWorker();
        hilo.setMessage(message);
        hilo.start();
        try {
            hilo.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        message = hilo.getMessage();
        if (message.getException() != null) {
            user = null;
            String error = exceptions();
            Alert alert = new Alert(Alert.AlertType.ERROR, error, ButtonType.OK);
            alert.showAndWait();
            message.setException(null);
        }
        return user;
    }

    /**
     * In case an error ocurred during the thread ejecution, it will return the
     * exception and will handle them
     *
     * @return String
     */
    public String exceptions() {
        return message.getException();
    }

    /**
     * Method to obtain the loggin of the user
     *
     * @return string login
     */
    public String getLogin() {

        return login;
    }
}
