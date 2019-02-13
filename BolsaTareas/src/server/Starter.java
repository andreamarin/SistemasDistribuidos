/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrea Marín y Luis Felipe Landa
 */
public class Starter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("java.security.policy","file://C:/Users/sdist.ITAM/Documents/NetBeansProjects/BolsaTareas/src/server/server.policy");
        
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        
        try {
            LocateRegistry.createRegistry(1099);
            
            SlaveNode s1 = new SlaveNode();
            SlaveNode s2 = new SlaveNode();
            SlaveNode s3 = new SlaveNode();
            
            s1.despliegue("Bioinformatica");
            s2.despliegue("Mineria");
            s3.despliegue("Imagenes");
            
            System.out.println("Servicios desplegados");
            
            
        } catch (RemoteException ex) {
            Logger.getLogger(Starter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
