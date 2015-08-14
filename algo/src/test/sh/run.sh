#!/bin/sh

JAVA_HOME='/usr/local/java_home'
JAVA=${JAVA_HOME}/bin/java

PROJECT_ROOT_DIR='/Users/avachat/Learn/github/Practice/algo'
TARGET_DIR=${PROJECT_ROOT_DIR}/target
CLASSES_DIR=${TARGET_DIR}/classes

PROJECT_CLASSPATH=${CLASSES_DIR}

if [ $# -lt 1 ]
then
    echo "No classname specified"
    exit 1
fi

$JAVA -classpath ".:${PROJECT_CLASSPATH}" $*

