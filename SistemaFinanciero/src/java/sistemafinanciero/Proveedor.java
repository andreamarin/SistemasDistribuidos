/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemafinanciero;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author sdist
 */
public class Proveedor {
    @Resource(mappedName = "jms/GlassFishTestConnectionFactory")  
    private static ConnectionFactory connectionFactory;  
    @Resource(mappedName = "jms/Education")  
    private static Topic edu;
    @Resource(mappedName = "jms/Telecommunications")  
    private static Topic tele;
    @Resource(mappedName = "jms/Banks")  
    private static Topic bank;
    @Resource(mappedName = "jms/Transportation")  
    private static Topic trans;
    @Resource(mappedName = "jms/FoodSupply")  
    private static Topic food;
    
    private Topic[] topicos = {edu, tele, bank, trans, food};
    

    /**
     * @param args the command line arguments
     */
    
    public void produceMessages(int numNot){
        MessageProducer messageProducer = null;    
        TextMessage textMessage;
        int alert;
        int topic;
        
        try    {      
            Connection connection = connectionFactory.createConnection();      
            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);      
            textMessage = session.createTextMessage();
            
            for(int i = 0; i<numNot;i++){
                topic = (int)Math.floor(Math.random()*5);
                messageProducer = session.createProducer(topicos[topic]);
                alert = (int)Math.floor(Math.random()*10)+1;
                textMessage.setText(""+alert);
                messageProducer.send(textMessage);
                
                System.out.println("Para el tópico "+topic+ " se mandó el nivel de alerta "+ alert);
            }
            
            for (Topic topico : topicos) {
                messageProducer = session.createProducer(topico);
                textMessage.setText("end");
                messageProducer.send(textMessage);
            }
              
            
            messageProducer.close();      
            session.close();      
            connection.close();    
        }    catch (JMSException e)    
        {      
            e.printStackTrace();    
        }
    }
    
    public static void main(String[] args) {
        new Proveedor().produceMessages(10);
    }
    
}
