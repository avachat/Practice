#!/bin/sh

JAR_DIR=./jars

#java -cp .:./txn txn/StartTxnTasks 1 10 100000
java -cp .:./txn txn/StartTxnTasks $*
