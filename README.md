Red Hat JBoss xPaaS MQTT Simple Demo 
=====================================================================

Demo based on xPaaS Fuse Cartridge AMQ Broker

Setup and Configuration
-----------------------

- Make sure you have successfully setup xPaaS A-MQ Broker with MQTT protocol on OpenShift
Please See http://wei-meilin.blogspot.tw/2014/05/red-hat-openshift-xpaas-simple-mqtt.html for xPaaS setup.

- run rhc port-forward $YOUR_CONTAINER_NAME forward OpenShift port to local machine.   

![port forward](https://github.com/weimeilin79/mqtt-demo/blob/master/docs/rhc.png)

- run 'init.sh' 

- run mvn -P consumer under projects\mqttdemo

![consumer](https://github.com/weimeilin79/mqtt-demo/blob/master/docs/consumer.png?raw=true)

- run mvn -P producer under projects\mqttdemo

![Producer](https://github.com/weimeilin79/mqtt-demo/blob/master/docs/producer.png?raw=true)

Supporting Articles
-------------------
N/A

Released versions
-----------------

See the tagged releases for the following versions of the product:

- v0.1 - JBoss xPaaS MQTT Simple Demo, Consumer and Producer

![init](https://github.com/weimeilin79/mqtt-demo/blob/master/docs/init.png)



