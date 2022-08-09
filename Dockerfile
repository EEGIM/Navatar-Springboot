# JDK11 이미지 사용
FROM openjdk:11-jdk

VOLUME /tmp

ARG JAR_FILE=./build/libs/duksung.eegim.Navatar-1.0.4-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]
