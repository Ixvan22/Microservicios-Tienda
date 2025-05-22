FROM maven:3.9.9-amazoncorretto-21-alpine AS build
WORKDIR /app

COPY . .
RUN mvn package -DskipTests

FROM amazoncorretto:21-alpine-jdk
WORKDIR /app

RUN addgroup --system --gid 1001 appgroup && adduser --system --uid 1001 --ingroup appgroup appuser
USER appuser:appgroup

COPY --from=build /app/target/*.jar eureka-server.jar

EXPOSE 8761

ENTRYPOINT [ "java", "-jar", "eureka-server.jar" ]