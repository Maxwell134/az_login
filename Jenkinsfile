import groovy.json.JsonSlurperClassic

pipeline {
    agent any

    environment {
        // Define a default environment variable if needed
        ENVIRONMENT = 'dev'
    }

    stages {
        stage('Initialize') {
            steps {
                script {
                    echo 'Loading deployer.groovy and aksdeployer.groovy...'
                    
                    // Ensure the Groovy scripts are properly loaded
                    
                    // Read and parse pipeline.json
                    def filePath = "${env.WORKSPACE}/pipeline.json"
                    if (!fileExists(filePath)) {
                        error "File not found: ${filePath}"
                    }
                    def inputFile = readFile(filePath)
                    def parsedJson = new JsonSlurperClassic().parseText(inputFile)
                    echo "Done Parsing"
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

        stage('Deploy') {
            steps {
                script {

                    def inputFile = readFile(filePath)
                    def pipelineConfig = new JsonSlurperClassic().parseText(inputFile)
                    def aksdeployer = load 'aksdeployer.groovy'

                    // Call the deploy function
                    aksdeployer.deploy(ENVIRONMENT, pipelineConfig)
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }
    }
}
