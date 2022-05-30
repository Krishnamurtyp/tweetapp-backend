FROM openjdk:8
LABEL maintainer="mrinal.shubham"
ADD target/tweetapp-0.0.1-SNAPSHOT.jar tweetapp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","tweetapp-0.0.1-SNAPSHOT.jar"]