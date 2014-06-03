package org.fusebyexample.activemq;

import static org.fusesource.hawtbuf.Buffer.utf8;

import java.util.Properties;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.Message;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

public class SimpleMQTTConsumer {
	private static final String BROKER_URL = "brokerURL";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";

	public static final void main(String args[]){
		try{
			
			Properties props = new Properties();
            props.load(SimpleMQTTConsumer.class.getResourceAsStream("/mqtt.properties"));
            
			MQTT mqtt = new MQTT();
			mqtt.setHost(props.getProperty(BROKER_URL));
			
			mqtt.setUserName(props.getProperty(USERNAME));
			mqtt.setPassword(props.getProperty(PASSWORD));
		
		
	        BlockingConnection connection = mqtt.blockingConnection();
	        
	        connection.connect();
	        
	        System.out.println("Connected!");
	        Topic[] topics = {new Topic(utf8("foo"), QoS.AT_LEAST_ONCE)};
	        connection.subscribe(topics);
	        System.out.println("Start receiving messages, type ctrl-C to exit.....");
	        int i = 1;
	        while (true) {
	        	Message message = connection.receive();
	        	System.out.println("Got " + (i++) + ". message: ["+new String(message.getPayload())+"]");
	            // To let the server know that it has been processed.
		        message.ack();
		       
		    }
		}catch(Exception e){
        	e.printStackTrace();
        }
		
	}
	
}
