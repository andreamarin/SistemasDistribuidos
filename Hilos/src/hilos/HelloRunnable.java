/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

/**
 *
 * @author sdist
 */
public class HelloRunnable implements Runnable{

    @Override
    /*
    REcibe el hilo a ejecutar
    */
    public void run() {
        
        for (int i = 0; i < 100; i++) {
            System.out.println("Hilo Runnable " + Thread.currentThread().getName());
        }
    }
    
}
