import groovy.json.JsonSlurperClassic

def az_login(credentialsID) {
    try {
        // Access the Jenkins credentials using the provided credentialsID
        withCredentials([string(credentialsId: credentialsID, variable: 'AZURE_CREDENTIALS')]) {
            def jsonSlurper = new JsonSlurperClassic()
            def credentialsJsonObj = jsonSlurper.parseText(AZURE_CREDENTIALS)

            // Ensure that `env.BRANCH_NAME` is correctly initialized for consistency
            def envUpperCase = env.BRANCH_NAME?.toUpperCase() ?: 'DEV' // Default to 'DEV' if BRANCH_NAME is null

            def selectedCredentials = (envUpperCase.contains('UAT') || envUpperCase.contains('TEST') || envUpperCase.equals('PROD')) ?
                                        credentialsJsonObj['prod_credentials'] :
                                        credentialsJsonObj['non_prod_credentials']

            def AZURE_USERNAME = selectedCredentials['username']
            def AZURE_PASSWORD = selectedCredentials['password']
            // def AZURE_TENANT = selectedCredentials['tenant'] // Uncomment if tenant is needed

            // Run the Azure login command
            sh "docker login -u ${AZURE_USERNAME} -p ${AZURE_PASSWORD}"

        }
    } catch (Exception e) {
        echo "Azure login failed: ${e.message}"
        error("Stopping pipeline due to failed Azure login.")
    }
}
