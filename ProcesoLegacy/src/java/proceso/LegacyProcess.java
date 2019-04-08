/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andreamarin
 */
public class LegacyProcess {
    public static void main(String[] args){
        System.setProperty("java.net.preferIPv4Stack", "true");
        try {
            ServerSocket listenSocket = new ServerSocket(6760);
            System.out.println("Waiting for messages");
            Socket clientSocket = listenSocket.accept(); 
            Connection c = new Connection(clientSocket);
            c.start(); //abre hilo por cada cliente
        } catch (IOException ex) {
            Logger.getLogger(LegacyProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static class Connection extends Thread{
        DataInputStream in;
        Socket tcp = null;
        
        public Connection(Socket tcp){
            try {
                this.tcp = tcp;
                in = new DataInputStream(tcp.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(LegacyProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        @Override
        public void run(){
            DatagramSocket udp = null;
            
            try {
                String msg = in.readUTF();
                System.out.println("Received: "+msg);
                msg += "!";
                
                // manda mensaje UDP
                udp = new DatagramSocket();
                InetAddress host = InetAddress.getByName("localhost");
                byte[] m = msg.getBytes();
                DatagramPacket response = new DatagramPacket(m, m.length, host,6770);
                udp.send(response);
                System.out.println("Sent message");
            } catch (SocketException ex) {
                Logger.getLogger(LegacyProcess.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(LegacyProcess.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                if(udp != null){
                    udp.close();
                }

                if(tcp != null){
                    try {
                        tcp.close();
                    } catch (IOException ex) {
                        Logger.getLogger(LegacyProcess.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
