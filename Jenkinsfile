pipeline {
    agent any

    tools {
        maven 'Maven 3.9.6'
        jdk 'JDK 17'
    }

    environment {
        DISPLAY = ':99'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git url: 'https://your-repo-url.git', branch: 'main'
            }
        }

        stage('Install Dependencies') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Archive Screenshots') {
            steps {
                archiveArtifacts artifacts: 'screenshots/*.png', allowEmptyArchive: true
            }
        }
    }

    post {
        success {
            echo 'Build & tests completed successfully.'
        }
        failure {
            echo 'Some tests failed. Please check the console output and screenshots.'
        }
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
