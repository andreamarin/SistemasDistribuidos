/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdist
 */
public class Launcher {

    /**
     * Genero hilos
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PrintWriter writer;
        try {
            int numClientes =200;
            int numSolicitudes = 2000;
            String filename = "tiempos"+numClientes+".csv";
            writer = new PrintWriter(new FileWriter(filename, true));
            
            writer.println("\n"+numSolicitudes+"");
            writer.close();
            
            for (int i = 0; i < numClientes; i++) {
                System.out.println("Cliente "+i);
                ClientThread clientThread = new ClientThread(i, numSolicitudes, filename);
                clientThread.start();
            }
            //writer.print("2 "+numSolicitudes+",");

        } catch (IOException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
