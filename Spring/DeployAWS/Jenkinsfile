pipeline {
    agent any 
    stages {
       stage('Prepare') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/KMGeon/DeployAWS.git'
            }
        }
		stage('Build Gradle') {
            steps{
                sh 'chmod +x gradlew'
                sh  './gradlew clean build'

                sh 'ls -al ./build'
            }
        }
        
		stage('Docker build image') {
            steps{
                sh 'docker build -t kimmugeon/docker-jenkins-github-test .'
            }
        }
        
		stage('Docker push image') {
            steps{
                sh 'docker login -u {id} -p {password}'
                sh 'docker push kimmugeon/docker-jenkins-github-test'
            }
        }
        
        stage('Run Container on SSH Dev Server'){
            steps{
                echo 'SSH'
                sshagent (credentials: ['dockerHubPwd']) {
                    sh 'echo " ============111==============="'
										sh "ssh -o StrictHostKeyChecking=no ubuntu@13.209.121.xxx 'whoami'"
										sh 'echo " ============222==============="'
                    sh "ssh -o StrictHostKeyChecking=no ubuntu@13.209.121.xxx 'docker ps -q --filter name=docker-jenkins-github-test | grep -q . && docker rm -f \$(docker ps -aq --filter name=docker-jenkins-github-test)'"
                    sh 'echo " ============333==============="'
                    sh "ssh -o StrictHostKeyChecking=no ubuntu@13.209.121.xxx 'docker rmi -f kimmugeon/docker-jenkins-github-test'"
                    sh 'echo " ============444==============="'
                    sh "ssh -o StrictHostKeyChecking=no ubuntu@13.209.121.xxx 'docker run -d --name docker-jenkins-github-test -p 8081:8080 kimmugeon/docker-jenkins-github-test'"
                }

            }

        }
    }
}
