// import groovy.json.JsonSlurperClassic

// def docker_login(credentialsID) {
//     def result = [:]  // Create a map to store result status and message

//     try {
//         withCredentials([string(credentialsId: credentialsID, variable: 'DOCKER_CREDENTIALS')]) {
//             def jsonSlurper = new JsonSlurperClassic()
//             def credentialsJsonObj = jsonSlurper.parseText(DOCKER_CREDENTIALS)
//             def DOCKER_USERNAME = credentialsJsonObj['username']
//             def DOCKER_PASSWORD = credentialsJsonObj['password']

//             // Debugging: Print username and password (be careful with sensitive data)
//             echo "DOCKER_USERNAME: ${DOCKER_USERNAME}"
//             // echo "Raw credentials: '${DOCKER_CREDENTIALS}'"

//             // Docker login command using --password-stdin
            
//            sh 'docker login -u "$DOCKER_USERNAME" -p "$DOCKER_PASSWORD"'      

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

            // Debugging: Print username and password (be careful with sensitive data)
            echo "DOCKER_USERNAME: ${DOCKER_USERNAME}"

            // Docker login command using --password-stdin
            def dockerLoginCommand = "echo ${DOCKER_PASSWORD} | docker login -u ${DOCKER_USERNAME} --password-stdin"
            echo "Running command: ${dockerLoginCommand}"
            
            sh script: dockerLoginCommand, returnStdout: true

            result.success = true
            result.message = "Docker login successful."
        }
    } catch (Exception e) {
        result.success = false
        result.message = "Docker login failed: ${e.message}"
        echo result.message  // Log the failure message
    }

    return result
}

return this
