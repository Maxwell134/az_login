// import groovy.json.JsonSlurper

// def pipelineConfig = readJSON(file: 'pipeline.json')
// def jsonContent = new JsonSlurper().parseText(pipelineConfig)

//     // Load the environment-specific configuration
// def deployEnvironments = jsonContent.aksDeploy.deployEnvironments
// def deploygroup = deployEnvironments.dev
// if (!deploygroup) {
//     error "Environment '${environment}' not found in pipeline.json"
// }
// def credentialsID = deploygroup.'CREDENTIALID'

//     // Ensure Docker login
// docker_login(credentialsID)

//     // Add your deployment logic here
// echo "Deploying to ${environment} environment with credentials ID ${credentialsID}"

//     // Example of deployment script
//     // sh "deploy_script.sh ${environment}"

import groovy.json.JsonSlurper

def deployToAks(environment) {
    // Load the pipeline configuration
    def pipelineConfig = readJSON(file: 'pipeline.json')

    // Load the environment-specific configuration
    def deployEnvironments = pipelineConfig.aksDeploy.deployEnvironments
    def deploygroup = deployEnvironments[environment]
    if (!deploygroup) {
        error "Environment '${environment}' not found in pipeline.json"
    }

    def credentialsID = deploygroup.'CREDENTIALID'

    // Ensure Docker login
    docker_login(credentialsID)

    // Add your deployment logic here
    echo "Deploying to ${environment} environment with credentials ID ${credentialsID}"

    // Example of deployment script
    // sh "deploy_script.sh ${environment}"
}
















