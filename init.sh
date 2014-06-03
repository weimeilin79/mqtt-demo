#!/bin/sh 
DEMO="JBoss xPaaS MQTT Simple Demo"
VERSION=6.1.0
AUTHORS="Christina Lin"
PROJECT="git@github.com:weimeilin79/mqtt-demo.git"
DEMO_HOME=./target
PRJ_DIR=./projects/mqttdemo
PROPS_NAME="mqtt.properties"
PRJ_PROPS_DIR=./projects/mqttdemo/src/main/resources/mqtt.properties

# wipe screen.
clear 



echo
echo "#################################################################"
echo "##                                                             ##"   
echo "##  Setting up the ${DEMO}   ##"
echo "##                                                             ##"   
echo "##                                                             ##"   
echo "##                 ###        #   #   ###                      ##"
echo "##                #   #       ## ##  #   #                     ##"
echo "##                #####  ###  # # #  #   #                     ##"
echo "##                #   #       #   #  #  ##                     ##"
echo "##                #   #       #   #   #####                    ##"
echo "##                                                             ##"   
echo "##                                                             ##"   
echo "##  brought to you by,                                         ##"   
echo "##                    ${AUTHORS}                            ##"
echo "##                                                             ##"   
echo "##  ${PROJECT}                   ##"
echo "##                                                             ##"   
echo "#################################################################"
echo

# double check for maven.
command -v mvn -q >/dev/null 2>&1 || { echo >&2 "Maven is required but not installed yet... aborting."; exit 1; }

echo "  - Broker setup -"
echo "Enter your broker URL and press [ENTER] (example: tcp://127.0.0.1:1883):"
read brokerurl
echo "Enter your broker username and press [ENTER]:"
read username
echo "Enter your broker password and press [ENTER]:"
read password

echo ""
echo "brokerURL="$brokerurl > support/mqtt.properties
echo "username="$username >> support/mqtt.properties
echo "password="$password >> support/mqtt.properties


if [ -f "$PRJ_PROPS_DIR" ]
then
	echo "  - existing JBoss Broker setting detected..."
	echo "  - backup previous file"
	mv $PRJ_PROPS_DIR $PRJ_PROPS_DIR.backup
	cp support/$PROPS_NAME $PRJ_PROPS_DIR
else
	cp support/$PROPS_NAME $PRJ_PROPS_DIR
fi

#echo Now going to build the project.
#echo
#cd $PRJ_DIR
#mvn clean install -DskipTests

echo ""
echo Make sure your OpenShift Gears are running correctly
echo
echo And PLEASE REMEBER TO FORWARD YOUR MQTT PORT BY executing => rhc port-forward \$YOUR_CONTAINER_NAME\ 
echo
echo You have two steps to start the demo:
echo ""
echo 1. Start up MQTT Consumer by running \"mvn -P consumer\" under projects\mqttdemo
echo ""
echo 2. Start up MQTT Consumer by running \"mvn -P producer\" under projects\mqttdemo
echo

echo Red Hat $DEMO $VERSION Setup Completed.
echo