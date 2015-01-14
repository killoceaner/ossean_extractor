#!/bin/bash

tmp='./bin/resources'
tmp='./target/classes':$tmp
tmp='./target/fetchnetworks-1.0-SNAPSHOT-jar-with-dependencies-without-resources/*':$tmp

CLASSPATH=$tmp:$CLASSPATH

echo $CLASSPATH

java -classpath $CLASSPATH net.trustie.extractor.CsdnBlogs_Extractor >>log/${SITE}.log 2>&1 &
