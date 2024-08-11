
    // Load the environment-specific configuration
def deployEnvironments = pipelineConfig.aksDeploy.deployEnvironments
def deploygroup = deployEnvironments[environment]
if (!deploygroup) {
    error "Environment '${environment}' not found in pipeline.json"
}
def credentialsID = deploygroup.CREDENTIALID

    // Ensure Docker login
docker_login(credentialsID)

    // Add your deployment logic here
echo "Deploying to ${environment} environment with credentials ID ${credentialsID}"

    // Example of deployment script
    // sh "deploy_script.sh ${environment}"

