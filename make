#!/bin/sh

rm bin/*
javac src/*.java -d bin/ -classpath src/
