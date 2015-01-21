#!/bin/bash

SITE='csdn_topic'

find ./target/classes -name "*.xml"|xargs rm -f

tmp='./bin/resources'
tmp='./target/classes':$tmp
tmp='./target/osseanextractor-0.0.1-jar-with-dependencies-without-resources/*':$tmp

CLASSPATH=$tmp:$CLASSPATH

JAVA_OPTS="-ms512m -mx512m -Xmn256m -Djava.awt.headless=true -XX:MaxPermSize=128m" 

echo $CLASSPATH

java $JAVA_OPTS -classpath $CLASSPATH net.trustie.extractor.CsdnTopic_Extractor >>log/${SITE}.log 2>&1 &


