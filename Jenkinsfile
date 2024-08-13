pipeline {
    agent any

    stages {
        stage('Deploy to AKS') {
            steps {
                script {
                    // Load the aksdeployer.groovy script
                    def aksDeploy = load 'aksdeployer.groovy'

                    // Check if aksDeploy was loaded successfully
                    if (aksDeploy == null) {
                        error "Failed to load aksdeployer.groovy"
                    }

                    // Load the pipeline configuration from the JSON file
                    def pipelineConfig = readJSON(file: 'pipeline.json')

                    // Deploy to the desired environment (e.g., 'dev')
                    aksDeploy('dev', pipelineConfig)
                }
            }
        }
    }
}
