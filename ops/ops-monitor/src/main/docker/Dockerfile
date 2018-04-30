FROM registry.cn-hangzhou.aliyuncs.com/vortexinfo/platform:ojdk8
MAINTAINER vortex
VOLUME /tmp
RUN mkdir /app
ARG ACTIVE_ENV=lan
ENV APP_PATH /app/app.jar
ADD ops-monitor-2.1.0-SNAPSHOT.jar ${APP_PATH}
ENV JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=${ACTIVE_ENV} -Duser.timezone=GMT+08 -Xms1024m -Xmx2048m
RUN /bin/sh -c 'touch ${APP_PATH}'
EXPOSE 8989
ENTRYPOINT java ${JAVA_OPTS} -jar ${APP_PATH}