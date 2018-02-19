#!/bin/bash
set -e
source secret.sh

scriptDir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "${scriptDir}"

# create docker-compose files
if [ -f docker-compose.yml ]; then
    rm docker-compose.yml
    rm Dockerfile
fi

# replace secrets in docker related files
sed -e "s/__PROVIDER__/$PROVIDER_ID/g; s/__SECRET__/${SECRET_KEY}/g" template/docker-compose.yml >> docker-compose.yml
cp ./template/Dockerfile Dockerfile

# clean maven build.
cd ../ && mvn clean install

# build the docker image
docker build -t envirocar/wfs4bit \
    --build-arg JAR_FILE=$(find ./target/ -name "*.jar") \
    -f docker/Dockerfile .