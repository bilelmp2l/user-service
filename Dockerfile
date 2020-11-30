FROM openjdk:8
ADD target/user_service.jar user_service.jar
EXPOSE 9092
ENTRYPOINT ["java","-jar","user_service.jar"]
