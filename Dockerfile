FROM amazoncorretto:11-alpine-jdk
MAINTAINER MO
COPY target/mo-0.0.1-SNAPSHOT.jar mo-app.jar
ENTRYPOINT ["java", "-jar", "/mo-app.jar"]