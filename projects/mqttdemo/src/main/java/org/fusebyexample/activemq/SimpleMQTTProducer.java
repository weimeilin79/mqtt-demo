package org.fusebyexample.activemq;

import java.util.Properties;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;

public class SimpleMQTTProducer {

	private static final String BROKER_URL = "brokerURL";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	
	private static final int MESSAGE_DELAY_MILLISECONDS = 100;
	private static final int NUM_MESSAGES_TO_BE_SENT = 100;
	private static final String MESSAGE_CONTENT = "Hello";
	private static final String TOPIC_NAME = "foo";
	
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
	        System.out.print("Start Sending .");
	        for (int i = 1; i <= NUM_MESSAGES_TO_BE_SENT; i++) {
	        	connection.publish(TOPIC_NAME, MESSAGE_CONTENT.getBytes(), QoS.AT_LEAST_ONCE, false);
	        	Thread.sleep(MESSAGE_DELAY_MILLISECONDS);
	        	System.out.print(".");
	        }
	        System.out.println(".");
	        System.out.println("Done!");
	        connection.disconnect();
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
	
}
