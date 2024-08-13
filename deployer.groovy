import groovy.json.JsonSlurper

def docker_login() {
    try {
        withCredentials([string(credentialsId: non_prod_credentials, variable: 'DOCKER_CREDENTIALS')]) {

            def jsonSlurper = new JsonSlurper()
            def credentialsJsonObj = jsonSlurper.parseText(DOCKER_CREDENTIALS)

            def DOCKER_USERNAME = credentialsJsonObj['username']
            def DOCKER_PASSWORD = credentialsJsonObj['password']

            // Perform Docker login securely
          
            sh 'docker login -u $DOCKER_USERNAME --password-stdin'
        

            // Confirm login success
            sh 'docker info'

        }
    } catch (Exception e) {
        echo "Docker login failed: ${e.message}"
        error("Stopping pipeline due to failed Docker login.")
    }
}

return this
