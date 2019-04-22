/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papacaliente;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author sdist
 */
public class ComePapa {
    @Resource(mappedName = "jms/GlassFishTestConnectionFactory") 
    private static ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/GlassFishTestQueue2")  
    private static Queue queue1;  
    
    public void getMessages()  {    
        Connection connection;
        MessageConsumer messageConsumer;
        ObjectMessage objectMessage;
        int cont = 100000;
        Papa papa;
        
        try    {      
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);
            messageConsumer = session.createConsumer(queue1);
            connection.start(); 
            while (cont > 0){
                cont--;
                System.out.println("Esperando papas");
                objectMessage = (ObjectMessage)messageConsumer.receive();
                if (objectMessage != null)        {
                    System.out.print("Comí papa");   
                    System.out.println();     
                }        
            }     

            messageConsumer.close();    
            session.close();    
            connection.close(); 
            
        }    catch (JMSException e)    {  
            e.printStackTrace();   
        } 
    }
    
    public static void main(String[] args){
        new ComePapa().getMessages();
    }
}
