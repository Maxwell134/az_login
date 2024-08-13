// import groovy.json.JsonSlurper

// def docker_login(credentialsID) {
//     def result = [:]  // Create a map to store result status and message

//     try {
//         withCredentials([string(credentialsId: credentialsID, variable: 'DOCKER_CREDENTIALS')]) {
//             def jsonSlurper = new JsonSlurper()
//             def credentialsJsonObj = jsonSlurper.parseText(DOCKER_CREDENTIALS)
//             def DOCKER_USERNAME = credentialsJsonObj['username']
//             def DOCKER_PASSWORD = credentialsJsonObj['password']

//             docker_login_cmd = """
//             set +x
//             docker login -u "$DOCKER_USERNAME" -p "$DOCKER_PASSWORD"
//             set -x
//             """
//             sh docker_login_cmd

//             result.success = true
//             result.message = "Docker login successful."
//         }
//     } catch (Exception e) {
//         result.success = false
//         result.message = "Docker login failed: ${e.message}"
//     }

//     return result
// }
// return this 

import groovy.json.JsonSlurper

def docker_login(credentialsID) {
    def result = [:]  // Create a map to store result status and message

    try {
        withCredentials([string(credentialsId: credentialsID, variable: 'DOCKER_CREDENTIALS')]) {
            def jsonSlurper = new JsonSlurper()
            def credentialsJsonObj = jsonSlurper.parseText(DOCKER_CREDENTIALS)
            def DOCKER_USERNAME = credentialsJsonObj['username']
            def DOCKER_PASSWORD = credentialsJsonObj['password']
            println "DOCKER_USERNAME: ${DOCKER_USERNAME}"
            println "DOCKER_PASSWORD: ${DOCKER_PASSWORD}"


            // Docker login command
            sh 'docker login -u "$DOCKER_USERNAME" -p "$DOCKER_PASSWORD"'

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


