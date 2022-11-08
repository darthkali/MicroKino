#Start with a base image containing Java runtime
FROM openjdk:17 as build

#Information around who maintains the image
MAINTAINER darthkali.de

# Add the application's jar to the container
COPY build/libs/movieservice-0.0.1-SNAPSHOT.jar movieservice-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/movieservice-0.0.1-SNAPSHOT.jar"]