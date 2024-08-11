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

                    // Read and parse the JSON file
                    def pipelineConfig = readJSON(file: 'pipeline.json')

                    // Load the environment-specific configuration dynamically
                    def deployEnvironments = pipelineConfig.aksDeploy.deployEnvironments
                    def deploygroup = deployEnvironments[ENVIRONMENT]
                    if (!deploygroup) {
                        error "Environment '${ENVIRONMENT}' not found in pipeline.json"
                    }
                    def credentialsID = deploygroup.'CREDENTIALID'

                    // Ensure the script exists and can be loaded
                    echo 'Loading aksdeployer.groovy...'
                    def aksdeployerFile = load 'aksdeployer.groovy'

                    // Call the deploy function
                    aksdeployerFile.deployToAks(ENVIRONMENT)
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
