FROM java:8

ARG JAR_FILE

ENV BIOT_PROVIDER_ID __PROVIDER__
ENV BIOT_PROVIDER_SECRET __SECRET__

ADD ${JAR_FILE} app.jar
RUN bash -c 'touch /app.jar'

EXPOSE 8080
EXPOSE 7776

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--bigiot.provider.id=${BIOT_PROVIDER_ID}","--bigiot.provider.secret=${BIOT_PROVIDER_SECRET}","--spring.config.location=/config/"]
