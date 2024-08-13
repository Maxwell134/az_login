// import groovy.json.JsonSlurper

pipeline {
    agent any

    stages {
        stage('Deploy to AKS') {
            steps {
                script {
                    // Load the aksdeployer.groovy script
                     // def dockerUtils = load 'deployer.groovy'
                    def aksDeploy = load 'aksdeployer.groovy'

                    // Check if aksDeploy was loaded successfully
                    // if (aksDeploy == null) {
                    //     error "Failed to load aksdeployer.groovy"
                    // }
                    def env = 'dev'
                    // Load the pipeline configuration from the JSON file
                    def pipelineConfig = readJSON(file: 'pipeline.json')
                    def deployEnvironments = pipelineConfig.aksDeploy.deployEnvironments
                    def deploygroup = deployEnvironments[env] 
                
                    if (!deploygroup) {
                        error "Environment '${env}' not found in the pipeline configuration."
                    }

                        def credentialsID = deploygroup.CREDENTIALID
                        echo "Environment '${env}' found in the pipeline configuration."
                        // dockerUtils.docker_login(credentialsID)

                    // Deploy to the desired environment (e.g., 'dev')
                    aksDeploy.call(env, pipelineConfig)
                }
            }
        }
    }
}
// import groovy.json.JsonSlurper
// pipeline {
//     agent any

    // environment {
    //     // Define any necessary environment variables here
    // }

//     stages {
//         stage('Deploy') {
//             steps {
//                 script {

//                     parserJson = ""
//                     // Define your pipeline configuration
//                     // def pipelineConfig = readFile('pipeline.json')  // Adjust this as needed
//                     // def parserJson = new JsonSlurper().parseText(pipelineConfig)
//                     // def env = 'dev' 
//                     def pipelineConfig = readJSON(file: 'pipeline.json')
//                     def deployEnvironments = pipelineConfig.aksDeploy.deployEnvironments
//                     def deploygroup = deployEnvironments.'dev'
//                     def credentialsID = deploygroup.CREDENTIALID
                     

//                     // Call the Groovy method with environment and pipeline configuration
//                      // Or any environment you need
//                     def aksDeploy = load 'aksdeployer.groovy'
//                     aksDeploy.aks('dev', pipelineConfig)
//                 }
//             }
//         }
//     }
// }
