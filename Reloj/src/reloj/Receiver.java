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

/**
 *
 * @author Andrea Marín y Luis Felipe Landa
*/
public class Receiver {
    public static void main(String args[]){ 
	MulticastSocket s =null;
   	 try {
            InetAddress group = InetAddress.getByName("228.8.6.7"); // destination multicast group 
            s = new MulticastSocket(6790);
            s.joinGroup(group); 

            byte[] buffer = new byte[1000];
            System.out.println("Waiting for messages");
            
            DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
            s.receive(messageIn);
            
            System.out.println("La hora es " + new String(messageIn.getData())+ " desde "+ messageIn.getAddress());

            s.leaveGroup(group);		
 	}catch (SocketException e){
             System.out.println("Socket: " + e.getMessage());
	 }
         catch (IOException e){
             System.out.println("IO: " + e.getMessage());
        }
	 finally {
            if(s != null) s.close();
        }
    }
    
}
