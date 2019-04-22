/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemafinanciero;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author sdist
 */
public class Broker {
    @Resource(mappedName = "jms/GlassFishTestConnectionFactory") 
    private static ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/FoodSupply")  
    private static Topic topic;  
    
    public void getMessages(){
        Connection connection;
        MessageConsumer messageConsumer;
        TextMessage textMessage;
        boolean goodByeReceived = false;
        try {
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);
            messageConsumer = session.createConsumer(topic);
            System.out.println("Handling FoodSupply accounts");
            connection.start(); 
            
            int level;
            String msg;
            
            while(!goodByeReceived){
                System.out.println("Waiting for messages...");
                textMessage = (TextMessage) messageConsumer.receive();
                if (textMessage != null){
                    msg = textMessage.getText();
                    
                    if(msg != null && msg.equals("end")){
                        goodByeReceived = true;
                        System.out.println("Bye!");
                    }else if(msg != null){
                        level = Integer.parseInt(msg);
                        System.out.println("I received an alert level "+level);
                        if(level > 5){
                            System.out.println("Selling a lot!!!!!!!!");
                        }else{
                            System.out.println("...");
                        }
                    }
                }     
            }
            
            messageConsumer.close();    
            session.close();    
            connection.close(); 
        } catch (JMSException ex) {
            Logger.getLogger(Broker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Broker().getMessages();
    }
    
}
