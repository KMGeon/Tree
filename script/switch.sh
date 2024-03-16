#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh


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