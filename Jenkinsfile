pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = 'dockerhub-pwd'
        DOCKER_IMAGE_NAME = 'juanca1013/mavenapp'
        Path = '/usr/bin:/bin:/usr/sbin:/sbin:/usr/local/bin/'
    }

    stages {
        stage('Cloning our git') {
            steps {
                 git branch: 'main', url: 'https://github.com/jmarmol1/lab2.git'
            }
        }

        stage('Build maven project'){
            steps{
                sh './mvnw clean package'
            }
        }

        stage('Build image') {
            steps {
               sh 'docker build -t $DOCKER_IMAGE_NAME:$BUILD_NUMBER .'
            }
        }

        stage('Code Coverage (JaCoCo)') {
            steps {
                jacoco(
                      execPattern: 'target/*.exec',
                      classPattern: 'target/classes',
                      sourcePattern: 'src/main/java',
                      exclusionPattern: 'src/test*'
                )
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([string(credentialsId: env.DOCKER_HUB_CREDENTIALS, variable: 'dockerhubpwd')]) {
                    sh "docker login"
                }
            }
        }

        stage('Docker Push') {
            steps {
                sh 'docker push $DOCKER_IMAGE_NAME:$BUILD_NUMBER'
            }
        }
    }
}
