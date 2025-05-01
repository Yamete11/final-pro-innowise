pipeline {
    agent any

    tools {
        jdk 'JDK 17'
        git 'git'
    }

    environment {
        ALLURE_RESULTS = 'build/allure-results'
        GRADLE_OPTS = '-Dorg.gradle.daemon=false'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Yamete11/final-pro-innowise.git'
            }
        }

        stage('Build & Run Tests') {
            steps {
                bat './gradlew clean test'
            }
        }

        stage('Publish Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: "${env.ALLURE_RESULTS}"]]
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'build/reports/**/*.*', fingerprint: true
            junit 'build/test-results/test/*.xml'
        }
    }
}