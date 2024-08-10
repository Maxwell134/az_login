import groovy.json.JsonSlurperClassic

def docker_login(credentialsID, env) {
    try {
        withCredentials([string(credentialsId: credentialsID, variable: 'DOCKER_CREDENTIALS')]) {
            def jsonSlurper = new JsonSlurperClassic()
            def credentialsJsonObj = jsonSlurper.parseText(DOCKER_CREDENTIALS)

            // Determine Docker credentials based on the environment
            def envUpperCase = env.toUpperCase()

            // Conditional logic to select credentials
            def dockerCredID = (env.contains('prod') || env.contains('dra') || 
                                env.contains('uat') || env.contains('test') || env.contains('qa')
                                env.equalsIgnoreCase('PRD')) ? 
                                'docker-credentials-prod' : 'docker-credentials-non-prod'

            def selectedCredentials = credentialsJsonObj[dockerCredID]

            def DOCKER_USERNAME = selectedCredentials['username']
            def DOCKER_PASSWORD = selectedCredentials['password']

            // Perform Docker login
            sh "echo ${DOCKER_PASSWORD} | docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD}"
        }
    } catch (Exception e) {
        echo "Docker login failed: ${e.message}"
        error("Stopping pipeline due to failed Docker login.")
    }
}
