FROM gradle:4.6-jdk8-alpine as compile

COPY . /home/source/java

WORKDIR /home/source/java

USER root

## Add the wait script to the image
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.5.0/wait .
RUN chmod +x wait

RUN chown -R gradle /home/source/java

USER gradle

RUN gradle clean build -x test

FROM openjdk:8-jre-alpine
WORKDIR /home/application/java

COPY --from=compile "/home/source/java/wait" .
COPY --from=compile "/home/source/java/build/libs/google-0.1.0.jar" .
EXPOSE 8080

CMD ./wait && java -jar /home/application/java/google-0.1.0.jar