FROM alpine:3.18

RUN apk --no-cache upgrade && \
    apk add --no-cache unzip

ARG version=20.0.2.9.1

# Please note that the THIRD-PARTY-LICENSE could be out of date if the base image has been updated recently.
# The Corretto team will update this file but you may see a few days' delay.
RUN wget -O /THIRD-PARTY-LICENSES-20200824.tar.gz https://corretto.aws/downloads/resources/licenses/alpine/THIRD-PARTY-LICENSES-20200824.tar.gz && \
    echo "82f3e50e71b2aee21321b2b33de372feed5befad6ef2196ddec92311bc09becb  /THIRD-PARTY-LICENSES-20200824.tar.gz" | sha256sum -c - && \
    tar x -ovzf THIRD-PARTY-LICENSES-20200824.tar.gz && \
    rm -rf THIRD-PARTY-LICENSES-20200824.tar.gz && \
    wget -O /etc/apk/keys/amazoncorretto.rsa.pub https://apk.corretto.aws/amazoncorretto.rsa.pub && \
    SHA_SUM="6cfdf08be09f32ca298e2d5bd4a359ee2b275765c09b56d514624bf831eafb91" && \
    echo "${SHA_SUM}  /etc/apk/keys/amazoncorretto.rsa.pub" | sha256sum -c - && \
    echo "https://apk.corretto.aws" >> /etc/apk/repositories && \
    apk add --no-cache amazon-corretto-20=$version-r0 && \
    rm -rf /usr/lib/jvm/java-20-amazon-corretto/lib/src.zip


ENV LANG C.UTF-8

ENV JAVA_HOME=/usr/lib/jvm/default-jvm
ENV PATH=$PATH:/usr/lib/jvm/default-jvm/bin

ENV APP_NAME spring-reactive-demo
ENV APP_ARTIFACT ${APP_NAME}-1.0.0-SNAPSHOT.jar

ENV APPLICATION_FOLDER .
ENV APP_HOME /app

RUN mkdir $APP_HOME
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser


ADD /${APPLICATION_FOLDER}/build/libs/*SNAPSHOT.jar ${APP_HOME}/${APP_ARTIFACT}

EXPOSE 7070

WORKDIR $APP_HOME

RUN chown -R javauser:javauser ${APP_HOME}
USER javauser

CMD ["sh", "-c", "java -jar $APP_ARTIFACT"]
