FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/medical-clinic-0.0.1-SNAPSHOT.jar medical-clinic-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/medical-clinic-0.0.1-SNAPSHOT.jar"]