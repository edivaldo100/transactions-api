# Multi-stage Dockerfile for building and running the transactions-api
FROM maven:3.9.4-eclipse-temurin-17 as build
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /build/target/transactions-api.jar app.jar
EXPOSE 8005
ENTRYPOINT ["java","-jar","/app/app.jar"]

