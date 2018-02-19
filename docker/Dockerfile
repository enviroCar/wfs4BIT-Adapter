FROM java:8

ARG JAR_FILE

ADD ${JAR_FILE} app.jar
RUN bash -c 'touch /app.jar'

EXPOSE 8080
EXPOSE 7776

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.config.location=/config/"]
