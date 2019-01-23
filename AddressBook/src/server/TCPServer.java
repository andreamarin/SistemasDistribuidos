/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author sdist
 */
public class TCPServer {

    /**
     * @param args the command line arguments
     */
    public static void main (String args[]) {
	try{
            AddressBook ab = new AddressBook();
          
            int serverPort = 7896; 
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while(true) {
                    System.out.println("Waiting for clients..."); 
                    Socket clientSocket = listenSocket.accept();
                    System.out.println("Aceptado");// Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made. 
                    Connection c = new Connection(clientSocket, ab);
                    c.start();
            }
	} catch(IOException e) {System.out.println("Listen :"+ e.getMessage());}
    }
    
}

class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
        AddressBook ab;
        
	public Connection (Socket aClientSocket, AddressBook ab) {
	    try {
                this.ab = ab;
		clientSocket = aClientSocket;
		in = new DataInputStream(clientSocket.getInputStream());
		out =new DataOutputStream(clientSocket.getOutputStream());
	     } catch(IOException e)  {System.out.println("Connection:"+e.getMessage());}
	}
        
        @Override
	public void run(){
            boolean flag = true;
            
            while(flag){
                try {			                 // an echo server
                    int data = in.readInt();
                    
                    if (data == -1){
                        close();
                        flag = false;
                        continue;
                    }
                    
                    System.out.println("Message received from: " + clientSocket.getRemoteSocketAddress());
                    Person p = ab.getRecord(data);
                    out.writeUTF(p.getName());
                    
                } 
                catch(EOFException e) {
                    System.out.println("EOF:"+e.getMessage());
                } 
                catch(IOException e) {
                    System.out.println("IO:"+e.getMessage());
                }
            }
        }
        
        public void close(){
            try {
                out.writeUTF("Cerrando conexion");
                clientSocket.close();
            } catch (IOException e){
                System.out.println(e);
            }     
        }
}
