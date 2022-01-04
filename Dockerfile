
FROM openjdk:11 as rabbitmq
EXPOSE 8081

ARG DEPENDENCY=target
ADD ${DEPENDENCY}/*.jar microServiceUser.jar
ENTRYPOINT ["java","-jar","microServiceUser.jar"]