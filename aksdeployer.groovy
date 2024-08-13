// aksdeployer.groovy
def call(env, pipelineConfig) {
    // Load the environment-specific configuration
    def deployEnvironments = pipelineConfig.aksDeploy.deployEnvironments
    def deploygroup = deployEnvironments[env]

    if (!deploygroup) {
        error "Environment '${env}' not found in the pipeline configuration."
    }

    def credentialsID = deploygroup.CREDENTIALID

    // Load the deployer.groovy script
    
    // Load the deployer.groovy script
    def dockerUtils = load 'deployer.groovy'

    // Check if dockerUtils was loaded successfully
    if (dockerUtils == null) {
        error "Failed to load deployer.groovy"
    }

    // Call the docker_login method and get the result
    def loginResult = dockerUtils.docker_login(credentialsID)

    // Check the result of Docker login
    if (loginResult.success) {
        echo loginResult.message
    } else {
        echo loginResult.message
        error("Stopping pipeline due to failed Docker login.")
    }

    // Add your deployment logic here
    echo "Deploying to ${env} environment with credentials ID ${credentialsID}"

    // Example of deployment script
    // sh "deploy_script.sh ${env}"
}

return this
