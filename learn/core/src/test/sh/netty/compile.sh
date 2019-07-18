#!/bin/sh

JAR_DIR=./jars
NETTY_JAR=$JAR_DIR/netty-3.2.5.Final.jar

rm -f ping/*.class
javac -1.6 -nowarn -cp .:$NETTY_JAR ping/*.java
echo ""
