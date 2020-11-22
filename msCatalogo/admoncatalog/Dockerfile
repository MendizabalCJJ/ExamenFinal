FROM adoptopenjdk/openjdk11:jdk-11.0.6_10-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ADD target/admoncatalog-0.0.1-SNAPSHOT.jar admoncatalog.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar admoncatalog.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar admoncatalog.jar
