#!/bin/bash

SITE='cnblog_news'

find ./target/classes/spring |xargs rm -f -r
find ./target/classes -name "*.xml"|xargs rm -f


tmp='./bin/resources'
tmp='./target/classes':$tmp
tmp='./target/osseanextractor-0.0.1-jar-with-dependencies-without-resources/*':$tmp

CLASSPATH=$tmp:$CLASSPATH

JAVA_OPTS="-ms128m -mx128m -Xmn128m -Djava.awt.headless=true -XX:MaxPermSize=128m" 

echo $CLASSPATH

java $JAVA_OPTS -classpath $CLASSPATH net.trustie.extractor.CNblogNews_Extractor >>log/${SITE}.log 2>&1 &