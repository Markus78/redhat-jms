#!/bin/bash

HOSTNAME=fantomen-00.nya-srv.its.umu.se
JMS_HOSTNAME=fantomen-00.nya-srv.its.umu.se
PRIVATE_IP=172.18.209.7

sudo podman rm -f eap8_2
sudo podman run --rm --name eap8_2 \
  --hostname=$HOSTNAME \
  -e JBOSS_JMS_SERVER_PORT=19080 \
  -e JBOSS_JMS_SERVER_ADDRESS=$JMS_HOSTNAME \
  -e JAVA_TOOL_OPTIONS="-Djboss.http.port=19080 -Djboss.bind.address.private=$PRIVATE_IP" \
  -it \
  -p 19080:19080 \
  -p 9991:9990 \
  jboss-eap-8


