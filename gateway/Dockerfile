FROM amazoncorretto:21-alpine-jdk
WORKDIR /app
EXPOSE 8090
ADD ./target/gateway-0.0.1-SNAPSHOT.jar gateway.jar

ENTRYPOINT [ "java", "-jar", "gateway.jar" ]