#!/bin/bash


java -jar bootable-jms-test-bootable.jar \
    -Djboss.bind.public=172.18.209.17 \
    -Djboss.bind.address.private=172.18.209.17 \
    -Djboss.bind.address.management=0.0.0.0 \
    -Djboss.server.log.dir=/tmp/log \
    -Djboss.http.port=19080 \
    -Djboss.jms.server.address=fantomen-00.nya-srv.its.umu.se \
    -Djboss.jms.server.address2=fantomen-01.nya-srv.its.umu.se \
    -Djboss.jms.server.address3=fantomen-02.nya-srv.its.umu.se \
    --deployment=/tmp/bootable-jms-test.war

