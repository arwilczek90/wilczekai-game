#!/bin/sh

class=$1
shift;
options=$*

echo "compiling..."
javac -cp $CLASSPATH:../../library $class.java `find . -name '*.java' -newer $class.java`
echo "running..."
java -cp $CLASSPATH:../../library $class $options
