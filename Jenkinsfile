import groovy.json.JsonSlurperClassic
pipeline {
    agent any

    environment {
        ENVIRONMENT = 'dev'  // Set the default environment; can be overridden
    }

    stages {
        stage('Initialize') {
            steps {
                script {
                    echo 'Loading pipeline configuration...'

                    // def filePath = "${env.WORKSPACE}/pipeline.json"
                    // if (!fileExists(filePath)) {
                    //     error "File not found: ${filePath}"

                    // def inputFile = readFile(filePath)
                    // def pipelineConfig = new JsonSlurperClassic().parseText(inputFile)

                    echo 'Loading aksdeployer.groovy...'
                    def inputFile = readJSON(file: 'pipeline.json')
                    def pipelineConfig = readJSON text: inputFile         

                    // Load the environment-specific configuration
                    def deployEnvironments = pipelineConfig.aksDeploy.deployEnvironments
                    def deploygroup = deployEnvironments.dev
                    def credentialsID = deploygroup.'CREDENTIALID'
                    // Ensure the script exists and can be loaded
                    def aksdeployerFile =  load 'aksdeployer.groovy'       

                    // Call the deploy function
                    aksdeployerFile.docker_login(credentialsID)
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
                    echo "Deploying to ${ENVIRONMENT} environment..."
                    // Deployment steps are handled in the 'Initialize' stage
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
