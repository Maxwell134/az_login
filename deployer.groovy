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
            sh """
                set +x  # Disable command echoing
                docker login -u 7002370412 -p 7002370412
                set -x  # Re-enable command echoing
            """

        }
    } catch (Exception e) {
        echo "Docker login failed: ${e.message}"
        error("Stopping pipeline due to failed Docker login.")
    }
}

return this
