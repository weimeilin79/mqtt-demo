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
