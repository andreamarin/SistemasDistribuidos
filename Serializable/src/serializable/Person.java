/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializable;

import java.io.Serializable;

/**
 * @author Andrea Marín Alarcón y Luis Felipe Landa Lizarralde 
 */
public class Person implements Serializable{
    private String name;
    private int age;
    private String place;
    
    /**
     * Para poder empaquetar y desmpaquetar necesitamos un constructor y getters 
     */
    
    public Person(String name, int age, String place){
        this.name = name;
        this.age = age;
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPlace() {
        return place;
    }
    
}
