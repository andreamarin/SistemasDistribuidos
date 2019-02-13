/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import interfaces.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
  * @author Andrea Marín y Luis Felipe Landa
*/
public class MasterNode {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("java.security.policy","file:/C:/Users/sdist.ITAM/Documents/NetBeansProjects/BolsaTareas/src/client/client.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        
        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            
            Task[] b1 = {new Task(1, "Imagenes", 5), new Task(2, "Imagenes", 10), new Task(3, "Imagenes", 15),new Task(4, "Imagenes", 20),
            new Task(5, "Imagenes", 30), new Task(6, "Imagenes", 5), new Task(7, "Imagenes", 10), new Task(8, "Imagenes", 15), 
            new Task(9, "Imagenes", 20), new Task(10, "Imagenes", 30)};
            
            Task[] b2 = {new Task(11, "Mineria", 5), new Task(12, "Mineria", 10), new Task(13, "Mineria", 15),new Task(14, "Mineria", 20),
            new Task(15, "Mineria", 30), new Task(16, "Mineria", 5), new Task(17, "Mineria", 10), new Task(18, "Mineria", 15), 
            new Task(19, "Mineria", 20), new Task(20, "Mineria", 30), new Task(21, "Mineria", 5), new Task(22, "Mineria", 10), new Task(23, "Mineria", 15),new Task(24, "Bioinformatica", 20),
            new Task(25, "Mineria", 30), new Task(26, "Mineria", 5), new Task(27, "Mineria", 10), new Task(28, "Mineria", 15), 
            new Task(29, "Mineria", 20), new Task(30, "Mineria", 30)};
            
            Task[] b3 = {new Task(31, "Bioinformatica", 5), new Task(32, "Bioinformatica", 10), new Task(33, "Bioinformatica", 15),new Task(34, "Bioinformatica", 20),
            new Task(35, "Bioinformatica", 30), new Task(36, "Bioinformatica", 5), new Task(37, "Bioinformatica", 10), new Task(38, "Bioinformatica", 15), 
            new Task(39, "Bioinformatica", 20), new Task(40, "Bioinformatica", 30), new Task(41, "Bioinformatica", 5), new Task(42, "Bioinformatica", 10), new Task(43, "Bioinformatica", 15),new Task(44, "Bioinformatica", 20),
            new Task(45, "Bioinformatica", 30)};
            
            Task[][] tareas = {b1, b2 ,b3};
            
            for (Task[] tarea : tareas) {
                System.out.println("Preparando para ejecutar tareas tipo "+ tarea[0].getRequirementId());
                EjecutarTareas ej = new EjecutarTareas(tarea, registry);
                ej.start();
            }
        } catch (RemoteException ex) {
            Logger.getLogger(MasterNode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    


    private static class EjecutarTareas extends Thread{
        Task[] bolsaTareas;
        Registry registry;
        String tipo;

        public EjecutarTareas(Task[] bolsaTareas, Registry registry){
            this.bolsaTareas = bolsaTareas;
            this.registry = registry;
            tipo = bolsaTareas[0].getRequirementId();
        }

        public void run(){
            long startTime = System.currentTimeMillis();
            try {
                switch(tipo){
                    case "Bioinformatica":
                        Bioinformatics nodeBio = (Bioinformatics) registry.lookup(tipo);
                        for (Task task : bolsaTareas) {
                            nodeBio.executeBioTask(task);
                        }
                        break;
                        
                    case "Mineria":
                        DataMining nodeData = (DataMining) registry.lookup(tipo);
                        for (Task task : bolsaTareas) {
                            nodeData.executeDataTask(task);
                        }
                        break;
                        
                    case "Imagenes":
                        ImageProcessing nodeImg = (ImageProcessing) registry.lookup(tipo);
                        for (Task task : bolsaTareas) {
                            nodeImg.executeImageTask(task);
                        }
                        break;      
                }
                
                long time = System.currentTimeMillis() - startTime;
                System.out.println(tipo+": "+time+"ms");
            } catch (RemoteException ex) {
                Logger.getLogger(MasterNode.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(MasterNode.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
