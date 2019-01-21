/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdist
 */
public class TCPClient {
    Socket s;
    int serverPort = 7896;
    DataInputStream in;
    DataOutputStream out;
    
    public TCPClient(){
        try {    
            s = new Socket("localhost", serverPort);
            in = new DataInputStream( s.getInputStream());
            out = new DataOutputStream( s.getOutputStream());
        } 
        catch (UnknownHostException e) {
		System.out.println("Sock:"+e.getMessage()); 
	}
        catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
             //   s = new Socket("127.0.0.1", serverPort);    
    }
    
    public String mandar(int msg){
        try {
            out.writeInt(msg);
            return in.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
            return "Error";
        }
    }
    
    public void close(){
        if(s!=null) 
            try {
                s.close();
            } catch (IOException e){
                System.out.println("close:"+e.getMessage());
            }
    }
    
    
}
