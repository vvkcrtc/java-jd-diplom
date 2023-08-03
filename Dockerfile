FROM openjdk:17-jdk-slim-buster

EXPOSE 8080

ADD target/jd-diplom-0.0.1-SNAPSHOT.jar cloud.jar

ENTRYPOINT ["java","-jar","/cloud.jar"]



