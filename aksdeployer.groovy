// Load the pipeline configuration file
def pipelineConfig = readJSON(file: 'pipeline.json')
def deployEnvironments = pipelineConfig.aksDeploy.deployEnvironments

// Retrieve the environment and credentials ID
// def env = env // Make sure to use the environment variable correctly
// def credentialsID = deployEnvironments[env]?.CREDENTIALID ?: 'azure-credentials'
def deploygroup = deployEnvironments.${environments}
def credentialsID =deploygroup.CREDENTIALID

// Ensure that the deployer object is correctly initialized before calling docker_login
deployer.docker_login(credentialsID)

// Add additional deployment logic as needed
echo "Deploying to ${environment} environment with credentials ID ${credentialsID}"
// Example: sh "deploy_script.sh ${parsedJson}"
