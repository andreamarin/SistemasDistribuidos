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
public class Counter {
    private int n = 0;
    
    public Counter(int n){
        this.n = n;
    }
    
    /* Con synchronized sólo puede ejecutarse por un hilo a la vez  */
    synchronized public void print(){
        for (int i = 0; i < 10; i++) {
            System.out.print(this.n+" ");
            this.n++;
        }
    }
    
}
