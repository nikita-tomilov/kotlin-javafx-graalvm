#!/bin/bash

if [ -z ${GRAALVM_HOME+x} ];
then
    echo "Please set the GRAALVM_HOME variable that will point to GraalVM JDK home"
    exit 1
fi

export JAVA_HOME=$GRAALVM_HOME
export ANDROID_SDK=/home/hotaro/Android/Sdk
export ANDROID_NDK=/home/hotaro/Android/android-ndk-r21e
mvn -Pandroid gluonfx:build gluonfx:package