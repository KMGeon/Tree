#!/bin/bash

# 스크립트들이 있는 패키지 경로
PACKAGE_PATH="/home/ubuntu/nginxPractice/script2"

# 각 스크립트 실행 함수
run_script() {
    local script_name="$1"
    echo "Running $script_name script..."
    if timeout 60s "$PACKAGE_PATH/$script_name"; then
        echo "$script_name script completed successfully."
        return 0
    else
        echo "$script_name script timed out or failed."
        failed_script="$script_name"
        return 1
    fi
}

# 각 단계 실행
run_script "stop.sh"
stop_result=$?

run_script "start.sh"
start_result=$?

run_script "health.sh"
health_result=$?

echo "Stop script result: $stop_result"
echo "Start script result: $start_result"
echo "Health check script result: $health_result"

# 실패한 스크립트 출력
if [ $stop_result -ne 0 ] || [ $start_result -ne 0 ] || [ $health_result -ne 0 ]; then
    echo "Failure occurred in $failed_script"
fi

# 성공 또는 실패 메시지 출력
if [ $stop_result -eq 0 ] && [ $start_result -eq 0 ] && [ $health_result -eq 0 ]; then
    echo "Deployment completed successfully."
else
    echo "Deployment failed."
fi

# 각 스크립트 실행 결과 반환
exit $(($stop_result + $start_result + $health_result))
