FROM openjdk:12
COPY ./out/production/LearningAIWithJava/ /tmp
WORKDIR /tmp
ENTRYPOINT ["java","Main"]