/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serializable;

import java.net.*;
import java.io.*;

/**
 * @author Andrea Marín Alarcón y Luis Felipe Landa Lizarralde 
 */
public class TCPClient {

    public static void main (String args[]) throws ClassNotFoundException {
	Socket s = null;
        try {
            int serverPort = 7896;
            s = new Socket("localhost", serverPort);   
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream in = new ObjectInputStream( s.getInputStream());

            Person p = new Person("Toño", 79, "timbuctu");
            out.writeObject(p);        	

            Person pp = (Person) in.readObject();
            System.out.println("Received: "+ p.getName()) ;      
        } 
        catch (UnknownHostException e) {
            System.out.println("Sock:"+e.getMessage()); 
        }
        catch (EOFException e) {
            System.out.println("EOF:"+e.getMessage());
        } 
        catch (IOException e) {
            System.out.println("IO:"+e.getMessage());
        } finally {
            if(s!=null) 
                try {
                    s.close();
                } catch (IOException e){
                    System.out.println("close:"+e.getMessage());
                }
        }
    }
}
