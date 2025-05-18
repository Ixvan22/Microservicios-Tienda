FROM amazoncorretto:21-alpine-jdk
WORKDIR /app

ADD ./target/pedidos-0.0.1-SNAPSHOT.jar pedidos.jar

ENTRYPOINT [ "java", "-jar", "pedidos.jar" ]