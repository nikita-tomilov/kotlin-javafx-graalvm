#!/bin/bash

if [ -z ${GRAALVM_HOME+x} ];
then
    echo "Please set the GRAALVM_HOME variable that will point to GraalVM JDK home"
    exit 1
fi

if [ -z ${JAVAFX_SDK_HOME+x} ];
then
    echo "Please set the JAVAFX_SDK_HOME variable that will point to JavaFX SDK home"
    exit 1
fi

JARFILE=./target/kotlin-javafx-graalvm-0.1-SNAPSHOT-jar-with-dependencies.jar

if [ ! -f $JARFILE ];
then
    echo "Please run 'mvn clean package' before running this script"
    exit 1
fi

java11fx() {
  export JAVA_HOME=$GRAALVM_HOME
  export JDK_HOME=$GRAALVM_HOME
  $JAVA_HOME/bin/java --module-path $JAVAFX_SDK_HOME/lib --add-modules javafx.base,javafx.controls,javafx.fxml "$@"
}

TMPDIR=/tmp/graaltmp
mkdir -p $TMPDIR

# https://habr.com/ru/company/1c/blog/566632/
java11fx -agentlib:native-image-agent=config-merge-dir=$TMPDIR -jar $JARFILE

# For some reason, gluon plugin expects a bit different filename
mv $TMPDIR/reflect-config.json ./src/main/resources/META-INF/substrate/config/reflectionconfig.json

echo OK