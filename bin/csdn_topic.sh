#!/bin/bash

SITE='csdn_topic'

tmp='./bin/resources'
tmp='./target/classes':$tmp
tmp='./target/osseanextractor-0.0.1-jar-with-dependencies-without-resources/*':$tmp

CLASSPATH=$tmp:$CLASSPATH

echo $CLASSPATH

java -classpath $CLASSPATH net.trustie.extractor.CsdnTopic_Extractor >>log/${SITE}.log 2>&1 &


