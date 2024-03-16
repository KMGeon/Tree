#!/bin/bash
BASE_PATH=/home/ubuntu/nginxPractice
BUILD_PATH=$(ls /home/ubuntu/nginxPractice/build/libs/*.war)
JAR_NAME=$(basename $BUILD_PATH)
echo "> build 파일명: $JAR_NAME"


find_idle_profile() {
    echo "> 현재 구동중인 Set 확인"
    CURRENT_PROFILE=$(curl -s http://localhost/profile)
    echo "> $CURRENT_PROFILE"

    if [ "$CURRENT_PROFILE" = "set1" ]; then
        IDLE_PROFILE="set2"
        IDLE_PORT="8082"
    elif [ "$CURRENT_PROFILE" = "set2" ]; then
        IDLE_PROFILE="set1"
        IDLE_PORT="8081"
    else
        echo "> 일치하는 Profile이 없습니다. Profile: $CURRENT_PROFILE"
        echo "> set1을 할당합니다. IDLE_PROFILE: set1"
        IDLE_PROFILE="set1"
        IDLE_PORT="8081"
    fi

    echo "$IDLE_PROFILE"
}

function find_idle_port()
{
    if [ ${IDLE_PROFILE} == "set1" ]
    then
      echo "8081"
    else
      echo "8082"
    fi
}


deploy() {
    echo "> ===============[deploy.sh START]==============="
    BUILD_PATH=$(ls "$BASE_PATH/build/libs"/*.war)
    JAR_NAME=$(basename "$BUILD_PATH")
    echo "> Build 파일명: $JAR_NAME"
    echo "> Build 파일 복사"
    cp $BUILD_PATH $DEPLOY_PATH
    IDLE_PROFILE=$(find_idle_profile)

    echo "> IDLE_PROFILE : {${IDLE_PROFILE}}"

    IDLE_APPLICATION=$IDLE_PROFILE-nginxPractice.war
    IDLE_APPLICATION_PATH=$DEPLOY_PATH$IDLE_APPLICATION

    echo "> application.war 교체"
    ln -Tfs $DEPLOY_PATH$JAR_NAME $IDLE_APPLICATION_PATH

    echo "> $IDLE_PROFILE 에서 구동중인 애플리케이션 pid 확인"
    IDLE_PID=$(pgrep -f "$IDLE_APPLICATION")

    if [ -z "$IDLE_PID" ]; then
        echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
    else
        echo "> kill -15 $IDLE_PID"
        sudo kill -15 "$IDLE_PID"
        sleep 5
    fi

    echo "> $IDLE_PROFILE 배포"
    echo "> $IDLE_PROFILE $IDLE_APPLICATION_PATH 경로"
    sudo nohup java -jar -Dspring.profiles.active=$IDLE_PROFILE $IDLE_APPLICATION_PATH 1>/dev/null 2>&1 &

    echo "> $IDLE_PROFILE 10초 후 Health check 시작"
    echo "> curl -s http://localhost:$IDLE_PORT/actuator/health "
    sleep 10

    for retry_count in {1..10}; do
        response=$(curl -s "http://localhost:$IDLE_PORT/actuator/health")
        up_count=$(echo "$response" | grep 'UP' | wc -l)

        if [ "$up_count" -ge 1 ]; then
            echo "> Health check 성공"
            break
        else
            echo "> Health check의 응답을 알 수 없거나 혹은 status가 UP이 아닙니다."
            echo "> Health check: ${response}"
        fi

        if [ "$retry_count" -eq 10 ]; then
            echo "> Health check 실패. "
            echo "> Nginx에 연결하지 않고 배포를 종료합니다."
            exit 1
        fi

        echo "> Health check 연결 실패. 재시도..."
        sleep 10
    done

    echo "> 스위칭"
    sleep 10

    echo "> ===============[deploy.sh END]==============="

    echo "> 성공 스위치"
    sudo sh /home/ubuntu/nginxPractice/script/switch.sh
}

deploy
