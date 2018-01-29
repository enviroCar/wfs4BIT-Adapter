#!/bin/bash
set -e

scriptDir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "${scriptDir}"

cd ../ && mvn clean install

docker build -t envirocar/wfs4bit \
    --build-arg JAR_FILE=$(find ./target/ -name "*.jar") \
    -f docker/Dockerfile .