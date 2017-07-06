#!/bin/bash

. environment.sh

VAR=$1

usage() {
 echo "Argument Required: (nutritional | funfacts | ayurvedic)"
}

if [ -z "$VAR" ];
then 
   usage
fi

java -Dspring.profiles.active=dev -classpath ${KALRAV_CLASSPATH} com.lognsys.loader.IngestXLSData $VAR 
