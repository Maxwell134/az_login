import groovy.json.JsonSlurperClassic
pipeline {
    agent any

    stages {
        stage('Initialize') {
            steps {
                script {
                    echo 'Loading deployer.groovy...'
                    // deployer = load 'deployer.groovy'
                    // echo 'Loading aksdeployer.groovy...'
                    // aksdeployer = load 'aksdeployer.groovy'

                    // Read and parse pipeline.json
                    def filePath = "${env.WORKSPACE}/pipeline.json"
                    if (!fileExists(filePath)) {
                        error "File not found: ${filePath}"
                    }
                    inputFile = readFile(filePath)
                    parsedJson = new JsonSlurperClassic().parseText(inputFile)
                    println "Done Parsing"
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    echo 'Building the application...'
                    // Add your build commands here
                }
            }
        }

        stage('Deploy to DEV') {
    steps {
        script {
            echo 'Deploying to DEV environment...'
            
            // Load the pipeline configuration file
            def pipelineConfig = readJSON(file: 'pipeline.json')
            def environments = pipelineConfig.aksDeploy.deployEnvironments

            // Retrieve the environment and credentials ID
            def env = 'dev' // Use a specific environment name if it's not coming from the environment variable
            def credentialsID = environments[env]?.CREDENTIALID ?: 'azure-credentials'

            // Initialize the deployer object if necessary
            // Assuming aksdeployer is a method from a shared library or plugin
            aksdeployer(env, credentialsID) // Ensure this method exists and is correctly implemented

            // Ensure that the deployer object is correctly initialized before calling docker_login
            // If docker_login is part of aksdeployer or a different deployer object, initialize accordingly
            // Example of initialization:
            // deployer.docker_login(credentialsID, env)

            // Add additional deployment logic as needed
            echo "Deploying to ${env} environment with credentials ID ${credentialsID}"

            // Example deployment step
            // sh "deploy_script.sh ${parsedJson}"
        }
    }
}


        // stage('Deploy to DEV') {
            
        //     steps {
        //         script {
        //             echo 'Deploying to DEV environment...'
        //             // aksdeployer.deploy('DEV')
        //             // Add your DEV deployment steps here
        //             aksdeployer('dev', parsedJson)
        //         }
        //     }
        // }

    //     stage('Deploy to QA') {
            
    //         steps {
    //             script {
    //                 echo 'Deploying to QA environment...'
    //                 aksdeployer('qa', parsedJson)
    //                 // Add your QA deployment steps here
    //             }
    //         }
    //     }

    //     stage('Deploy to UAT') {
            
    //         steps {
    //             script {
    //                 echo 'Deploying to UAT environment...'
    //                 aksdeployer('uat', parsedJson)
    //                 // Add your UAT deployment steps here
    //             }
    //         }
    //     }

    //     stage('Deploy to TEST') {
            
    //         steps {
    //             script {
    //                 echo 'Deploying to TEST environment...'
    //                 aksdeployer('test', parsedJson)
    //                 // Add your TEST deployment steps here
    //             }
    //         }
    //     }

    //     stage('Deploy to DRA') {
            
    //         steps {
    //             script {
    //                 echo 'Deploying to DRA environment...'
    //                 aksdeployer('dra', parsedJson)
    //                 // Add your DRA deployment steps here
    //             }
    //         }
    //     }

    //     stage('Deploy to PROD') {
            
    //         steps {
    //             script {
    //                 echo 'Deploying to PROD environment...'
    //                 aksdeployer('prod', parsedJson)
    //                 // Add your PROD deployment steps here
    //             }
    //         }
    //     }
    // }

    // post {
    //     always {
    //         script {
    //             echo 'Pipeline execution completed.'
    //         }
    //     }
    }
}
