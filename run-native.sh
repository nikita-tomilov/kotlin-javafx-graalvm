#!/bin/bash

EXEC=./target/gluonfx/x86_64-linux/kotlin-javafx-graalvm

if [ ! -f $EXEC ];
then
    echo "Please run './build-native.sh' before running this script"
    exit 1
fi

$EXEC