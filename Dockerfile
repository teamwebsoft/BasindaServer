FROM maven:3.8.5-openjdk-17 AS BUILD
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk-17.0.1-jdk-slim
COPY --from=build /target/BasindaServer-0.0.1-SNAPSHOT.jar BasindaServer.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "BasindaServer.jar"]