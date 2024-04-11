#!/bin/bash

# 스크립트들이 있는 패키지 경로
PACKAGE_PATH="/home/ubuntu/nginxPractice/script"

chmod_script(){
  sudo chmod 777 *.sh
  echo "> 스크립트 권한 변경"
}

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
chmod_script

run_script "deploy.sh"
deploy_result=$?

run_script "switch.sh"
switch_result=$?


echo "deploy script result: $deploy_result"
echo "switch script result: $switch_result"

if [ $deploy_result -ne 0 ] || [ $switch_result -ne 0 ] ; then
    echo "Failure occurred in $failed_script"
fi

if [ $stop_result -eq 0 ] && [ $start_result -eq 0 ] && [ $health_result -eq 0 ]; then
    echo "Deployment completed successfully."
else
    echo "Deployment failed."
fi

# 각 스크립트 실행 결과 반환
exit $(($stop_result + $start_result + $health_result))
