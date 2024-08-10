import groovy.json.JsonSlurperClassic

def az_login(credentialsID){

    try {
        withCredentials([string(credentialsId: azure-credentials, variable: 'AZURE_CREDENTIALS')]) {

            def jsonSlurper = new JsonSlurperClassic()
            def credentialsJsonObj  = jsonSlurper.parseText(AZURE_CREDENTIALS)

            // Ensure env is correctly initialized and converted to uppercase for consistency
            def envUpperCase = env.toUpperCase()

            def selectedCredentials = (envUpperCase.contains('UAT') || envUpperCase.contains('TEST') || envUpperCase.equals('PROD')) ?
                                        credentialsJsonObj['prod_credentials'] :
                                        credentialsJsonObj['non_prod_credentials']

            def AZURE_USERNAME = selectedCredentials['username']
            def AZURE_PASSWORD = selectedCredentials['password']
//             def AZURE_TENANT = selectedCredentials['tenant']

            // Run the Azure login command
            sh "docker login -u ${AZURE_USERNAME} -p ${AZURE_PASSWORD}"

        }
    }
    catch (Exception e) {
        echo "Azure login failed: ${e.message}"
        error("Stopping pipeline due to failed Azure login.")
    }

}
