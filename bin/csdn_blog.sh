#!/bin/bash

SITE='csdn_blog'

find ./target/classes -name "*.xml"|xargs rm -f

tmp='./bin/resources'
tmp='./target/classes':$tmp
tmp='./target/osseanextractor-0.0.1-jar-with-dependencies-without-resources/*':$tmp

CLASSPATH=$tmp:$CLASSPATH

JAVA_OPTS="-Xms256m -Xmx256m -Xmn128m" 

echo $CLASSPATH

java $JAVA_OPTS -classpath $CLASSPATH net.trustie.extractor.CsdnBlogs_Extractor >>log/${SITE}.log 2>&1 &


