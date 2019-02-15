/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import interfaces.Compute;
import interfaces.Credential;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdist
 */
public class ComputeServer implements Compute{
    
    public ComputeServer() throws RemoteException{
        super();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("java.security.policy","file:/C:/Users/sdist.ITAM/SistemasDistribuidos/RMI/src/server/server.policy");
        
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        
        // Iniciar RMI
        try {
            LocateRegistry.createRegistry(1099);
            String name = "Compute";
            
            ComputeServer engine = new ComputeServer();
            Compute stub = (Compute)UnicastRemoteObject.exportObject(engine, 0);
            
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            
            System.out.println("Holi");
            
        } catch (RemoteException ex) {
            Logger.getLogger(ComputeServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public double square(int number, Credential cred) throws RemoteException {
        if(cred.getPswd().equals("12345")){
            return number*number;
        }else{
            System.out.println("Wrong credential");
            return 0;
        }
    }

    @Override
    public double power(int num1, int num2, Credential cred) throws RemoteException {
        if(cred.getPswd().equals("12345")){
            return java.lang.Math.pow(num1,num2);
        }else{
            System.out.println("Wrong credential");
            return 0;
        }
    }
    
}
