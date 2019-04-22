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
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author sdist
 */
public class Jugador {
    @Resource(mappedName = "jms/GlassFishTestConnectionFactory")  
    private static ConnectionFactory connectionFactory;  
    @Resource(mappedName = "jms/GlassFishTestQueue1")  
    private static Queue queue1;
    @Resource(mappedName = "jms/GlassFishTestQueue2")  
    private static Queue queue2;

    public void juega()  {    
        MessageProducer messageProducer;
        MessageConsumer messageConsumer;
        ObjectMessage objectMessageRecieve;
        ObjectMessage objectMessageSend;
        Papa papa;
        boolean end = false;
        try{
            
            Connection connection = connectionFactory.createConnection();      
            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);      
            
            messageProducer = session.createProducer(queue2);  
            messageConsumer = session.createConsumer(queue1);
            objectMessageSend = session.createObjectMessage();
            objectMessageRecieve = session.createObjectMessage();
            
            connection.start();
            
            Papa p = new Papa();
            objectMessageSend.setObject(p);
            messageProducer.send(objectMessageSend);
            System.out.println("La primera papa ha sido enviada");
            System.out.println("ID Papa:" + p.getID());
            System.out.println("Vida Papa" + p.getVida());
            
            while(!end){
                objectMessageRecieve = (ObjectMessage)messageConsumer.receive();
                papa = (Papa)objectMessageRecieve.getObject();
                
                if(papa.getVida() != 0){
                    end = papa.pierdeVida();
                    objectMessageSend.setObject(papa);
                    messageProducer.send(objectMessageSend);
                }else{
                    end = true;
                    System.out.println("Gané");
                }
            } 
            
            messageProducer.close();      
            session.close();      
            connection.close();    
        } catch (JMSException e) {      
            e.printStackTrace();    
        }  
    }
    
    public static void main(String[] args){
        new Jugador().juega();
    }
    
}
