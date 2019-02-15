/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import interfaces.Compute;
import interfaces.Credential;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdist
 */
public class ComputeClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.setProperty("java.security.policy","file:/C:/Users/sdist.ITAM/SistemasDistribuidos/RMI/src/client/client.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        
        try {
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry("localhost");
            Compute comp = (Compute)registry.lookup(name);
            
            Credential c = new Credential("Juan", "Aguascalientes", 1987, "12345");
            
            System.out.println("3^2 = "+comp.square(3, c));
            System.out.println("3^3 = "+comp.power(3, 3, c));
            
        } catch (RemoteException ex) {
            Logger.getLogger(ComputeClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ComputeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
