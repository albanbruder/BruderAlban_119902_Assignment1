#!/bin/bash
rm -r ./bin
rm -r ./dist
mkdir bin
mkdir dist
javac -d ./bin ./src/**/*.java
cd bin
jar cvfe ../dist/App.jar app.App ./**/*.class