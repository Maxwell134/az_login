import groovy.json.JsonSlurper

def docker_login(credentialsID) {
    try {
        withCredentials([string(credentialsId: credentialsID, variable: 'DOCKER_CREDENTIALS')]) {

            echo "DOCKER_CREDENTIALS: ${DOCKER_CREDENTIALS}"
            def jsonSlurper = new JsonSlurper()
            def credentialsJsonObj = jsonSlurper.parseText(DOCKER_CREDENTIALS)

            def DOCKER_USERNAME = credentialsJsonObj['username']
            def DOCKER_PASSWORD = credentialsJsonObj['password']

            // Perform Docker login securely
            sh "docker login -u ${DOCKER_USERNAME} --password-stdin"

        }
    } catch (Exception e) {
        echo "Docker login failed: ${e.message}"
        error("Stopping pipeline due to failed Docker login.")
    }
}

return this
