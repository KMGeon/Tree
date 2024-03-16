#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

REPOSITORY=/home/ubuntu/nginxPractice
PROJECT_NAME=nginxPractice

echo "> Build 파일 복사"
echo "> cp $REPOSITORY/bliud/libs/*.war $REPOSITORY"

cp $REPOSITORY/build/libs/*.war $REPOSITORY/


echo "> 새 어플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/*.war | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

nohup java -jar \
    -Dspring.config.location=classpath:/application-$IDLE_PROFILE.yml \
    -Dspring.profiles.active=$IDLE_PROFILE \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
