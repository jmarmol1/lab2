pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = 'dockerhub_id'
        DOCKER_IMAGE_NAME = 'juanca1013/mavenapp:latest'
    }

    stages {
        stage('Cloning our git') {
            steps {
                 git branch: 'main', url: 'https://github.com/jmarmol1/lab2.git'
            }
        }

        stage('Build image') {
            steps {
               script {
                    dockerImage = docker.build(env.DOCKER_IMAGE_NAME)
               }
            }
        }

        stage('Code Coverage (JaCoCo)') {
            steps {
                sh 'mvn jacoco:prepare-agent test jacoco:report'
            }
        }


        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(credentialsId: env.DOCKER_HUB_CREDENTIALS, passwordVariable: 'DOCKER_HUB_PASSWORD', usernameVariable: 'DOCKER_HUB_USERNAME')]) {
                    sh "docker login -u ${env.DOCKER_HUB_USERNAME} -p ${env.DOCKER_HUB_PASSWORD}"
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    dockerImage.push()
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
