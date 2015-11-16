#!/bin/sh

JAR_DIR=./jars
NETTY_JAR=$JAR_DIR/netty-3.2.5.Final.jar

java -cp .:./ping:$NETTY_JAR ping/MyNettyClient
