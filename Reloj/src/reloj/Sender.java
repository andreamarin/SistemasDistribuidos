/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reloj;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrea Marín y Luis Felipe Landa
 */
public class Sender {
    public static void main(String args[]){ 

    MulticastSocket s =null;
     try {
        InetAddress group = InetAddress.getByName("228.8.6.7"); // destination multicast group 
        s = new MulticastSocket(6789);
        s.joinGroup(group); 
        while(true){
            System.out.println("Messages' TTL (Time-To-Live): "+ s.getTimeToLive());

            String myMessage= (new Date()).toString();
            byte [] m = myMessage.getBytes();

            DatagramPacket messageOut = new DatagramPacket(m, m.length, group, 6790);
            s.send(messageOut);
            
            Thread.sleep(2000);
        }	
    }catch (SocketException e){
         System.out.println("Socket: " + e.getMessage());
    }catch (IOException e){
         System.out.println("IO: " + e.getMessage());
     }  catch (InterruptedException ex) {
            Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
        }
     finally {
        if(s != null) s.close();
    }
}		     
}
