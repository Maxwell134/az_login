// Load the pipeline configuration file
def pipelineConfig = readJSON(file: 'pipeline.json')
def environments = pipelineConfig.environments

// Determine the credentials ID based on the environment
def credentialsID = environments[env]?.CREDENTIALID ?: 'azure-credentials'

// Ensure that the deployer object is correctly initialized before calling az_login
deployer.az_login(credentialsID)
