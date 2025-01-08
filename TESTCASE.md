# Testcase

You need 2 servers and your own computer is then the server where you built everything on lets call that "Builder"

So

Server1
Server2
Builder


You have to build jboss-eap-8 and transfer the image to Server1 and Server2 ( see setup.sh for more inspiration )

After you built the war and image you need to start the server on Server2 like this ( replace the variables with your values )


#!/bin/bash

HOSTNAME=server2's hostname 
JMS_HOSTNAME=server1's hostname 
PRIVATE_IP=server2's_ip 

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


After that you start the same image on Server1

#!/bin/bash

HOSTNAME=Server1's hostname
JMS_HOSTNAME=Server1's hostname
PRIVATE_IP=Server1 IP

sudo podman rm -f eap8_1
sudo podman run --rm --name eap8_1 \
  --hostname=$HOSTNAME \
  -e JBOSS_JMS_SERVER_PORT=19080 \
  -e JBOSS_JMS_SERVER_ADDRESS=$JMS_HOSTNAME \
  -e JAVA_TOOL_OPTIONS="-Djboss.http.port=19080 -Djboss.bind.address.private=$PRIVATE_IP -Djms.broker.host=fantomen-00.nya-srv.its.umu.se" \
  -it \
  -v $(pwd)/log:/var/myuser:z \
  -p 19080:19080 \
  -p 9991:9990 \
  jboss-eap-8

Once both of these are up and running on Server1 and Server2 you can test that JMS works fine by using curl on your Builder node 

curl -X POST  http://Server1:19080/bootable-jms-test/message?number=100

After this curl you should see in podman logs for the containers on Server1 and Server2 that they are working fine and the Messages are beeing clustered between Server1 and Server2

At this point if you restart Server2 it will no longer receieve any JMS messages when it comes back up after the restart. 


