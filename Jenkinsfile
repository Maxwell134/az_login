pipeline {
    agent any

    // environment {
    //     ENVIRONMENT = 'dev'  // Set the default environment; can be overridden
    // }

    stages {
        stage('Initialize') {
            steps {
                script {
                    echo 'Loading pipeline configuration...'

                    // Read and parse the JSON file
                    def pipelineConfig = readJSON(file: 'pipeline.json')
                    
                    // // Load the environment-specific configuration dynamically
                    def deployEnvironments = pipelineConfig.aksDeploy.deployEnvironments
                    def environment = 'dev'
                    def deployGroup = deployEnvironments.'${environment}'
                    if (!deployGroup) {
                        error "Environment '${ENVIRONMENT}' not found in pipeline.json"
                    }
                    def credentialsID = deployGroup.CREDENTIALID

                    // Ensure the script exists and can be loaded
                    echo 'Loading deployer.groovy...'
                    // def aksDeployerFile = load 'deployer.groovy'
                    def aksDeployerFile = load 'aksdeployer.groovy'

                    // Call the deploy function
                    // aksDeployerFile.docker_login(credentialsID)
                    echo "'${environment}' found in pipeline.json"
                     aksDeployerFile.deployToAks(environment)
                }
            }
        }

        stage('Test Docker Access') {
            steps {
                script {
                    // Ensure Docker is accessible
                    sh 'docker --version'
                    sh 'sudo docker ps'
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
