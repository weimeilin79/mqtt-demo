/*
 * Copyright (C) Red Hat, Inc.
 * http://www.redhat.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author christina
 * */
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
