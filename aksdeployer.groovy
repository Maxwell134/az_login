// Load the pipeline configuration file
def pipelineConfig = readJSON(file: 'pipeline.json')
def environments = pipelineConfig.environments

// Determine the credentials ID based on the environment
def getCredentialsID(env) {
    return environments[env]?.CREDENTIALID ?: 'azure-credentials'
}

// Define a method to deploy based on the credentials ID
def deploy(env) {
    def credentialsID = getCredentialsID(env)
    deployer.az_login(credentialsID)
}
