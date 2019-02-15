/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdist
 */
public class Hilos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Barrera para que el hilo 3 corra hasta que acabe el hilo 1 y 2
        hilo1.join();
        hilo2.join();
        */
            
        Counter counter = new Counter(0);
        SynchronizedThread hilo1 = new SynchronizedThread(counter);
        hilo1.start();
        
        SynchronizedThread hilo2 = new SynchronizedThread(counter);
        hilo2.start();
        
        
    }
    
}
