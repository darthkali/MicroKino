# Multi-stage Docker Image Build
FROM gradle:jdk17 as build
WORKDIR /workspace/app

# Copy Gradle Config
COPY build.gradle.kts .
COPY settings.gradle.kts .

# First gradle run without src to pull all dependencies
# Just ignoring errors
RUN gradle build 2>/dev/null || true

# copy source and build again
COPY src src
RUN gradle build

# Final Image that will contain the application created by above build image
FROM openjdk:17.0.2

ENV LANGUAGE='en_US:en'

# We make four distinct layers so if there are application changes the library layers can be re-used
COPY --from=build --chown=185 /workspace/app/build/libs/bookingservice-0.0.1-SNAPSHOT.jar bookingservice-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/bookingservice-0.0.1-SNAPSHOT.jar"]