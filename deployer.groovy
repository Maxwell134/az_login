// import groovy.json.JsonSlurper

def docker_login(credentialsID) {
    def result = [:]  // Create a map to store result status and message

    try {
        withCredentials([string(credentialsId: credentialsID, variable: 'DOCKER_CREDENTIALS')]) {
            // def jsonSlurper = new JsonSlurper()
            // def credentialsJsonObj = jsonSlurper.parseText(DOCKER_CREDENTIALS)
            def credentialsJson = readJSON(file: 'DOCKER_CREDENTIALS')
            echo " '${credentialsJson}'"
            def DOCKER_USERNAME = credentialsJsonObj['username']
            def DOCKER_PASSWORD = credentialsJsonObj['password']

            // Debugging: Print username and password (be careful with sensitive data)
            echo "DOCKER_USERNAME: ${DOCKER_USERNAME}"

            // Docker login command using --password-stdin
            sh '''
            echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
            '''

            result.success = true
            result.message = "Docker login successful."
        }
    } catch (Exception e) {
        result.success = false
        result.message = "Docker login failed: ${e.message}"
    }

    return result
}
return this
