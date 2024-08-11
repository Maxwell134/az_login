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
                    // Load the Groovy scripts
                    deployer = load 'deployer.groovy'
                    aksdeployer = load 'aksdeployer.groovy'

                    // Read and parse pipeline.json
                    def filePath = "${env.WORKSPACE}/pipeline.json"
                    if (!fileExists(filePath)) {
                        error "File not found: ${filePath}"
                    }
                    inputFile = readFile(filePath)
                    parsedJson = new JsonSlurperClassic().parseText(inputFile)
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
                    def deployEnvironments = parsedJson.aksDeploy.deployEnvironments
                    def deploygroup = deployEnvironments[ENVIRONMENT]
                    if (!deploygroup) {
                        error "Environment '${ENVIRONMENT}' not found in pipeline.json"
                    }
                    def credentialsID = deploygroup.CREDENTIALID

                    echo "Deploying to ${ENVIRONMENT} environment with credentials ID ${credentialsID}"
                    
                    // Perform Docker login and deployment
                    deployer.docker_login(credentialsID)
                    aksdeployer.deploy(ENVIRONMENT, parsedJson)
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
