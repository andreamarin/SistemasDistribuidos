/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papacaliente;

import java.io.Serializable;

/**
 *
 * @author sdist
 */
public class Papa implements Serializable{
    private static long id = 0;
    private double vida;
    
    public Papa(){
        id++;
        vida = Math.floor(Math.random()*5);
    }
    
    public boolean pierdeVida(){
        vida = vida -1;
        System.out.println(vida);
        return vida == 0;
    }
    
    public double getVida(){
        return vida;
    }
    
    public long getID(){
        return id;
    }
}
