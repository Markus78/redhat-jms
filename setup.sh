#!/bin/bash

TARGET_HOST1=fantomen-00.nya-srv.its.umu.se
TARGET_HOST2=fantomen-01.nya-srv.its.umu.se

echo "# Build bootable jar"
./mvnw clean package

echo "# Build and export image"
rm -f jboss-eap-8
rm -f jboss-eap-8.tar
podman build . -t jboss-eap-8
podman save --output jboss-eap-8.tar jboss-eap-8

echo "# Transfer image to target hosts and load"
rsync -avz --info=progress2 jboss-eap-8.tar root@$TARGET_HOST1:/tmp/
rsync -avz --info=progress2 jboss-eap-8.tar root@$TARGET_HOST2:/tmp/
ssh root@$TARGET_HOST1 'podman load --input /tmp/jboss-eap-8.tar'
ssh root@$TARGET_HOST2 'podman load --input /tmp/jboss-eap-8.tar'

echo "# Transfer start scripts"
rsync -avz eap8_1.sh root@$TARGET_HOST1:/tmp/
rsync -avz eap8_2.sh root@$TARGET_HOST2:/tmp/
ssh root@$TARGET_HOST1 'chmod u+x /tmp/eap8_1.sh'
ssh root@$TARGET_HOST2 'chmod u+x /tmp/eap8_2.sh'


