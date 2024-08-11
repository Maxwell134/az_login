import groovy.json.JsonSlurperClassic

def docker_login(credentialsID) {
    try {
        withCredentials([string(credentialsId: credentialsID, variable: 'DOCKER_CREDENTIALS')]) {
            def jsonSlurper = new JsonSlurperClassic()
            def credentialsJsonObj = jsonSlurper.parseText(DOCKER_CREDENTIALS)

            // Determine Docker credentials based on the environment
            // def envUpperCase = env.toUpperCase()

            // // Conditional logic to select credentials
            // def dockerCredID = (env.BRANCH_NAME?.toLowerCase().contains('prod') ||
            //         env.BRANCH_NAME?.toLowerCase().contains('dra') ||
            //         env.BRANCH_NAME?.toLowerCase().contains('uat') ||
            //         env.BRANCH_NAME?.toLowerCase().contains('test') ||
            //         env.BRANCH_NAME?.toLowerCase().contains('qa') ||
            //         env.BRANCH_NAME?.toLowerCase().equals('prd')) ?
            //         'prod_credentials' : 'non_prod_credentials'


            // def selectedCredentials = credentialsJsonObj[dockerCredID]

            def DOCKER_USERNAME = credentialsJsonObj['username']
            def DOCKER_PASSWORD = credentialsJsonObj['password']

            // Perform Docker login
            sh "echo ${DOCKER_PASSWORD} | docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD}"
        }
    } catch (Exception e) {
        echo "Docker login failed: ${e.message}"
        error("Stopping pipeline due to failed Docker login.")
    }
}
