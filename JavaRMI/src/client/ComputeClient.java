//
// indicate the location of security policies.
//

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.math.BigDecimal;
import interfaces.Compute;

public class ComputeClient {
    public static void main(String args[]) {
        System.setProperty("java.security.policy","file:/C:/Users/jgutierrgarc/Documents/NetBeansProjects/JavaRMI/src/client/client.policy");
        
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry("localhost"); // server's ip address args[0]
            Compute comp = (Compute) registry.lookup(name);
            
            System.out.println("3^2 = "+comp.square(3));
            System.out.println("3^3 = "+comp.power(3, 3));
            
        } catch (Exception e) {
            System.err.println("exception");
            e.printStackTrace();
        }
    }    
}
