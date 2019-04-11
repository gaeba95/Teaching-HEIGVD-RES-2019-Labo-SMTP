#!/bin/bash

# Ask maven to build the executable jar file from the source files
mvn clean install --file ../../SMTP-Client/pom.xml

# Copy the executable jar file in the current directory
cp ../../SMTP-Client/target/SMTP-Client-0.0.1-SNAPSHOT.jar .

# Build the Docker image locally
docker build --tag mail-robot .
