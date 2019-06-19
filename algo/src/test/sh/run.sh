#!/bin/sh

#JAVA_HOME='/usr/local/java_home'
JAVA_HOME='/Library/Java/JavaVirtualMachines/jdk-10.0.1.jdk/Contents/Home'
JAVA=${JAVA_HOME}/bin/java

#PROJECT_ROOT_DIR='/Users/avachat/Learn/github/Practice/algo'
PROJECT_ROOT_DIR='/Users/abhava01/Abhay/repo/Practice/algo'
TARGET_DIR=${PROJECT_ROOT_DIR}/target
CLASSES_DIR=${TARGET_DIR}/classes

PROJECT_CLASSPATH=${CLASSES_DIR}

if [ $# -lt 1 ]
then
    echo "No classname specified"
    exit 1
fi

$JAVA -classpath ".:${PROJECT_CLASSPATH}" $*

