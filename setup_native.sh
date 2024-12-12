#!/bin/bash

./mvnw package
rsync -avz run_native_1.sh  root@fantomen-00:/tmp/
rsync -avz run_native_2.sh  root@fantomen-01:/tmp/
rsync -avz run_native_3.sh  root@fantomen-02:/tmp/
ssh root@fantomen-00 'chmod u+x /tmp/run_native_1.sh'
ssh root@fantomen-01 'chmod u+x /tmp/run_native_2.sh'
ssh root@fantomen-02 'chmod u+x /tmp/run_native_3.sh'
rsync -avz target/*.jar root@fantomen-00:/tmp/
rsync -avz target/*.war root@fantomen-00:/tmp/
rsync -avz target/*.jar root@fantomen-01:/tmp/
rsync -avz target/*.war root@fantomen-01:/tmp/
rsync -avz target/*.jar root@fantomen-02:/tmp/
rsync -avz target/*.war root@fantomen-02:/tmp/





