FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/finance-tracker-0.0.4-SNAPSHOT.jar /app/finance-tracker.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/finance-tracker.jar"]
