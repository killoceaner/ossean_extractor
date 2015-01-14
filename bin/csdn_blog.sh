#!/bin/bash

SITE='csdn_blog'

tmp='./bin/resources'
tmp='./target/classes':$tmp
tmp='./target/osseanextractor-0.0.1-jar-with-dependencies-without-resources/*':$tmp

CLASSPATH=$tmp:$CLASSPATH

echo $CLASSPATH

java -classpath $CLASSPATH net.trustie.extractor.CsdnBlogs_Extractor >>log/${SITE}.log 2>&1 &
