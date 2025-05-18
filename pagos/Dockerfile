FROM amazoncorretto:21-alpine-jdk
WORKDIR /app

ADD ./target/pagos-0.0.1-SNAPSHOT.jar pagos.jar

ENTRYPOINT [ "java", "-jar", "pagos.jar" ]