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
        long[] tiempos = new long[numSolicitudes];
        long startTime, spentTime;
        try {
            writer = new PrintWriter(new FileWriter(filename, true));
            
            TCPClient client = new TCPClient();
            String res;
            for (int i = 0; i < numSolicitudes; i++) {
                startTime = System.currentTimeMillis();
                res = client.mandar(1);
                spentTime = System.currentTimeMillis() - startTime;
                
                System.out.println("Cliente "+idCliente+" solicitud "+i+" received: "+res);
                if(res.equals("Error"))
                    break;
                
                tiempos[i] = spentTime;
            }   
            
            res = client.mandar(-1);
            System.out.println("Cilente " + idCliente + ": " + res);
            
            double desv = stdDev(tiempos);
            double avg = prom(tiempos);
            
            writer.println(avg + ", "+desv+"");
            
            System.out.println("Avg: "+avg);
            System.out.println("Desv: "+desv);
            
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
    
    private double stdDev(long[] list){
        double sum = 0.0;
        double num = 0.0;

        for (int i=0; i < list.length; i++)
        sum+=list[i];

        double mean = sum/list.length;
        for (int i=0; i <list.length; i++)
            num+=Math.pow((list[i] - mean),2);
        return Math.sqrt(num/list.length);
    }
    
    private double prom(long[] list){
        double sum = 0.0;
        
        for (int i = 0; i < list.length; i++) {
            sum += list[i];
        }
        
        return sum/list.length;
    }
    
}
