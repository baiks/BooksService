FROM openjdk:17-jdk-slim

VOLUME /tmp

COPY target/Book-Service-0.0.1-SNAPSHOT.jar Book-Service-0.0.1-SNAPSHOT.jar

EXPOSE 8001

ENTRYPOINT exec java -jar Book-Service-0.0.1-SNAPSHOT.jar