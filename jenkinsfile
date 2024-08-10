pipeline {
    agent any

    stages {
        stage('Initialize') {
            steps {
                script {
                    echo 'Loading deployer.groovy...'
                    deployer = load 'deployer.groovy'
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    echo 'Building the application...'
                    // Add your build commands here
                }
            }
        }

        stage('Deploy to DEV') {
            steps {
                script {
                    echo 'Deploying to DEV environment...'
                    deployer.az_login(credentialsID)
                    // Add your DEV deployment steps here
                }
            }
        }

        stage('Deploy to QA') {
            when {
                branch 'qa'
            }
            steps {
                script {
                    echo 'Deploying to QA environment...'
                    deployer.az_login(credentialsID)
                    // Add your QA deployment steps here
                }
            }
        }

        stage('Deploy to UAT') {
            when {
                branch 'uat'
            }
            steps {
                script {
                    echo 'Deploying to UAT environment...'
                    deployer.az_login(credentialsID)
                    // Add your UAT deployment steps here
                }
            }
        }

        stage('Deploy to TEST') {
            when {
                branch 'test'
            }
            steps {
                script {
                    echo 'Deploying to TEST environment...'
                    deployer.az_login(credentialsID)
                    // Add your TEST deployment steps here
                }
            }
        }

        stage('Deploy to DRA') {
            when {
                branch 'dra'
            }
            steps {
                script {
                    echo 'Deploying to DRA environment...'
                    deployer.az_login(credentialsID)
                    // Add your DRA deployment steps here
                }
            }
        }

        stage('Deploy to PROD') {
            when {
                branch 'main'
            }
            steps {
                script {
                    echo 'Deploying to PROD environment...'
                    deployer.az_login(credentialsID)
                    // Add your PROD deployment steps here
                }
            }
        }
    }

    post {
        always {
            script {
                echo 'Pipeline execution completed.'
            }
        }
    }
}
