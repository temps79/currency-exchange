#
# Build stage
#
FROM maven:3.8.1-openjdk-17 AS build
COPY / /home/app
#COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install -DskipTests

#
# Package stage
#
FROM openjdk:17-alpine
COPY --from=build /home/app/target/currency-exchange-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]