/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import interfaces.*;
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
public class SlaveNode implements Bioinformatics, DataMining, ImageProcessing{
    
    public SlaveNode() throws RemoteException{
        super();
    }
    
    public void despliegue(String tipoServicio){
        try{
            SlaveNode node = new SlaveNode();
            Registry registry = LocateRegistry.getRegistry();
            switch(tipoServicio){
                case "Bioinformatica":
                        Bioinformatics stubBio = (Bioinformatics)UnicastRemoteObject.exportObject(node, 0);
                        registry.rebind("Bioinformatica", stubBio);
                    break;

                case "Mineria":
                    try {
                        DataMining stubMin = (DataMining)UnicastRemoteObject.exportObject(node, 0);
                        registry.rebind("Mineria", stubMin);
                    } catch (RemoteException ex) {
                        Logger.getLogger(SlaveNode.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case "Imagenes":
                    ImageProcessing stubImg = (ImageProcessing)UnicastRemoteObject.exportObject(node, 0);
                    registry.rebind("Imagenes", stubImg);
                    break; 
            }
        } catch (RemoteException ex) {
            Logger.getLogger(SlaveNode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Task executeBioTask(Task t) throws RemoteException{
        long seg = t.getLength()*1000;
        try {
            Thread.sleep(seg);
        } catch (InterruptedException ex) {
            Logger.getLogger(SlaveNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    @Override
    public Task executeDataTask(Task t) throws RemoteException{
        long seg = t.getLength()*1000;
        try {
            Thread.sleep(seg);
        } catch (InterruptedException ex) {
            Logger.getLogger(SlaveNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    @Override
    public Task executeImageTask(Task t) throws RemoteException{
        long seg = t.getLength()*1000;
        try {
            Thread.sleep(seg);
        } catch (InterruptedException ex) {
            Logger.getLogger(SlaveNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return t;
    }
    
}
