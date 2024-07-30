FROM maven:3.9.4-eclipse-temurin-17 as build

COPY src src
COPY pom.xml pom.xml

RUN mvn clean package -DskipTests

FROM bellsoft/liberica-openjdk-debian:17

RUN adduser --system run-user && addgroup --system myresale-application && adduser run-user myresale-application
USER run-user

WORKDIR /app

COPY compose.yml compose.yml
COPY --from=build target/MyResale-4.0.4-SNAPSHOT.jar ./application.jar

ENTRYPOINT ["java", "-jar", "./application.jar"]