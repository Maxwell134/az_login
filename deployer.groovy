import groovy.json.JsonSlurperClassic

def docker_login(credentialsID) {
    try {
        withCredentials([string(credentialsId: credentialsID, variable: 'DOCKER_CREDENTIALS')]) {

            echo "DOCKER_CREDENTIALS: ${DOCKER_CREDENTIALS}"
            def jsonSlurper = new JsonSlurperClassic()
            def credentialsJsonObj = jsonSlurper.parseText(DOCKER_CREDENTIALS)

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

return this

