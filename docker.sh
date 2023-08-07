export JAVA_HOME="C:\Program Files\Java\jdk-18.0.2.1"

export PATH=$JAVA_HOME/bin:$PATH

docker rm -f "$(docker ps -a| grep demo| cut -f1 -d' ')"

./gradlew clean build

docker-compose -f ./docker/docker-compose.yaml --project-directory ./ build
docker-compose -f ./docker/docker-compose.yaml --project-directory ./ up
