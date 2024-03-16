#!/bin/bash

# Nginx의 상태 엔드포인트로부터 접속자 수를 가져오는 함수
get_connections() {
    connections=$(curl -s http://localhost/nginx_status | grep 'Active connections' | awk '{print $3}')
    echo "$connections"
}

# Nginx의 상태를 확인하고, 접속자 수가 0이면 특정 작업을 실행하는 함수
check_and_execute() {
    connections=$(get_connections)
    if [ "$connections" -eq 0 ]; then
        echo "현재 접속자 수: $connections"
        echo "작업을 실행합니다."
        # 여기에 원하는 작업을 실행하는 코드를 추가하세요
    else
        echo "현재 접속자 수: $connections"
        echo "접속자가 있어서 작업을 실행하지 않습니다."
    fi
}

# 메인 실행 부분
check_and_execute
