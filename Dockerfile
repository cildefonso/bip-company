FROM adoptopenjdk/openjdk9-openj9:latest

WORKDIR /app

COPY target/bip-company-0.0.1-SNAPSHOT.jar /app/bip-company.jar

ENTRYPOINT ["java","-jar","bip-company.jar"]