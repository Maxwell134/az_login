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
//                     if (aksDeploy == null) {
//                         error "Failed to load aksdeployer.groovy"
//                     }

//                     // Load the pipeline configuration from the JSON file
//                     def pipelineConfig = readJSON(file: 'pipeline.json')
//                     def deployEnvironments = pipelineConfig.aksDeploy.deployEnvironments
//                     def deploygroup = deployEnvironments.'dev'
                
//                     // if (!deploygroup) {
//                     //     error "Environment '${env}' not found in the pipeline configuration."
//                     // }

//                     //     def credentialsID = deploygroup.CREDENTIALID
//                     //     dockerUtils.docker_login(credentialsID)

//                     // Deploy to the desired environment (e.g., 'dev')
//                     aksDeploy('dev', pipelineConfig)
//                 }
//             }
//         }
//     }
// }

pipeline {
    agent any

    // environment {
    //     // Define any necessary environment variables here
    // }

    stages {
        stage('Deploy') {
            steps {
                script {
                    // Define your pipeline configuration
                    def pipelineConfig = readJSON(file: 'pipelineConfig.json')  // Adjust this as needed

                    // Call the Groovy method with environment and pipeline configuration
                    def env = 'dev'  // Or any environment you need
                    call(env, pipelineConfig)
                }
            }
        }
    }
}
