#!/bin/bash

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


# 함수: Nginx 프록시 포트 변경 및 Nginx 리로드
switch_nginx_proxy_port() {
    IDLE_PORT=$(find_idle_port)
    echo "> 전환할 Port: $IDLE_PORT"
    echo "> Port 전환"
    echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc

    PROXY_PORT=$(curl -s http://localhost/profile)
    echo "> Nginx Current Proxy Port: $PROXY_PORT"

    echo "> Nginx Reload"
    sudo service nginx reload
}

main() {
    IDLE_PORT=$(find_idle_port)
    IDLE_PROFILE=$(find_idle_profile)
    switch_nginx_proxy_port "$IDLE_PORT"
}

main