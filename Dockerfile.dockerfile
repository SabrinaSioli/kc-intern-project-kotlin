FROM ubuntu

COPY . /app_hello_world

RUN apt-get update -y
RUN apt-get upgrade -y
RUN apt-get install -y openjdk-8-jre
RUN apt-get install -y maven
RUN ls

WORKDIR /app_hello_world

RUN mvn package

CMD mvn exec:java
