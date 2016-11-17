FROM java:8
EXPOSE 8080
ADD /build/libs/rabbitMQGrails3-0.1.war rabbitMQGrails3-0.1.war
ENTRYPOINT ["java","-Dgrails.env=prod","-jar","rabbitMQGrails3-0.1.war"]