/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author sdist
 */
public class ClientThread extends Thread{
    int idCliente;
    
    public ClientThread(int id){
        this.idCliente = id;
    }
    
    @Override
    public void run(){
        TCPClient client = new TCPClient();
        int numSolicitudes = 5;
        String res;
        
        for (int i = 0; i < numSolicitudes; i++) {
            res = client.mandar(3);
            System.out.println("Cliente "+idCliente+" solicitud "+i+" received: "+res);
            if(res.equals("Error"))
                break;
        }
        
        client.mandar(-1);
        client.close();
        
    }
    
}
