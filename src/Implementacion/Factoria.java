/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementacion;

/**
 * Client implementation factory
 *
 * @author Ruben
 */
public class Factoria {

    /**
     * Returns an implementation
     *
     * @return Client server implementation
     */
    public ClientServerImplementation getImplement() {
        return new ClientServerImplementation();
    }
}
