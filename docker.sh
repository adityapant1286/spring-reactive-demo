export JAVA_HOME="C:\Program Files\Java\jdk20.0.2_10"

export PATH=$JAVA_HOME/bin:$PATH

docker rm -f "$(docker ps -a| grep demo| cut -f1 -d' ')"

./gradlew clean build

docker-compose -f ./docker/docker-compose.yaml --project-directory ./ build
docker-compose -f ./docker/docker-compose.yaml --project-directory ./ up
