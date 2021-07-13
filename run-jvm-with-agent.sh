#!/bin/bash

if [ -z ${GRAALVM_HOME+x} ];
then
    echo "Please set the GRAALVM_HOME variable that will point to GraalVM JDK home"
    exit 1
fi

export JAVA_HOME=$GRAALVM_HOME
mvn gluonfx:runagent