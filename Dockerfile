FROM openjdk:8-jdk
EXPOSE 8086
ADD ./build/libs/cash-flow-management-service-*-SNAPSHOT.jar clash-flow-service.jar
ENTRYPOINT ["java", "-jar", "clash-flow-service.jar"]