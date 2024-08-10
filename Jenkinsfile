pipeline {
    agent any

    stages {
        stage('Initialize') {
            steps {
                script {
                    
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
                    // aksdeployer.deploy('DEV')
                    // Add your DEV deployment steps here
                    aksdeployer('dev', parsedJson)
                }
            }
        }

        stage('Deploy to QA') {
            
            steps {
                script {
                    echo 'Deploying to QA environment...'
                    aksdeployer('qa', parsedJson)
                    // Add your QA deployment steps here
                }
            }
        }

        stage('Deploy to UAT') {
            
            steps {
                script {
                    echo 'Deploying to UAT environment...'
                    aksdeployer('uat', parsedJson)
                    // Add your UAT deployment steps here
                }
            }
        }

        stage('Deploy to TEST') {
            
            steps {
                script {
                    echo 'Deploying to TEST environment...'
                    aksdeployer('test', parsedJson)
                    // Add your TEST deployment steps here
                }
            }
        }

        stage('Deploy to DRA') {
            
            steps {
                script {
                    echo 'Deploying to DRA environment...'
                    aksdeployer('dra', parsedJson)
                    // Add your DRA deployment steps here
                }
            }
        }

        stage('Deploy to PROD') {
            
            steps {
                script {
                    echo 'Deploying to PROD environment...'
                    aksdeployer('prod', parsedJson)
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
