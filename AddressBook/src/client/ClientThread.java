/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdist
 */
public class ClientThread extends Thread{
    int idCliente;
    int numSolicitudes;
    String filename;
    
    public ClientThread(int id, int n, String file){
        this.idCliente = id;
        this.numSolicitudes = n;
        this.filename  = file;
    }
    
    //@Overwrite
    @Override
    public void run(){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(filename, true));
            
            TCPClient client = new TCPClient();
            String res;
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < numSolicitudes; i++) {
                res = client.mandar(1);
                System.out.println("Cliente "+idCliente+" solicitud "+i+" received: "+res);
                if(res.equals("Error"))
                    break;
            }   long spentTime = System.currentTimeMillis() - startTime;
            res = client.mandar(-1);
            System.out.println("Cilente " + idCliente + ": " + res);
            System.out.println("Cliente "+idCliente+" "+spentTime);
            writer.print(spentTime + ", ");
            client.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
        }
    }
    
}
