import groovy.json.JsonSlurper

// pipeline {
//     agent any

//     stages {
//         stage('Deploy to AKS') {
//             steps {
//                 script {
//                     // Load the aksdeployer.groovy script
//                      def dockerUtils = load 'deployer.groovy'
//                     // def aksDeploy = load 'aksdeployer.groovy'

//                     // Check if aksDeploy was loaded successfully
//                     // if (aksDeploy == null) {
//                     //     error "Failed to load aksdeployer.groovy"
//                     // }

//                     // Load the pipeline configuration from the JSON file
//                     def pipelineConfig = readJSON(file: 'pipeline.json')
//                         dockerUtils.docker_login(credentialsID)

//                     // Deploy to the desired environment (e.g., 'dev')
//                     // aksDeploy('dev', pipelineConfig)
//                 }
//             }
//         }
//     }
// }

pipeline {
    agent any

    stages {
        stage('Deploy to AKS') {
            steps {
                script {
                    // Load the deployer.groovy script
                    def dockerUtils = load "${env.workspace}/deployer.groovy"

                    // Check if dockerUtils was loaded successfully
                    if (dockerUtils == null) {
                        error "Failed to load deployer.groovy"
                    }

                    // Load the pipeline configuration from the JSON file
                    def pipelineConfig = readFile("${env.workspace}/pipeline.json")
                    def parser = new JsonSlurper().parseText(pipelineConfig)
                    // Extract the credentialsID for the desired environment (e.g., 'dev')
                    def deployEnvironments = parser.aksDeploy.deployEnvironments
                    def deploygroup = deployEnvironments['dev']
                    def credentialsID = deploygroup.CREDENTIALID

                    if (!credentialsID) {
                        error "Credentials ID not found for environment 'dev'"
                    }

                    // Call the docker_login method with the credentialsID
                    def loginResult = dockerUtils.docker_login(credentialsID)

                    // Check the result of Docker login
                    if (loginResult.success) {
                        echo loginResult.message
                    } else {
                        echo loginResult.message
                        error("Stopping pipeline due to failed Docker login.")
                    }

                    // Add your deployment logic here
                    echo "Deploying to 'dev' environment with credentials ID ${credentialsID}"

                    // Example of deployment script
                    // sh "deploy_script.sh 'dev'"
                }
            }
        }
    }
}




















