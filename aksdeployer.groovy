// Load the pipeline configuration file
def pipelineConfig = readJSON(file: 'pipeline.json')
def environments = pipelineConfig.aksDeploy.deployEnvironments

// Retrieve the environment and credentials ID
def env = env // Make sure to use the environment variable correctly
def credentialsID = environments[env]?.CREDENTIALID ?: 'docker-credentials'
// def credentialsID = environments.${environments}

// Ensure that the deployer object is correctly initialized before calling docker_login
deployer.docker_login(credentialsID, env)

// Add additional deployment logic as needed
echo "Deploying to ${env} environment with credentials ID ${credentialsID}"
// Example: sh "deploy_script.sh ${parsedJson}"
