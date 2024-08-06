FROM openjdk:11-jdk
LABEL maintainer="研究院研发组 <research-maint@baode.cn>"
RUN echo "Asia/Shanghai" > /etc/timezone
ARG PACKAGE_PATH=./target/ybybjzo2o-gateway.jar

ADD ${PACKAGE_PATH:-./} app.jar
ENTRYPOINT ["sh","-c","java  -jar $JAVA_OPTS app.jar"]