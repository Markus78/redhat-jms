# bootable-eap

Simple war to send and receive a JMS message. A INFO message is printed on each received message.

# Build
./mvnw package

# Buildimage

podman build . -t jboss-eap-8

# Run 

```bash
java -Djboss.jms.server.address=localhost -jar target/bootable-jms-test-bootable.jar --deployment=target/bootable-jms-test.war
```

# Test

## Send message

```bash
curl -X POST  http://localhost:19080/bootable-jms-test/message
```

Send 10 messages

```bash
curl -X POST  http://localhost:19080/bootable-jms-test/message?number=10
```
