#!/bin/bash

podman run --rm -it -v $(pwd):/opt/buildenv:z -w /opt/buildenv openjdk:21-jdk bash -c "./mvnw package -Dmaven.repo.local=/opt/buildenv/m2_repo"
