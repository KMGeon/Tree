#!/usr/bin/env bash

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
    IDLE_PROFILE=$(find_idle_profile)

    if [ ${IDLE_PROFILE} == "set1" ]
    then
      echo "8081"
    else
      echo "8082"
    fi
}