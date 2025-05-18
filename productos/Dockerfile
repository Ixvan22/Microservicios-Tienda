FROM amazoncorretto:21-alpine-jdk
WORKDIR /app

ADD ./target/productos-0.0.1-SNAPSHOT.jar productos.jar

ENTRYPOINT [ "java", "-jar", "productos.jar" ]