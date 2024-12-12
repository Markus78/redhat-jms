FROM registry.access.redhat.com/ubi9/openjdk-21-runtime:1.21-1@sha256:77ddb49be45d284b0996f669f1465b4d8b0d0cc2147e79d150f70bc68143940c

USER root

ENV TZ=Europe/Stockholm HOME=/opt/myuser LANG=C.utf8

RUN groupadd --system --gid=1000 myuser && \
    useradd --system --no-log-init --gid myuser --uid=1000 myuser && \
    mkdir /var/myuser && chown myuser:myuser /var/myuser && \
    mkdir /opt/myuser && chown myuser:myuser /opt/myuser

USER myuser

ENV JBOSS_JMS_SERVER_ADDRESS=localhost
ENV JBOSS_JMS_SERVER_PORT=19080

COPY target/bootable-jms-test-bootable.jar /opt/myuser/
COPY target/bootable-jms-test.war /opt/myuser/

WORKDIR /var/myuser

EXPOSE 19080
EXPOSE 8787
EXPOSE 9990

CMD ["java", \
     "-agentlib:jdwp=transport=dt_socket,address=*:8787,server=y,suspend=n", \
     "-jar", \
     "/opt/myuser/bootable-jms-test-bootable.jar", \
     "-Djboss.bind.address=0.0.0.0", \
     "-Djboss.bind.address.management=0.0.0.0", \
     "-Djboss.server.log.dir=/var/myuser", \
     "--deployment=/opt/myuser/bootable-jms-test.war" \
]
