import groovy.json.JsonSlurper

def docker_login() {
    try {
        withCredentials([string(credentialsId: 'non_prod_credentials', variable: 'DOCKER_CREDENTIALS')]) {

            def jsonSlurper = new JsonSlurper()
            def credentialsJsonObj = jsonSlurper.parseText(DOCKER_CREDENTIALS)

            def DOCKER_USERNAME = credentialsJsonObj['username']
            def DOCKER_PASSWORD = credentialsJsonObj['password']

            // Perform Docker login securely
            azCmd = """
            set +x
            docker login -u "$DOCKER_USERNAME" -p "$DOCKER_PASSWORD"
        
            set -x
            // Confirm login success
            sh 'docker info'
            """
            sh azCmd
        }
    } catch (Exception e) {
        echo "Docker login failed: ${e.message}"
        error("Stopping pipeline due to failed Docker login.")
    }
}

return this
