pipeline {
    agent any

    tools {
        jdk 'JDK 17'
        git 'Git'
    }

    environment {
        ALLURE_RESULTS = 'build/allure-results'
        GRADLE_OPTS = '-Dorg.gradle.daemon=false'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Yamete11/final-pro-innowise.git'
            }
        }

        stage('Build & Run Tests') {
            steps {
                sh './gradlew clean test'
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
