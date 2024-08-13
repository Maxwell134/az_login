// // import groovy.json.JsonSlurper

// pipeline {
//     agent any

//     stages {
//         stage('Deploy to AKS') {
//             steps {
//                 script {
//                     // Load the aksdeployer.groovy script
//                      // def dockerUtils = load 'deployer.groovy'
//                     def aksDeploy = load 'aksdeployer.groovy'

//                     // Check if aksDeploy was loaded successfully
//                     // if (aksDeploy == null) {
//                     //     error "Failed to load aksdeployer.groovy"
//                     // }
//                     def env = 'dev'
//                     // Load the pipeline configuration from the JSON file
//                     def pipelineConfig = readJSON(file: 'pipeline.json')
//                     def deployEnvironments = pipelineConfig.aksDeploy.deployEnvironments
//                     def deploygroup = deployEnvironments[env] 
                
//                     if (!deploygroup) {
//                         error "Environment '${env}' not found in the pipeline configuration."
//                     }

//                         def credentialsID = deploygroup.CREDENTIALID
//                         echo "Environment '${env}' found in the pipeline configuration."
//                         // dockerUtils.docker_login(credentialsID)

//                     // Deploy to the desired environment (e.g., 'dev')
//                     aksDeploy.call(env, pipelineConfig)
//                 }
//             }
//         }
//     }
// }


import groovy.json.JsonSlurperClassic // Using JsonSlurperClassic for serializable objects

pipeline {
    agent any

    stages {
        stage('Deploy to AKS') {
            steps {
                script {
                    // Load the aksdeployer.groovy script
                    def aksDeploy = load 'aksdeployer.groovy'

                    // Ensure the aksDeploy script is loaded successfully
                    if (aksDeploy == null) {
                        error "Failed to load aksdeployer.groovy"
                    }

                    def env = 'dev'

                    // Load the pipeline configuration from the JSON file
                    def inputFile = readFile("${env.WORKSPACE}/pipeline.json")
                    def parsedJson = new JsonSlurperClassic().parseText(inputFile)
                    echo "JSON object parsed successfully."

                    // Deploy to the desired environment (e.g., 'dev')
                    aksDeploy.call(env, parsedJson)
                }
            }
        }
    }
}




