#!/bin/sh

rm -rf bin/* src/**/*.class
javac src/**/*.java -d bin/ -classpath src/
